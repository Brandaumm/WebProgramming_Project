package Controllers;

import Repository.LivroRepository;
import Livro.Livro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@Validated
public class LivroController {

    @Autowired // Injeta uma instância do repositório LivroRepository.
    private LivroRepository livroRepository;

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }
    @PostMapping
    public Livro createLivro(@Valid @RequestBody Livro livro) {
        return livroRepository.save(livro); // SALVA UM LIVRO NOVO NO BANCO
    }
    }