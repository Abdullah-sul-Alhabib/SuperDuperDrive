package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/home/download")
public class DownloadController {
    private FileService fileService;

    public DownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody Resource downloadFile(@RequestParam("fileid") int fileId) throws SQLException, IOException {

        return new ByteArrayResource(fileService.getFileById(fileId).getBinaryStream().readAllBytes());

    }
}
