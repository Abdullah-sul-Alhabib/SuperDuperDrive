package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {
    /**
     * Gets user.
     *
     * @param username username.
     * @return User object tied to provided username.
     */
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    /**
     * Insert int.
     *
     * @param user user object to insert.
     * @return newly generated userId.
     */
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
