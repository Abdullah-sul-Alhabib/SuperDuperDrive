package com.udacity.jwdnd.course1.cloudstorage.controller.files;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/file/download")
public class DownloadController {

    private final UserService userService;
    private final FileService fileService;

    public DownloadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileid") int fileId) throws SQLException, IOException {
        File file = fileService.getFileById(fileId);
        HttpHeaders headers = new HttpHeaders();
        //ensure the file is uploaded by the requester
        User currentUser = userService.getUser(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());

        if (currentUser.getUserId() != file.getUserId()) {
            headers.add("Location", "/result?status=3");
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        }

        ByteArrayResource fileBytes = new ByteArrayResource(file.getFileData().getBinaryStream().readAllBytes());


        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .headers(headers)
                .body(new InputStreamResource(fileBytes.getInputStream()));
    }
}
