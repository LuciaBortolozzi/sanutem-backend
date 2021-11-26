package com.sanutem.backend.model;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "GeneralParameter")
public class GeneralParameter {

    @Id
    @SequenceGenerator(
            name = "general_parameter_sequence",
            sequenceName = "general_parameter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "general_parameter_sequence"
    )
    @Column(
            name = "idGeneralParameter",
            updatable = false
    )
    private Integer idGeneralParameter;
    @Column(
            name = "valueGeneralParameter",
            updatable = false
    )
    private String valueGeneralParameter;
    @Column(
            name = "descriptionGeneralParameter"/*,
            nullable = false*/
    )
    private String descriptionGeneralParameter;
    @Column(
            name = "timeStampGeneralParameter"/*,
            nullable = false*/
    )
    private Date timeStampGeneralParameter;

    public GeneralParameter() {
    }

    public GeneralParameter(Integer idGeneralParameter, String valueGeneralParameter,
                            String descriptionGeneralParameter, Date timeStampGeneralParameter) {
        this.idGeneralParameter = idGeneralParameter;
        this.valueGeneralParameter = valueGeneralParameter;
        this.descriptionGeneralParameter = descriptionGeneralParameter;
        this.timeStampGeneralParameter = timeStampGeneralParameter;
    }

    public Integer getIdGeneralParameter() {
        return idGeneralParameter;
    }

    public void setIdGeneralParameter(Integer idGeneralParameter) {
        this.idGeneralParameter = idGeneralParameter;
    }

    public String getValueGeneralParameter() {
        return valueGeneralParameter;
    }

    public void setValueGeneralParameter(String valueGeneralParameter) {
        this.valueGeneralParameter = valueGeneralParameter;
    }

    public String getDescriptionGeneralParameter() {
        return descriptionGeneralParameter;
    }

    public void setDescriptionGeneralParameter(String descriptionGeneralParameter) {
        this.descriptionGeneralParameter = descriptionGeneralParameter;
    }

    public Date getTimeStampGeneralParameter() {
        return timeStampGeneralParameter;
    }

    public void setTimeStampGeneralParameter(Date timeStampGeneralParameter) {
        this.timeStampGeneralParameter = timeStampGeneralParameter;
    }
}
