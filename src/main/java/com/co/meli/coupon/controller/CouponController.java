package com.co.meli.coupon.controller;

import com.co.meli.coupon.dto.BodyCoupon;
import com.co.meli.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class CouponController {
  private final CouponService couponService;

  public CouponController(CouponService couponService) {
    this.couponService = couponService;
  }

  @PostMapping(
          value = "/coupon/",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(
          @RequestBody @Valid BodyCoupon requestCoupon) {
    requestCoupon=couponService.getListItemsPurchasedWithCoupon(requestCoupon);
    return new ResponseEntity<>(requestCoupon, HttpStatus.OK);
  }
}
