package com.co.meli.coupon.service;

import com.co.meli.coupon.delegate.ItemPriceImpDelegate;
import com.co.meli.coupon.dto.BodyCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service("CouponServiceImp")
public class CouponServiceImp implements CouponService {
    private final ItemPriceImpDelegate itemPriceImpDelegate;
    private final String ITEMS_NOT_FOUND = "Los items de la lista enviada, no se encontraron o su valor supera el valor del coupon";

    public CouponServiceImp(ItemPriceImpDelegate itemPriceImpDelegate) {
        this.itemPriceImpDelegate = itemPriceImpDelegate;
    }

    private static Map<String, Float> sortedMap(final Map<String, Float> items) {
        return items.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Float>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon) {
        Map<String, Float> items = new HashMap<>();
        List<String> itemIDS = requestCoupon.getItemIDS();
        for (String itemID : itemIDS) {
            Float itemPrice = itemPriceImpDelegate.getItemPriceItem(itemID);
            if (itemPrice != null) {
                if (itemPrice <= requestCoupon.getAmount())
                    items.put(itemID, itemPrice);
            }
        }
        log.info(String.valueOf(items));
        if (items.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, ITEMS_NOT_FOUND);
        }
        List<String> item = calculate(items, requestCoupon.getAmount());
        Map<String, Float> itemsCopy = new HashMap<>(items);
        Map<Float, List<String>> aux = new HashMap<>();
        items.forEach((key, value) -> {
            List<String> item1 = calculate(itemsCopy, requestCoupon.getAmount());
            final Float[] total1 = {(float) 0};
            item.forEach(y -> {
                total1[0] = total1[0] + items.get(y);
            });
            aux.put(total1[0], item1);
            itemsCopy.remove(key);
        });
        Float maxValueInMap = (Collections.max(aux.keySet()));
        List<String> item1 = aux.get(maxValueInMap);
        item1.forEach(System.out::println);
        final float[] total = {0};
        item.forEach(x -> {
            total[0] = total[0] + items.get(x);
        });
        return new ResponseEntity<BodyCoupon>(BodyCoupon.builder().amount(total[0]).itemIDS(item).build(), HttpStatus.OK);
    }

    private List<String> calculate(Map<String, Float> items, Float amount) {
        Map<String, Float> itemProducts = sortedMap(items);
        List<String> itemFilter = new ArrayList<>();
        final int[] valorAux = {0};
        itemProducts.entrySet().stream().filter(x -> {
            int v = (int) (x.getValue() + valorAux[0]);
            if (v <= amount) {
                valorAux[0] = v;
                itemFilter.add(x.getKey());
                return true;
            }
            return false;
        }).forEach(System.out::println);
        itemProducts.entrySet().forEach(System.out::println);
        return itemFilter;
    }

}
