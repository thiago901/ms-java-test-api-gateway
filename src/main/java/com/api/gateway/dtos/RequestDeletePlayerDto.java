package com.api.gateway.dtos;
import jakarta.validation.constraints.NotBlank;

public record RequestDeletePlayerDto(
  @NotBlank String player_id
) {
  
}
