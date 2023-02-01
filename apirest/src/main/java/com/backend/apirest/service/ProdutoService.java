package com.backend.apirest.service;

import com.backend.apirest.model.Produto;
import com.backend.apirest.model.exception.ResourceNotFoundException;
import com.backend.apirest.repository.ProdutoRepository;
import com.backend.apirest.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obterTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());

    }
    public Optional<ProdutoDTO> obterPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id "+ id + "não encontrado");
        }
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }
    public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
        produtoDTO.setId(null);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDTO, Produto.class);
        produto = produtoRepository.save(produto);
        produtoDTO.setId(produto.getId());
        return produtoDTO;
    }
    public void deletar(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            throw  new ResourceNotFoundException("Não foi possivel encontrar o produto com id: "+id);
        }
        produtoRepository.delete(id);
    }
    public ProdutoDTO atualizar(Long id,ProdutoDTO produtoDTO){
        produtoDTO.setId(id);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDTO, Produto.class);
        produtoRepository.save(produto);
        return produtoDTO;
       }
}
