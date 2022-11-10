package com.ciclabsindia.cic.model;

public class Draft {

    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address, notify_name,
            notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods, gross_weight,
            net_weight, total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no, last_edited_date_time;

    public Draft()
    {   }

    public Draft(String certificate_no, String report_no, String date, String shipper_name, String shipper_address,
                 String consignee_name, String consignee_address, String notify_name, String notify_address,
                 String port_of_loading, String port_of_discharge, String final_destination, String description_of_goods,
                 String gross_weight, String net_weight, String total_no_of_bags, String invoice_no_pk, String invoice_date,
                 String packing, String bl_no, String last_edited_date_time) {
        this.certificate_no = certificate_no;
        this.report_no = report_no;
        this.date = date;
        this.shipper_name = shipper_name;
        this.shipper_address = shipper_address;
        this.consignee_name = consignee_name;
        this.consignee_address = consignee_address;
        this.notify_name = notify_name;
        this.notify_address = notify_address;
        this.port_of_loading = port_of_loading;
        this.port_of_discharge = port_of_discharge;
        this.final_destination = final_destination;
        this.description_of_goods = description_of_goods;
        this.gross_weight = gross_weight;
        this.net_weight = net_weight;
        this.total_no_of_bags = total_no_of_bags;
        this.invoice_no_pk = invoice_no_pk;
        this.invoice_date = invoice_date;
        this.packing = packing;
        this.bl_no = bl_no;
        this.last_edited_date_time = last_edited_date_time;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public String getShipper_address() {
        return shipper_address;
    }

    public void setShipper_address(String shipper_address) {
        this.shipper_address = shipper_address;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getConsignee_address() {
        return consignee_address;
    }

    public void setConsignee_address(String consignee_address) {
        this.consignee_address = consignee_address;
    }

    public String getNotify_name() {
        return notify_name;
    }

    public void setNotify_name(String notify_name) {
        this.notify_name = notify_name;
    }

    public String getNotify_address() {
        return notify_address;
    }

    public void setNotify_address(String notify_address) {
        this.notify_address = notify_address;
    }

    public String getPort_of_loading() {
        return port_of_loading;
    }

    public void setPort_of_loading(String port_of_loading) {
        this.port_of_loading = port_of_loading;
    }

    public String getPort_of_discharge() {
        return port_of_discharge;
    }

    public void setPort_of_discharge(String port_of_discharge) {
        this.port_of_discharge = port_of_discharge;
    }

    public String getFinal_destination() {
        return final_destination;
    }

    public void setFinal_destination(String final_destination) {
        this.final_destination = final_destination;
    }

    public String getDescription_of_goods() {
        return description_of_goods;
    }

    public void setDescription_of_goods(String description_of_goods) {
        this.description_of_goods = description_of_goods;
    }

    public String getGross_weight() {
        return gross_weight;
    }

    public void setGross_weight(String gross_weight) {
        this.gross_weight = gross_weight;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getTotal_no_of_bags() {
        return total_no_of_bags;
    }

    public void setTotal_no_of_bags(String total_no_of_bags) {
        this.total_no_of_bags = total_no_of_bags;
    }

    public String getInvoice_no_pk() {
        return invoice_no_pk;
    }

    public void setInvoice_no_pk(String invoice_no_pk) {
        this.invoice_no_pk = invoice_no_pk;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getBl_no() {
        return bl_no;
    }

    public void setBl_no(String bl_no) {
        this.bl_no = bl_no;
    }

    public String getLast_edited_date_time() {
        return last_edited_date_time;
    }

    public void setLast_edited_date_time(String last_edited_date_time) {
        this.last_edited_date_time = last_edited_date_time;
    }
}