package com.co.meli.coupon.service;

import com.co.meli.coupon.delegate.ItemPriceImpDelegate;
import com.co.meli.coupon.dto.BodyCoupon;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class CouponServiceImpTest {
    protected Gson gson = new Gson();
    @InjectMocks
    CouponServiceImp couponServiceImp;
    @Mock
    private ItemPriceImpDelegate itemPriceImpDelegate;
    @Value("classpath:bodyRequestService_200.json")
    private Resource bodyResquestOk;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getListItemsPurchasedWithCoupon_ok() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("MLA932320637");
        BodyCoupon bodyCouponRequest = BodyCoupon.builder().itemIDS(list).amount(300.0F).build();
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320637")).thenReturn(300.0F);
        ResponseEntity<BodyCoupon> listItemsPurchasedWithCoupon = this.couponServiceImp.getListItemsPurchasedWithCoupon(bodyCouponRequest);
        Assert.assertNotNull(listItemsPurchasedWithCoupon);
        Assert.assertEquals(listItemsPurchasedWithCoupon.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getListItemsPurchasedWithCoupon_not_found() {
        List<String> list = new ArrayList<>();
        list.add("DFREÑDFLÑFSDK");
        BodyCoupon bodyCouponRequest = BodyCoupon.builder().itemIDS(list).amount(300.0F).build();
        when(itemPriceImpDelegate.getItemPriceItem("DFREÑDFLÑFSDK")).thenReturn(null);
        Throwable exception = Assert.assertThrows(ResponseStatusException.class, () -> {
            ResponseEntity<BodyCoupon> response = this.couponServiceImp.getListItemsPurchasedWithCoupon(bodyCouponRequest);
        });
    }

    @Test
    void getListItemsPurchasedWithCoupon_ok_2() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("MLA932320637");
        list.add("MLA932320634");
        BodyCoupon bodyCouponRequest = BodyCoupon.builder().itemIDS(list).amount(11627F).build();
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320637")).thenReturn(300.0F);
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320634")).thenReturn(460F);
        ResponseEntity<BodyCoupon> listItemsPurchasedWithCoupon = this.couponServiceImp.getListItemsPurchasedWithCoupon(bodyCouponRequest);
        Assert.assertNotNull(listItemsPurchasedWithCoupon);
        Assert.assertEquals(listItemsPurchasedWithCoupon.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getListItemsPurchasedWithCoupon_ok_3() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("MLA932320637");
        list.add("MLA932320634");
        BodyCoupon bodyCouponRequest = BodyCoupon.builder().itemIDS(list).amount(11627F).build();
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320637")).thenReturn(300.0F);
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320634")).thenReturn(11627F);
        ResponseEntity<BodyCoupon> listItemsPurchasedWithCoupon = this.couponServiceImp.getListItemsPurchasedWithCoupon(bodyCouponRequest);
        Assert.assertNotNull(listItemsPurchasedWithCoupon);
        Assert.assertEquals(listItemsPurchasedWithCoupon.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getListItemsPurchasedWithCoupon_ok_4() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("MLA932320637");
        list.add("MLA932320634");
        BodyCoupon bodyCouponRequest = BodyCoupon.builder().itemIDS(list).amount(11627F).build();
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320637")).thenReturn(500000.0F);
        when(itemPriceImpDelegate.getItemPriceItem("MLA932320634")).thenReturn(11627F);
        ResponseEntity<BodyCoupon> listItemsPurchasedWithCoupon = this.couponServiceImp.getListItemsPurchasedWithCoupon(bodyCouponRequest);
        Assert.assertNotNull(listItemsPurchasedWithCoupon);
        Assert.assertEquals(listItemsPurchasedWithCoupon.getStatusCode(), HttpStatus.OK);
    }


}