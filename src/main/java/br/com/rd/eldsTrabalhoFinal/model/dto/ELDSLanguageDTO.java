package br.com.rd.eldsTrabalhoFinal.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ELDSLanguageDTO {

    private ELDSCompositeKeyLangDTO compositeKey;
    private LocalDateTime lastUpdate;

}
