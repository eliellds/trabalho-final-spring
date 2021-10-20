package br.com.rd.eldsTrabalhoFinal.service;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSCategoryDTO;
import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSCompositeKeyLangDTO;
import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSFilmDTO;
import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSLanguageDTO;
import br.com.rd.eldsTrabalhoFinal.model.embeddable.ELDSCompositeKeyLang;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSCategory;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSLanguage;
import br.com.rd.eldsTrabalhoFinal.repository.contract.ELDSCategoryRepository;
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
public class ELDSFilmService {

    @Autowired
    ELDSFilmRepository filmRepository;

    @Autowired
    ELDSLanguageRepository languageRepository;

    @Autowired
    ELDSCategoryRepository categoryRepository;

    public ELDSFilmDTO create(ELDSFilmDTO newFilm) throws SQLIntegrityConstraintViolationException {
        ELDSFilm film = dtoToBusiness(newFilm);

        if (film.getLanguage() != null) {
            ELDSLanguage language = film.getLanguage();

            if (film.getLanguage().getCompositeKey() != null) {
                ELDSCompositeKeyLang key = new ELDSCompositeKeyLang();
                key.setCountry(film.getLanguage().getCompositeKey().getCountry());
                key.setName((film.getLanguage().getCompositeKey().getName()));

                if (languageRepository.existsById(key)) {
                    language = languageRepository.getById(key);
                } else {
                    language.setCompositeKey(key);
                }

                film.setLanguage(language);
            }
            language.setLastUpdate(LocalDateTime.now());
            film.setLanguage(language);
        }

        if (film.getCategory() != null) {
            ELDSCategory category = film.getCategory();

            if (film.getCategory().getCategoryId() != null) {
                Long id = film.getCategory().getCategoryId();

                if (categoryRepository.existsById(id)) {
                    category = categoryRepository.getById(id);
                } else {
                    category.setCategoryId(null);
                }

            }

            category.setLastUpdate(LocalDateTime.now());
            film.setCategory(category);
        }

        film.setLastUpdate(LocalDateTime.now());

        film = filmRepository.save(film);
        return businessToDto(film);
    }

    public List<ELDSFilmDTO> findAll() {
        List<ELDSFilm> allList = filmRepository.findAll();
        return listToDto(allList);
    }

    public ELDSFilmDTO searchById(Long id) {
        Optional<ELDSFilm> option = filmRepository.findById(id);

        if (option.isPresent()) {
            return businessToDto(option.get());
        }
        return null;
    }

    public ELDSFilmDTO updateById(ELDSFilmDTO dto, Long id) throws SQLIntegrityConstraintViolationException {
        Optional<ELDSFilm> option = filmRepository.findById(id);

        if (option.isPresent()) {
            ELDSFilm film = option.get();

            if (dto.getTitle() != null) {
                film.setTitle(dto.getTitle());
            }
            if (dto.getDescription() != null) {
                film.setDescription(dto.getDescription());
            }
            if (dto.getReleaseYear() != null) {
                film.setReleaseYear(dto.getReleaseYear());
            }
            if (dto.getLanguage() != null) {
                ELDSLanguage language = new ELDSLanguage();

                if (dto.getLanguage().getCompositeKey() != null) {
                    ELDSCompositeKeyLang lang = new ELDSCompositeKeyLang();
                    lang.setName(dto.getLanguage().getCompositeKey().getName());
                    lang.setCountry(dto.getLanguage().getCompositeKey().getCountry());

                    if (languageRepository.existsById(lang)) {
                        language = languageRepository.getById(lang);
                    } else {
                        language.setCompositeKey(lang);
                    }
                }
                language.setLastUpdate(LocalDateTime.now());

                film.setLanguage(language);
            }
            if (dto.getRentalRate() != null) {
                film.setRentalRate(dto.getRentalRate());
            }
            if (dto.getCategory() != null) {
                ELDSCategory category = new ELDSCategory();

                if (dto.getCategory().getCategoryId() != null) {
                    Long categoryId = dto.getCategory().getCategoryId();

                    if (categoryRepository.existsById(categoryId)) {
                        category = categoryRepository.getById(categoryId);
                    } else {
                        category.setCategoryId(null);
                    }
                }

                if (dto.getCategory().getName() != null) {
                    if (category.getCategoryId() == null) {
                        category.setName(dto.getCategory().getName());
                    }
                }

                category.setLastUpdate(LocalDateTime.now());

                film.setCategory(category);
            }

            film.setLastUpdate(LocalDateTime.now());

            filmRepository.save(film);
            return businessToDto(film);

        }
        return null;
    }

    public ELDSFilmDTO deleteByIdReturningDTO(Long id) {
        ELDSFilmDTO film = searchById(id);

        if (filmRepository.existsById(id)) {
            film.setLastUpdate(LocalDateTime.now());
            filmRepository.deleteById(id);
        }
        return film;
    }

    public List<ELDSFilmDTO> searchFilmByTitle(String description) {
        return listToDto(filmRepository.searchFilmByTitle(description));
    }

    public List<ELDSFilmDTO> searchFilmByReleaseYear(Integer year) {
        return listToDto(filmRepository.searchByReleaseYear(year));
    }

    public List<ELDSFilmDTO> listToDto(List<ELDSFilm> list) {
        List<ELDSFilmDTO> listDto = new ArrayList<>();

        for (ELDSFilm business : list) {
            listDto.add(businessToDto(business));
        }
        return listDto;
    }

    private ELDSFilm dtoToBusiness(ELDSFilmDTO dto) {
        ELDSFilm business = new ELDSFilm();

        business.setTitle(dto.getTitle());
        business.setDescription(dto.getDescription());
        business.setReleaseYear(dto.getReleaseYear());

        if (dto.getLanguage() != null) {
            ELDSLanguage language = new ELDSLanguage();
            ELDSCompositeKeyLang keyLang = new ELDSCompositeKeyLang();

            if (dto.getLanguage().getCompositeKey().getName() != null) {
                keyLang.setName(dto.getLanguage().getCompositeKey().getName());
            } else {
                throw new NullPointerException("Field 'name' is null");
            }

            if (dto.getLanguage().getCompositeKey().getCountry() != null) {
                keyLang.setCountry(dto.getLanguage().getCompositeKey().getCountry());
            } else {
                throw new NullPointerException("Field 'country' is null");
            }

            language.setCompositeKey(keyLang);

            business.setLanguage(language);
        }

        business.setRentalRate(dto.getRentalRate());
        business.setLastUpdate(dto.getLastUpdate());

        if (dto.getCategory() != null) {
            ELDSCategory category = new ELDSCategory();

            category.setCategoryId(dto.getCategory().getCategoryId());
            category.setName(dto.getCategory().getName());

            business.setCategory(category);
        }

        return business;
    }

    private ELDSFilmDTO businessToDto(ELDSFilm business) {
        ELDSFilmDTO dto = new ELDSFilmDTO();

        dto.setFilmId(business.getFilmId());
        dto.setTitle(business.getTitle());
        dto.setDescription(business.getDescription());
        dto.setReleaseYear(business.getReleaseYear());

        if (business.getLanguage() != null) {
            ELDSLanguageDTO language = new ELDSLanguageDTO();
            ELDSCompositeKeyLangDTO keyLang = new ELDSCompositeKeyLangDTO();

            if (business.getLanguage().getCompositeKey().getName() != null) {
                keyLang.setName(business.getLanguage().getCompositeKey().getName());
            } else {
                throw new NullPointerException("Field 'name' is null");
            }

            if (business.getLanguage().getCompositeKey().getCountry() != null) {
                keyLang.setCountry(business.getLanguage().getCompositeKey().getCountry());
            } else {
                throw new NullPointerException("Field 'country' is null");
            }

            language.setCompositeKey(keyLang);
            language.setLastUpdate(business.getLanguage().getLastUpdate());

            dto.setLanguage(language);
        }

        dto.setRentalRate(business.getRentalRate());
        dto.setLastUpdate(business.getLastUpdate());

        if (business.getCategory() != null) {
            ELDSCategoryDTO category = new ELDSCategoryDTO();


            if (business.getCategory().getName() != null) {
                category.setName(business.getCategory().getName());
            } else {
                throw new NullPointerException("Field 'category' is null");
            }

            category.setCategoryId(business.getCategory().getCategoryId());
            category.setLastUpdate(business.getCategory().getLastUpdate());

            dto.setCategory(category);
        }
        dto.setLastUpdate(business.getLastUpdate());

        return dto;
    }

}
