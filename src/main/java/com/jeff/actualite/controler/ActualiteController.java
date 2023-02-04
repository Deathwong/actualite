package com.jeff.actualite.controler;

import com.jeff.actualite.domain.dto.ActualiteDto;
import com.jeff.actualite.domain.response.DtoOutput;
import com.jeff.actualite.exception.ConstraintViolationException;
import com.jeff.actualite.service.CreationActualiteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actualite")
@AllArgsConstructor
public class ActualiteController {

    private CreationActualiteService actualiteService;

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
}
