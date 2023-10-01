package com.udacity.jwdnd.course1.cloudstorage.controller.credentials;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credential/delete")
public class DeleteCredential {


    private final CredentialService credentialService;

    public DeleteCredential(CredentialMapper credentialMapper, CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    public String deleteCredential(@RequestParam("credid") Integer credentialId){
        try{
            credentialService.deleteCredential(credentialId);
            return "redirect:/result?status=0";
        }catch (Error e)
        {
            return "redirect:/result?status=6";
        }
    }

}
