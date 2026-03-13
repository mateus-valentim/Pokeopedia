package com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes;

import com.Stormzyz.Pokeopedia.api.Models.PokemonType;
import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SeePokemonType {
    private long id;
    private String typeName;
    private String colorhex;
    private List<AttackingTypeDTO> atackingEffectiveness = new ArrayList<>();
    private List<DefendingTypeDTO> defendingEffectiveness = new ArrayList<>();



    public SeePokemonType(PokemonType pokemonType) {
        this.id = pokemonType.getId();
        this.typeName = pokemonType.getTypeName();
        this.colorhex = pokemonType.getColorhex();
        this.atackingEffectiveness = pokemonType.getAtackingEffectiveness() == null
                ? List.of()
                : pokemonType.getAtackingEffectiveness()
                .stream()
                .map(AttackingTypeDTO::new)
                .toList();
        this.defendingEffectiveness = pokemonType.getDefendingEffectiveness() == null
                ? List.of()
                : pokemonType.getDefendingEffectiveness()
                .stream()
                .map(DefendingTypeDTO::new)
                .toList();
    }

    @Getter
    @Setter
    public static class AttackingTypeDTO{
        private String typeName;
        private BigDecimal multiplier;

        public AttackingTypeDTO(TypeEffectiveness typeEffectiveness) {
            this.multiplier = typeEffectiveness.getMultiplier();
            this.typeName = typeEffectiveness.getDefendingType().getTypeName();
        }
    }


    @Getter
    @Setter
    public static class DefendingTypeDTO{
        private String typeName;
        private BigDecimal multiplier;

        public DefendingTypeDTO(TypeEffectiveness typeEffectiveness) {
            this.multiplier = typeEffectiveness.getMultiplier();
            this.typeName = typeEffectiveness.getAttackingType().getTypeName();
        }
    }

}
