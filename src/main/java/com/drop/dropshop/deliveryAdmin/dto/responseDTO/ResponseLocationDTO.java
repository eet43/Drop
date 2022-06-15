package com.drop.dropshop.deliveryAdmin.dto.responseDTO;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ResponseLocationDTO {
    private String area1;
    private String area2;
    private String area3;

    public ResponseLocationDTO(ArrayList<String> address){
        this.area1 = address.get(0);
        this.area2 = address.get(1);
        this.area3 = address.get(2);
    }
}
