package com.co.meli.coupon.service;

import com.co.meli.coupon.delegate.ItemPriceImpDelegate;
import com.co.meli.coupon.dto.BodyCoupon;
import org.springframework.stereotype.Service;

@Service("CouponServiceImp")
public class CouponServiceImp implements CouponService {
    private final ItemPriceImpDelegate itemPriceImpDelegate;

    public CouponServiceImp(ItemPriceImpDelegate itemPriceImpDelegate) {
        this.itemPriceImpDelegate = itemPriceImpDelegate;
    }

    @Override
    public BodyCoupon getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon) {
        Double pr= requestCoupon.getAmount()*100;
        requestCoupon.setAmount(pr);
        return (requestCoupon);
    }
}
