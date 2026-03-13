package com.Stormzyz.Pokeopedia.api.DTO.TypeAbilityEffectiveness;

import com.Stormzyz.Pokeopedia.api.Models.TypeAbilityEffectiveness;
import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SeeTypeAbilityEffectiveness {

    private Long id;
    private String typeName;
    private String abilityName;
    private BigDecimal multiplier;

    public SeeTypeAbilityEffectiveness(TypeAbilityEffectiveness typeAbilityEffectiveness) {
        this.id = typeAbilityEffectiveness.getId();
        this.typeName = typeAbilityEffectiveness.getAttackingType().getTypeName();
        this.abilityName = typeAbilityEffectiveness.getDefendingAbility().getAbilityName();
        this.multiplier = typeAbilityEffectiveness.getMultiplier();
    }




}
