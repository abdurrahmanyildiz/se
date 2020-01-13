package com.yildiz;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.yildiz.common.Utils;
import com.yildiz.models.se.SearchKeyword;
import com.yildiz.searchcore.SeleniumChromeDriver;

/**
 * @author abdurrahman
 *
 */
public class App {

	public static Properties properties = new Properties();
	public static Logger logger = Logger.getLogger(App.class.getName());
	public static List<SearchKeyword> keywords = null;

	public static void main(String[] args) {

		while (true) {
			try {
				LoadSystemParameters();
				Integer i = 0;
				SeleniumChromeDriver scd = new SeleniumChromeDriver();

				SearchKeyword kw = keywords.get(i);

				while (kw != null) {
					logger.log(Level.FINE, "Working for= " + kw.getKeyword());

					scd.CrawlContents(kw);

					i++;

					if (i < keywords.size()) {
						kw = keywords.get(i);
					} else {
						i = 0;
						kw = keywords.get(i);
						logger.log(Level.INFO, "[Keywords Restarted]");
					}

					System.gc();
				}
			} catch (Exception e) {
				logger.severe("Unexpected Error Occured!");
				logger.severe(e.getMessage());
			} finally {
				logger.log(Level.INFO, "[Program Finished]");
				for (Handler h : logger.getHandlers()) {
					h.close();
				}
			}
		}
	}

	/**
	 * Calls load methods.
	 * 
	 * @throws Exception
	 */
	public static void LoadSystemParameters() throws Exception {
		LoadLogging();
		LoadProperties();
		LoadKeywords();
		logger.log(Level.FINE, "System Parameters loaded");
		logger.log(Level.INFO, "[Program started]");
		logger.log(Level.FINE, "Hello from Yildiz Search Engine Crawler");
		String selectedBrowser = properties.getProperty("webdriver.browser");
		if (selectedBrowser != null && selectedBrowser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					Utils.RootDirectory + properties.getProperty("webdriver.geckopath"));
			logger.log(Level.INFO, "[Firefox webdriver selected]");
		} else {
			System.setProperty("webdriver.chrome.driver",
					Utils.RootDirectory + properties.getProperty("webdriver.chromepath"));
			logger.log(Level.INFO, "[Chrome webdriver selected]");
		}

		logger.log(Level.FINE, "Selenium WebDriver Loaded as: " + selectedBrowser);
	}

	/**
	 * Reads logging parameters from resource file
	 */
	public static void LoadLogging() {
		InputStream stream = null;
		try {
			stream = new FileInputStream(Utils.RootDirectory + "/resources/logging.properties");
			LogManager.getLogManager().readConfiguration(stream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: logging.properties file could not found!" + e.toString());
		}
		logger.log(Level.FINE, "Logging properties Loaded");
	}

	/**
	 * Loads parameters from properties file
	 * 
	 * @throws Exception
	 */
	public static void LoadProperties() throws Exception {
		InputStream input = null;
		try {
			input = new FileInputStream(Utils.RootDirectory + "/resources/app.properties");
			// input = App.class.getClassLoader().getResourceAsStream("app.properties");
			properties.load(input);

		} catch (IOException ex) {
			System.out.println("ERROR: app.properties file could not found!" + ex.toString());
			throw new Exception(ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.log(Level.FINE, "Application properties Loaded");
	}

	/**
	 * Loads Search keywords from file
	 * 
	 * @throws Exception
	 */
	public static void LoadKeywords() throws Exception {
		keywords = new ArrayList<SearchKeyword>();
		try (BufferedReader br = new BufferedReader(new FileReader(Utils.RootDirectory + "/resources/keywords.txt"))) {

			String line;
			SearchKeyword cat;
			while ((line = br.readLine()) != null) {
				String[] items = line.split(",");
				cat = new SearchKeyword();
				cat.setKeyword(items[0]);
				cat.setLanguage(items[1]);
				cat.setCategory(items[2]);
				keywords.add(cat);
			}

		} catch (IOException ex) {
			System.out.println("ERROR: keywords.txt file could not found!" + ex.toString());
			throw new Exception(ex);
		}

		logger.log(Level.FINE, "Category cashing completed");
	}
}
