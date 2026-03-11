package com.Stormzyz.Pokeopedia.api.DTO.PokemonTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
public class EditPokemonType {

    private String typeName;


    @Pattern(
            regexp = "^#[0-9A-Fa-f]{6}$",
            message = "Invalid HEX format. Use format #FFFFFF"
    )
    private String colorhex;

}
