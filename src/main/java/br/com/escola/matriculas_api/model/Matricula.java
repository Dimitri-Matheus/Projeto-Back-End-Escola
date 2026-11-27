package br.com.escola.matriculas_api.model;

import br.com.escola.matriculas_api.record.MatriculaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_matriculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_aluno", nullable = false)
    private String nomeAluno;

    @Column(name = "nome_curso", nullable = false)
    private String nomeCurso;

    @Column(nullable = false)
    private String turma;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    public Matricula(MatriculaRequest request) {
        this.nomeAluno = request.nomeAluno();
        this.nomeCurso = request.nomeCurso();
        this.turma = request.turma();
        this.dataMatricula = request.dataMatricula();
    }
}