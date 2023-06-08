package com.jeff.actualite.controler;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.entity.Actualite;
import com.jeff.actualite.domain.response.DtoOutput;
import com.jeff.actualite.exception.ConstraintViolationException;
import com.jeff.actualite.exception.EntityNotFoundException;
import com.jeff.actualite.exception.NotHabilitatedException;
import com.jeff.actualite.service.ActualiteService;
import com.jeff.actualite.service.HabilitationService;
import com.jeff.actualite.utils.Constant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actualite")
@AllArgsConstructor
public class ActualiteController {

    private final ActualiteService actualiteService;
    private final HabilitationService habilitationActualiteService;

    @PostMapping
    ResponseEntity<DtoOutput> createActualite(@Validated @RequestBody ActualiteDto actualiteDto,
                                              BindingResult bindingResult) {
        // Gestion des erreurs de validation
        if (bindingResult.hasErrors()) {
            throw new ConstraintViolationException(bindingResult);
        }

        Long actualiteId = actualiteService.creer(actualiteDto);
        return ResponseEntity.ok().body(new DtoOutput(actualiteId));
    }

    @GetMapping
    ResponseEntity<DtoOutput> consulterActualite(@RequestParam("id") Long id,
                                                 @RequestParam(required = false, value = "codesAcces")
                                                 List<String> codesAcces) {

        // Vérification de l'existence de l'actualité
        if (!actualiteService.existeActualite(id)) {
            throw new EntityNotFoundException(Constant.ENTITY_NOT_FOUND_MESSAGE
                    .formatted(Actualite.class.getSimpleName(), id));
        }

        // Contrôle de l'accès
        if (!habilitationActualiteService.verifier(id, codesAcces)) {
            throw new NotHabilitatedException(Constant.UNAUTHORIZED.formatted(id));
        }

        ActualiteDto actualite = actualiteService.consulter(id);
        return ResponseEntity.ok().body(new DtoOutput(actualite));
    }

    @PutMapping
    ResponseEntity<DtoOutput> modifierActualite(@RequestParam("id") Long id,
                                                @RequestBody @Validated ActualiteDto actualiteDto,
                                                BindingResult bindingResult,
                                                @RequestParam(required = false, value = "codesAcces")
                                                List<String> codesAcces) {

        // Vérification de l'existence de l'actualité
        if (!actualiteService.existeActualite(id)) {
            throw new EntityNotFoundException(Constant.ENTITY_NOT_FOUND_MESSAGE
                    .formatted(Actualite.class.getSimpleName(), id));
        }

        // Contrôle del'accès
        if (!habilitationActualiteService.verifier(id, codesAcces)) {
            throw new NotHabilitatedException(Constant.UNAUTHORIZED.formatted(id));
        }

        if (bindingResult.hasErrors()) {
            throw new ConstraintViolationException(bindingResult);
        }

        actualiteService.modifier(actualiteDto, id);
        return ResponseEntity.ok().body(new DtoOutput());
    }

    @DeleteMapping
    ResponseEntity<DtoOutput> supprimerActualite(@RequestParam("id") Long id,
                                                 @RequestParam(required = false, value = "codesAcces")
                                                 List<String> codesAcces) {

        // Vérification de l'existence de l'actualité
        if (!actualiteService.existeActualite(id)) {
            throw new EntityNotFoundException(Constant.ENTITY_NOT_FOUND_MESSAGE
                    .formatted(Actualite.class.getSimpleName(), id));
        }

        // Contrôle del'accès
        if (!habilitationActualiteService.verifier(id, codesAcces)) {
            throw new NotHabilitatedException(Constant.UNAUTHORIZED.formatted(id));
        }

        actualiteService.supprimer(id);
        return ResponseEntity.ok().body(new DtoOutput());
    }

    @GetMapping("/all")
    ResponseEntity<DtoOutput> rechercherActualite(@RequestParam("dateCreation") String dateCreation) {
        List<ActualiteDto> actualiteDtoPage = actualiteService.rechercherWithDateCreation(dateCreation);
        return ResponseEntity.ok().body(new DtoOutput(actualiteDtoPage));
    }
}
