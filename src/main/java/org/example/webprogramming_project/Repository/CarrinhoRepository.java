package org.example.webprogramming_project.Repository;


import org.example.webprogramming_project.Carrinho.Carrinho;
import org.example.webprogramming_project.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario(Usuario usuario);
}