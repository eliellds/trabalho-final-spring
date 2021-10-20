package br.com.rd.eldsTrabalhoFinal.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_film")
@Data
public class ELDSFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column
    private Integer releaseYear;

    @ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "language_country"),
        @JoinColumn(name = "language_name")
    })
    private ELDSLanguage language;

    @Column(nullable = false)
    private Double rentalRate;
    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private ELDSCategory category;

}
