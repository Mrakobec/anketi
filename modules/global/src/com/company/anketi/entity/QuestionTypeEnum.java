package com.company.anketi.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum QuestionTypeEnum implements EnumClass<String> {

    Single("single"),
    Multi("multi"),
    Detailed("detailed");

    private String id;

    QuestionTypeEnum(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static QuestionTypeEnum fromId(String id) {
        for (QuestionTypeEnum at : QuestionTypeEnum.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}