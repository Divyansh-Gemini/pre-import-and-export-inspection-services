package com.ciclabsindia.cic.model;

public class Document {

    String doc_id_pk, doc_type, shipper_name_fk, invoice_no_fk, last_edited_date_time_fk;

    public Document()
    {   }

    public Document(String doc_id_pk, String doc_type, String shipper_name_fk, String invoice_no_fk, String last_edited_date_time_fk) {
        this.doc_id_pk = doc_id_pk;
        this.doc_type = doc_type;
        this.shipper_name_fk = shipper_name_fk;
        this.invoice_no_fk = invoice_no_fk;
        this.last_edited_date_time_fk = last_edited_date_time_fk;
    }

    public String getDoc_id_pk() {
        return doc_id_pk;
    }

    public void setDoc_id_pk(String doc_id_pk) {
        this.doc_id_pk = doc_id_pk;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getShipper_name_fk() {
        return shipper_name_fk;
    }

    public void setShipper_name_fk(String shipper_name_fk) {
        this.shipper_name_fk = shipper_name_fk;
    }

    public String getInvoice_no_fk() {
        return invoice_no_fk;
    }

    public void setInvoice_no_fk(String invoice_no_fk) {
        this.invoice_no_fk = invoice_no_fk;
    }

    public String getLast_edited_date_time_fk() {
        return last_edited_date_time_fk;
    }

    public void setLast_edited_date_time_fk(String last_edited_date_time_fk) {
        this.last_edited_date_time_fk = last_edited_date_time_fk;
    }
}