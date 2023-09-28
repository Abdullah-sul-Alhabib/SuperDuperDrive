package com.udacity.jwdnd.course1.cloudstorage.controller.notes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class CreateNote {
    private final NoteService noteService;
    private final UserService userService;

    public CreateNote(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String postNote(Model model, Note note)
    {
        if (note.getNoteId() > 0)
        {
            return "forward:/note/update";
        }
        User currentUser = userService.getUser(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        try {
            noteService.addNote(note, currentUser.getUserId());
            return "redirect:/result?status=0";
        }catch (Error e)
        {
            return "redirect:/result?status=4";
        }

    }
}
