package com.company.anketi.web.personvariant;

import com.company.anketi.entity.PersonVarianQuestion;
import com.company.anketi.entity.PersonVariant;
import com.company.anketi.entity.QuestionTypeEnum;
import com.company.anketi.exception.AnketiException;
import com.company.anketi.service.AnketiService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Map;

public class Variantsolve extends AbstractWindow {


    private boolean correctnessTestMode = false;
    private PersonVariant anketiVariant;
    private Integer questionNumber = 0;
    private PersonVarianQuestion currentQuestion;

    @Inject
    private AnketiService anketiService;
    @Inject
    private TimeSource timeSource;
    @Inject
    private DataManager dataManager;
    @Inject
    private Button nextQuestionButton;
    @Inject
    private Button previousQuestionButton;
    @Inject
    private Button closeTestButton;
    @Inject
    private Button finishTestButton;
    @Inject
    private VBoxLayout contentBox;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        anketiVariant = (PersonVariant) params.get("anketiPersonVarian");

        changeButtonsState();

        previousQuestionButton.setVisible(false);
        nextQuestionButton.setCaption(getMessage("toFirstQuestion"));

        openFrame(contentBox, "anketi$VariantInfo", ParamsMap.of("anketiVariant", anketiVariant));
    }

    private void changeButtonsState() {
        previousQuestionButton.setVisible(true);
        nextQuestionButton.setVisible(true);
        nextQuestionButton.setCaption(getMessage("toNextQuestion"));
        if (questionNumber == 1) {
            previousQuestionButton.setEnabled(false);
        } else {
            previousQuestionButton.setEnabled(true);
        }
        if (questionNumber == anketiVariant.getPersonVarianQuestions().size()) {
            nextQuestionButton.setEnabled(false);
            finishTestButton.setEnabled(true);
            finishTestButton.setVisible(true);
        } else {
            nextQuestionButton.setEnabled(true);
        }
    }

    private void openQuestionFrame(PersonVarianQuestion question) {
        if (question.getType().equals(QuestionTypeEnum.Single)) {
            openFrame(contentBox, "anketi$SimpleQuestion", ParamsMap.of("anketiQuestion", question));
        } else if (question.getType().equals(QuestionTypeEnum.Multi)) {
            openFrame(contentBox, "anketi$MultiQuestion", ParamsMap.of("anketiQuestion", question));
        }
    }

    public void goToPreviousQuestion() {
        if (questionNumber > 1) {
            currentQuestion = anketiVariant.getPersonVarianQuestions().get(questionNumber-2);
            openQuestionFrame(currentQuestion);
            questionNumber--;
        }
        changeButtonsState();
    }

    public void goToNextQuestion() {
        if (questionNumber < anketiVariant.getPersonVarianQuestions().size()) {
            if(currentQuestion == null){
                anketiVariant.setStartTime(timeSource.currentTimestamp());
                dataManager.commit(anketiVariant);
                dataManager.reload(anketiVariant, "personVariantView");
            }

            currentQuestion = anketiVariant.getPersonVarianQuestions().get(questionNumber);
            openQuestionFrame(currentQuestion);
            questionNumber++;
        }
        changeButtonsState();
    }

    public void finishTest() {
        showOptionDialog(
                "Завершение тестирования",
                "Вы действительно хотите завершить тестирование?",
                MessageType.CONFIRMATION,
                new Action[]{
                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY) {
                            public void actionPerform(Component component) {
                                try {
                                    anketiService.completeTest(anketiVariant.getId());
                                } catch (AnketiException ae) {
                                    showNotification("При завершении тестирования возникла проблема: " + ae.getMessage(),
                                            NotificationType.WARNING_HTML);
                                }
                                // перезагружаем вариант
                                anketiVariant = dataManager.reload(anketiVariant, "personVariantView");
                                openFrame(contentBox,
                                        "anketi$VariantResults",
                                        ParamsMap.of("anketiVariant", anketiVariant));
                                // делаем кнопки невидимыми
                                previousQuestionButton.setVisible(false);
                                nextQuestionButton.setVisible(false);
                                finishTestButton.setVisible(false);

                                // отображаем кнопку закрытия окна
                                closeTestButton.setVisible(true);
                            }
                        },
                        new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL)
                }
        );
    }

    public void closeTest() {
        close(Window.CLOSE_ACTION_ID,true);
    }
}