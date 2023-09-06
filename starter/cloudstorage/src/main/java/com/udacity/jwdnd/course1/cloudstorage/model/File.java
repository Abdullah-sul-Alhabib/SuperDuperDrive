package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

/**
 * File model to match the FILES table in the schema.
 */
public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private Blob fileData;

    /**
     * @param fileId Auto generated primary key.
     * @param fileName File Name.
     * @param contentType File extension (.jpg, .txt etc..).
     * @param fileSize File size in Kilo Bytes.
     * @param userId ID of file owner (FK).
     */
    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }
}
