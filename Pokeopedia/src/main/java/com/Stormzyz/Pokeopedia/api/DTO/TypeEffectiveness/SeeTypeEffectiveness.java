package com.Stormzyz.Pokeopedia.api.DTO.TypeEffectiveness;


import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SeeTypeEffectiveness {

    private String attackingTypeName;
    private String defendingTypeName;
    private BigDecimal multiplier;

    public SeeTypeEffectiveness(TypeEffectiveness typeEffectiveness){
        this.attackingTypeName = typeEffectiveness.getAttackingType().getTypeName();
        this.defendingTypeName = typeEffectiveness.getDefendingType().getTypeName();
        this.multiplier = typeEffectiveness.getMultiplier();
    }



}
