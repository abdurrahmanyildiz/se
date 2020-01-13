package com.yildiz.data;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author abdurrahman
 *
 */
public class ESClientManager {
	private static RestHighLevelClient client = null; 
	
	public ESClientManager() {
		if (client == null) {
			client = new RestHighLevelClient(RestClient.builder(new HttpHost("0.0.0.0", 9200, "http")));			
		}
	}
	
	public RestHighLevelClient GetClient() {
		return client;
	}
	
	public void CloseClient() throws IOException {
		client.close();
	}
	
}
