package br.com.escola.escola_api.service;

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

    public Sala cadastrarSala(Sala sala) {
        if (salaRepository.existsByRoomCode(sala.getRoomCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificação de sala já cadastrada!");
        }
        sala.setId(null);
        return salaRepository.save(sala);
    }

    public List<Sala> listarTodos() {
        return salaRepository.findAll();
    }

    public Sala buscarPorId(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));
    }

    public Sala atualizarSala(Long id, Sala salaRecebida) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));
        if (salaRepository.existsByRoomCodeAndIdNot(salaRecebida.getRoomCode(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificação de sala já cadastrada!");
        }
        sala.setRoomCode(salaRecebida.getRoomCode());
        sala.setBlock(salaRecebida.getBlock());
        sala.setRoomType(salaRecebida.getRoomType());
        sala.setCapacity(salaRecebida.getCapacity());
        return salaRepository.save(sala);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!");
        }
        salaRepository.deleteById(id);
    }
}
