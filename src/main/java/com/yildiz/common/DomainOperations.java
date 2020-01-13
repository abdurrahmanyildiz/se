package com.yildiz.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.net.whois.WhoisClient;

import com.google.common.net.InternetDomainName;

public class DomainOperations {
	static Logger logger = Logger.getLogger(DomainOperations.class.getName());
	private static final String wp = "wordpress.com";
	private static final String bs = "blogspot.com";
	private static final String tb = "tumblr.com";

	public static String getDomainName(String url) throws MalformedURLException {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		URL netUrl = new URL(url);
		String host = netUrl.getHost();
		if (host.startsWith("www")) {
			host = host.substring("www".length() + 1);
		}
		return host;
	}

	public static String getBaseDomainName(String url) throws MalformedURLException {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		URL netUrl = new URL(url);
		String host = netUrl.getHost();
		if (host.startsWith("www")) {
			host = host.substring("www".length() + 1);
		}
		host = InternetDomainName.from(host).topPrivateDomain().topPrivateDomain().toString();
		return host;
	}

	public static String getTopPrivateDomainName(String url) {
		try {
			return InternetDomainName.from(url).topPrivateDomain().toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDomainNameWithSubdomain(String url) throws MalformedURLException {
		try {
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				url = "http://" + url;
			}
			URL netUrl = new URL(url);
			return netUrl.getHost();
		} catch (MalformedURLException e) {
			if (url.startsWith("http://")) {
				url = url.replaceAll("http://", "");
			}
			if (url.startsWith("https://")) {
				url = url.replaceAll("https://", "");
			}
			if (url.contains("/")) {
				url = url.substring(0, url.indexOf("/"));
			}
			if (url.contains(":")) {
				url = url.substring(0, url.indexOf(":"));
			}
			return url;
		}
	}

	public static String getLastDotName(String domain) {
		String[] parts = domain.split("\\.");
		int len = parts.length;
		if (len <= 2) {
			return domain;
		}
		return parts[len - 2] + "." + parts[len - 1];
	}

	public static Boolean isDomainRegistered(String domain) {
		String result = getWhoisDomainInformation(domain);
		if (result != null && !result.equals("") && result.contains("No match for")) {
			return false;
		} else if (result != null && !result.equals("")) {
			return true;
		}

		return null;
	}

	public static String getWhoisDomainInformation(String domainName) {
		StringBuilder info = new StringBuilder("");
		WhoisClient whois = new WhoisClient();
		try {
			whois.connect(WhoisClient.DEFAULT_HOST);
			String data = whois.query("=" + domainName);
			info.append(data);
			whois.disconnect();

		} catch (SocketException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

		}
		return info.toString();
	}

	public static Boolean isFqdn(String url) {
		String suffix = GetPublicSuffix(url);
		if (suffix.contains(wp) || suffix.contains(bs) || suffix.contains(tb)) {
			return true;
		}

		return false;
	}

	public static String GetPublicSuffix(String url) {
		try {
			return InternetDomainName.from(url).publicSuffix().toString();
		} catch (Exception e) {
			return null;
		}
	}

}
