package com.udacity.jwdnd.course1.cloudstorage.controller.files;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Controller
@RequestMapping("/file/download")
public class DownloadController {
    private FileService fileService;

    public DownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileid") int fileId) throws SQLException, IOException {
        File file = fileService.getFileById(fileId);

        // @// TODO: validate that the file being accessed is uploaded by the same currently logged in user.

        ByteArrayResource fileBytes = new ByteArrayResource(file.getFileData().getBinaryStream().readAllBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .headers(headers)
                .body(new InputStreamResource(fileBytes.getInputStream()));
    }
}
