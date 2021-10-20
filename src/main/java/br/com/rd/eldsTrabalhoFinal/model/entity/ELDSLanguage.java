package br.com.rd.eldsTrabalhoFinal.model.entity;

import br.com.rd.eldsTrabalhoFinal.model.embeddable.ELDSCompositeKeyLang;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.Version;

@Entity(name = "tb_language")
@Data
public class ELDSLanguage {
    @EmbeddedId
    private ELDSCompositeKeyLang compositeKey;

    @Column
    private LocalDateTime lastUpdate;

}
