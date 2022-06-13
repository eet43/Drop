package com.drop.dropshop.droneCompany.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PagingResponseDetails {
    private Date timestamp;
    private Object data;
    private Long total;
    private int currentPage;
    private int currentPageSize;
    private int httpStatus;
    private String path;
}
