package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileInfoEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.sql.Blob;
import java.util.List;

/**
 * fileId INT PRIMARY KEY auto_increment,
 * filename VARCHAR,
 * contenttype VARCHAR,
 * filesize VARCHAR,
 * userid INT,
 * filedata BLOB,
 * foreign key (userid) references USERS(userid)
 */
@Mapper
public interface FileMapper {
    /**
     * Gets file info.
     *
     * @param userid user ID.
     * @return info for ALL files uploaded by user, without their content, for that refer to {@code getOneFileInfo}.
     */
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<FileInfoEntity> getAllFilesFromUserId(Integer userid);

    /**
     * Gets file data.
     *
     * @param fileId file ID
     * @return file content as java.sql.blob object.
     */
    @Select("SELECT * FROM FILES WHERE fileid = #{filedId}")
    File getOneFileInfo(Integer fileId);

    /**
     * get a file ID by entering all other data.
     * @param fileInfoEntity
     * @return Integer file ID
     */
    @Select("SELECT fileid FROM FILES WHERE ( filename, contenttype, filesize, userid) = ( #{fileName}, #{contentType}, #{fileSize}, #{userId})")
    Integer getFileId(FileInfoEntity fileInfoEntity);

    /**
     * Insert file, return fileId.
     *
     * @param file file to insert.
     */
    @Insert("INSERT INTO FILES ( filename, contenttype, filesize, userid) VALUES( #{fileName}, #{contentType}, #{fileSize}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void insert(FileInfoEntity file);

    /**
     * insert file blob into its row
     * @param fileData
     * @param fileId
     */
    @Update("UPDATE FILES SET filedata= #{fileData} WHERE fileId = #{fileId}")
    void updateFileData(Blob fileData, int fileId);

    /**
     * delete a file
     * @param fileId
     */
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(int fileId);

}
