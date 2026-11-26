package br.com.escola.escola_api.record;

import br.com.escola.escola_api.model.Sala;

public record SalaResponse(
        Long id,
        String roomCode,
        String block,
        String roomType,
        Integer capacity
) {
    public SalaResponse(Sala sala) {
        this(
                sala.getId(),
                sala.getRoomCode(),
                sala.getBlock(),
                sala.getRoomType(),
                sala.getCapacity()
        );
    }
}
