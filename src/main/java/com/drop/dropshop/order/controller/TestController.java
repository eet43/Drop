package com.drop.dropshop.order.controller;

import com.drop.dropshop.order.entity.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"TEST API"})
@RequiredArgsConstructor
public class TestController {

        @PostMapping(value = "/api/orders")
        @ApiOperation(value = "테스트")
        public int test() {
            return 0;
        }
    }
