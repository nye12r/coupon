package com.co.meli.coupon.controller;

import com.co.meli.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CouponController.class)
class CouponControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;

    CouponControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getListItemsCoupon(){
        
    }
}