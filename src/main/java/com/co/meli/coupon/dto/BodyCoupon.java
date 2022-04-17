package com.co.meli.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "Lista de Items favoritos", required = true)
    @JsonProperty("item_ids")
    @NotNull(message = "La Lista de Items no puede ser vacia")
    @NotEmpty(message = "La Lista de Items no puede ser vacia")
    @SerializedName("item_ids")
    private List<String> itemIDS;

    @ApiModelProperty(value = "Valor del cupon", required = true)
    @NotNull(message = "El Valor del cupon no puede ser vacio")
    private Float amount;
}
