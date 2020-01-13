package com.yildiz.models.se;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author abdurrahman
 *
 */
public class SearchEngineResultForES {

	private String crawl_content;

	private String description;

	private String domain;

	private String fqdn;

	private String last_redirection_url;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date insert_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date last_update_date;

	private String se;

	private String se_category;

	private String search_site;

	private String title;

	public String getCrawl_content() {
		return crawl_content;
	}

	public void setCrawl_content(String crawl_content) {
		this.crawl_content = crawl_content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFqdn() {
		return fqdn;
	}

	public void setFqdn(String fqdn) {
		this.fqdn = fqdn;
	}

	public String getLast_redirection_url() {
		return last_redirection_url;
	}

	public void setLast_redirection_url(String last_redirection_url) {
		this.last_redirection_url = last_redirection_url;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public String getSe_category() {
		return se_category;
	}

	public void setSe_category(String se_category) {
		this.se_category = se_category;
	}

	public String getSearch_site() {
		return search_site;
	}

	public void setSearch_site(String search_site) {
		this.search_site = search_site;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
