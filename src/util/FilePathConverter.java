package util;

/**
 * This class provides the funcionality of converting
 * a relative path to the absolute path
 * @author Dan Sun
 *
 */
public class FilePathConverter {
    /**
     * Gets the absoluate path given a relative path inside the project folder
     * @param relative The relative path inside the project folder
     * @return the absolute path
     */
    public static String getAbsolutePath(String relative) {
	String projectDir = System.getProperty("user.dir");
	char firstChar = relative.charAt(0);
	if(firstChar != '/') {
	    projectDir = projectDir + '/';
	}
	return projectDir + relative;
    }
}
