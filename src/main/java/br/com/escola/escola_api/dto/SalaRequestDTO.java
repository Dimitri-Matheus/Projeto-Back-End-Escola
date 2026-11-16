package br.com.escola.escola_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalaRequestDTO(
        @NotBlank(message = "A identificação da sala é obrigatória")
        String roomCode,

        @NotBlank(message = "O bloco é obrigatório")
        String block,

        @NotBlank(message = "O tipo de sala é obrigatório")
        String roomType,

        @NotNull(message = "A capacidade é obrigatória")
        @Positive(message = "A capacidade deve ser um número maior que zero")
        Integer capacity
) {
}
