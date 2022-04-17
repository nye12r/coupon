package com.co.meli.coupon.delegate;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ItemPriceImpDelegateTest {


    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ItemPriceImpDelegate itemPriceImpDelegate = new ItemPriceImpDelegate();


    @Test
    public void getItemPriceItem() {
        String bodyResponse = "{\"price\": 460}";
        when(restTemplate.getForEntity("https://api.mercadolibre.com/items/MLA932320637", String.class))
                .thenReturn(new ResponseEntity(bodyResponse, HttpStatus.OK));
        Float itemPrice = itemPriceImpDelegate.getItemPriceItem("MLA932320637");
        Assert.assertNotNull(itemPrice);
        Assertions.assertEquals(itemPrice, 460F);
    }

    @Test
    public void getItemPriceItem_not_found() {
        when(restTemplate.getForEntity("https://api.mercadolibre.com/items/MLA932320637", String.class))
                .thenThrow(HttpClientErrorException.NotFound.class);
        Float price = itemPriceImpDelegate.getItemPriceItem("MLA932320637");
        Assert.assertNull(price);

    }
}