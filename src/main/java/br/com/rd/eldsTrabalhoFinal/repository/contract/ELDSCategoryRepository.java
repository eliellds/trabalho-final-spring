package br.com.rd.eldsTrabalhoFinal.repository.contract;

import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELDSCategoryRepository extends JpaRepository<ELDSCategory, Long> {



}
