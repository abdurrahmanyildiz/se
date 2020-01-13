package com.yildiz.searchcore;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By.ByCssSelector;

import com.yildiz.App;
import com.yildiz.enums.SearchLanguage;
import com.yildiz.models.se.SearchKeyword;
import com.yildiz.models.se.SearchSiteParameter;

/**
 * @author Abdurrahman Yıldız
 *
 */
public class GoogleSearch {
		
	private static SearchSiteParameter parameters = new SearchSiteParameter
			.Builder()
			.searchSite("google")
			.resultElementsCssSelector(new ByCssSelector("div.g"))
			.titleCssSelector(new ByCssSelector("div.r a"))
			.linkCssSelector(new ByCssSelector("div.r a"))
			.textCssSelector(new ByCssSelector("div.rc div.s"))
			.nextButtonCssSelector(new ByCssSelector("a#pnnext"))
			.isLiveLoggingActive(false)
			.maxResult(getMaxNumberOrDefault())
			.build();
		
	public static SearchSiteParameter CreateParameters(SearchKeyword seKeyword, Boolean isLiveLoggingActive) {
		parameters.setSeKeyword(seKeyword);
		StringBuilder query = new StringBuilder("https://www.google.com/search?"); //num=50

		if (!StringUtils.isBlank(seKeyword.getLanguage())) {
			query.append("lr=lang_").append(SearchLanguage.GetLanguageHlFromLanguage(seKeyword.getLanguage()).getHl()).append("&");
		}
		
		query.append("q=").append(seKeyword.getKeyword());
		
//		if (!StringUtils.isNullOrEmpty(seKeyword.getSearchSite()) && !seKeyword.getSearchSite().equals("None")) {
//			query.append(" site:.").append(seKeyword.getSearchSite());
//		}

		parameters.setQuery(query.toString());
		parameters.setIsLiveLoggingActive(isLiveLoggingActive);
		
		return parameters;
	}

	private static Integer getMaxNumberOrDefault() {
		try {
			return Integer.parseInt(App.properties.getProperty("search.google.siteparams.maxresult"));
		} catch (Exception e) {
			return 1000;
		}
	}
	 
}
