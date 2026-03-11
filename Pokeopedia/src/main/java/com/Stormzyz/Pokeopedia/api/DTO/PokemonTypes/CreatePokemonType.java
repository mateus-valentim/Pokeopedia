package com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePokemonType {


    @NotBlank(message = "A name for the typing is required")
    private String typeName;

    @NotBlank(message = "A color for the typing is required")
    @Size(min = 7, max = 7, message = "The color need to be a 7 character hex")
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Invalid HEX format. Use format #FFFFFF")
    private String colorhex;
}
