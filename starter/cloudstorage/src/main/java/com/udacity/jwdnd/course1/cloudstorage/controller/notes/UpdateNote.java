package com.udacity.jwdnd.course1.cloudstorage.controller.notes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note/update")
public class UpdateNote {


    private final NoteService noteService;

    public UpdateNote(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String postUpdateNote(Model model, Note note) {
        try {
            noteService.updateNote(note);
            return "redirect:/result?status=0";
        } catch (Error e) {
            return "redirect:/result?status=5";
        }

    }

}
