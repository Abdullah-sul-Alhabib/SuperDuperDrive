package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.sql.Blob;

/**
 * This class had to be made because mybatis mapper needs to instantiate objects,
 * and it cannot instantiate Blob, hence comes this class.
 */
public class FileDataEntity {
    private Blob fileData;

    /**
     * Instantiates a new File data entity.
     *
     * @param fileData the file data
     */
    public FileDataEntity(Blob fileData) {
        this.fileData = fileData;
    }

    /**
     * Gets file data.
     *
     * @return the file data
     */
    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }
}
