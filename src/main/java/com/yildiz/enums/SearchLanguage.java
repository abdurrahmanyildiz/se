package com.yildiz.enums;

/**
 * @author Abdurrahman Yıldız
 *
 *         Language enum(column) from SearchEngineCategorizedKeywords with hl of
 *         languge locale.
 *
 */
public enum SearchLanguage {
	ARABIC("ARABIC", "ar"), CHINESE_SIMPLIFIED("CHINESE_SIMPLIFIED", "zh-CN"),
	CHINESE_TRADITIONAL("CHINESE_TRADITIONAL", "zh-TW"), ENGLISH_UNITED_KINGDOM("ENGLISH_UNITED_KINGDOM", "en"),
	ENGLISH_UNITED_STATES("ENGLISH_UNITED_STATES", "en"), FRENCH_FRANCE("FRENCH_FRANCE", "fr"),
	GERMAN_GERMANY("GERMAN_GERMANY", "de"), ITALIAN("ITALIAN", "it"), JAPANESE("JAPANESE", "ja"), LATIN("LATIN", "la"),
	PERSIAN("PERSIAN", "fa"), PORTUGUESE_BRAZIL("PORTUGUESE_BRAZIL", "pt-BR"),
	PORTUGUESE_PORTUGAL("PORTUGUESE_BRAZIL", "pt-PT"), RUSSIAN("RUSSIAN", "ru"),
	SPANISH_ARGENTINA("SPANISH_ARGENTINA", "es-419"), SPANISH_SPAIN("SPANISH_SPAIN", "es"), TURKISH("TURKISH", "tr"),
	UKRAINIAN("UKRAINIAN", "uk"), DEFAULT("DEFAULT", "en");

	String languageName;
	String hl;

	private SearchLanguage(String languageName, String hl) {
		this.languageName = languageName;
		this.hl = hl;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getHl() {
		return hl;
	}

	public void setHl(String hl) {
		this.hl = hl;
	}

	public static SearchLanguage GetLanguageHlFromLanguage(String languageName) {

		for (SearchLanguage item : SearchLanguage.values()) {
			if (item.getLanguageName().equals(languageName.toUpperCase())) {
				return item;
			}
		}

		return DEFAULT;
	}

}
