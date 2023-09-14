package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    public void uploadFile(MultipartFile file, int userId) throws SQLException, IOException {
        File fileHolder = fileFactory(file,userId);
        fileMapper.insert(fileHolder);
        fileMapper.insertFile(fileHolder.getFileData(),fileHolder.getFileId());
    }

    public File fileFactory(MultipartFile file, int userId) throws IOException, SQLException {
        Blob blob = new SerialBlob(file.getBytes());
        File file1 = new File(null, file.getOriginalFilename(), file.getContentType(),file.getSize(),userId);
        file1.setFileData(blob);
        return file1;
    }
}
