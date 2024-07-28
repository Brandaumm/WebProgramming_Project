package org.example.webprogramming_project.Livro;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isDeleted;

    private String imageUri;

    @NotBlank(message = "O nome é obrigatório.") // NÃO DEIXAR VAZIO, VAI SE REPITIR SEMPRE
    @Size(max = 50, message = "O nome deve ter no máximo 100 caracteres.") // PARA LIMITAR A QUANTIDADE DE CARACTERIS
    private String nome;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 300, message = "O nome deve ter no máximo 100 caracteres.")
    private String sinopse;

    @NotBlank(message = "O gênero é obrigatório.")
    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres.")
    private String genero;

    @NotBlank(message = "O autor é obrigatório.")
    @Size(max = 50, message = "O autor deve ter no máximo 100 caracteres.")
    private String autor;

    @NotNull(message = "A data de publicação é obrigatória.")
    @PastOrPresent(message = "A data de publicação deve ser uma data passada ou presente.")
    private LocalDate dataPublicacao;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.") //ESSE INCLUSE FALSE E PARA DEIXAR VALORES APENAS MAIORES QUE 0
    private BigDecimal preco;
}
