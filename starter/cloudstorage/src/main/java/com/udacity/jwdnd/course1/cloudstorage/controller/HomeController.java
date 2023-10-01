package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.entity.FileInfoEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for {@linkplain /home} page.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService,
                          NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    /**
     * Home get mapping.
     *
     * @return home
     */
    @GetMapping
    public String getHome( Model model){
        //get the current logged-in user,
        // this project does not contain cache so this is suboptimal,
        // because every request to home will send an SQL query.
        User currentUser = userService.getUser(
                SecurityContextHolder
                .getContext()
                .getAuthentication()
                        .getName());
        //return list of file info uploaded by the user
        List<FileInfoEntity> fileList = new ArrayList<>(fileService.getFileList(currentUser.getUserId()));
        model.addAttribute("storedFiles",fileList);

        //return list of notes uploaded by the user
        List<Note> noteList= noteService.getNoteList(currentUser.getUserId());
        model.addAttribute("storedNotes", noteList);

        //return list of credentials uploaded by the user
        List<Credential> credentialList= credentialService.getCredentialList(currentUser.getUserId());
        model.addAttribute("storedCredentials", credentialList);


        return "home";
    }

}
