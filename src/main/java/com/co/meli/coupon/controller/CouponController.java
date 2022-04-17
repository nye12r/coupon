package com.co.meli.coupon.controller;

import com.co.meli.coupon.dto.BodyCoupon;
import com.co.meli.coupon.service.CouponService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@ApiOperation("Coupon API")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @ApiOperation(value = "Obtener Items por cupon")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Items Obtenidos"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Something went wrong")
            })
    @PostMapping(
            value = "/coupon/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodyCoupon> getListItemsPurchasedWithCoupon(
            @RequestBody @Valid BodyCoupon requestCoupon) {
        return couponService.getListItemsPurchasedWithCoupon(requestCoupon);

    }
}
