package com.yildiz.data;

import java.io.IOException;
import java.text.ParseException;

import com.yildiz.models.se.SearchEngineResultForES;

public class SearchEngineResultRepository {
	ESClientManager cliMng = new ESClientManager();
	public String index = "index_name";

	public void PutDbResultsToES(SearchEngineResultForES resultForES) throws IOException, ParseException {
		//TODO: implement elastic search client for java and insert record to index  
	}

}
