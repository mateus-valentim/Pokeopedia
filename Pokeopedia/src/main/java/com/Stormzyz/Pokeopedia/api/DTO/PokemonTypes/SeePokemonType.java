package com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes;

import com.Stormzyz.Pokeopedia.api.Models.PokemonTypes;
import com.Stormzyz.Pokeopedia.api.Models.TypeEffectiveness;
import lombok.AllArgsConstructor;
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



    public SeePokemonType(PokemonTypes pokemonTypes) {
        this.id = pokemonTypes.getId();
        this.typeName = pokemonTypes.getTypeName();
        this.colorhex = pokemonTypes.getColorhex();
        this.atackingEffectiveness = pokemonTypes.getAtackingEffectiveness() == null
                ? List.of()
                : pokemonTypes.getAtackingEffectiveness()
                .stream()
                .map(AttackingTypeDTO::new)
                .toList();
        this.defendingEffectiveness = pokemonTypes.getDefendingEffectiveness() == null
                ? List.of()
                : pokemonTypes.getDefendingEffectiveness()
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
