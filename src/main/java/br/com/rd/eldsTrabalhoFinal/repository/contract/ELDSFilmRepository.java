package br.com.rd.eldsTrabalhoFinal.repository.contract;

import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELDSFilmRepository extends JpaRepository<ELDSFilm, Long>,
                                            ELDSFilmRepositoryCustom,
                                            ELDSFilmRepositoryCustom2 {



}
