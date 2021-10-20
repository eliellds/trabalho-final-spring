package br.com.rd.eldsTrabalhoFinal.repository.contract;

import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;

import java.util.List;

public interface ELDSFilmRepositoryCustom {

    List<ELDSFilm> searchByLanguage(String country, String name);

    List<ELDSFilm> searchByReleaseYear(Integer year);

}
