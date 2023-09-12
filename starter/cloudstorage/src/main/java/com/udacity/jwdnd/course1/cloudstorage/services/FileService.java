package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * File handler service.
 *
 * @// TODO : 10-Sep-23 recieve files, store them, retrieve them by id, ensure their size isn't past limit
 */
@Service
public class FileService {

    private List<File> fileList;
    private FileMapper fileMapper;


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public Blob getFileById(int fileId) {
        return fileMapper.getFileData(fileId);
    }

    public List<String> getFileNames(int userId){
        setFileList(userId);
        //Initiate placeholder for list of file names
        List <String> fileNames = new ArrayList<>();
        fileList.forEach(f -> fileNames.add(f.getFileName()));
        return fileNames;

    }

    public void setFileList(int userId) {
        fileList = fileMapper.getFileInfo(userId);
    }

}
