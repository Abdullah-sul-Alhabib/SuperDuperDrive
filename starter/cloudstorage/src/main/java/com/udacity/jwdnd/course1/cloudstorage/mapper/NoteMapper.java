package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * The interface Note mapper.
 */
@Mapper
public interface NoteMapper {
    /**
     * Gets all notes.
     *
     * @param userid user ID
     * @return notes tied to the user.
     */
    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    /**
     * get a single Note
     * @param noteId
     * @return Note
     */
    @Select("Select * FROM NOTES WHERE noteId = #{noteId}")
    Note getOneNote(Integer noteId);

    /**
     * Insert note int.
     *
     * @param note Note object to insert.
     * @return newly generated noteId.
     */
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insertNote(Note note);

    /**
     * edit note title and description
     * @param note
     */
    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    void updateNote(Note note);

    /**
     * delete a note
     * @param noteId
     */
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
