package com.co.meli.coupon.service;

import com.co.meli.coupon.delegate.ItemPriceImpDelegate;
import com.co.meli.coupon.dto.BodyCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service("CouponServiceImp")
public class CouponServiceImp implements CouponService {
    private final ItemPriceImpDelegate itemPriceImpDelegate;

    public CouponServiceImp(ItemPriceImpDelegate itemPriceImpDelegate) {
        this.itemPriceImpDelegate = itemPriceImpDelegate;
    }

    @Override
    public BodyCoupon getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon) {
        Map<String, Float> items = new TreeMap<>();
        List<String> itemIDS = requestCoupon.getItemIDS();
        for (String itemID : itemIDS) {
            Float itemPrice = itemPriceImpDelegate.getItemPriceItem(itemID);
            if (itemPrice !=null){
                if(itemPrice<requestCoupon.getAmount())
                items.put(itemID,itemPrice);
            }
        }
        log.info(String.valueOf(items));
        return (requestCoupon);
    }
}
