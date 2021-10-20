package br.com.rd.eldsTrabalhoFinal.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ELDSFilmDTO {

    private Long filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private ELDSLanguageDTO language;
    private Double rentalRate;
    private LocalDateTime lastUpdate;
    private ELDSCategoryDTO category;

}
