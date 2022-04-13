package com.co.meli.coupon.delegate;

import org.springframework.stereotype.Service;

@Service("ItemPriceImpDelegate")
public class ItemPriceImpDelegate implements ItemPriceDelegate {
    @Override
    public double getItemPriceItem(String idItem) {
        return 0;
    }
}
