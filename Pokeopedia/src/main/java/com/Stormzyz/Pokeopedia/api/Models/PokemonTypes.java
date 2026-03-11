package com.Stormzyz.Pokeopedia.api.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pokemon_types")
public class PokemonTypes {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, name = "type_name")
    private String typeName;

    @Column(nullable = false, length = 7)
    private String colorhex;

    @OneToMany(mappedBy = "attackingType")
    private List<TypeEffectiveness> atackingEffectiveness;

    @OneToMany(mappedBy = "defendingType")
    private List<TypeEffectiveness> defendingEffectiveness;


}
