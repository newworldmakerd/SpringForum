package com.example.forum_test.provider;

import com.alibaba.fastjson.JSON;
import com.example.forum_test.dto.AccessTokenDTO;
import com.example.forum_test.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String tokenString = split[0];
            String[] split1 = tokenString.split("=");
            String token = split1[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization","token "+accessToken)
                .url("https://api.github.com/user")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDTO githubUser = JSON.parseObject(string, GithubUserDTO.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
