package com.inventus.domain.produto;

import com.inventus.domain.categoria.Categoria;
import com.inventus.domain.fornecedor.Fornecedor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name="Produto")
@Table(name="tb_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String nome;
    private String descricao;

    @Column(unique = true)
    private String codigo;

    private BigDecimal preco;
    private Integer quantidadeEmEstoque;
    private String unidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private LocalDate dataCadastro;

}