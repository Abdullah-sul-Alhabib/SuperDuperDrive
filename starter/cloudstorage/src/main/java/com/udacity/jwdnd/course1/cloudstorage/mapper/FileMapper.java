package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Blob;
import java.util.List;

/**
 *     fileId INT PRIMARY KEY auto_increment,
 *     filename VARCHAR,
 *     contenttype VARCHAR,
 *     filesize VARCHAR,
 *     userid INT,
 *     filedata BLOB,
 *     foreign key (userid) references USERS(userid)
 */
@Mapper
public interface FileMapper {
    /**
     * @param userid user ID.
     * @return info for ALL files uploaded by user, without their content, for that refer to {@code getFileData}.
     */
    @Select("SELECT fileId, filename, contenttype, filesize FROM FILES WHERE userid = #{userid}")
    List<File> getFileInfo(Integer userid);

    /**
     *
     * @param fileId file ID
     * @return file content as java.sql.blob object.
     */
    @Select("SELECT filedata FROM FILES WHERE fileId = #{filedId}")
    Blob getFileData(Integer fileId);

    /**
     *
     * @param file file to insert.
     * @return newly generayed fileid.
     */
    @Insert("INSERT INTO FILES ( filename, contenttype, filesize, userid, filedata) VALUES( #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

}
