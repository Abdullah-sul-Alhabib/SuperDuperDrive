package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

/**
 * File model to match the FILES table in the schema.
 */
public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private long fileSize;
    private Integer userId;
    //File date isn't in the constructor because of error creating beans with it there.
    private Blob fileData;

    /**
     * Instantiates a new File.
     *
     * @param fileId      Auto generated primary key.
     * @param fileName    File Name.
     * @param contentType File extension (.jpg, .txt etc..).
     * @param fileSize    File size in Kilo Bytes.
     * @param userId      ID of file owner (FK).
     */
    public File(Integer fileId, String fileName, String contentType, long fileSize, Integer userId, Blob fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    /**
     * Gets file id.
     *
     * @return the file id
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * Sets file id.
     *
     * @param fileId the file id
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets content type.
     *
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets file size.
     *
     * @return the file size
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize the file size
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets file data.
     *
     * @return the file data
     */
    public Blob getFileData() {
        return fileData;
    }

    /**
     * Sets file data.
     *
     * @param fileData the file data
     */
    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }
}
