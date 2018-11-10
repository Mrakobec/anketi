package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|personVariant,number")
@Table(name = "ANKETI_PERSON_VARIAN_QUESTIONNAIRE")
@Entity(name = "anketi$PersonVarianQuestionnaire")
public class PersonVarianQuestionnaire extends StandardEntity {
    private static final long serialVersionUID = 2550917079742775466L;

    @Column(name = "NUMBER_")
    protected Integer number;

    @Lob
    @Column(name = "NAME")
    protected String name;

    @OrderBy("orderNumber")
    @OneToMany(mappedBy = "personVarianQuestionnaire")
    protected List<PersonVarianQuestion> questions;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_VARIANT_ID")
    protected PersonVariant personVariant;

    public void setPersonVariant(PersonVariant personVariant) {
        this.personVariant = personVariant;
    }

    public PersonVariant getPersonVariant() {
        return personVariant;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuestions(List<PersonVarianQuestion> questions) {
        this.questions = questions;
    }

    public List<PersonVarianQuestion> getQuestions() {
        return questions;
    }


}