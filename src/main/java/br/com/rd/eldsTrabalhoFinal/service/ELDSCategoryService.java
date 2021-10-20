package br.com.rd.eldsTrabalhoFinal.service;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSCategoryDTO;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSCategory;
import br.com.rd.eldsTrabalhoFinal.repository.contract.ELDSCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ELDSCategoryService {

    @Autowired
    ELDSCategoryRepository categoryRepository;

    public ELDSCategoryDTO create(ELDSCategoryDTO newCategory) {
        ELDSCategory category = dtoToBusiness(newCategory);
        category.setLastUpdate(LocalDateTime.now());
        category = categoryRepository.save(category);

        return businessToDto(category);
    }

    public List<ELDSCategoryDTO> findAll() {
        List<ELDSCategory> allList = categoryRepository.findAll();
        return listToDto(allList);
    }

    public ELDSCategoryDTO searchById(Long id) {
        Optional<ELDSCategory> option = categoryRepository.findById(id);

        if (option.isPresent()) {
            return businessToDto(option.get());
        }
        return null;
    }

    public ELDSCategoryDTO updateById(ELDSCategoryDTO dto, Long id) {
        Optional<ELDSCategory> option = categoryRepository.findById(id);

        if (option.isPresent()) {
            ELDSCategory category = option.get();

            if (dto.getName() != null) {
                category.setName(dto.getName());
            }

            category.setLastUpdate(LocalDateTime.now());

            categoryRepository.save(category);
            return businessToDto(category);

        }
        return null;
    }

    public ELDSCategoryDTO deleteByIdReturningDTO(Long id) {
        ELDSCategoryDTO dto = searchById(id);

        if (categoryRepository.existsById(id)) {
            dto.setLastUpdate(LocalDateTime.now());
            categoryRepository.deleteById(id);
        }
        return dto;
    }

    public List<ELDSCategoryDTO> listToDto(List<ELDSCategory> list) {
        List<ELDSCategoryDTO> listDto = new ArrayList<>();

        for (ELDSCategory business : list) {
            listDto.add(businessToDto(business));
        }
        return listDto;
    }

    private ELDSCategory dtoToBusiness(ELDSCategoryDTO dto) {
        ELDSCategory business = new ELDSCategory();
        business.setName(dto.getName());
        business.setLastUpdate(dto.getLastUpdate());

        return business;
    }

    private ELDSCategoryDTO businessToDto(ELDSCategory business) {
        ELDSCategoryDTO dto = new ELDSCategoryDTO();
        dto.setCategoryId(business.getCategoryId());
        dto.setName(business.getName());
        dto.setLastUpdate((business.getLastUpdate()));

        return dto;
    }

}
