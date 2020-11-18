package com.yjr.ordering_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjr.ordering_system.entity.ProductInfo;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yjr
 * @since 2020/11/08 20:55
 */
public class HttpClientTest {

    public static void main(String[] args) throws IOException {
        // testGet();
        // testPost();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setCategoryType(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productInfo);
        System.out.println(json);
    }

    private static void testPost() throws IOException {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setCategoryType(1);


        String bodyData = new ObjectMapper().writeValueAsString(productInfo);
        RequestBody requestBody = RequestBody.create(bodyData.getBytes());
        Request request = new Request.Builder()

                .url("http://localhost:8080/sell/buyer/product/create")
                .header("Content-Type", "application/json")
                .method("POST", requestBody)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void testGet() throws IOException {
        HttpUrl url = HttpUrl.parse("http://www.google.com/search").newBuilder()
                .addQueryParameter("username", "1")
                .addQueryParameter("", "")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        System.out.println(response.body().string());
    }
}
