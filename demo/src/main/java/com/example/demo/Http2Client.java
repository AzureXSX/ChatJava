package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Protocol;
import java.util.Arrays;


public class Http2Client {
    // public static void main(String[] args) throws Exception {
    //     OkHttpClient client = new OkHttpClient.Builder()
    //             .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
    //             .build();

    //     Request request = new Request.Builder()
    //             .url("https://http2.akamai.com/demo")
    //             .build();

    //     try (Response response = client.newCall(request).execute()) {
    //         System.out.println(response.protocol());
    //         System.out.println(response.body().string());
    //     }
    // }
}
