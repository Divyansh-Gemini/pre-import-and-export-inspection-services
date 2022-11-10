package com.ciclabsindia.cic.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Certificate {
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no_pk, place_of_inspection, date_of_inspection, port_of_discharge,
            total_no_of_bags, gross_weight, tare_weight, net_weight, cleanliness_statement,
            quality_statement, packing, weight, conclusion, last_edited_date_time;

    byte[]  marking_of_bag;

    //############################ DEFAULT CONSTRUCTOR ############################
    public Certificate()
    {   }

    //######################### PARAMETERIZED CONSTRUCTOR #########################
    public Certificate(String certificate_no, String report_no, String date, String shipper_name,
                       String shipper_address, String shipper_tel, String shipper_fax, String shipper_gst,
                       String notify_name, String notify_address, String notify_tel, String notify_fax,
                       String description_of_goods, String contract_no, String invoice_no_pk,
                       String place_of_inspection, String date_of_inspection, String port_of_discharge,
                       byte[] marking_of_bag, String total_no_of_bags, String gross_weight, String tare_weight,
                       String net_weight, String cleanliness_statement, String quality_statement, String packing,
                       String weight, String conclusion, String last_edited_date_time) {
        this.certificate_no = certificate_no;
        this.report_no = report_no;
        this.date = date;
        this.shipper_name = shipper_name;
        this.shipper_address = shipper_address;
        this.shipper_tel = shipper_tel;
        this.shipper_fax = shipper_fax;
        this.shipper_gst = shipper_gst;
        this.notify_name = notify_name;
        this.notify_address = notify_address;
        this.notify_tel = notify_tel;
        this.notify_fax = notify_fax;
        this.description_of_goods = description_of_goods;
        this.contract_no = contract_no;
        this.invoice_no_pk = invoice_no_pk;
        this.place_of_inspection = place_of_inspection;
        this.date_of_inspection = date_of_inspection;
        this.port_of_discharge = port_of_discharge;
        this.marking_of_bag = marking_of_bag;
        this.total_no_of_bags = total_no_of_bags;
        this.gross_weight = gross_weight;
        this.tare_weight = tare_weight;
        this.net_weight = net_weight;
        this.cleanliness_statement = cleanliness_statement;
        this.quality_statement = quality_statement;
        this.packing = packing;
        this.weight = weight;
        this.conclusion = conclusion;
        this.last_edited_date_time = last_edited_date_time;
    }

    //############################ SETTER & GETTER ############################
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

    public String getShipper_tel() {
        return shipper_tel;
    }

    public void setShipper_tel(String shipper_tel) {
        this.shipper_tel = shipper_tel;
    }

    public String getShipper_fax() {
        return shipper_fax;
    }

    public void setShipper_fax(String shipper_fax) {
        this.shipper_fax = shipper_fax;
    }

    public String getShipper_gst() {
        return shipper_gst;
    }

    public void setShipper_gst(String shipper_gst) {
        this.shipper_gst = shipper_gst;
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

    public String getNotify_tel() {
        return notify_tel;
    }

    public void setNotify_tel(String notify_tel) {
        this.notify_tel = notify_tel;
    }

    public String getNotify_fax() {
        return notify_fax;
    }

    public void setNotify_fax(String notify_fax) {
        this.notify_fax = notify_fax;
    }

    public String getDescription_of_goods() {
        return description_of_goods;
    }

    public void setDescription_of_goods(String description_of_goods) {
        this.description_of_goods = description_of_goods;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public String getInvoice_no_pk() {
        return invoice_no_pk;
    }

    public void setInvoice_no_pk(String invoice_no_pk) {
        this.invoice_no_pk = invoice_no_pk;
    }

    public String getPlace_of_inspection() {
        return place_of_inspection;
    }

    public void setPlace_of_inspection(String place_of_inspection) {
        this.place_of_inspection = place_of_inspection;
    }

    public String getDate_of_inspection() {
        return date_of_inspection;
    }

    public void setDate_of_inspection(String date_of_inspection) {
        this.date_of_inspection = date_of_inspection;
    }

    public String getPort_of_discharge() {
        return port_of_discharge;
    }

    public void setPort_of_discharge(String port_of_discharge) {
        this.port_of_discharge = port_of_discharge;
    }

    public byte[] getMarking_of_bag() {
        return marking_of_bag;
    }

    public Bitmap getMarking_of_bag_Bitmap(){
        return BitmapFactory.decodeByteArray(getMarking_of_bag(), 0, getMarking_of_bag().length);
    }

    public void setMarking_of_bag(byte[] marking_of_bag) {
        this.marking_of_bag = marking_of_bag;
    }

    public void setMarking_of_bag(Bitmap marking_of_bag_Bitmap)
    {
        // Converting Bitmap to byte[] & passing it to setMarking_of_bag()
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        marking_of_bag_Bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        setMarking_of_bag(byteArrayOutputStream.toByteArray());
    }

    public void setMarking_of_bag(Uri marking_of_bag_Uri)
    {
        // Converting Uri to File & passing it to setMarking_of_bag()
        setMarking_of_bag(new File(marking_of_bag_Uri.getPath()));
    }

    public void setMarking_of_bag(File marking_of_bag_file)
    {
        // Converting File to byte[] & passing it to setMarking_of_bag
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream;
        try
        {
            fileInputStream = new FileInputStream(marking_of_bag_file);
            byte[] marking_of_bag_byteArray = new byte[1024];
            int n;
            while (-1 != (n = fileInputStream.read(marking_of_bag_byteArray)))
                byteArrayOutputStream.write(marking_of_bag_byteArray, 0, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMarking_of_bag(byteArrayOutputStream.toByteArray());
    }

    public String getTotal_no_of_bags() {
        return total_no_of_bags;
    }

    public void setTotal_no_of_bags(String total_no_of_bags) {
        this.total_no_of_bags = total_no_of_bags;
    }

    public String getGross_weight() {
        return gross_weight;
    }

    public void setGross_weight(String gross_weight) {
        this.gross_weight = gross_weight;
    }

    public String getTare_weight() {
        return tare_weight;
    }

    public void setTare_weight(String tare_weight) {
        this.tare_weight = tare_weight;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getCleanliness_statement() {
        return cleanliness_statement;
    }

    public void setCleanliness_statement(String cleanliness_statement) {
        this.cleanliness_statement = cleanliness_statement;
    }

    public String getQuality_statement() {
        return quality_statement;
    }

    public void setQuality_statement(String quality_statement) {
        this.quality_statement = quality_statement;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getLast_edited_date_time() {
        return last_edited_date_time;
    }

    public void setLast_edited_date_time(String last_edited_date_time) {
        this.last_edited_date_time = last_edited_date_time;
    }
}