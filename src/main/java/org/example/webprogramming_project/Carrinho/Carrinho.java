package org.example.webprogramming_project.Carrinho;

import jakarta.persistence.*;
import lombok.Data;
import org.example.webprogramming_project.Usuario.Usuario;

import java.util.List;


@Data
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id")
    private List<ItemCarrinho> itens;

}
