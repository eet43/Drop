package com.drop.dropshop.droneCompany.util;

import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.exception.OpenApiResponseErrorException;
import com.drop.dropshop.droneCompany.security.ApiKey;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OpenWeatherMap {

    private ApiKey apiKey;

    public OpenWeatherMap(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public double getWeather(JSONObject coordinate) throws IOException, OpenApiResponseErrorException {
        int x = (int) Double.parseDouble(coordinate.getString("x"));
        System.out.println(x);
        int y = (int) Double.parseDouble(coordinate.getString("y"));
        System.out.println(y);

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + y + "&lon=" + x + "&appid=" + apiKey.getOpenWeatherMapApiKey();
        System.out.println(url);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject responseObject = new JSONObject(responseBody);
        if(responseObject.getInt("cod") != 200){
            throw new OpenApiResponseErrorException("openweathermap API 에러", ErrorCode.BAD_REQUEST, "날씨 정보를 가져올 수 없습니다.", responseObject.get("cod"), responseObject.get("message"));
        }
        double weather = (double) responseObject.getJSONObject("main").get("temp");
        weather = weather - 273.15; // 절대 온도 계산
        return weather;
    }
}
