package com.wildcat.data.upload;

public abstract class AbstractImporterData implements ImporterData {
    protected ImporterPersistentLayer persistentLayer;

    public AbstractImporterData(ImporterPersistentLayer persistentLayer) {
        this.persistentLayer = persistentLayer;
    }
}
