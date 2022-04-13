package com.co.meli.coupon.controller;

import com.co.meli.coupon.dto.BodyCoupon;
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

  @PostMapping(
          value = "/coupon/",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(
          @RequestBody @Valid BodyCoupon requestCoupon) {

    return new ResponseEntity<>(requestCoupon, HttpStatus.OK);
  }
}
