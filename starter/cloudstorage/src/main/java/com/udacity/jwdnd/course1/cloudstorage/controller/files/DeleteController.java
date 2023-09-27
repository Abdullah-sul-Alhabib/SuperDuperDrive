package com.udacity.jwdnd.course1.cloudstorage.controller.files;


import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/file/delete")
public class DeleteController {
    private FileService fileService;

    public DeleteController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String deleteController(@RequestParam("fileid") int fileId){
        fileService.deleteFile(fileId);

        //status 0: success
        return "redirect:/result?status=0";

    }
}
