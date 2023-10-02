package com.udacity.jwdnd.course1.cloudstorage.controller.notes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/note/delete")
public class DeleteNote {

    private final NoteService noteService;
    private final UserService userService;

    public DeleteNote(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public String getDeleteNote(@RequestParam("noteId") Integer noteId) {
        try {
            Note note = noteService.getNoteById(noteId);

            User currentUser = userService.getUser(
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName());

            if (!Objects.equals(currentUser.getUserId(), note.getUserId())) {
                return "redirect:/result?status=3";
            }
            noteService.deleteNote(noteId);
            return "redirect:/result?status=0";
        } catch (Error e) {
            return "redirect:/result?status=6";
        }
    }
}
