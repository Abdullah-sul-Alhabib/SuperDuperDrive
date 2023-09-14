package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
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
    public String getHome(@ModelAttribute("storedFiles") FileEntity fileEntity, Model model){
        //get the current logged-in user,
        // this project does not contain cache so this is suboptimal,
        // because every request to home will send an SQL query.
        User currentUser = userService.getUser(
                SecurityContextHolder
                .getContext()
                .getAuthentication()
                        .getName());
        fileEntity.setUserId(currentUser.getUserId());
        //return list of file info uploaded by the user
        fileEntity.setFileNames(fileService.getFileNames(currentUser.getUserId()));
        // @// TODO Tie fileNames to their fileId.
        model.addAttribute("storedFiles",fileEntity);
        //If file list is not empty
        if (!fileEntity.getFileNames().isEmpty())
        {
            //download functionality for the download button
        }
        //fill the credential tab

        //return note list

        return "home";
    }

    @PostMapping
    public String homePost(@RequestParam("fileUpload") MultipartFile uploadedFile, Model model) throws SQLException, IOException {
        boolean isUploadSuccessful, isNoteSuccessful, isCredentialSuccessful = false;
        //receive file uploads and tie them to user
        User currentUser = userService.getUser(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        fileService.uploadFile(uploadedFile, currentUser.getUserId());
        //receive notes and tie them to user

        //receive credentials and tie them to user

        //if/else condition using isUploadSuccessful flag,
        // for upload successes, redirect to result with the proper result variable,
        // else return them back to home
        return "home";
    }
}
