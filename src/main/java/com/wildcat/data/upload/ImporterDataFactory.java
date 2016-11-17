package com.wildcat.data.upload;

import com.wildcat.data.mongodb.upload.ImporterMongodbPersistentLayer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ImporterDataFactory {
    private List<ImporterData> importers;

    static private ImporterDataFactory instance;

    public static ImporterDataFactory getInstance() {
        if (instance == null) {
            instance = new ImporterDataFactory(new ImporterMongodbPersistentLayer());
        }

        return instance;
    }

    private ImporterDataFactory(ImporterPersistentLayer persistentLayer) {
        importers = new LinkedList<>();

        registerImporter(new ImporterSample(persistentLayer));
    }

    void registerImporter(ImporterData importerData) {
        if (!importers.contains(importerData)) {
            importers.add(importerData);
        }
    }

    /**
     * find importer enabled to parse dataFile or null
     * @param dataFile data files will parse for importer
     * @return ImporterData || null
     */
    public boolean importDataFile(File dataFile) {
        for (ImporterData importer : importers) {
            if (importer.importDataFile(dataFile)) {
                return true;
            }
        }

        return false;
    }
}
