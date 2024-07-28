package org.example.webprogramming_project.Service;

import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;  // REPOSITORIO

    public Livro salvarLivro(Livro livro) {
        return this.livroRepository.save(livro);
    } //SALVAR

    public List<Livro> listarLivros(){
        return this.livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id) {
        return this.livroRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro.Livro não encontrado"));
    }

    public void deletarLivro(Long id) {
        this.livroRepository.deleteById(id);
    }

    public void alterarLivro(Long id, Livro livroAlterado) {
        this.livroRepository.findById(id).map(livro -> {
            livro.setAutor(livroAlterado.getAutor());
            livro.setDataPublicacao(livroAlterado.getDataPublicacao());
            livro.setDeleted(livroAlterado.isDeleted());
            livro.setGenero(livroAlterado.getGenero());
            livro.setImageUri(livroAlterado.getImageUri());
            livro.setNome(livroAlterado.getNome());
            livro.setPreco(livroAlterado.getPreco());
            livro.setSinopse(livroAlterado.getSinopse());
            return this.livroRepository.save(livro);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro.Livro não encontrado para ser alterado"));
    }
}
