package com.udacity.jwdnd.course1.cloudstorage.controller.files;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/file/upload")
public class UploadController {
    private FileService fileService;
    private UserService userService;

    public UploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
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

        //TODO: Try/catch to get upload failure
        fileService.uploadFile(uploadedFile, currentUser.getUserId());
        //receive notes and tie them to user

        //receive credentials and tie them to user

        //if/else condition using isUploadSuccessful flag,
        // then redirect to result with the proper result parameters,

        return "redirect:/result?status=0";
    }
}
