package br.com.escola.escola_api.controller;

import br.com.escola.escola_api.record.SalaRequest;
import br.com.escola.escola_api.record.SalaResponse;
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
    public ResponseEntity<SalaResponse> cadastrar(@RequestBody @Valid SalaRequest request) {
        SalaResponse response = salaService.cadastrarSala(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listarTodos() {
        return ResponseEntity.ok(salaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponse> atualizarSala(@PathVariable Long id, @RequestBody @Valid SalaRequest request) {
        return ResponseEntity.ok(salaService.atualizarSala(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
