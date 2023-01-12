package western.covid.project.utils;

import java.io.File;

/**
 * A class used to generate the path of working directory.
 * @author Tianci Du
 * @version 1.0
 */
public class PathTools {

	/**
	 * Get path by file name.
	 * @param databaseName
	 * @return
	 */
	public static String databaseFilePath(String databaseName) {
		String workingPath = System.getProperty("user.dir");
		String homePath=workingPath+File.separator+"database"+File.separator+databaseName;
		return homePath;
	}

}
