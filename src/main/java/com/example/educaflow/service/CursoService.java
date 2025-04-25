package com.example.educaflow.service;

import com.example.educaflow.model.Curso;
import com.example.educaflow.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso atualizar(Long id, Curso dados) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        cursoExistente.setNome(dados.getNome());
        cursoExistente.setDescricao(dados.getDescricao());
        cursoExistente.setCargaHoraria(dados.getCargaHoraria());
        cursoExistente.setAlunos(dados.getAlunos());
        return cursoRepository.save(cursoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }
}