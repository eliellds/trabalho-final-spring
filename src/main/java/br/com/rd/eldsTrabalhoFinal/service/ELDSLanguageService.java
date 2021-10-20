package br.com.rd.eldsTrabalhoFinal.service;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSCompositeKeyLangDTO;
import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSLanguageDTO;
import br.com.rd.eldsTrabalhoFinal.model.embeddable.ELDSCompositeKeyLang;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSLanguage;
import br.com.rd.eldsTrabalhoFinal.repository.contract.ELDSFilmRepository;
import br.com.rd.eldsTrabalhoFinal.repository.contract.ELDSLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ELDSLanguageService {

    @Autowired
    ELDSLanguageRepository languageRepository;

    @Autowired
    ELDSFilmRepository filmRepository;

    public ELDSLanguageDTO create(ELDSLanguageDTO newLanguage) throws SQLIntegrityConstraintViolationException {
        ELDSLanguage language = dtoToBusiness(newLanguage);

        if (languageRepository.existsById(language.getCompositeKey())) {
            throw new SQLIntegrityConstraintViolationException("Primary key already exists!");
        } else {
            language.setLastUpdate(LocalDateTime.now());
            language = languageRepository.save(language);

            return businessToDto(language);
        }
    }

    public List<ELDSLanguageDTO> findAll() {
        List<ELDSLanguage> allList = languageRepository.findAll();
        return listToDto(allList);
    }

    public ELDSLanguageDTO searchById(String country, String name) {
        ELDSCompositeKeyLang langKey = new ELDSCompositeKeyLang();

        langKey.setCountry(country);
        langKey.setName(name);

        Optional<ELDSLanguage> option = languageRepository.findById(langKey);

        if (option.isPresent()) {
            return businessToDto(option.get());
        }
        return null;
    }

    public ELDSLanguageDTO updateById(ELDSLanguageDTO dto,
                                      String country,
                                      String name) throws SQLIntegrityConstraintViolationException {

        ELDSLanguage language = dtoToBusiness(dto);

        if (languageRepository.existsById(language.getCompositeKey())) {
            throw new SQLIntegrityConstraintViolationException("Primary key already exists!");
        } else {

            language.setLastUpdate(LocalDateTime.now());
            language = languageRepository.save(language);

            List<ELDSFilm> listToChange = filmRepository.searchByLanguage(country, name);
            for (ELDSFilm film : listToChange) {
                film.setLanguage(language);
            }

            deleteByIdReturningDTO(country, name);

            return businessToDto(language);
        }

    }

    public ELDSLanguageDTO deleteByIdReturningDTO(String country, String name) {
        ELDSCompositeKeyLangDTO langKey = new ELDSCompositeKeyLangDTO();
        langKey.setCountry(country);
        langKey.setName(name);

        ELDSLanguageDTO lang = searchById(country, name);

        if (languageRepository.existsById(dtoToBusiness(lang).getCompositeKey())) {
            lang.setLastUpdate(LocalDateTime.now());
            languageRepository.deleteById(dtoToBusiness(lang).getCompositeKey());
        }
        return lang;
    }

    public List<ELDSLanguageDTO> searchOrderedByName() {
        return listToDto(languageRepository.findAllByOrderByCompositeKeyNameAsc());
    }

    public List<ELDSLanguageDTO> listToDto(List<ELDSLanguage> list) {
        List<ELDSLanguageDTO> listDto = new ArrayList<>();

        for (ELDSLanguage business : list) {
            listDto.add(businessToDto(business));
        }
        return listDto;
    }

    private ELDSLanguage dtoToBusiness(ELDSLanguageDTO dto) {
        ELDSLanguage business = new ELDSLanguage();

        if (dto.getCompositeKey() != null) {
            ELDSCompositeKeyLang langB = new ELDSCompositeKeyLang();

            if (dto.getCompositeKey().getName() != null) {
                langB.setName(dto.getCompositeKey().getName());
            } else {
                throw new NullPointerException("Field 'name' is null");
            }

            if (dto.getCompositeKey().getCountry() != null) {
                langB.setCountry(dto.getCompositeKey().getCountry());
            } else {
                throw new NullPointerException("Field 'country' is null");
            }

            business.setCompositeKey(langB);
        }
        business.setLastUpdate(dto.getLastUpdate());

        return business;
    }

    private ELDSLanguageDTO businessToDto(ELDSLanguage business) {
        ELDSLanguageDTO dto = new ELDSLanguageDTO();

        if (business.getCompositeKey() != null) {
            ELDSCompositeKeyLangDTO langD = new ELDSCompositeKeyLangDTO();

            langD.setCountry(business.getCompositeKey().getCountry());
            langD.setName(business.getCompositeKey().getName());

            dto.setCompositeKey(langD);
        }
        dto.setLastUpdate(business.getLastUpdate());

        return dto;
    }

}
