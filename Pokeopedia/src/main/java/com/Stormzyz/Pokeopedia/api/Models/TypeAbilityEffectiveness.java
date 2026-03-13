package com.Stormzyz.Pokeopedia.api.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="type_ability_effectiveness")
public class TypeAbilityEffectiveness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attacking_type_id")
    private PokemonType attackingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="defending_ability_id")
    private Ability defendingAbility;

    @Column(nullable = false)
    private BigDecimal multiplier;

}
