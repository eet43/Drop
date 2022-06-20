package com.drop.dropshop.droneCompany.util;

import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.OpenApiResponseErrorException;
import com.drop.dropshop.droneCompany.security.ApiKey;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Slf4j
@Component
public class NaverGeocode {
    private ApiKey apiKey;

    public NaverGeocode(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject geocode(String address) throws UnsupportedEncodingException, OpenApiResponseErrorException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + URLEncoder.encode(address, "UTF-8");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("X-NCP-APIGW-API-KEY-ID", apiKey.getNaverGeoApiKey())
                .addHeader("X-NCP-APIGW-API-KEY", apiKey.getNaverGeoApiSecretKey())
                .build();

        JSONObject responseObject = null;
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            responseObject = new JSONObject(responseBody);
        } catch (IOException e) {
            log.error("Json Object IOException", e);
        }

        if (!responseObject.get("status").equals("OK")) {
            throw new OpenApiResponseErrorException("네이버 geocode API 에러", ErrorCode.BAD_REQUEST, "주소 -> 좌표 변화에 실패하였습니다.", responseObject.get("status"), responseObject.get("errorMessage"));
        } else {
            JSONArray jsonArray = responseObject.getJSONArray("addresses");
            if(jsonArray.length() == 0) {
                throw new OpenApiResponseErrorException("네이버 geocode API 에러", ErrorCode.BAD_REQUEST, "주소 -> 좌표 변화에 실패하였습니다.", "INVALID ADDRESS", "받아온 주소 정보가 없습니다.");
            }
            JSONObject responseAddress = jsonArray.getJSONObject(0);

            JSONObject response = new JSONObject();
            response.put("x", responseAddress.get("x"));
            response.put("y", responseAddress.get("y"));
            return response;
        }
    }
}
