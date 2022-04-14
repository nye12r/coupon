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

    @Override
    public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(BodyCoupon requestCoupon) {
        Map<String, Float> items = new HashMap<>( );
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
            aux.put(total1[0],item1);
            itemsCopy.remove(key);
        });
        Float maxValueInMap=(Collections.max(aux.keySet()));
        List<String> item1 =aux.get(maxValueInMap);
        System.out.println("VALOR TOTAL");
        System.out.println(maxValueInMap);
        System.out.println("ITEMS NUEVA LISTA");
        item1.forEach(System.out::println);
        System.out.println("LISTA DE ITEMS");
        System.out.println(item);
        final float[] total = {0};
        item.forEach(x -> {
            total[0] = total[0] + items.get(x);
        });
        return new ResponseEntity<BodyCoupon>(BodyCoupon.builder().amount(total[0]).itemIDS(item).build(), HttpStatus.OK);
    }

    private List<String> calculate(Map<String, Float> items, Float amount) {
        List<String> itemFilter = new ArrayList<>();
        final float[] valorAux = {0};
        items.entrySet().stream().filter(x -> {
            float v = x.getValue() + valorAux[0];
            if (v <= amount) {
                System.out.println("Numero del item: " + x.getKey());
                System.out.println("Valor item: " + x.getValue());
                System.out.println("Valor acumulador: " + valorAux[0]);
                System.out.println("Valor cupon: " + amount);
                System.out.println("Valor Resta: " + (amount-valorAux[0]));
                valorAux[0] = v;
                itemFilter.add(x.getKey());
                return true;
            }
            return false;
        }).forEach(System.out::println);
        //return new ArrayList<>(items.keySet());
        items.entrySet().forEach(System.out::println);
        return itemFilter;
    }

}
