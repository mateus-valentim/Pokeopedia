package com.Stormzyz.Pokeopedia.api.DTO.Abilities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAbility {

    @NotBlank(message = "A name for the ability is necessary")
    private String abilityName;

    @NotBlank(message = "A description for the ability is necessary")
    private String abilityDescription;

}
