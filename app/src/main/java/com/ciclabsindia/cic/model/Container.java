package com.ciclabsindia.cic.model;

public class Container {
    String container_no, container_size, no_of_bags, condition, invoice_no_fk;

    //############################ DEFAULT CONSTRUCTOR ############################
    public Container()
    {   }

    //######################### PARAMETERIZED CONSTRUCTOR #########################
    public Container(String container_no, String container_size, String no_of_bags, String condition, String invoice_no_fk) {
        this.container_no = container_no;
        this.container_size = container_size;
        this.no_of_bags = no_of_bags;
        this.condition = condition;
        this.invoice_no_fk = invoice_no_fk;
    }

    //############################ SETTER & GETTER ############################
    public String getContainer_no() {
        return container_no;
    }

    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    public String getContainer_size() {
        return container_size;
    }

    public void setContainer_size(String container_size) {
        this.container_size = container_size;
    }

    public String getNo_of_bags() {
        return no_of_bags;
    }

    public void setNo_of_bags(String no_of_bags) {
        this.no_of_bags = no_of_bags;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInvoice_no_fk() {
        return invoice_no_fk;
    }

    public void setInvoice_no_fk(String invoice_no_fk) {
        this.invoice_no_fk = invoice_no_fk;
    }
}