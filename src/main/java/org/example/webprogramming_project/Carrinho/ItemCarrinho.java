package org.example.webprogramming_project.Carrinho;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.webprogramming_project.Livro.Livro;

@Data
@Entity
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private Livro livro;

    private int quantidade;

    // O LOMBOK FAZ OS GETTERS E SETTERS
}
