package org.example.webprogramming_project.Controllers;

import org.example.webprogramming_project.Service.UsuarioService;
import org.example.webprogramming_project.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadusuario")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadusuario";
    }




}
