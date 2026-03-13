package com.Stormzyz.Pokeopedia.api.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="abilities")
public class Ability {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String abilityName;

    @Lob
    private String abilityDescription;

    @OneToMany(mappedBy = "defendingAbility")
    private List<TypeAbilityEffectiveness> defendingAbilityEffectiveness;

}
