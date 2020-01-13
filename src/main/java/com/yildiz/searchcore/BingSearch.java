package com.yildiz.searchcore;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By.ByCssSelector;

import com.yildiz.App;
import com.yildiz.enums.SearchLanguage;
import com.yildiz.models.interfaces.SearchSite;
import com.yildiz.models.se.SearchKeyword;
import com.yildiz.models.se.SearchSiteParameter;

/**
 * Search Crawler class for bing.com
 * 
 * @author Abdurrahman Yıldız
 *
 */
public class BingSearch {

	private static SearchSiteParameter parameters = 
			new SearchSiteParameter.Builder()
			.searchSite("bing")
			.resultElementsCssSelector(new ByCssSelector("li.b_algo"))
			.titleCssSelector(new ByCssSelector("li.b_algo a"))
			.linkCssSelector(new ByCssSelector("li.b_algo a"))
			.textCssSelector(new ByCssSelector("div.b_caption p"))
			.nextButtonCssSelector(new ByCssSelector("a.sb_pagN"))
			.isLiveLoggingActive(false)
			.maxResult(getMaxNumberOrDefault())
			.build();

	public static SearchSiteParameter CreateParameters(SearchKeyword seKeyword, Boolean isLiveLoggingActive) {
		parameters.setSeKeyword(seKeyword);
		//count=50&
		StringBuilder query = new StringBuilder("https://www.bing.com/search?q=").append(seKeyword.getKeyword());

//		if (!StringUtils.isNullOrEmpty(seKeyword.getSearchSite()) && !seKeyword.getSearchSite().equals("None")) {
//			query.append(" site:.").append(seKeyword.getSearchSite());
//		}

		if (!StringUtils.isBlank(seKeyword.getLanguage())) {
			query.append(" language:").append(SearchLanguage.GetLanguageHlFromLanguage(seKeyword.getLanguage()).getHl());
		}

		parameters.setQuery(query.toString());
		parameters.setIsLiveLoggingActive(isLiveLoggingActive);

		return parameters;
	}

	private static Integer getMaxNumberOrDefault() {
		try {
			return Integer.parseInt(App.properties.getProperty("search.bing.siteparams.maxresult"));
		} catch (Exception e) {
			return 1000;
		}
	}

	
}
