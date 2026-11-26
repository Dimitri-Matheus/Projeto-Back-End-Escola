package br.com.escola.escola_api.service;

import br.com.escola.escola_api.record.SalaRequest;
import br.com.escola.escola_api.record.SalaResponse;
import br.com.escola.escola_api.model.Sala;
import br.com.escola.escola_api.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public SalaResponse cadastrarSala(SalaRequest dto) {
        if (salaRepository.existsByRoomCode(dto.roomCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificação de sala já cadastrada!");
        }
        Sala sala = new Sala();
        sala.setRoomCode(dto.roomCode());
        sala.setBlock(dto.block());
        sala.setRoomType(dto.roomType());
        sala.setCapacity(dto.capacity());
        Sala salaSalva = salaRepository.save(sala);
        return new SalaResponse(salaSalva);
    }

    public List<SalaResponse> listarTodos() {
        return salaRepository.findAll()
                .stream()
                .map(SalaResponse::new)
                .toList();
    }

    public SalaResponse buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));
        return new SalaResponse(sala);
    }

    public SalaResponse atualizarSala(Long id, SalaRequest dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));
        if (salaRepository.existsByRoomCodeAndIdNot(dto.roomCode(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificação de sala já cadastrada!");
        }
        sala.setRoomCode(dto.roomCode());
        sala.setBlock(dto.block());
        sala.setRoomType(dto.roomType());
        sala.setCapacity(dto.capacity());
        Sala salaAtualizada = salaRepository.save(sala);
        return new SalaResponse(salaAtualizada);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!");
        }
        salaRepository.deleteById(id);
    }
}
