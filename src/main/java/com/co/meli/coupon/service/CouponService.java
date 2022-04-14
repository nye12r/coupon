package com.co.meli.coupon.service;

import com.co.meli.coupon.dto.BodyCoupon;
import org.springframework.http.ResponseEntity;


public interface CouponService {
    public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon);
}
