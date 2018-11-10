package com.company.anketi.web.personvariant;

import com.company.anketi.entity.PersonVariant;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;

import javax.inject.Inject;
import java.util.Map;

public class Variantinfo extends AbstractFrame {
    @Inject
    private Label variantName;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        PersonVariant anketiVariant = (PersonVariant) params.get("anketiVariant");

        variantName.setValue(String.format("<div><font size=%s><b>%s</b></font></div>", "\"4\"", anketiVariant.getName()));
    }
}