package com.capt.ebankingbackend2022.configuration;

import com.capt.ebankingbackend2022.utils.Const;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {

    @Value("${dropbox.access.token}")
    private String dropboxAccessToken;

    @Value("${dropbox.refresh.token}")
    private String dropboxRefreshToken;

    @Value("${dropbox.app.key}")
    private String dropboxAppKey;

    @Value("${dropbox.app.secret}")
    private String dropboxAppSecret;

    @Value("${dropbox.access.lltoken}")
    private String llToken;


    @Bean
    public DbxClientV2 getClient() throws DbxException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
        DbxClientV2 client;
        try {
            DbxCredential credentials = new DbxCredential(dropboxAccessToken, -1L, dropboxRefreshToken, dropboxAppKey, dropboxAppSecret);
            client = new DbxClientV2(config, credentials);
            FullAccount account;
            account = client.users().getCurrentAccount();
            assert account != null;
            System.out.println(account.getName().getDisplayName());
            return client;
        } catch (DbxException e) {
            System.out.println("Short live access token not work. Using long live instead. Error: " + e.getMessage());
            client = new DbxClientV2(config, llToken);
            FullAccount account = client.users().getCurrentAccount();
            assert account != null;
            System.out.println(account.getName().getDisplayName());
            return client;
        }
    }
}
