package com.example.educaflow.service;

import com.example.educaflow.model.Aluno;
import com.example.educaflow.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para operações relacionadas a Aluno.
 */
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, Aluno dados) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        alunoExistente.setNome(dados.getNome());
        alunoExistente.setEmail(dados.getEmail());
        alunoExistente.setMatricula(dados.getMatricula());
        alunoExistente.setCursos(dados.getCursos());
        return alunoRepository.save(alunoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}