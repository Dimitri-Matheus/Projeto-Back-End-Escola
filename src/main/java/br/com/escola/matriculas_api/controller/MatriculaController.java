package br.com.escola.matriculas_api.controller;

import br.com.escola.matriculas_api.record.MatriculaRequest;
import br.com.escola.matriculas_api.record.MatriculaResponse;
import br.com.escola.matriculas_api.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaResponse> cadastrar(@RequestBody @Valid MatriculaRequest request) {
        MatriculaResponse response = matriculaService.cadastrarMatricula(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> listarTodos() {
        return ResponseEntity.ok(matriculaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponse> atualizarMatricula(@PathVariable Long id, @RequestBody @Valid MatriculaRequest request) {
        return ResponseEntity.ok(matriculaService.atualizarMatricula(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaService.deletarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}