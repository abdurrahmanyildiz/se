package com.yildiz.common;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @author Abdurrahman
 *
 */
public class Utils {

	public static final SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SIMPLE_DATE_FORMATTER_SSS = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	public static final String SEPERATOR = "\\|";
	public static final String RootDirectory = new File(new File("").getAbsolutePath()).getPath();
	public static final String UserAgent = "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";
	public static final String failMsg = "FAILED";
}
