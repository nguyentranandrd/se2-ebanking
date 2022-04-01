package com.capt.ebankingbackend2022.configuration;

import com.capt.ebankingbackend2022.utils.Const;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {
    @Bean
    public DbxClientV2 provideDbxClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder(Const.DROP_BOX_CONTEXT_PATH).build();
        DbxClientV2 client = new DbxClientV2(config, Const.DROP_BOX_ACCESS_TOKEN);
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        assert account != null;
        System.out.println(account.getName().getDisplayName());
        return client;
    }
}
