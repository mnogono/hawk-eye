package com.wildcat.data.upload;

public interface ImporterPersistentLayer {
    /**
     * insert or update data (depend of some data logic) into some persistent layer
     * @param data data to save
     * @return success flag
     */
    <T> boolean save(T data);
}
