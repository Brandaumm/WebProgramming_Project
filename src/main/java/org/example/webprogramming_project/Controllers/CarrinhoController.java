package org.example.webprogramming_project.Controllers;
import jakarta.servlet.http.HttpSession;
import org.example.webprogramming_project.Carrinho.Carrinho;
import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.example.webprogramming_project.Service.CarrinhoService;
import org.example.webprogramming_project.Service.UsuarioService;
import org.example.webprogramming_project.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/adicionarCarrinho")
    public String adicionarCarrinho(@RequestParam("id") Long id, HttpSession session) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        List<Livro> carrinho = (List<Livro>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(livro);
        session.setAttribute("carrinho", carrinho);

        System.out.println("Livro adicionado ao carrinho: " + livro); // Log para depuração

        return "redirect:/index";
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        List<Livro> carrinho = (List<Livro>) session.getAttribute("carrinho");

        if (carrinho == null || carrinho.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Não há itens no carrinho.");
            return "redirect:/index";
        }

        model.addAttribute("carrinho", carrinho);
        return "verCarrinho";
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session) {
        session.invalidate(); // Invalida a sessão
        return "redirect:/index";
    }
}


