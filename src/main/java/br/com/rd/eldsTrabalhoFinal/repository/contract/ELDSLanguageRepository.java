package br.com.rd.eldsTrabalhoFinal.repository.contract;

import br.com.rd.eldsTrabalhoFinal.model.embeddable.ELDSCompositeKeyLang;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ELDSLanguageRepository extends JpaRepository<ELDSLanguage, ELDSCompositeKeyLang> {

    List<ELDSLanguage> findAllByOrderByCompositeKeyNameAsc();

}
