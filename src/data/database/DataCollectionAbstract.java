package data.database;

import java.io.File;

/**
 * This class is the superclass for all classes 
 * that acts as a collection of a single type of data.
 * This class provides common functionality such as 
 * getting files that specify each individual from a folder
 * @author Dan Sun
 *
 */
public abstract class DataCollectionAbstract {
    
    //adopted from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
    /**
     * Obtains files with in folder
     * @param folder File object of the folder
     * @return An array of File object that contains files within the folder
     */
    protected File[] getFilesWithinAsArray(File folder) {
	assert(folder.exists());
	assert(folder.isDirectory());
	File[] listOfFiles = folder.listFiles();
	return listOfFiles;
    }
    /**
     * Obtains files with in folder
     * @param folder Path of the folder
     * @return An array of File object that contains files within the folder
     */
    protected File[] getFilesWithinAsArray(String folder) {
//	System.out.println(folder); //for testing
	File folderFile = new File(folder);
	return getFilesWithinAsArray(folderFile);
    }
}
