package com.ciclabsindia.cic.model;

public class QualityCheck {
    String category, check_parameter, specification_in_parts, specification, test_result, extra_well_milled, invoice_no_fk;

    //############################ DEFAULT CONSTRUCTOR ############################
    public QualityCheck()
    {   }

    //######################### PARAMETERIZED CONSTRUCTOR #########################
    public QualityCheck(String category, String check_parameter, String specification_in_parts, String specification,
                        String test_result, String extra_well_milled, String invoice_no_fk) {
        this.category = category;
        this.check_parameter = check_parameter;
        this.specification_in_parts = specification_in_parts;
        this.specification = specification;
        this.test_result = test_result;
        this.extra_well_milled = extra_well_milled;
        this.invoice_no_fk = invoice_no_fk;
    }

    //############################ SETTER & GETTER ############################
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCheck_parameter() {
        return check_parameter;
    }

    public void setCheck_parameter(String check_parameter) {
        this.check_parameter = check_parameter;
    }

    public String getSpecification_in_parts() {
        return specification_in_parts;
    }

    public void setSpecification_in_parts(String specification_in_parts) {
        this.specification_in_parts = specification_in_parts;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public String getExtra_well_milled() {
        return extra_well_milled;
    }

    public void setExtra_well_milled(String extra_well_milled) {
        this.extra_well_milled = extra_well_milled;
    }

    public String getInvoice_no_fk() {
        return invoice_no_fk;
    }

    public void setInvoice_no_fk(String invoice_no_fk) {
        this.invoice_no_fk = invoice_no_fk;
    }
}