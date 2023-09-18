package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

import java.util.List;

public class FileEntity {
    private int fileId;
    private String fileName;

    public int getUserId() {
        return fileId;
    }

    public void setUserId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileNames() {
        return fileName;
    }

    public void setFileNames(String fileName) {
        this.fileName = fileName;
    }

}
