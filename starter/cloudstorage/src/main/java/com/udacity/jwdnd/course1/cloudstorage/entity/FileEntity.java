package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.util.List;

public class FileEntity {
    private int userId;
    private List<String> fileNames;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

}
