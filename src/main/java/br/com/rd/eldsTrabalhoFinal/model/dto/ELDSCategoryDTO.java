package br.com.rd.eldsTrabalhoFinal.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ELDSCategoryDTO {

    private Long categoryId;
    private String name;
    private LocalDateTime lastUpdate;

}
