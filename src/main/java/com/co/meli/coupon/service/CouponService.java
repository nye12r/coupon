package com.co.meli.coupon.service;

import com.co.meli.coupon.dto.BodyCoupon;
import org.springframework.stereotype.Service;


public interface CouponService {
    public BodyCoupon getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon);
}
