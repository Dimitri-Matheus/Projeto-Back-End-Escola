package br.com.escola.escola_api.dto;

import br.com.escola.escola_api.model.Sala;

public record SalaResponseDTO(
        Long id,
        String roomCode,
        String block,
        String roomType,
        Integer capacity
) {
    public SalaResponseDTO(Sala sala) {
        this(
                sala.getId(),
                sala.getRoomCode(),
                sala.getBlock(),
                sala.getRoomType(),
                sala.getCapacity()
        );
    }
}
