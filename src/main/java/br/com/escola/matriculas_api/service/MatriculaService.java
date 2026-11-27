package br.com.escola.matriculas_api.service;

import br.com.escola.matriculas_api.exception.ResourceNotFoundException;
import br.com.escola.matriculas_api.model.Matricula;
import br.com.escola.matriculas_api.record.MatriculaRequest;
import br.com.escola.matriculas_api.record.MatriculaResponse;
import br.com.escola.matriculas_api.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public MatriculaResponse cadastrarMatricula(MatriculaRequest request) {
        if (matriculaRepository.existsByNomeAlunoAndNomeCursoAndTurma(
                request.nomeAluno(), request.nomeCurso(), request.turma())) {
            throw new IllegalArgumentException("Este aluno já está matriculado neste curso e turma!");
        }

        Matricula matricula = new Matricula(request);
        Matricula salva = matriculaRepository.save(matricula);
        return new MatriculaResponse(salva);
    }

    public List<MatriculaResponse> listarTodos() {
        return matriculaRepository.findAll()
                .stream()
                .map(MatriculaResponse::new)
                .toList();
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada com o ID: " + id));
        return new MatriculaResponse(matricula);
    }

    public MatriculaResponse atualizarMatricula(Long id, MatriculaRequest request) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada com o ID: " + id));

        if (matriculaRepository.existsByNomeAlunoAndNomeCursoAndTurmaAndIdNot(
                request.nomeAluno(), request.nomeCurso(), request.turma(), id)) {
            throw new IllegalArgumentException("Já existe outra matrícula para este aluno neste curso e turma!");
        }

        matricula.setNomeAluno(request.nomeAluno());
        matricula.setNomeCurso(request.nomeCurso());
        matricula.setTurma(request.turma());
        matricula.setDataMatricula(request.dataMatricula());

        Matricula atualizada = matriculaRepository.save(matricula);
        return new MatriculaResponse(atualizada);
    }

    public void deletarMatricula(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Matrícula não encontrada com o ID: " + id);
        }
        matriculaRepository.deleteById(id);
    }
}