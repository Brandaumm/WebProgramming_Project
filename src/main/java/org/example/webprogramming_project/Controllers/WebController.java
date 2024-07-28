package org.example.webprogramming_project.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    @Autowired
    private LivroRepository livroRepository; // CERREGANDO UM NOVO REPOSITORIO

    @GetMapping("/index")
    public String index(HttpServletResponse response, Model model) { //METODO GET
        List<Livro> livros = livroRepository.findByIsDeletedFalse();
        model.addAttribute("livros", livros);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        ResponseCookie cookie = ResponseCookie.from("visita", currentDateTime) // COLOCANDO O COOKIE
                .httpOnly(true)
                .maxAge(86400) // 24 HORAS
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro() {

        return "cadastro";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "logout";
    }

    @GetMapping("/produto")
    public String produto() {

        return "product";
    }

}