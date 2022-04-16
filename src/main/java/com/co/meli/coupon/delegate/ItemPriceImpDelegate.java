package com.co.meli.coupon.delegate;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("ItemPriceImpDelegate")
public class ItemPriceImpDelegate implements ItemPriceDelegate {
    private final String URL = "https://api.mercadolibre.com/items/%s";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Float getItemPriceItem(String idItem) {
        ResponseEntity<String> entity;
        String url = String.format(URL, idItem);
        try {
            entity = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(entity.getBody());
            return json.getFloat("price");
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
