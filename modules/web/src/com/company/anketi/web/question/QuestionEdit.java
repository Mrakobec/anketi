package com.company.anketi.web.question;

import com.company.anketi.entity.AnswerVarian;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.anketi.entity.Question;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.rsmu.common.gui.components.CKEditor;

import javax.inject.Inject;

public class QuestionEdit extends AbstractEditor<Question> {

    @Inject
    private CKEditor hypertextEditor;

    @Inject
    private Table<AnswerVarian> answerVariantsTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    protected void postInit() {
        super.postInit();

        if (getItem().getContent()!=null)
            hypertextEditor.setValue(getItem().getContent());

        answerVariantsTable.addGeneratedColumn("content", entity -> {
            Label field = (Label) componentsFactory.createComponent(Label.NAME);
            field.setHtmlEnabled(true);
            field.setWidth("100%");
            field.setValue(entity.getContent());
            return field;
        });

        CreateAction createAction = (CreateAction) answerVariantsTable.getActionNN("create");
        createAction.setInitialValues(ParamsMap.of("question", getItem()));
        createAction.setBeforeActionPerformedHandler(this::commit);
    }

    @Override
    protected boolean preCommit() {
        getItem().setContent(hypertextEditor.getValue());
        return super.preCommit();
    }
}