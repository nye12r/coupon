package com.co.meli.coupon.controller;

import com.co.meli.coupon.dto.BodyCoupon;
import com.co.meli.coupon.service.CouponService;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import  static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
class CouponControllerTest {
   @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;
    @Value("classpath:bodyResponse_200.json")
    private Resource bodyResponseOk;
    @Value("classpath:bodyRequest_200.json")
    private Resource bodyResquestOk;

    protected Gson gson = new Gson();



    @Test
    void getListItemsCoupon() throws Exception {
        final String bodyResponse = IOUtils.toString(this.bodyResponseOk.getInputStream(), StandardCharsets.UTF_8);
        BodyCoupon bodyCoupon= this.gson.fromJson(bodyResponse,BodyCoupon.class);
        final String bodyResquest = IOUtils.toString(this.bodyResquestOk.getInputStream(), StandardCharsets.UTF_8);
        BodyCoupon bodyCouponRequest= this.gson.fromJson(bodyResquest,BodyCoupon.class);
        ResponseEntity<BodyCoupon> response = new ResponseEntity<>(bodyCoupon, HttpStatus.OK);
        when(this.couponService.getListItemsPurchasedWithCoupon(bodyCouponRequest)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/coupon/")
                        .content(bodyResquest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}