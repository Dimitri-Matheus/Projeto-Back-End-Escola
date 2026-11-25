package br.com.escola.escola_api.controller;

import br.com.escola.escola_api.dto.SalaRequestDTO;
import br.com.escola.escola_api.dto.SalaResponseDTO;
import br.com.escola.escola_api.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping()
    public ResponseEntity<SalaResponseDTO> cadastrar(@RequestBody @Valid SalaRequestDTO dto) {
        SalaResponseDTO response = salaService.cadastrarSala(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(salaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> atualizarSala(@PathVariable Long id, @RequestBody @Valid SalaRequestDTO dto) {
        return ResponseEntity.ok(salaService.atualizarSala(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
