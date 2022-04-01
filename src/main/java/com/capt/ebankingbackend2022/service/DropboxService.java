package com.capt.ebankingbackend2022.service;

import com.dropbox.core.DbxException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DropboxService {
    String uploadFile(MultipartFile file, String filePath) throws IOException, DbxException;

    String getDownloadUrl(String path) throws DbxException;

    void deleteFile(String path) throws DbxException;

    boolean isExist(String dropboxPath) throws DbxException;
}
