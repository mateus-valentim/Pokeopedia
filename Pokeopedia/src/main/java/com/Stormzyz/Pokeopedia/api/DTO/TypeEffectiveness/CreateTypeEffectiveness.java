package com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTypeEffectiveness {
    @NotNull(message = "The attacking type is required")
    private Long attackingId;

    @NotNull(message = "The defending type is required")
    private Long defendingId;

    @NotNull(message = "The attack multiplier is required")
    private BigDecimal multiplier;
}
