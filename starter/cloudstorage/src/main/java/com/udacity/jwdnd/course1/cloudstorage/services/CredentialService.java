package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class CredentialService {
    private final EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }


    public void addCredential(Credential credential, int userId) {
        credential.setKey(generateKey());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(),credential.getKey()));
        credential.setUserId(userId);

        credentialMapper.insertCredentials(credential);
    }

    private String generateKey(){
        //set of characters for key generation
        final String CHARACTER_SET="0123456789abcdefghijklmnopqrstuvwxyz";
        //a6 byte for AES key length
        int keyLength = 16;
        SecureRandom random = new SecureRandom();

        //generation of the key randomly
        StringBuffer buff = new StringBuffer(keyLength);
        for(int i=0;i<keyLength;i++) {
            int offset = random.nextInt(CHARACTER_SET.length());
            buff.append(CHARACTER_SET.substring(offset,offset+1));
        }
        return buff.toString();
    }

    public void updateCredential(Credential newCredential){
        //regenerate key
        newCredential.setKey(generateKey());
        newCredential.setPassword(encryptionService.encryptValue(newCredential.getPassword(),newCredential.getKey()));
        credentialMapper.updateCredential(newCredential);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getCredentialList(Integer userId){
        //As per rubrics, credentials return to user unencrypted.
        List<Credential> credList = credentialMapper.getAllCredentials(userId);

        for (Credential cred : credList)
        {
            cred.setPassword(encryptionService.decryptValue(cred.getPassword(),cred.getKey()));
        }

        return credList;
    }
}
