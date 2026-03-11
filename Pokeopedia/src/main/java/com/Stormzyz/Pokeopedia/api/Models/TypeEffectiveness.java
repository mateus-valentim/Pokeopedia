package com.Stormzyz.Pokeopedia.api.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_effectiveness")
@Entity
public class TypeEffectiveness {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attacking_type_id")
    private PokemonTypes attackingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defending_type_id")
    private PokemonTypes defendingType;

    @Column(nullable = false, unique = false, precision = 3, scale = 2)
    private BigDecimal multiplier;


}
