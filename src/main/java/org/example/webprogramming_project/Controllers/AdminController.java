package org.example.webprogramming_project.Controllers;

import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        // Busca todos os livros que não estão deletados
        List<Livro> livros = livroRepository.findByIsDeletedFalse();

        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);

        // Retorna o nome da view Thymeleaf
        return "admin";
    }

    @PostMapping("/livros/salvarLivro")
    public String salvarLivro(@ModelAttribute Livro livro, @RequestParam("imagem") MultipartFile imagem) {
        // Processar a imagem se necessário
        if (!imagem.isEmpty()) {
            // Lógica para salvar a imagem
        }

        livroRepository.save(livro);
        return "redirect:/admin"; // Redireciona de volta para a página admin
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable("id") Long id, Model model) {
        Livro livro = livroRepository.findById(id).orElse(null);
        model.addAttribute("livro", livro);
        return "adminEditar"; // Certifique-se de que o template 'editar.html' está na pasta templates
    }

    @PostMapping("/deletar")
    public ModelAndView deletarLivro(@RequestParam Long id) {
        livroRepository.deleteById(id); // Assumindo que você tem um método deleteById no seu repositório
        return new ModelAndView("redirect:/admin"); // Redireciona de volta para a página admin
    }
}
