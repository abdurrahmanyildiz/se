package com.yildiz.searchcore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.yildiz.App;
import com.yildiz.common.DomainOperations;
import com.yildiz.common.Utils;
import com.yildiz.models.se.SearchEngineResultForES;
import com.yildiz.models.se.SearchKeyword;
import com.yildiz.models.se.SearchSiteParameter;

/**
 * 
 * @author Abdurrahman Yıldız
 *
 */
public class SeleniumChromeDriver {
	static Logger logger = Logger.getLogger(SeleniumChromeDriver.class.getName());
	private static WebDriver webDriver;

	public SeleniumChromeDriver() {
		if (App.properties.getProperty("webdriver.browser") != null
				&& App.properties.getProperty("webdriver.browser").equals("firefox")) {
			FirefoxOptions fOptions = new FirefoxOptions().setHeadless(false).addArguments(Utils.UserAgent)
					.addArguments("--disable-ble-advertising-in-apps").addArguments("--disable-infobars")
					.setPageLoadStrategy(PageLoadStrategy.NONE);
			webDriver = new FirefoxDriver(fOptions);
		} else {
			ChromeOptions cOptions = new ChromeOptions().setHeadless(false).addArguments(Utils.UserAgent)
					.addArguments("--disable-ble-advertising-in-apps").addArguments("--disable-infobars")
					.addArguments("start-maximized") // open Browser in maximized mode
					.addArguments("disable-infobars") // disabling infobars
					.addArguments("--disable-extensions") // disabling extensions
					.addArguments("--disable-gpu") // applicable to windows os only
					.addArguments("--disable-dev-shm-usage") // overcome limited resource problems
					.addArguments("--no-sandbox") // Bypass OS security model
					.setPageLoadStrategy(PageLoadStrategy.NONE);
			webDriver = new ChromeDriver(cOptions);
		}
	}

	public void CrawlContents(SearchKeyword kw) {

		webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(App.properties.getProperty("webdriver.timeout")),
				TimeUnit.MILLISECONDS);

		webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");

		if (isSearchEngineActive("google")) {
			Search(GoogleSearch.CreateParameters(kw, GetLiveLoggingValue("search.google.livelogging")), webDriver);
		}
		if (isSearchEngineActive("bing")) {
			Search(BingSearch.CreateParameters(kw, GetLiveLoggingValue("search.bing.livelogging")), webDriver);
		}
		if (isSearchEngineActive("yandex")) {
			Search(YandexSearch.CreateParameters(kw, GetLiveLoggingValue("search.yandex.livelogging")), webDriver);
		}
		if (isSearchEngineActive("yahoo")) {
			Search(YahooSearch.CreateParameters(kw, GetLiveLoggingValue("search.yahoo.livelogging")), webDriver);
		}

		webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		// webDriver.close();
		System.gc();
	}

	private static void Search(SearchSiteParameter parameters, WebDriver chrome) {
		Set<String> results = new HashSet<String>();

		try {
			LogBeginning(parameters);
			chrome.get(parameters.getQuery());
			List<WebElement> webElements = chrome.findElements(parameters.getResultElementsCssSelector());
			SearchEngineResultForES res = null; // model for elastic search
			Integer resultCount = 0;
			String title, href = null, text = null, domain = null, fqdn;
			Integer numberOfClickedNextButton = 0;
			while (webElements != null) {
				for (WebElement element : webElements) {
					try {
						href = GetHrefFromWebElement(element, parameters.getLinkCssSelector());
						domain = DomainOperations.getBaseDomainName(href);
						fqdn = DomainOperations.getDomainNameWithSubdomain(href);

						if (!fqdn.equals(Utils.failMsg) && !results.contains(fqdn)) {

							title = GetTitleFromWebElement(element, parameters.getTitleCssSelector());
							text = GetTextFromWebElement(element, parameters.getTextCssSelector());

							res = new SearchEngineResultForES(); 
							res.setTitle(title);
							res.setDescription(text);
							res.setDomain(domain);
							res.setSe_category(parameters.getSeKeyword().getCategory());
							res.setFqdn(fqdn);
							res.setSearch_site(parameters.getSearchSite());
							res.setSe(parameters.getSearchSite());
							
							//TODO: save res object to db,es or anywhere you like

							results.add(fqdn);

							if (parameters.getIsLiveLoggingActive()) {
								logger.log(Level.INFO,
										new StringBuilder(fqdn).append(" [Object added to db]").append(" [Title]:")
												.append(title).append(" [Link]:").append(href).append(" [Link Text]:")
												.append(text).toString());
							}
						}

					} catch (Exception e) {
						// Outlier node (Images etc.) or reputation server error Just Go on...
						logger.log(Level.WARNING,
								new StringBuilder(
										"An Exception occured while saving result or Outlier node in results #domain: ")
												.append(domain).append(" #url: ").append(href).append(" #Exception: ")
												.append(e.toString()).toString());
					}

					resultCount++;
				}

				try {
					// WebElement nextButton = new WebDriverWait(chrome,
					// 100).until(ExpectedConditions.elementToBeClickable(parameters.getNextButtonCssSelector()));
					WebElement nextButton = chrome.findElement(parameters.getNextButtonCssSelector());
					if (nextButton != null) {
						if (isSleepActive()) {
							Integer time = ThreadLocalRandom.current().nextInt(getMinSleepValue(),
									getMaxSleepValue() + 1);
							Thread.sleep(time);
						}
						nextButton.click();
						numberOfClickedNextButton++;
						if (parameters.getIsLiveLoggingActive()) {
							logger.log(Level.INFO, "Next Button Clicked");
						}
					} else {
						break;
					}
				} catch (Exception e) {
					break;
				}

				webElements = chrome.findElements(parameters.getResultElementsCssSelector());

				if (resultCount > parameters.getMaxResult()) {
					break;
				}
			}

			LogCompleting(parameters, resultCount, numberOfClickedNextButton);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
		} finally {
			// chrome.close();
		}
	}

	private static Boolean isSleepActive() {
		try {
			return Boolean.parseBoolean(App.properties.getProperty("webdriver.nextbutton.issleep"));
		} catch (Exception e) {
			return false;
		}
	}

	private static Boolean isSearchEngineActive(String searchEngine) {
		try {
			return Boolean.parseBoolean(App.properties.getProperty("search." + searchEngine + ".isActive"));
		} catch (Exception e) {
			return false;
		}
	}

	private static String GetTitleFromWebElement(WebElement element, ByCssSelector titleCssSelector) {
		try {
			WebElement titleElement = element.findElement(titleCssSelector);
			if (titleElement != null && titleElement.getAttribute("innerText") != null) {
				return titleElement.getAttribute("innerText");
			}

			return Utils.failMsg;

		} catch (Exception e) {
			return Utils.failMsg;
		}

	}

	private static String GetHrefFromWebElement(WebElement element, ByCssSelector linkCssSelector) {
		try {
			WebElement linkElement = element.findElement(linkCssSelector);
			if (linkElement != null && linkElement.getAttribute("href") != null) {
				return linkElement.getAttribute("href");
			}

			return Utils.failMsg;

		} catch (Exception e) {
			return Utils.failMsg;
		}
	}

	private static String GetTextFromWebElement(WebElement element, ByCssSelector textCssSelector) {
		try {
			WebElement textElement = element.findElement(textCssSelector);
			if (textElement != null && textElement.getAttribute("innerText") != null) {
				return textElement.getAttribute("innerText");
			}

			return Utils.failMsg;

		} catch (Exception e) {
			return Utils.failMsg;
		}
	}

	private static void LogBeginning(SearchSiteParameter parameters) {
		logger.log(Level.INFO, new StringBuilder("Crawling Started on= [").append(parameters.getSearchSite())
				.append("] for= [ ").append(parameters.getSeKeyword().getKeyword()).append(" ]").toString());
	}

	private static void LogCompleting(SearchSiteParameter parameters, Integer resultCount, Integer numberOfNextButton) {
		StringBuilder sb = new StringBuilder("Crawling Completed on= [").append(parameters.getSearchSite())
				.append("] for= [ ").append(parameters.getSeKeyword().getKeyword()).append("] ")
				.append(" Number of checked results = [").append(resultCount).append("] Next Button clicked [")
				.append(numberOfNextButton).append("] times.");

		logger.log(Level.INFO, sb.toString());
	}

	private static Integer getMinSleepValue() {
		try {
			return Integer.parseInt(App.properties.getProperty("webdriver.nextbutton.sleepmin"));
		} catch (Exception e) {
			return 1;
		}
	}

	private static Integer getMaxSleepValue() {
		try {
			return Integer.parseInt(App.properties.getProperty("webdriver.nextbutton.sleepmax"));
		} catch (NumberFormatException e) {
			return 2;
		}
	}

	public static Boolean GetLiveLoggingValue(String key) {
		try {
			return Boolean.parseBoolean(App.properties.getProperty(key));
		} catch (Exception e) {
			return false;
		}
	}
}
