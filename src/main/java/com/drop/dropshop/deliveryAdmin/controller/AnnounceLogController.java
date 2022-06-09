package com.drop.dropshop.deliveryAdmin.controller;

import com.drop.dropshop.deliveryAdmin.dto.AnnounceLogDto;
import com.drop.dropshop.deliveryAdmin.service.AnnounceLogService;
import com.drop.dropshop.deliveryAdmin.util.ReverseGeocode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnounceLogController {
    private final AnnounceLogService announceLogService;

    @Autowired
    public AnnounceLogController(AnnounceLogService announceLogService){this.announceLogService = announceLogService;}
    @PostMapping("/api/announce")
    public String createAnnounceLog(@RequestBody AnnounceLogDto requestDto){
        ReverseGeocode reverseGeocode = new ReverseGeocode();
        announceLogService.createAnnounceLog(requestDto);
        String response = reverseGeocode.ReverseGeo();
        return reverseGeocode.fromStringToJson(response).toString();
    }
}
