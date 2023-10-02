package com.udacity.jwdnd.course1.cloudstorage.controller.files;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/file/delete")
public class DeleteController {
    private final UserService userService;
    private final FileService fileService;

    public DeleteController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    public String deleteController(@RequestParam("fileid") int fileId) {
        try {
            File file = fileService.getFileById(fileId);

            User currentUser = userService.getUser(
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName());

            if (!Objects.equals(currentUser.getUserId(), file.getUserId())) {
                return "redirect:/result?status=3";
            }
            fileService.deleteFile(fileId);

            //status 0: success
            return "redirect:/result?status=0";
        } catch (Exception e) {
            return "redirect:/result?status=1";
        }

    }
}
