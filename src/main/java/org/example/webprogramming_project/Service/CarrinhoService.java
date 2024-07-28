package org.example.webprogramming_project.Service;


import jakarta.transaction.Transactional;
import org.example.webprogramming_project.Carrinho.Carrinho;
import org.example.webprogramming_project.Carrinho.ItemCarrinho;
import org.example.webprogramming_project.Livro.Livro;
import org.example.webprogramming_project.Repository.CarrinhoRepository;
import org.example.webprogramming_project.Repository.LivroRepository;
import org.example.webprogramming_project.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;



@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void adicionarAoCarrinho(Usuario usuario, Long livroId, int quantidade) {
        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario).orElseGet(() -> {
            Carrinho novoCarrinho = new Carrinho();
            novoCarrinho.setUsuario(usuario);
            return carrinhoRepository.save(novoCarrinho);
        });

        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getLivro().getId().equals(livroId))
                .findFirst()
                .orElse(new ItemCarrinho());

        Livro livro = livroRepository.findById(livroId).orElseThrow(() -> new NoSuchElementException("Livro não encontrado"));

        item.setLivro(livro);
        item.setQuantidade(item.getQuantidade() + quantidade);
        carrinho.getItens().add(item);

        carrinhoRepository.save(carrinho);
    }

    public Carrinho getCarrinho(Usuario usuario) {
        return carrinhoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));
    }

    @Transactional
    public void removerDoCarrinho(Usuario usuario, Long livroId) {
        Carrinho carrinho = getCarrinho(usuario);

        ItemCarrinho itemParaRemover = carrinho.getItens().stream()
                .filter(i -> i.getLivro().getId().equals(livroId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item não encontrado no carrinho"));

        carrinho.getItens().remove(itemParaRemover);
        carrinhoRepository.save(carrinho);
    }

    public List<ItemCarrinho> listarItensDoCarrinho(Usuario usuario) {  //LISTA DO CARRINHO
        Carrinho carrinho = getCarrinho(usuario);
        return carrinho.getItens();
    }
}

