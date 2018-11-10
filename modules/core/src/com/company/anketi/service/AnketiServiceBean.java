package com.company.anketi.service;

import com.company.anketi.entity.*;
import com.company.anketi.exception.AnketiException;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@Service(AnketiService.NAME)
public class AnketiServiceBean implements AnketiService {

    @Inject
    private Metadata metadata;

    @Inject
    private DataManager dataManager;

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    public PersonVariant generateVariantForTest(@NotNull Questionnaire questionnaire) throws AnketiException {
        CommitContext commitContext = new CommitContext();

        PersonVariant newPersonVariant = metadata.create(PersonVariant.class);
        newPersonVariant.setName("Тестирование корректности №"
                + uniqueNumbersAPI.getNextNumber("anketitest" + questionnaire.getId().toString().replace("-", "")));

        commitContext.addInstanceToCommit(newPersonVariant);

        PersonVarianQuestionnaire newPersonVarianQuestionnaire = metadata.create(PersonVarianQuestionnaire.class);
        newPersonVarianQuestionnaire.setName(questionnaire.getName());
        newPersonVarianQuestionnaire.setNumber(questionnaire.getNumber());
        newPersonVarianQuestionnaire.setPersonVariant(newPersonVariant);
        commitContext.addInstanceToCommit(newPersonVarianQuestionnaire);

        LoadContext<Question> questionLoadContext = LoadContext.create(Question.class)
                .setQuery(LoadContext
                        .createQuery("select o from anketi$Question o where o.questionnaire.id=:questionnaire order by o.number")
                        .setParameter("questionnaire", questionnaire)
                ).setView("questionView");

        int i = 1;
        for (Question question : dataManager.loadList(questionLoadContext)) {
            PersonVarianQuestion newPersonVarianQuestion = metadata.create(PersonVarianQuestion.class);
            newPersonVarianQuestion.setPersonVariant(newPersonVariant);
            newPersonVarianQuestion.setOrderNumber(i);
            newPersonVarianQuestion.setType(question.getType());
            newPersonVarianQuestion.setContent(question.getContent());
            newPersonVarianQuestion.setPersonVarianQuestionnaire(newPersonVarianQuestionnaire);
            newPersonVarianQuestion.setQuestionId(question.getId());
            i++;
            commitContext.addInstanceToCommit(newPersonVarianQuestion);
            int j = 1;
            for (AnswerVarian answerVarian : question.getAnswerVariants()) {
                PersonVariantAnswer newPersonVariantAnswer = metadata.create(PersonVariantAnswer.class);
                newPersonVariantAnswer.setPersonVarianQuestion(newPersonVarianQuestion);
                newPersonVariantAnswer.setOrderNumber(j);
                newPersonVariantAnswer.setContent(answerVarian.getContent());
                j++;
                commitContext.addInstanceToCommit(newPersonVariantAnswer);
            }
        }

        dataManager.commit(commitContext);

        return dataManager.reload(newPersonVariant, "personVariantView");
    }

    @Override
    public void completeTest(UUID id) {

    }
}