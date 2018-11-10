package com.company.anketi.service;


import com.company.anketi.entity.PersonVariant;
import com.company.anketi.entity.Questionnaire;
import com.company.anketi.exception.AnketiException;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

public interface AnketiService {
    String NAME = "anketi_AnketiService";

    /**Генерировать вариант для проверки и корректности*/
    PersonVariant generateVariantForTest(@NotNull Questionnaire questionnaire) throws AnketiException;

    void completeTest(UUID id);

}