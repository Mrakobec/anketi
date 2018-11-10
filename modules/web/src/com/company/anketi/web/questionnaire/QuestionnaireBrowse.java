package com.company.anketi.web.questionnaire;

import com.company.anketi.entity.Questionnaire;
import com.company.anketi.exception.AnketiException;
import com.company.anketi.service.AnketiService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.LinkButton;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.gui.components.renderers.WebComponentRenderer;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class QuestionnaireBrowse extends AbstractLookup {

    @Inject
    private DataGrid<Questionnaire> questionnaireGrid;
    @Inject
    private AnketiService anketiService;
    @Inject
    private GroupDatasource<Questionnaire, UUID> questionnairesDs;
    @Inject
    private ComponentsFactory componentsFactory;

    public void changeStatus() {
        openEditor("statusmanagement$Status.change",
                questionnaireGrid.getSingleSelected(),
                WindowManager.OpenType.DIALOG,
                questionnaireGrid.getDatasource()).addCloseWithCommitListener(() -> {
            questionnaireGrid.getDatasource().commit();
        });
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        questionnairesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                questionnaireGrid.getAction("anketaTest").setEnabled(true);
                questionnaireGrid.getAction("anketaTest").setVisible(true);

                questionnaireGrid.getActionNN("edit").setEnabled(true);
                questionnaireGrid.getActionNN("remove").setEnabled(true);
                //questionnaireGrid.getActionNN("edit").setEnabled(e.getItem().getStatus().getId().equals("anketiStat_onCompletion"));
                //questionnaireGrid.getActionNN("remove").setEnabled(e.getItem().getStatus().getId().equals("anketiStat_onCompletion"));
            } else {
                questionnaireGrid.getAction("testQtPackage").setEnabled(false);
                questionnaireGrid.getAction("testQtPackage").setVisible(false);
            }
        });


        //кнопка статус
        questionnaireGrid.getAction("changeStatus").setEnabled(false);

        questionnairesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                questionnaireGrid.getAction("changeStatus").setEnabled(true);
            }
        });

        DataGrid.Column status = questionnaireGrid.addGeneratedColumn("Status", new DataGrid.ColumnGenerator<Questionnaire, LinkButton>() {
            @Override
            public LinkButton getValue(DataGrid.ColumnGeneratorEvent<Questionnaire> event) {
                LinkButton button = componentsFactory.createComponent(LinkButton.class);
                button.setCaption(event.getItem().getStatus().getName());
                button.setAction(new BaseAction("showStatusLog").withHandler(e -> {
                    openWindow("statusmanagement$StatusLog.browse",
                            WindowManager.OpenType.DIALOG,
                            ParamsMap.of("recordId", event.getItem(),
                                    "recordMetaClass", event.getItem().getMetaClass().getName()));
                }));
                return button;
            }

            @Override
            public Class<LinkButton> getType() {
                return LinkButton.class;
            }
        });
        status.setRenderer(new WebComponentRenderer());

    }

    public void anketaTest() {
        try {
            openWindow("anketi$VariantSolve",
                    WindowManager.OpenType.THIS_TAB,
                    ParamsMap.of("anketiPersonVarian", anketiService.generateVariantForTest(questionnaireGrid.getSingleSelected())));
        } catch (AnketiException ae) {
            showNotification(ae.getMessage(), NotificationType.WARNING);
        }
    }
}