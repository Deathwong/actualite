package com.jeff.actualite.service;

public interface VerificationExistenceService {
    boolean existeActualite(Long actualiteId);

    boolean existeImage(Long imageId);
}
