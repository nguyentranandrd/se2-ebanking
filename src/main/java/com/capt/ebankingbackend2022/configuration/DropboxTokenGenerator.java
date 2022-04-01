package com.capt.ebankingbackend2022.configuration;

import com.capt.ebankingbackend2022.utils.Const;
import com.dropbox.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// run this one to get initial short live access token and refresh token
//@Configuration
public class DropboxTokenGenerator {
    //    @Bean
    public void getToken() {
        try {
            DbxRequestConfig config = DbxRequestConfig.newBuilder(Const.DROP_BOX_CONTEXT_PATH).build();

            String dropboxAppKey = "9n9ge8eoxsnxt30"; // Put your Dropbox App Key here
            String dropboxAppSecret = "0a1881rew9jhns9"; // Put your Dropbox App Secret here

            DbxAppInfo appInfo = new DbxAppInfo(dropboxAppKey, dropboxAppSecret);
            DbxAuthFinish authFinish = scopeAuthorize(appInfo, config);
            System.out.println("Authorization complete.");
            System.out.println("- User ID: " + authFinish.getUserId());
            System.out.println("- Account ID: " + authFinish.getAccountId());
            System.out.println("- Access Token: " + authFinish.getAccessToken());
            System.out.println("- Expires At: " + authFinish.getExpiresAt());
            System.out.println("- Refresh Token: " + authFinish.getRefreshToken());
            System.out.println("- Scope: " + authFinish.getScope());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static DbxAuthFinish scopeAuthorize(DbxAppInfo appInfo, DbxRequestConfig requestConfig) throws IOException, DbxException {
        DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo);

        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
                .withNoRedirect()
                .withTokenAccessType(TokenAccessType.OFFLINE)
                // Define here the scopes that you need in your application - and the app created in Dropbox has
                .withScope(Arrays.asList("files.content.read", "files.content.write"))
                .withIncludeGrantedScopes(IncludeGrantedScopes.USER)
                .build();

        String authorizeUrl = webAuth.authorize(webAuthRequest);
        System.out.println(authorizeUrl);
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (code == null) {
            System.exit(1);
        }
        code = code.trim();

        DbxAuthFinish authFinish = webAuth.finishFromCode("U4ulO0o5JbsAAAAAAAABQTMRVpLzcXvGc2DbB7weDlk");

        System.out.println("Successfully requested scope " + authFinish.getScope());
        return authFinish;
    }
}
