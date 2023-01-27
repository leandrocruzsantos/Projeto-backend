package com.backend.apirest.controller;

import com.backend.apirest.model.Produto;
import com.backend.apirest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public List<Produto> obterTodos(){return produtoService.obterTodos();}
    @GetMapping("/{id}")
    public Optional<Produto> obterPorid(@PathVariable Long id){return produtoService.obterPorId(id);}
    @PostMapping
    public Produto adicionar(@RequestBody Produto produto){return produtoService.adicionar(produto);}
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){produtoService.deletar(id);}
    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto produto,@PathVariable Long id){return produtoService.atualizar(id,produto);}
}
