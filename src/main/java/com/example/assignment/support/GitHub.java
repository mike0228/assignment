package com.example.assignment.support;

import com.alibaba.fastjson.JSON;
import com.example.assignment.dto.AccessToken;
import com.example.assignment.dto.Guser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHub {
    public String getAccessToken(AccessToken accesstoken){
        MediaType type = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(type, JSON.toJSONString(accesstoken));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String token = string.split("&")[0].split("=")[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
    public Guser gtuser(String accesstoken)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accesstoken)
                .build();
        try  {
            Response response = client.newCall(request).execute();
            String string =response.body().string();
            System.out.println(string);
            Guser guser=JSON.parseObject(string, Guser.class);
            return guser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
