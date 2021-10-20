package br.com.rd.eldsTrabalhoFinal.controller;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSCategoryDTO;
import br.com.rd.eldsTrabalhoFinal.service.ELDSCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ELDSCategoryController {

    @Autowired
    ELDSCategoryService categoryService;

    @PostMapping
    @ResponseBody
    public ELDSCategoryDTO create(@RequestBody ELDSCategoryDTO newCategory) {
        return categoryService.create(newCategory);
    }

    @GetMapping
    public List<ELDSCategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ELDSCategoryDTO searchById(@PathVariable("id") Long id) {
        return categoryService.searchById(id);
    }

    @PutMapping("/{id}")
    public ELDSCategoryDTO updateById(@RequestBody ELDSCategoryDTO dto, @PathVariable("id") Long id) {
        return categoryService.updateById(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ELDSCategoryDTO deleteByIdReturningDTO(@PathVariable("id") Long id) {
        return categoryService.deleteByIdReturningDTO(id);
    }

}
