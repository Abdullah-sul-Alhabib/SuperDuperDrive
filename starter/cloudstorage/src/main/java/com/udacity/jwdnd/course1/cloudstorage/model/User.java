package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * User model to match the USERS table in the schema.
 */
public class User {

    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    /**
     *
     * @param userId Auto generated primary key.
     * @param username username.
     * @param salt salt used for hashing the password.
     * @param password hashed password.
     * @param firstName first name.
     * @param lastName last name.
     */
    public User(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
