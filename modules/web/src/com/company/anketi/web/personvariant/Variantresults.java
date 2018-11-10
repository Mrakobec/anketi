package com.company.anketi.web.personvariant;

import com.company.anketi.entity.PersonVarianQuestion;
import com.company.anketi.entity.PersonVariant;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class Variantresults extends AbstractFrame {
    @Inject
    private Datasource<PersonVariant> personVariantDs;
    @Inject
    private CollectionDatasource<PersonVarianQuestion, UUID> personVarianQuestionsDs;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private Table<PersonVarianQuestion> questionsTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        personVariantDs.setItem((PersonVariant) params.get("anketiVariant"));

        questionsTable.addGeneratedColumn("content", entity -> {
            Label field = componentsFactory.createComponent(Label.class);
            field.setDatasource(questionsTable.getItemDatasource(entity), "content");
            field.setWidth("100%");
            field.setHtmlEnabled(true);
            return field;
        });
    }
}