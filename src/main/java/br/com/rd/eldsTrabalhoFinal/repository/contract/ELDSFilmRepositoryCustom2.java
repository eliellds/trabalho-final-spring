package br.com.rd.eldsTrabalhoFinal.repository.contract;

import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ELDSFilmRepositoryCustom2 {

    @Query(value = "SELECT * FROM tb_film WHERE title LIKE %:desc% OR description LIKE %:desc% ORDER BY title ASC",
            nativeQuery = true)
    List<ELDSFilm> searchFilmByTitle(@Param("desc") String desc);

}
