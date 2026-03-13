package com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTypeAbilityEffectiveness {

    @NotNull(message = "É preciso de um tipo para a efetividade")
    private Long typeId;

    @NotNull(message = "É preciso de uma abilidade para a efetividade")
    private Long abilityId;

    @NotNull(message = "É preciso de um multiplicador de efetividade")
    private BigDecimal multiplier;
}
