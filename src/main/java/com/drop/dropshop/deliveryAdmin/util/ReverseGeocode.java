package com.drop.dropshop.deliveryAdmin.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;



public class ReverseGeocode {
    public String ReverseGeo() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-NCP-APIGW-API-KEY-ID", "ycw1hip9vb");
        headers.add("X-NCP-APIGW-API-KEY", "7f0KGNmZoSlFeE9ierahHwKmP4ci1xu9pSAAVAlT");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=128.12345,37.98776&orders=addr&output=json", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
//        System.out.println("Response status: " + status);
//        System.out.println(response);

        return response;
    }

    public JSONArray fromStringToJson(String result){
        JSONObject rjson = new JSONObject(result);
        JSONArray results = rjson.getJSONArray("results");

        return results;
    }
}