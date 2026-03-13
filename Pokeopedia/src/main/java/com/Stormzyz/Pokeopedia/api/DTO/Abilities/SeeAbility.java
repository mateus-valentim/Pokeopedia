package com.Stormzyz.Pokeopedia.api.DTO.Abilities;

import com.Stormzyz.Pokeopedia.api.Models.Ability;
import com.Stormzyz.Pokeopedia.api.Models.TypeAbilityEffectiveness;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SeeAbility {

    private Long id;
    private String abilityName;
    private String abilityDescription;
    private List<DefendingAbilityDTO> defendingAbilities = new ArrayList<>();

    public SeeAbility(Ability ability) {
        this.id = ability.getId();
        this.abilityName = ability.getAbilityName();
        this.abilityDescription = ability.getAbilityDescription();
        this.defendingAbilities = ability.getDefendingAbilityEffectiveness() == null ?
                List.of() :
                ability.getDefendingAbilityEffectiveness().stream().map(DefendingAbilityDTO::new).toList();
    }


    @Getter
    @Setter
    public static class DefendingAbilityDTO{
        private String typeName;
        private BigDecimal multiplier;

        public DefendingAbilityDTO(TypeAbilityEffectiveness typeAbilityEffectiveness){
            this.typeName = typeAbilityEffectiveness.getAttackingType().getTypeName();
            this.multiplier = typeAbilityEffectiveness.getMultiplier();
        }

    }
}
