package com.backend.apirest.view.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
