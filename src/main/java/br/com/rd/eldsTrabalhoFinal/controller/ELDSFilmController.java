package br.com.rd.eldsTrabalhoFinal.controller;

import br.com.rd.eldsTrabalhoFinal.model.dto.ELDSFilmDTO;
import br.com.rd.eldsTrabalhoFinal.model.entity.ELDSFilm;
import br.com.rd.eldsTrabalhoFinal.service.ELDSFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class ELDSFilmController {

    @Autowired
    ELDSFilmService filmService;

    @PostMapping
    @ResponseBody
    public ELDSFilmDTO create(@RequestBody ELDSFilmDTO newFilm) {
        try {
            return filmService.create(newFilm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @GetMapping
    public List<ELDSFilmDTO> findAll() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public ELDSFilmDTO searchById(@PathVariable("id") Long id) {
        return filmService.searchById(id);
    }

    @PutMapping("/{id}")
    public ELDSFilmDTO updateById(@RequestBody ELDSFilmDTO dto, @PathVariable("id") Long id) {
        try {
            return filmService.updateById(dto, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ELDSFilmDTO deleteByIdReturningDTO(@PathVariable("id") Long id) {
        return filmService.deleteByIdReturningDTO(id);
    }

    @GetMapping("/search-description")
    public List<ELDSFilmDTO> searchFilmByTitle(@RequestParam("description") String description) {
        return filmService.searchFilmByTitle(description);
    }

    @GetMapping("/search-year/{year}")
    public List<ELDSFilmDTO> searchFilmByReleaseYear(@PathVariable("year") Integer year) {
        return filmService.searchFilmByReleaseYear(year);
    }

}
