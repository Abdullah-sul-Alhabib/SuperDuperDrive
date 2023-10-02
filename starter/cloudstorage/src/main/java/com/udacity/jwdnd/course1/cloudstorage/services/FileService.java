package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileDataEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.FileInfoEntity;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * File handler service.
 */
@Service
public class FileService {

    private final FileMapper fileMapper;
    private List<FileInfoEntity> fileList;


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFileById(int fileId) {
        return fileMapper.getOneFileInfo(fileId);
    }


    public List<FileInfoEntity> getFileList(int userId) {
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
        } else {
            setFileList(userId);
        }
        return fileList;
    }

    public void setFileList(int userId) {
        fileList = fileMapper.getAllFilesFromUserId(userId);
    }

    public void uploadFile(MultipartFile file, int userId) throws SQLException, IOException {
        //verify file name doesn't already exist
        if (file.isEmpty()) {
            throw new FileUploadException("6");
        }
        getFileList(userId);
        List<String> fileNamesForUser = fileList.stream()
                .map(FileInfoEntity::getFileName)
                .collect(Collectors.toList());
        if (fileNamesForUser.contains(file.getOriginalFilename())) {
            throw new FileAlreadyExistsException("2");
        }
        //call file factory to get type File instead of multiPartFile.
        FileInfoEntity fileInfoHolder = getFileInfoFromMultipartFile(file, userId);
        FileDataEntity fileDataHolder = getFileDataFromMultipartFile(file, userId);

        fileMapper.insert(fileInfoHolder);
        // another query to get file ID (return value of mapper insert is number of rows, not generated ID)
        int fileId = fileMapper.getFileId(fileInfoHolder);
        //Use fileId to upload the file separately,
        // this is done in a separate query in case we want to handle special logic for file uploading here.
        fileMapper.updateFileData(fileDataHolder.getFileData(), fileId);
    }

    public FileInfoEntity getFileInfoFromMultipartFile(MultipartFile file, int userId) throws IOException, SQLException {
        //create a file object, the first null is the fileId which will be generated by ORM later
        return new FileInfoEntity(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId);
    }

    public FileDataEntity getFileDataFromMultipartFile(MultipartFile file, int userId) throws IOException, SQLException {
        return new FileDataEntity(new SerialBlob(file.getBytes()));
    }

    public void deleteFile(int fileId) {
        fileMapper.deleteFile(fileId);
    }
}
