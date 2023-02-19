package com.jeff.actualite.service;

import java.util.List;

public interface HabilitationActualiteService {
    boolean verifier(Long id, List<String> codesAcces);
}
