package com.yildiz.searchcore;

import org.openqa.selenium.By.ByCssSelector;

import com.yildiz.App;
import com.yildiz.models.se.SearchKeyword;
import com.yildiz.models.se.SearchSiteParameter;

/**
 * @author Abdurrahman Yıldız
 *
 */
public class YahooSearch {
		
	private static SearchSiteParameter parameters = new SearchSiteParameter
			.Builder()
			.searchSite("yahoo")
			.resultElementsCssSelector(new ByCssSelector("div.algo-sr"))
			.titleCssSelector(new ByCssSelector("h3.title"))
			.linkCssSelector(new ByCssSelector("h3.title a"))
			.textCssSelector(new ByCssSelector("p.lh-16"))
			.nextButtonCssSelector(new ByCssSelector("a.next"))
			.isLiveLoggingActive(false)
			.maxResult(getMaxNumberOrDefault())
			.build();
	
	public static SearchSiteParameter CreateParameters(SearchKeyword seKeyword, Boolean isLiveLoggingActive){
		parameters.setSeKeyword(seKeyword);
		StringBuilder query = new StringBuilder("https://search.yahoo.com/search?p=").append(seKeyword.getKeyword()); //n=50&

//		if (!StringUtils.isNullOrEmpty(seKeyword.getSearchSite()) && !seKeyword.getSearchSite().equals("None")) {
//			query.append(" site:.").append(seKeyword.getSearchSite());
//		}

//		if (!StringUtils.isNullOrEmpty(language)) {
//			query.append(" lang:").append(SearchLanguage.GetLanguageHlFromLanguage(language).getHl());
//		}

		parameters.setQuery(query.toString());
		parameters.setIsLiveLoggingActive(isLiveLoggingActive);
		
		return parameters;
	}

	private static Integer getMaxNumberOrDefault() {
		try {
			return Integer.parseInt(App.properties.getProperty("search.yahoo.siteparams.maxresult"));
		} catch (Exception e) {
			return 1000;
		}
	}
}
