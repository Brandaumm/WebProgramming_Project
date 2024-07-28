package org.example.webprogramming_project.Service;

import java.util.List;
import java.util.Optional;


import jakarta.servlet.http.HttpSession;
import org.example.webprogramming_project.Repository.UsuarioRepository;
import org.example.webprogramming_project.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario getUsuarioLogado(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            return findById(userId);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não está logado");
    }

}