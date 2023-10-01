package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Credential model to match the CREDENTIALS table in the schema.
 */
public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    /**
     * Instantiates a new Credential.
     *
     * @param credentialId Auto-generated primary key.
     * @param url          URL for this credential.
     * @param username     username.
     * @param key          Hash key.
     * @param password     Hashed password
     * @param userid       ID of the user this credential ties to (FK).
     */
    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userid) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }

    /**
     * Gets credential id.
     *
     * @return the credential id
     */
    public Integer getCredentialId() {
        return credentialId;
    }

    /**
     * Sets credential id.
     *
     * @param credentialId the credential id
     */
    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets userid.
     *
     * @return the userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * Sets userid.
     *
     * @param userid the userid
     */
    public void setUserId(Integer userid) {
        this.userid = userid;
    }


}
