package com.co.meli.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
public class BodyCoupon {
  @JsonProperty("item_ids")
  @NotNull(message = "La Lista de Items no puede ser vacia")
  @NotEmpty(message = "La Lista de Items no puede ser vacia")
  private List<String> itemIDS;

  @NotNull(message = "El Valor del cupon no puede ser vacio")
  private Float amount;
}
