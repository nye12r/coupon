package com.co.meli.coupon.delegate;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("ItemPriceImpDelegate")
public class ItemPriceImpDelegate implements ItemPriceDelegate {
    private final RestTemplate restTemplate;
    private final String URL = "https://api.mercadolibre.com/items/%s";

    public ItemPriceImpDelegate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Float getItemPriceItem(String idItem) {
        ResponseEntity<String> entity;
        String url = String.format(URL, idItem);
        try {
            entity = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
        JSONObject json = new JSONObject(entity.getBody());
        return json.getFloat("price");
    }
}
