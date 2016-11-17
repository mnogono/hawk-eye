package com.wildcat.data.upload;

import java.io.File;

public interface ImporterData {
    /**
     * importer start procedure of data import
     * @param dataFile file with data
     * @return return success flag if import was finished correctly
     */
    boolean importDataFile(File dataFile);
}
