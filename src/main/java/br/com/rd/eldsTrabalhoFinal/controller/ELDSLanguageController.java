package br.com.rd.eldsTrabalhoFinal.controller;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSLanguageDTO;
import br.com.rd.eldsTrabalhoFinal.service.ELDSLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class ELDSLanguageController {

    @Autowired
    ELDSLanguageService languageService;

    @PostMapping
    @ResponseBody
    public ELDSLanguageDTO create(@RequestBody ELDSLanguageDTO newLanguage) {
        try {
            return languageService.create(newLanguage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @GetMapping
    public List<ELDSLanguageDTO> findAll() {
        return languageService.findAll();
    }

    @GetMapping("/{country}/{name}")
    public ELDSLanguageDTO searchById(@PathVariable("country") String country,
                                      @PathVariable("name") String name) {
        try {
            return languageService.searchById(country, name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @PutMapping("/{country}/{name}")
    public ELDSLanguageDTO updateById(@RequestBody ELDSLanguageDTO dto,
                                      @PathVariable("country") String country,
                                      @PathVariable("name") String name) {
        try {
            return languageService.updateById(dto, country, name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @DeleteMapping("/delete/{country}/{name}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ELDSLanguageDTO deleteByIdReturningDTO(@PathVariable("country") String country,
                                                  @PathVariable("name") String name) {
        try {
            return languageService.deleteByIdReturningDTO(country, name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @GetMapping("/ordered")
    public List<ELDSLanguageDTO> searchOrderedByName() {
        return languageService.searchOrderedByName();
    }

}
