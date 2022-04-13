package com.co.meli.coupon.service;

import com.co.meli.coupon.dto.BodyCoupon;


public interface CouponService {
    public BodyCoupon getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon);
}
