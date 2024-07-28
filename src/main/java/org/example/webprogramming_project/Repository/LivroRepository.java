package org.example.webprogramming_project.Repository;

import org.example.webprogramming_project.Livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIsDeletedFalse();
}