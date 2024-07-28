package org.example.webprogramming_project.Usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Size(max = 50, message = "O nome de usuário deve ter no máximo 50 caracteres.")
    private String username;


    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;

    private boolean isAdmin;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres.")
    private String email;


}