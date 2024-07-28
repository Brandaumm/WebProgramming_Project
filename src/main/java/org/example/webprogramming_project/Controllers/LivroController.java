package org.example.webprogramming_project.Controllers;



import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.example.webprogramming_project.Service.ImagemService;
import org.example.webprogramming_project.Service.LivroService;
import org.example.webprogramming_project.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
@Validated
public class LivroController {

    @Autowired // Injeta uma instância do repositório LivroRepository.
    private LivroService livroService;

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private LivroRepository livroRepository;


    @PostMapping("/salvar")
    public ModelAndView createLivro(@Valid @ModelAttribute Livro livro, @RequestParam("imagem") MultipartFile imagem, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cadastro");

        if (result.hasErrors()) {
            modelAndView.addObject("errorMessage", "Erro na validação dos dados do livro.");
            return modelAndView;
        }

        try {
            // Processar a imagem se estiver presente
            if (!imagem.isEmpty()) {
                // Gerar um nome único para a imagem
                String nomeUnico = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();

                // Caminho para salvar a imagem
                Path caminhoImagem = Paths.get("src/main/resources/static/img", nomeUnico);

                // Salvar a imagem no diretório especificado
                Files.copy(imagem.getInputStream(), caminhoImagem, StandardCopyOption.REPLACE_EXISTING);

                // Definir o URI da imagem
                livro.setImageUri("/img/" + nomeUnico);
            }

            // Verificar se o ID está presente
            if (livro.getId() == null) {
                // ID é null, então é uma criação de novo livro
                livroService.salvarLivro(livro);
                modelAndView.addObject("successMessage", "Livro cadastrado com sucesso!");
            } else {
                // ID não é null, então é uma atualização de livro existente
                livroService.alterarLivro(livro.getId(), livro);
                modelAndView.addObject("successMessage", "Livro atualizado com sucesso!");
            }
        } catch (IOException e) {
            modelAndView.addObject("errorMessage", "Erro ao salvar a imagem: " + e.getMessage());
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", "Erro ao salvar o livro: " + e.getMessage());
        }

        // Redirecionar com parâmetros na URL para exibir as mensagens na página de cadastro
        modelAndView.setViewName("redirect:/cadastro?successMessage=" + modelAndView.getModel().get("successMessage") + "&errorMessage=" + modelAndView.getModel().get("errorMessage"));

        return modelAndView;
    }

    @PostMapping("/deletar")
    public String deletarLivro(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        // Soft delete: marca o livro como deletado
        livro.setDeleted(true); // Certifique-se de que o campo é `isDeleted` e não `deleted`
        livroRepository.save(livro);

        // Adiciona uma mensagem de sucesso
        redirectAttributes.addFlashAttribute("message", "Livro removido com sucesso");

        // Redireciona para a página de administração
        return "redirect:/admin";
    }


}
