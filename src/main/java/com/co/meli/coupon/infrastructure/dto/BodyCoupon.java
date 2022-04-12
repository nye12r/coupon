package com.co.meli.coupon.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BodyCoupon {
  @JsonProperty("item_ids")
  private String[] itemIDS;

  private Float amount;
}
