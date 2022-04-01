package com.capt.ebankingbackend2022.service.impl;

import com.capt.ebankingbackend2022.service.DropboxService;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.GetMetadataErrorException;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DropboxServiceImpl extends BaseServiceImpl implements DropboxService {
    @Autowired
    private DbxClientV2 client;

    @Override
    public String uploadFile(MultipartFile file, String filePath) throws IOException, DbxException {
        if (isExist(filePath)) {
            deleteFile(filePath);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
        Metadata uploadMeta = client.files().uploadBuilder(filePath).uploadAndFinish(inputStream);
        System.out.println(uploadMeta.toString());

        return getDownloadUrl(filePath);
    }

    @Override
    public String getDownloadUrl(String path) throws DbxException {
        SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(path);
        System.out.println(sharedLinkMetadata.getUrl());
        return sharedLinkMetadata.getUrl();
    }

    @Override
    public void deleteFile(String path) throws DbxException {
        client.files().deleteV2(path);
    }

    @Override
    public boolean isExist(String dropboxPath) throws DbxException {
        try {
            client.files().getMetadata(dropboxPath);
            return true;
        } catch (GetMetadataErrorException e) {
            if (e.getMessage().contains("{\".tag\":\"path\",\"path\":\"not_found\"}")) {
                return false;
            } else {
                throw e;
            }
        }
    }

}
