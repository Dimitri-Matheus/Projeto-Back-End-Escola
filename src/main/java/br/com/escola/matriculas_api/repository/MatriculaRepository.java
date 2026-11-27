package br.com.escola.matriculas_api.repository;

import br.com.escola.matriculas_api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByNomeAlunoAndNomeCursoAndTurma(String nomeAluno, String nomeCurso, String turma);
    boolean existsByNomeAlunoAndNomeCursoAndTurmaAndIdNot(String nomeAluno, String nomeCurso, String turma, Long id);
}