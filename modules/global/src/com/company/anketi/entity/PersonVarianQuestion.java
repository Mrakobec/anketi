package com.company.anketi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

@NamePattern("%s %s|personVariant,orderNumber")
@Table(name = "ANKETI_PERSON_VARIAN_QUESTION")
@Entity(name = "anketi$PersonVarianQuestion")
public class PersonVarianQuestion extends StandardEntity {
    private static final long serialVersionUID = -7881883813304916526L;

    @Column(name = "ORDER_NUMBER")
    protected Integer orderNumber;

    @Column(name = "TYPE_")
    protected String type;

    @Lob
    @Column(name = "CONTENT")
    protected String content;

    @OrderBy("orderNumber")
    @OneToMany(mappedBy = "personVarianQuestion")
    protected List<PersonVariantAnswer> answers;

    @Column(name = "QUESTION_ID")
    protected UUID questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_VARIAN_QUESTIONNAIRE_ID")
    protected PersonVarianQuestionnaire personVarianQuestionnaire;

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


    public void setPersonVarianQuestionnaire(PersonVarianQuestionnaire personVarianQuestionnaire) {
        this.personVarianQuestionnaire = personVarianQuestionnaire;
    }

    public PersonVarianQuestionnaire getPersonVarianQuestionnaire() {
        return personVarianQuestionnaire;
    }


    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setType(QuestionTypeEnum type) {
        this.type = type == null ? null : type.getId();
    }

    public QuestionTypeEnum getType() {
        return type == null ? null : QuestionTypeEnum.fromId(type);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAnswers(List<PersonVariantAnswer> answers) {
        this.answers = answers;
    }

    public List<PersonVariantAnswer> getAnswers() {
        return answers;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public UUID getQuestionId() {
        return questionId;
    }


}