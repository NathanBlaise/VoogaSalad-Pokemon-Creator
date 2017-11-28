package data;

public class LanguageReader {
	
	public static String convertLanguage(String language, String name){
		return new PropertyReader("../resources/"+language+".properties").getString(name);
	}
}
