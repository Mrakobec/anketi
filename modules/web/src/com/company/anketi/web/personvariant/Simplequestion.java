package com.company.anketi.web.personvariant;

import com.company.anketi.entity.PersonVarianQuestion;
import com.company.anketi.entity.PersonVariantAnswer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class Simplequestion extends AbstractFrame {

    @Inject
    private DataManager dataManager;
    @Inject
    private Datasource<PersonVarianQuestion> personVarianQuestionDs;
    @Inject
    private CollectionDatasource<PersonVariantAnswer, UUID> personVariantAnswersDs;
    @Inject
    private Table<PersonVariantAnswer> answersTable;
    @Inject
    private Label questionNumber;
    @Inject
    private Label questionContent;
    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        // загружаем personVarianQuestion-view
        LoadContext<PersonVarianQuestion> loadContext = LoadContext.create(PersonVarianQuestion.class)
                .setQuery(LoadContext.createQuery("select o from anketi$PersonVarianQuestion o where o.id=:question")
                        .setParameter("question", params.get("anketiQuestion")))
                .setView("personVarianQuestion-view");

        // устанавливаем переданный вопрос в источник данных
        personVarianQuestionDs.setItem(dataManager.load(loadContext));

        // выводим контент в таблице вариантов ответа
        answersTable.addGeneratedColumn("content", entity -> {
            Label field = componentsFactory.createComponent(Label.class);
            field.setWidth("100%");
            field.setHtmlEnabled(true);
            field.setValue(entity.getValue("content"));
            return field;
        });

        // сохраняем ответ
        answersTable.getDatasource().addItemPropertyChangeListener(e -> {
            if (e.getItem().getValue("selectedByPerson")) {
                for (PersonVariantAnswer qva : personVariantAnswersDs.getItems()) {
                    if (!qva.getId().equals(e.getItem().getId()))
                        qva.setSelectedByPerson(false);
                }
                e.getDs().commit();
                e.getDs().refresh();
            } else {
                e.getDs().commit();
            }
        });
        // номер вопроса
        questionNumber.setValue("Вопрос №" + personVarianQuestionDs.getItem().getOrderNumber());
        // жирный h3 в тексте вопроса
        questionContent.setValue("<h3><b>" + questionContent.getValue() + "</b></h3>");
    }
}