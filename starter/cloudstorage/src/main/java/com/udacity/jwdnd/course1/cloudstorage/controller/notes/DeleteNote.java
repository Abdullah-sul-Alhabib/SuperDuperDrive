package com.udacity.jwdnd.course1.cloudstorage.controller.notes;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/note/delete")
public class DeleteNote {

    private final NoteService noteService;

    public DeleteNote(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String getDeleteNote(@RequestParam("noteId") Integer noteId){
        try{
            noteService.deleteNote(noteId);
            return "redirect:/result?status=0";
        }catch (Error e)
        {
            return "redirect:/result?status=6";
        }
    }
}
