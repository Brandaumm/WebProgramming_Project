package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/produto")
    public String produto() {
        return "product.html";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout.html";
    }

    @GetMapping("/blank")
    public String blank() {
        return "blank.html";
    }

    @GetMapping("/loja")
    public String loja() {
        return "store.html";
    }
}