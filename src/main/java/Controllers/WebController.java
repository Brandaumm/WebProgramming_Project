package Controllers;

import Livro.Livro;
import Repository.LivroRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/index")
    public String index(HttpServletResponse response, Model model) {
        List<Livro> livros = livroRepository.findByIsDeletedFalse();
        model.addAttribute("livros", livros);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        ResponseCookie cookie = ResponseCookie.from("visita", currentDateTime)
                .httpOnly(true)
                .maxAge(86400) // 24 horas em segundos
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return "index";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/produto")
    public String produto() {

        return "product";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/blank")
    public String blank() {
        return "blank";
    }

    @GetMapping("/load")
    public String loja() {
        return "index";
    }
}