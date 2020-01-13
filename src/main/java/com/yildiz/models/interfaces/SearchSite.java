package com.yildiz.models.interfaces;

import com.yildiz.models.se.SearchKeyword;
import com.yildiz.models.se.SearchSiteParameter;

/**
 * Method Interface of search sites.
 * 
 * @author Abdurrahman Yıldız
 *
 */
public interface SearchSite {
	
	SearchSiteParameter CreateParameters(SearchKeyword seKeyword,Boolean isLiveLoggingActive);

}
