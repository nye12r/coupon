package com.co.meli.coupon.service;

import com.co.meli.coupon.delegate.ItemPriceImpDelegate;
import com.co.meli.coupon.dto.BodyCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("CouponServiceImp")
public class CouponServiceImp implements CouponService {
    private final ItemPriceImpDelegate itemPriceImpDelegate;

    public CouponServiceImp(ItemPriceImpDelegate itemPriceImpDelegate) {
        this.itemPriceImpDelegate = itemPriceImpDelegate;
    }

    @Override
    public BodyCoupon getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon) {
        List<String> itemIDS = requestCoupon.getItemIDS();
        for (String itemID : itemIDS) {
            Double itemPriceItem = itemPriceImpDelegate.getItemPriceItem(itemID);
        }

        Double pr = requestCoupon.getAmount() * 100;
        requestCoupon.setAmount(pr);
        return (requestCoupon);
    }
}
