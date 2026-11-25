package br.com.escola.escola_api.service;

import br.com.escola.escola_api.dto.SalaRequestDTO;
import br.com.escola.escola_api.dto.SalaResponseDTO;
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

    public SalaResponseDTO cadastrarSala(SalaRequestDTO dto) {
        if (salaRepository.existsByRoomCode(dto.roomCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificação de sala já cadastrada!");
        }
        Sala sala = new Sala();
        sala.setRoomCode(dto.roomCode());
        sala.setBlock(dto.block());
        sala.setRoomType(dto.roomType());
        sala.setCapacity(dto.capacity());
        Sala salaSalva = salaRepository.save(sala);
        return new SalaResponseDTO(salaSalva);
    }

    public List<SalaResponseDTO> listarTodos() {
        return salaRepository.findAll()
                .stream()
                .map(SalaResponseDTO::new)
                .toList();
    }

    public SalaResponseDTO buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));
        return new SalaResponseDTO(sala);
    }

    public SalaResponseDTO atualizarSala(Long id, SalaRequestDTO dto) {
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
        return new SalaResponseDTO(salaAtualizada);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!");
        }
        salaRepository.deleteById(id);
    }
}
