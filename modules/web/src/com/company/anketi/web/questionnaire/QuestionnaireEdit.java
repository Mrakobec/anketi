package com.company.anketi.web.questionnaire;

import com.company.anketi.entity.Question;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.anketi.entity.Questionnaire;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.rsmu.statusmanagement.service.StatusLogService;
import ru.rsmu.statusmanagement.service.StatusModelService;

import javax.inject.Inject;
import java.util.Map;

public class QuestionnaireEdit extends AbstractEditor<Questionnaire> {

    @Inject
    private Table<Question> questionsTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private StatusModelService statusModelService;

    @Inject
    private StatusLogService statusLogService;

    private boolean isNotExistStatus = false;

    @Override
    protected void initNewItem(Questionnaire item) {
        super.initNewItem(item);
        if (item.getStatus()== null) {
            item.setStatus(statusModelService.getInitialStatus(item.getMetaClass().getName()));
            this.isNotExistStatus = true;
        }
    }

    @Override
    protected void postInit() {
        super.postInit();

        questionsTable.addGeneratedColumn("content", entity -> {
            Label field = (Label) componentsFactory.createComponent(Label.NAME);
            field.setHtmlEnabled(true);
            field.setWidth("100%");
            field.setValue(entity.getContent());
            return field;
        });

        CreateAction createAction = (CreateAction) questionsTable.getActionNN("create");
        createAction.setInitialValues(ParamsMap.of("questionnaire", getItem()));
        createAction.setBeforeActionPerformedHandler(this::commit);

    }

    @Override
    protected boolean preCommit() {
        if (this.isNotExistStatus) {
            statusLogService.writeToLog(getItem(), null);
        }
        return true;
    }
}