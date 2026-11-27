package br.com.escola.matriculas_api.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MatriculaRequest(
        @NotBlank(message = "O nome do aluno é obrigatório")
        String nomeAluno,

        @NotBlank(message = "O nome do curso é obrigatório")
        String nomeCurso,

        @NotBlank(message = "A turma é obrigatória")
        String turma,

        @NotNull(message = "A data da matrícula é obrigatória")
        @PastOrPresent(message = "A data da matrícula deve ser igual ou anterior à data atual")
        LocalDate dataMatricula
) {
}