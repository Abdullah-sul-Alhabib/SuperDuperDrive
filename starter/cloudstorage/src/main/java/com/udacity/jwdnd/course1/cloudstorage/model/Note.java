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
     *
     * @param noteId Auto generated key.
     * @param noteTitle note title.
     * @param noteDescription note description.
     * @param userId ID for the user this note ties to (FK).
     */
    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
