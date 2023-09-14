package com.udacity.jwdnd.course1.cloudstorage.mapper;

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
     * @return info for ALL files uploaded by user, without their content, for that refer to {@code getFileData}.
     */
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getFileInfo(Integer userid);

    /**
     * Gets file data.
     *
     * @param fileId file ID
     * @return file content as java.sql.blob object.
     */
    @Select("SELECT filedata FROM FILES WHERE fileId = #{filedId}")
    Blob getFileData(Integer fileId);

    /**
     * Insert file, return fileId.
     *
     * @param file file to insert.
     * @return newly generated fileid.
     */
    @Insert("INSERT INTO FILES ( filename, contenttype, filesize, userid) VALUES( #{fileName}, #{contentType}, #{fileSize}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Update("UPDATE FILES SET fileData= #{fileData} WHERE fileId = #{fileId}")
    int insertFile(Blob fileData, int fileId);


}
