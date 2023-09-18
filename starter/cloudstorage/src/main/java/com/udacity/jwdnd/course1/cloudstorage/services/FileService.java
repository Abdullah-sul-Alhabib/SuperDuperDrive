package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public List<File> getFileList(int userId){
        //if file list is empty (this means the server just started)
        // then just set a new one and return it.
        if (fileList == null || fileList.isEmpty()) {
            setFileList(userId);
            return fileList;
        }
        //if there is a file list already, ensure it is for the same user
        AtomicInteger uId = new AtomicInteger();
        this.fileList.stream().peek(f -> uId.set(f.getUserId()));
        if (uId.get() == userId) {
            return fileList;
        }
        else {
            setFileList(userId);
        }
        return fileList;
    }

    public void setFileList(int userId) {
        fileList = fileMapper.getFileInfo(userId);
    }

    public void uploadFile(MultipartFile file, int userId) throws SQLException, IOException {
        File fileHolder = fileFactory(file,userId);
        int fileId = fileMapper.insert(fileHolder);
        fileMapper.insertFile(fileHolder.getFileData(),fileId);
    }

    public File fileFactory(MultipartFile file, int userId) throws IOException, SQLException {
        Blob blob = new SerialBlob(file.getBytes());
        File file1 = new File(null, file.getOriginalFilename(), file.getContentType(),file.getSize(),userId);
        file1.setFileData(blob);
        return file1;
    }
}
