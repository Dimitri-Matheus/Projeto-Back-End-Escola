package br.com.escola.escola_api.repository;

import br.com.escola.escola_api.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    boolean existsByRoomCode(String roomCode);
    boolean existsByRoomCodeAndIdNot(String roomCode, Long id);
}
