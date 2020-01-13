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
public class YandexSearch {
	
	private static SearchSiteParameter parameters = new SearchSiteParameter
			.Builder()
			.searchSite("yandex")
			.resultElementsCssSelector(new ByCssSelector("li.serp-item"))
			.titleCssSelector(new ByCssSelector("h2.organic__title-wrapper"))
			.linkCssSelector(new ByCssSelector("h2.organic__title-wrapper a"))
			.textCssSelector(new ByCssSelector("div.organic__content-wrapper div"))
			.nextButtonCssSelector(new ByCssSelector("a.pager__item_kind_next"))
			.isLiveLoggingActive(false)
			.maxResult(getMaxNumberOrDefault())
			.build();

	public static SearchSiteParameter CreateParameters(SearchKeyword seKeyword, Boolean isLiveLoggingActive) {
		parameters.setSeKeyword(seKeyword);
		StringBuilder query = new StringBuilder("https://yandex.com/search/?text=").append(seKeyword.getKeyword());

//		if (!StringUtils.isNullOrEmpty(seKeyword.getSearchSite()) && !seKeyword.getSearchSite().equals("None")) {
//			query.append(" site:.").append(seKeyword.getSearchSite());
//		}

		if (!StringUtils.isBlank(seKeyword.getLanguage())) {
			query.append(" lang:").append(SearchLanguage.GetLanguageHlFromLanguage(seKeyword.getLanguage()).getHl());
		}

		parameters.setQuery(query.toString());
		parameters.setIsLiveLoggingActive(isLiveLoggingActive);

		return parameters;
	}

	private static Integer getMaxNumberOrDefault() {
		try {
			return Integer.parseInt(App.properties.getProperty("search.yandex.siteparams.maxresult"));
		} catch (Exception e) {
			return 1000;
		}
	}

}
