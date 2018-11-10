package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|personVarianQuestion,orderNumber")
@Table(name = "ANKETI_PERSON_VARIANT_ANSWER")
@Entity(name = "anketi$PersonVariantAnswer")
public class PersonVariantAnswer extends StandardEntity {
    private static final long serialVersionUID = 9162817542118858808L;

    @Column(name = "ORDER_NUMBER")
    protected Integer orderNumber;

    @Lob
    @Column(name = "CONTENT")
    protected String content;

    @Column(name = "SELECTED_BY_PERSON")
    protected Boolean selectedByPerson;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_VARIAN_QUESTION_ID")
    protected PersonVarianQuestion personVarianQuestion;

    public void setPersonVarianQuestion(PersonVarianQuestion personVarianQuestion) {
        this.personVarianQuestion = personVarianQuestion;
    }

    public PersonVarianQuestion getPersonVarianQuestion() {
        return personVarianQuestion;
    }


    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSelectedByPerson(Boolean selectedByPerson) {
        this.selectedByPerson = selectedByPerson;
    }

    public Boolean getSelectedByPerson() {
        return selectedByPerson;
    }


}