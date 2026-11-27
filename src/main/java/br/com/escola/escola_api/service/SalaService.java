package br.com.escola.escola_api.service;

import br.com.escola.escola_api.exception.ResourceNotFoundException;
import br.com.escola.escola_api.model.Sala;
import br.com.escola.escola_api.record.SalaRequest;
import br.com.escola.escola_api.record.SalaResponse;
import br.com.escola.escola_api.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public SalaResponse cadastrarSala(SalaRequest request) {
        if (salaRepository.existsByRoomCode(request.roomCode())) {
            throw new IllegalArgumentException("Identificação de sala já cadastrada!");
        }
        Sala sala = new Sala();
        sala.setRoomCode(request.roomCode());
        sala.setBlock(request.block());
        sala.setRoomType(request.roomType());
        sala.setCapacity(request.capacity());
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
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada com o ID: " + id));
        return new SalaResponse(sala);
    }

    public SalaResponse atualizarSala(Long id, SalaRequest request) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada com o ID: " + id));
        if (salaRepository.existsByRoomCodeAndIdNot(request.roomCode(), id)) {
            throw new IllegalArgumentException("Identificação de sala já cadastrada!");
        }
        sala.setRoomCode(request.roomCode());
        sala.setBlock(request.block());
        sala.setRoomType(request.roomType());
        sala.setCapacity(request.capacity());
        Sala salaAtualizada = salaRepository.save(sala);
        return new SalaResponse(salaAtualizada);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala não encontrada!");
        }
        salaRepository.deleteById(id);
    }
}
