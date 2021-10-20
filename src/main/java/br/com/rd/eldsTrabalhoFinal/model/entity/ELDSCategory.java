package br.com.rd.eldsTrabalhoFinal.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_category")
@Data
public class ELDSCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private String name;
    @Column
    private LocalDateTime lastUpdate;

}
