package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Note model to match the NOTES table in the schema.
 */
public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    /**
     * Instantiates a new Note.
     *
     * @param noteId          Auto generated key.
     * @param noteTitle       note title.
     * @param noteDescription note description.
     * @param userId          ID for the user this note ties to (FK).
     */
    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    /**
     * Gets note id.
     *
     * @return the note id
     */
    public Integer getNoteId() {
        return noteId;
    }

    /**
     * Sets note id.
     *
     * @param noteId the note id
     */
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    /**
     * Gets note title.
     *
     * @return the note title
     */
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
     * Sets note title.
     *
     * @param noteTitle the note title
     */
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    /**
     * Gets note description.
     *
     * @return the note description
     */
    public String getNoteDescription() {
        return noteDescription;
    }

    /**
     * Sets note description.
     *
     * @param noteDescription the note description
     */
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
