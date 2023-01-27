package com.backend.apirest.service;

import com.backend.apirest.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepositoryOLD produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }
    public Optional<Produto> obterPorId(Long id){return produtoRepository.obterPorId(id);}
    public Produto adicionar(Produto produto){return produtoRepository.adicionar(produto);}
    public void deletar(Long id){produtoRepository.deletar(id);}
    public Produto atualizar(Long id,Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);}
}
