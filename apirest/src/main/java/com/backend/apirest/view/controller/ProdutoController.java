package com.backend.apirest.view.controller;

import com.backend.apirest.model.Produto;
import com.backend.apirest.service.ProdutoService;
import com.backend.apirest.shared.ProdutoDTO;
import com.backend.apirest.view.model.ProdutoRequest;
import com.backend.apirest.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public ResponseEntity <List<ProdutoResponse>> obterTodos(){
    List<ProdutoDTO> produtos = produtoService.obterTodos();
        ModelMapper mapper = new ModelMapper();
       List<ProdutoResponse> resposta =produtos.stream()
               .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
               .collect(Collectors.toList());
       return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorid(@PathVariable Long id) {
        Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
        ProdutoResponse produto;
        produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = produtoService.adicionar(produtoDTO);
        return new ResponseEntity<>(mapper.map(produtoDTO, ProdutoResponse.class), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Produto produto){
        produtoService.deletar(produto.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoRequest,@PathVariable Long id){
       ModelMapper mapper = new ModelMapper();
       ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = produtoService.atualizar(id,produtoDTO);
        return new ResponseEntity<>(
                mapper.map(produtoDTO, ProdutoResponse.class),
                HttpStatus.OK);
    }
}
