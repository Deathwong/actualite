package com.jeff.actualite.service.implement;

import com.jeff.actualite.repository.*;
import com.jeff.actualite.service.SuppressionActualiteService;
import com.jeff.actualite.service.VerificationExistenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuppressionActualiteServiceImpl implements SuppressionActualiteService {

    private final ImageRepository imageRepository;
    private final ResourceRepository resourceRepository;
    private final SectionRepository sectionRepository;
    private final FiltreRepository filtreRepository;
    private final HabilitationRepository habilitationRepository;
    private final ActualiteRepository actualiteRepository;
    private final VerificationExistenceService verificationExistenceService;

    public void supprimer(Long id) {

        //Activité n°1 - Supprimer la totalité d’une actualité
        if (verificationExistenceService.existeActualite(id)) {
            //S004-TX001-RG001
            //Supprimer l’ensemble des images

            Long idImage = imageRepository.getIdImageByIdActualite(id);
            if (idImage != null) {
                if (verificationExistenceService.existeImage(idImage)) {
                    imageRepository.deleteAllByIdActualite(id);
                }
            }

            //S004-TX001-RG002
            //Supprimer l’ensemble des ressources
            resourceRepository.deleteAllByIdActualite(id);

            //S004-TX001-RG003
            //Supprimer l’ensemble des sections
            sectionRepository.deleteAllByIdActualite(id);

            //S004-TX001-RG004
            //Supprimer l’ensemble des filtres
            filtreRepository.deleteAllByIdActualite(id);

            //S004-TX001-RG005
            //Supprimer l’ensemble des habilitations
            habilitationRepository.deleteAllByIdActualite(id);

            //S004-TX001-RG006
            //Supprimer l’actualité
            actualiteRepository.deleteById(id);
        }


    }
}
