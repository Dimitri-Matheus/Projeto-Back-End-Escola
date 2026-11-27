package br.com.escola.matriculas_api.record;

import br.com.escola.matriculas_api.model.Matricula;

import java.time.LocalDate;

public record MatriculaResponse(
        Long id,
        String nomeAluno,
        String nomeCurso,
        String turma,
        LocalDate dataMatricula
) {
    public MatriculaResponse(Matricula matricula) {
        this(
                matricula.getId(),
                matricula.getNomeAluno(),
                matricula.getNomeCurso(),
                matricula.getTurma(),
                matricula.getDataMatricula()
        );
    }
}