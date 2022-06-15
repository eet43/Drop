package com.drop.dropshop.deliveryAdmin.util;

//import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseLocationDTO;
import com.drop.dropshop.deliveryAdmin.dto.responseDTO.ResponseLocationDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


public class ReverseGeocode {
    public ResponseLocationDTO ReverseGeo(String lonLat) {
        /*
        - String 형태의 위도경도(lon, lat)를 받아, 
        - 네이버의 ReversCode API를 통해 실 주소가 담긴 meta data를 응답받는다.
        - 응답받은 메타 데이터를 아래 작성한 parseLocation()에 전달해, 
        - 실 주소값이 파싱되어 저장된 ResponseLocationDTO 객체를 받아

        - Return: 실 주소값이 담긴 ResponseLocationDTO
                      + 를 AnnounceLogService.class에 findAnnounceLog()함수로 전달한다
         */
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-NCP-APIGW-API-KEY-ID", "ycw1hip9vb");
        headers.add("X-NCP-APIGW-API-KEY", "7f0KGNmZoSlFeE9ierahHwKmP4ci1xu9pSAAVAlT");
        String body = "";
        
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords="+lonLat+"&orders=addr&output=json", HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();

        return parseLocation(response); // ReverseGeoCode에서 응답해준 metadata 중에서 주소값을 파싱한 뒤 ResponseLocationDTO에 담아 리턴   
    }

    public ResponseLocationDTO parseLocation(String response){
        JSONObject responseObject = new JSONObject(response);
        JSONObject basejObj = responseObject.getJSONArray("results").getJSONObject(0).getJSONObject("region"); // 지역 주소값까지 파싱

        ArrayList<String> address = new ArrayList<>();
        for(int i=1; i<4; i++){                  // 각 지역단위를 돌며 이름만 파싱, address 배열에 push
            JSONObject areajObj = (JSONObject)basejObj.get("area"+i);
            address.add(areajObj.get("name").toString());
        }
        return new ResponseLocationDTO(address); // 만들어진 주소 리스트를 ResponseLocationDTO에 적재후 리턴
    }
}
