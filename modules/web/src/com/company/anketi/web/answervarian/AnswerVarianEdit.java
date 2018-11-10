package com.company.anketi.web.answervarian;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.anketi.entity.AnswerVarian;
import ru.rsmu.common.gui.components.CKEditor;

import javax.inject.Inject;

public class AnswerVarianEdit extends AbstractEditor<AnswerVarian> {

    @Inject
    private CKEditor hypertextEditor;

    @Override
    protected void postInit() {
        super.postInit();

        if(getItem().getContent() != null)
            hypertextEditor.setValue(getItem().getContent());
    }

    @Override
    protected boolean preCommit() {
        getItem().setContent(hypertextEditor.getValue());
        return super.preCommit();
    }
}