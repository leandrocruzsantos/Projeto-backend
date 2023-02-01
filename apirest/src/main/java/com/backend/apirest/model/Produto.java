package com.backend.apirest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
