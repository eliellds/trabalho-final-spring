package br.com.rd.eldsTrabalhoFinal.repository.implement;

import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import br.com.rd.eldsTrabalhoFinal.repository.contract.ELDSFilmRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ELDSFilmRepositoryImpl implements ELDSFilmRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ELDSFilm> searchByLanguage(String country, String name) {

        Query sql = entityManager.createNativeQuery
                ("SELECT * FROM tb_film WHERE language_country LIKE '%' :country '%' AND language_name LIKE '%' :name '%'",
                        ELDSFilm.class);
        sql.setParameter("country", country);
        sql.setParameter("name", name);

        return sql.getResultList();

    }

    @Override
    public List<ELDSFilm> searchByReleaseYear(Integer year) {

        Query sql = entityManager.createNativeQuery("SELECT * FROM tb_film WHERE release_year = ? ORDER BY release_year DESC",
                ELDSFilm.class);
        sql.setParameter(1, year);

        return sql.getResultList();

    }

}
