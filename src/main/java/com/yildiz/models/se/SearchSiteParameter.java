package com.yildiz.models.se;

import org.openqa.selenium.By.ByCssSelector;


/**
 * This Class created to pass parameters of search sites to search method.
 * Class has a builder.
 * 
 * @author Abdurrahman Yıldız
 *
 */
public class SearchSiteParameter {

	private String searchSite;
	private SearchKeyword seKeyword;
	private String query;
	private ByCssSelector resultElementsCssSelector;
	private ByCssSelector titleCssSelector;
	private ByCssSelector linkCssSelector;
	private ByCssSelector textCssSelector;
	private ByCssSelector nextButtonCssSelector;
	private Boolean isLiveLoggingActive = false;
	private Integer maxResult = 1000;

	/**
	 * 
	 * @param searchSite
	 * @param keyword
	 * @param query
	 * @param resultElementsCssSelector css class of result item.
	 * @param titleCssSelector          css class of title of result items.
	 * @param linkCssSelector           css class of link that in the result item.
	 * @param textCssSelector           css class of text of result item.
	 * @param nextButtonCssSelector     css class of the next button that placed
	 *                                  bottom of the search site.
	 * @param isLiveLoggingActive       if you want to trace status of algorithm.
	 */
	public SearchSiteParameter(String searchSite, SearchKeyword seKeyword, String query, ByCssSelector resultElementsCssSelector,
			ByCssSelector titleCssSelector, ByCssSelector linkCssSelector, ByCssSelector textCssSelector,
			ByCssSelector nextButtonCssSelector, Boolean isLiveLoggingActive) {
		super();
		this.searchSite = searchSite;
		this.seKeyword= seKeyword;
		this.query = query;
		this.resultElementsCssSelector = resultElementsCssSelector;
		this.titleCssSelector = titleCssSelector;
		this.linkCssSelector = linkCssSelector;
		this.textCssSelector = textCssSelector;
		this.nextButtonCssSelector = nextButtonCssSelector;
		this.isLiveLoggingActive = isLiveLoggingActive;
	}

	public String getSearchSite() {
		return searchSite;
	}

	public void setSearchSite(String searchSite) {
		this.searchSite = searchSite;
	}
	
	public SearchKeyword getSeKeyword() {
		return seKeyword;
	}

	public void setSeKeyword(SearchKeyword seKeyword) {
		this.seKeyword = seKeyword;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public ByCssSelector getResultElementsCssSelector() {
		return resultElementsCssSelector;
	}

	public void setResultElementsCssSelector(ByCssSelector resultElementsCssSelector) {
		this.resultElementsCssSelector = resultElementsCssSelector;
	}

	public ByCssSelector getTitleCssSelector() {
		return titleCssSelector;
	}

	public void setTitleCssSelector(ByCssSelector titleCssSelector) {
		this.titleCssSelector = titleCssSelector;
	}

	public ByCssSelector getTextCssSelector() {
		return textCssSelector;
	}

	public void setTextCssSelector(ByCssSelector textCssSelector) {
		this.textCssSelector = textCssSelector;
	}

	public ByCssSelector getLinkCssSelector() {
		return linkCssSelector;
	}

	public void setLinkCssSelector(ByCssSelector linkCssSelector) {
		this.linkCssSelector = linkCssSelector;
	}

	public ByCssSelector getNextButtonCssSelector() {
		return nextButtonCssSelector;
	}

	public void setNextButtonCssSelector(ByCssSelector nextButtonCssSelector) {
		this.nextButtonCssSelector = nextButtonCssSelector;
	}

	public Boolean getIsLiveLoggingActive() {
		return isLiveLoggingActive;
	}

	public void setIsLiveLoggingActive(Boolean isLiveLoggingActive) {
		this.isLiveLoggingActive = isLiveLoggingActive;
	}

	public Integer getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	public static class Builder {
		private String searchSite;
		private SearchKeyword seKeyword;
		private String query;
		private ByCssSelector resultElementsCssSelector;
		private ByCssSelector titleCssSelector;
		private ByCssSelector linkCssSelector;
		private ByCssSelector textCssSelector;
		private ByCssSelector nextButtonCssSelector;
		private Boolean isLiveLoggingActive;
		private Integer maxResult;

		public Builder searchSite(String searchSite) {
			this.searchSite = searchSite;
			return this;
		}

		public Builder keyword(SearchKeyword seKeyword) {
			this.seKeyword = seKeyword;
			return this;
		}

		public Builder query(String query) {
			this.query = query;
			return this;
		}

		public Builder resultElementsCssSelector(ByCssSelector resultElementsCssSelector) {
			this.resultElementsCssSelector = resultElementsCssSelector;
			return this;
		}

		public Builder titleCssSelector(ByCssSelector titleCssSelector) {
			this.titleCssSelector = titleCssSelector;
			return this;
		}

		public Builder linkCssSelector(ByCssSelector linkCssSelector) {
			this.linkCssSelector = linkCssSelector;
			return this;
		}

		public Builder textCssSelector(ByCssSelector textCssSelector) {
			this.textCssSelector = textCssSelector;
			return this;
		}

		public Builder nextButtonCssSelector(ByCssSelector nextButtonCssSelector) {
			this.nextButtonCssSelector = nextButtonCssSelector;
			return this;
		}

		public Builder isLiveLoggingActive(Boolean isLiveLoggingActive) {
			this.isLiveLoggingActive = isLiveLoggingActive;
			return this;
		}
		public Builder maxResult(Integer maxResult) {
			this.maxResult = maxResult;
			return this;
		}

		public SearchSiteParameter build() {
			return new SearchSiteParameter(this);
		}
	}

	private SearchSiteParameter(Builder builder) {
		this.searchSite = builder.searchSite;
		this.seKeyword = builder.seKeyword;
		this.query = builder.query;
		this.resultElementsCssSelector = builder.resultElementsCssSelector;
		this.titleCssSelector = builder.titleCssSelector;
		this.linkCssSelector = builder.linkCssSelector;
		this.textCssSelector = builder.textCssSelector;
		this.nextButtonCssSelector = builder.nextButtonCssSelector;
		this.isLiveLoggingActive = builder.isLiveLoggingActive;
		this.maxResult = builder.maxResult;
	}
}
