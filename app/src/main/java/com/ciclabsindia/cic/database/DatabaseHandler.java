package com.ciclabsindia.cic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ciclabsindia.cic.model.Certificate;
import com.ciclabsindia.cic.model.Container;
import com.ciclabsindia.cic.model.Document;
import com.ciclabsindia.cic.model.Draft;
import com.ciclabsindia.cic.model.QualityCheck;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final String TAG = "CIC Labs India";
    public static final int VERSION_CODE = 1;
    public static final String DB_NAME = "cic";

    //############################## DRAFT TABLE ##############################
    public static final String TABLE_DRAFT = "table_draft";
    public static final String DR_CERTIFICATE_NO = "certificate_no";
    public static final String DR_REPORT_NO = "report_no";
    public static final String DR_DATE = "date";
    public static final String DR_SHIPPER_NAME = "shipper_name";
    public static final String DR_SHIPPER_ADDRESS = "shipper_address";
    public static final String DR_CONSIGNEE_NAME = "consignee_name";
    public static final String DR_CONSIGNEE_ADDRESS = "consignee_address";
    public static final String DR_NOTIFY_NAME = "notify_name";
    public static final String DR_NOTIFY_ADDRESS = "notify_address";
    public static final String DR_PORT_OF_LOADING = "port_of_loading";
    public static final String DR_PORT_OF_DISCHARGE = "port_of_discharge";
    public static final String DR_FINAL_DESTINATION = "final_destination";
    public static final String DR_DESCRIPTION_OF_GOODS = "description_of_goods";
    public static final String DR_GROSS_WEIGHT = "gross_weight";
    public static final String DR_NET_WEIGHT = "net_weight";
    public static final String DR_TOTAL_NO_OF_BAGS = "total_no_of_bags";
    public static final String DR_INVOICE_NO_PK = "invoice_no_pk";                       // <-- Primary Key
    public static final String DR_INVOICE_DATE = "invoice_date";
    public static final String DR_PACKING = "packing";
    public static final String DR_BL_NO = "bl_no";
    public static final String DR_LAST_EDITED_DATE_TIME = "last_edited_date_time";


    //############################## CERTIFICATE TABLE ##############################
    public static final String TABLE_CERTIFICATE = "table_certificate";
    public static final String CR_CERTIFICATE_NO = "certificate_no";
    public static final String CR_REPORT_NO = "report_no";
    public static final String CR_DATE = "date";
    public static final String CR_SHIPPER_NAME = "shipper_name";
    public static final String CR_SHIPPER_ADDRESS = "shipper_address";
    public static final String CR_SHIPPER_TEL = "shipper_tel";
    public static final String CR_SHIPPER_FAX = "shipper_fax";
    public static final String CR_SHIPPER_GST = "shipper_gst";
    public static final String CR_NOTIFY_NAME = "notify_name";
    public static final String CR_NOTIFY_ADDRESS = "notify_address";
    public static final String CR_NOTIFY_TEL = "notify_tel";
    public static final String CR_NOTIFY_FAX = "notify_fax";
    public static final String CR_DESCRIPTION_OF_GOODS = "description_of_goods";
    public static final String CR_CONTRACT_NO = "contract_no";
    public static final String CR_INVOICE_NO_PK = "invoice_no_pk";                      // <-- Primary Key
    public static final String CR_PLACE_OF_INSPECTION = "place_of_inspection";
    public static final String CR_DATE_OF_INSPECTION = "date_of_inspection";
    public static final String CR_PORT_OF_DISCHARGE = "port_of_discharge";
    public static final String CR_MARKING_OF_BAG = "marking_of_bag";
    public static final String CR_TOTAL_NO_OF_BAGS = "total_no_of_bags";
    public static final String CR_GROSS_WEIGHT = "gross_weight";
    public static final String CR_TARE_WEIGHT = "tare_weight";
    public static final String CR_NET_WEIGHT = "net_weight";
    public static final String CR_CLEANLINESS_STATEMENT = "cleanliness_statement";
    public static final String CR_QUALITY_STATEMENT = "quality_statement";
    public static final String CR_PACKING = "packing";
    public static final String CR_WEIGHT = "weight";
    public static final String CR_CONCLUSION = "conclusion";
    public static final String CR_LAST_EDITED_DATE_TIME = "last_edited_date_time";


    //############################## QUALITY TABLE ##############################
    public static final String TABLE_QUALITY = "table_quality";
    public static final String Q_QUALITY_CHECK_ID_PK = "quality_check_id_pk";           // <-- Primary Key
    public static final String Q_CATEGORY = "category";
    public static final String Q_CHECK_PARAMETER = "check_parameter";
    public static final String Q_SPECIFICATION_IN_PARTS = "specification_in_parts";
    public static final String Q_SPECIFICATION = "specification";
    public static final String Q_TEST_RESULT = "test_result";
    public static final String Q_EXTRA_WELL_MILLED = "extra_well_milled";
    public static final String Q_INVOICE_NO_FK = "invoice_no_fk";                       // <-- Foreign Key


    //############################## CONTAINER TABLE ##############################
    public static final String TABLE_CONTAINER = "table_container";
    public static final String C_CONTAINER_ID_PK = "container_id_pk";                   // <-- Primary Key
    public static final String C_CONTAINER_NO = "container_no";
    public static final String C_CONTAINER_SIZE = "container_size";
    public static final String C_NO_OF_BAGS = "no_of_bags";
    public static final String C_CONDITION = "condition";
    public static final String C_INVOICE_NO_FK = "invoice_no_fk";                       // <-- Foreign Key


    //############################## DOCUMENT TABLE ##############################
    public static final String TABLE_DOCUMENT = "table_document";
    public static final String DOC_ID_PK = "doc_id_pk";                                 // <-- Primary Key
    public static final String DOC_TYPE = "doc_type";
    public static final String SHIPPER_NAME_FK = "shipper_name_fk";                     // <-- Foreign Key
    public static final String INVOICE_NO_FK = "invoice_no_fk";                         // <-- Foreign Key
    public static final String LAST_EDITED_DATE_TIME_FK = "last_edited_date_time_fk";   // <-- Foreign Key

    public DatabaseHandler(Context context)
    {
        super(context, DB_NAME, null, VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_draft = "CREATE TABLE " + TABLE_DRAFT + " (" + DR_CERTIFICATE_NO + " TEXT, " + DR_REPORT_NO + " TEXT, " + DR_DATE + " TEXT, " +
                DR_SHIPPER_NAME + " TEXT, " + DR_SHIPPER_ADDRESS + " TEXT, " + DR_CONSIGNEE_NAME + " TEXT, " + DR_CONSIGNEE_ADDRESS + " TEXT, " + DR_NOTIFY_NAME +
                " TEXT, " + DR_NOTIFY_ADDRESS + " TEXT, " + DR_PORT_OF_LOADING + " TEXT, " + DR_PORT_OF_DISCHARGE + " TEXT, " + DR_FINAL_DESTINATION + " TEXT, " +
                DR_DESCRIPTION_OF_GOODS + " TEXT, " + DR_GROSS_WEIGHT + " TEXT, " + DR_NET_WEIGHT + " TEXT, " + DR_TOTAL_NO_OF_BAGS + " TEXT, " + DR_INVOICE_NO_PK +
                " TEXT PRIMARY KEY, " + DR_INVOICE_DATE + " TEXT, " + DR_PACKING + " TEXT, " + DR_BL_NO + " TEXT, " + DR_LAST_EDITED_DATE_TIME + " TEXT);";

        String create_table_certificate = "CREATE TABLE " + TABLE_CERTIFICATE + " (" + CR_CERTIFICATE_NO + " TEXT, " + CR_REPORT_NO + " TEXT, " + CR_DATE + " TEXT, " +
                CR_SHIPPER_NAME + " TEXT, " + CR_SHIPPER_ADDRESS + " TEXT, " + CR_SHIPPER_TEL + " TEXT, " + CR_SHIPPER_FAX + " TEXT, " + CR_SHIPPER_GST + " TEXT, " +
                CR_NOTIFY_NAME + " TEXT, " + CR_NOTIFY_ADDRESS + " TEXT, " + CR_NOTIFY_TEL + " TEXT, " + CR_NOTIFY_FAX + " TEXT, " + CR_DESCRIPTION_OF_GOODS + " TEXT, " +
                CR_CONTRACT_NO + " TEXT, " + CR_INVOICE_NO_PK + " TEXT PRIMARY KEY, " + CR_PLACE_OF_INSPECTION + " TEXT, " + CR_DATE_OF_INSPECTION +" TEXT, " +
                CR_PORT_OF_DISCHARGE + " TEXT, " + CR_MARKING_OF_BAG + " BLOB, " + CR_TOTAL_NO_OF_BAGS + " TEXT, " + CR_GROSS_WEIGHT + " TEXT, " + CR_TARE_WEIGHT +
                " TEXT, " + CR_NET_WEIGHT + " TEXT, " + CR_CLEANLINESS_STATEMENT + " TEXT, " + CR_QUALITY_STATEMENT + " TEXT, " + CR_PACKING + " TEXT, " + CR_WEIGHT +
                " TEXT, " + CR_CONCLUSION + " TEXT, " + CR_LAST_EDITED_DATE_TIME + " TEXT);";

        String create_table_quality = "CREATE TABLE " + TABLE_QUALITY + " (" + Q_QUALITY_CHECK_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Q_CATEGORY + " TEXT, " +
                Q_CHECK_PARAMETER + " TEXT, " + Q_SPECIFICATION_IN_PARTS + " TEXT, " + Q_SPECIFICATION + " TEXT, " + Q_TEST_RESULT + " TEXT, " + Q_EXTRA_WELL_MILLED +
                " TEXT, " + Q_INVOICE_NO_FK + " TEXT, FOREIGN KEY (" + Q_INVOICE_NO_FK + ") REFERENCES " + TABLE_CERTIFICATE + " (" + CR_INVOICE_NO_PK + "));";

        String create_table_container = "CREATE TABLE " + TABLE_CONTAINER + " (" + C_CONTAINER_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_CONTAINER_NO +
                " TEXT, " + C_CONTAINER_SIZE + " TEXT, " + C_NO_OF_BAGS + " TEXT, " + C_CONDITION + " TEXT, " + C_INVOICE_NO_FK + " TEXT, FOREIGN KEY (" +
                C_INVOICE_NO_FK + ") REFERENCES " + TABLE_CERTIFICATE + " (" + CR_INVOICE_NO_PK + "));";

        String create_table_document = "CREATE TABLE " + TABLE_DOCUMENT + " (" + DOC_ID_PK + " TEXT PRIMARY KEY, " + DOC_TYPE + " TEXT, " + SHIPPER_NAME_FK +
                " TEXT, " + INVOICE_NO_FK + " TEXT, " + LAST_EDITED_DATE_TIME_FK + " TEXT, FOREIGN KEY (" + SHIPPER_NAME_FK + ") REFERENCES " + TABLE_DRAFT +
                " (" + DR_SHIPPER_NAME + "), FOREIGN KEY (" + SHIPPER_NAME_FK + ") REFERENCES " + TABLE_CERTIFICATE + " (" + CR_SHIPPER_NAME + "), FOREIGN KEY (" +
                INVOICE_NO_FK + ") REFERENCES " + TABLE_DRAFT + " (" + DR_INVOICE_NO_PK + "), FOREIGN KEY (" + INVOICE_NO_FK + ") REFERENCES " + TABLE_CERTIFICATE +
                " (" + CR_INVOICE_NO_PK + "), FOREIGN KEY (" + LAST_EDITED_DATE_TIME_FK + ") REFERENCES " + TABLE_DRAFT + " (" + DR_LAST_EDITED_DATE_TIME +
                "), FOREIGN KEY (" + LAST_EDITED_DATE_TIME_FK + ") REFERENCES " + TABLE_CERTIFICATE + " (" + CR_LAST_EDITED_DATE_TIME + "));";

        db.execSQL(create_table_draft);
        db.execSQL(create_table_certificate);
        db.execSQL(create_table_quality);
        db.execSQL(create_table_container);
        db.execSQL(create_table_document);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRAFT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTAINER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUALITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCUMENT);
        onCreate(db);
    }

    //######################################## INSERT DRAFT ##################################
    public long insertDraft (Draft draft) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DR_CERTIFICATE_NO, draft.getCertificate_no());
        values.put(DR_REPORT_NO, draft.getReport_no());
        values.put(DR_DATE, draft.getDate());
        values.put(DR_SHIPPER_NAME, draft.getShipper_name());
        values.put(DR_SHIPPER_ADDRESS, draft.getShipper_address());
        values.put(DR_CONSIGNEE_NAME, draft.getConsignee_name());
        values.put(DR_CONSIGNEE_ADDRESS, draft.getConsignee_address());
        values.put(DR_NOTIFY_NAME, draft.getNotify_name());
        values.put(DR_NOTIFY_ADDRESS, draft.getNotify_address());
        values.put(DR_PORT_OF_LOADING, draft.getPort_of_loading());
        values.put(DR_PORT_OF_DISCHARGE, draft.getPort_of_discharge());
        values.put(DR_FINAL_DESTINATION, draft.getFinal_destination());
        values.put(DR_DESCRIPTION_OF_GOODS, draft.getDescription_of_goods());
        values.put(DR_GROSS_WEIGHT, draft.getGross_weight());
        values.put(DR_NET_WEIGHT, draft.getNet_weight());
        values.put(DR_TOTAL_NO_OF_BAGS, draft.getTotal_no_of_bags());
        values.put(DR_INVOICE_NO_PK, draft.getInvoice_no_pk());
        values.put(DR_INVOICE_DATE, draft.getInvoice_date());
        values.put(DR_PACKING, draft.getPacking());
        values.put(DR_BL_NO, draft.getBl_no());
        values.put(DR_LAST_EDITED_DATE_TIME, draft.getLast_edited_date_time());
        return db.insert(TABLE_DRAFT, null, values);
    }

    //######################################## INSERT CERTIFICATE ##################################
    public long insertCertificate (Certificate certificate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CR_CERTIFICATE_NO, certificate.getCertificate_no());
        values.put(CR_REPORT_NO, certificate.getReport_no());
        values.put(CR_DATE, certificate.getDate());
        values.put(CR_SHIPPER_NAME, certificate.getShipper_name());
        values.put(CR_SHIPPER_ADDRESS, certificate.getShipper_address());
        values.put(CR_SHIPPER_TEL, certificate.getShipper_tel());
        values.put(CR_SHIPPER_FAX, certificate.getShipper_fax());
        values.put(CR_SHIPPER_GST, certificate.getShipper_gst());
        values.put(CR_NOTIFY_NAME, certificate.getNotify_name());
        values.put(CR_NOTIFY_ADDRESS, certificate.getNotify_address());
        values.put(CR_NOTIFY_TEL, certificate.getNotify_tel());
        values.put(CR_NOTIFY_FAX, certificate.getNotify_fax());
        values.put(CR_DESCRIPTION_OF_GOODS, certificate.getDescription_of_goods());
        values.put(CR_CONTRACT_NO, certificate.getContract_no());
        values.put(CR_INVOICE_NO_PK, certificate.getInvoice_no_pk());
        values.put(CR_PLACE_OF_INSPECTION, certificate.getPlace_of_inspection());
        values.put(CR_DATE_OF_INSPECTION, certificate.getDate_of_inspection());
        values.put(CR_PORT_OF_DISCHARGE, certificate.getPort_of_discharge());
        values.put(CR_MARKING_OF_BAG, certificate.getMarking_of_bag());
        values.put(CR_TOTAL_NO_OF_BAGS, certificate.getTotal_no_of_bags());
        values.put(CR_GROSS_WEIGHT, certificate.getGross_weight());
        values.put(CR_TARE_WEIGHT, certificate.getTare_weight());
        values.put(CR_NET_WEIGHT, certificate.getNet_weight());
        values.put(CR_CLEANLINESS_STATEMENT, certificate.getCleanliness_statement());
        values.put(CR_QUALITY_STATEMENT, certificate.getQuality_statement());
        values.put(CR_PACKING, certificate.getPacking());
        values.put(CR_WEIGHT, certificate.getWeight());
        values.put(CR_CONCLUSION, certificate.getConclusion());
        values.put(CR_LAST_EDITED_DATE_TIME, certificate.getLast_edited_date_time());
        return db.insert(TABLE_CERTIFICATE, null, values);
    }

    //######################################## INSERT CONTAINER ##################################
    public long insertContainer (Container container) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_CONTAINER_NO, container.getContainer_no());
        values.put(C_CONTAINER_SIZE, container.getContainer_size());
        values.put(C_NO_OF_BAGS, container.getNo_of_bags());
        values.put(C_CONDITION, container.getCondition());
        values.put(C_INVOICE_NO_FK, container.getInvoice_no_fk());
        return db.insert(TABLE_CONTAINER, null, values);
    }

    //######################################## INSERT QUALITY_CHECK ##################################
    public long insertQualityCheck (QualityCheck qualityCheck) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_CATEGORY, qualityCheck.getCategory());
        values.put(Q_CHECK_PARAMETER, qualityCheck.getCheck_parameter());
        values.put(Q_SPECIFICATION_IN_PARTS, qualityCheck.getSpecification_in_parts());
        values.put(Q_SPECIFICATION, qualityCheck.getSpecification());
        values.put(Q_TEST_RESULT, qualityCheck.getTest_result());
        values.put(Q_EXTRA_WELL_MILLED, qualityCheck.getExtra_well_milled());
        values.put(Q_INVOICE_NO_FK, qualityCheck.getInvoice_no_fk());
        return db.insert(TABLE_QUALITY, null, values);
    }

    //######################################## INSERT DOCUMENT ##################################
    public long insertDocument (Document document) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOC_ID_PK, document.getDoc_id_pk());
        values.put(DOC_TYPE, document.getDoc_type());
        values.put(SHIPPER_NAME_FK, document.getShipper_name_fk());
        values.put(INVOICE_NO_FK, document.getInvoice_no_fk());
        values.put(LAST_EDITED_DATE_TIME_FK, document.getLast_edited_date_time_fk());
        return db.insert(TABLE_DOCUMENT, null, values);
    }

    //######################################## UPDATE DRAFT ##################################
    public long updateDraft (Draft draft, String old_invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DR_CERTIFICATE_NO, draft.getCertificate_no());
        values.put(DR_REPORT_NO, draft.getReport_no());
        values.put(DR_DATE, draft.getDate());
        values.put(DR_SHIPPER_NAME, draft.getShipper_name());
        values.put(DR_SHIPPER_ADDRESS, draft.getShipper_address());
        values.put(DR_CONSIGNEE_NAME, draft.getConsignee_name());
        values.put(DR_CONSIGNEE_ADDRESS, draft.getConsignee_address());
        values.put(DR_NOTIFY_NAME, draft.getNotify_name());
        values.put(DR_NOTIFY_ADDRESS, draft.getNotify_address());
        values.put(DR_PORT_OF_LOADING, draft.getPort_of_loading());
        values.put(DR_PORT_OF_DISCHARGE, draft.getPort_of_discharge());
        values.put(DR_FINAL_DESTINATION, draft.getFinal_destination());
        values.put(DR_DESCRIPTION_OF_GOODS, draft.getDescription_of_goods());
        values.put(DR_GROSS_WEIGHT, draft.getGross_weight());
        values.put(DR_NET_WEIGHT, draft.getNet_weight());
        values.put(DR_TOTAL_NO_OF_BAGS, draft.getTotal_no_of_bags());
        values.put(DR_INVOICE_NO_PK, draft.getInvoice_no_pk());
        values.put(DR_INVOICE_DATE, draft.getInvoice_date());
        values.put(DR_PACKING, draft.getPacking());
        values.put(DR_BL_NO, draft.getBl_no());
        values.put(DR_LAST_EDITED_DATE_TIME, draft.getLast_edited_date_time());
        return db.update(TABLE_DRAFT, values, DR_INVOICE_NO_PK + "=?", new String[] {old_invoice_no});
    }

    //######################################## UPDATE CERTIFICATE ##################################
    public long updateCertificate (Certificate certificate, String old_invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CR_CERTIFICATE_NO, certificate.getCertificate_no());
        values.put(CR_REPORT_NO, certificate.getReport_no());
        values.put(CR_DATE, certificate.getDate());
        values.put(CR_SHIPPER_NAME, certificate.getShipper_name());
        values.put(CR_SHIPPER_ADDRESS, certificate.getShipper_address());
        values.put(CR_SHIPPER_TEL, certificate.getShipper_tel());
        values.put(CR_SHIPPER_FAX, certificate.getShipper_fax());
        values.put(CR_SHIPPER_GST, certificate.getShipper_gst());
        values.put(CR_NOTIFY_NAME, certificate.getNotify_name());
        values.put(CR_NOTIFY_ADDRESS, certificate.getNotify_address());
        values.put(CR_NOTIFY_TEL, certificate.getNotify_tel());
        values.put(CR_NOTIFY_FAX, certificate.getNotify_fax());
        values.put(CR_DESCRIPTION_OF_GOODS, certificate.getDescription_of_goods());
        values.put(CR_CONTRACT_NO, certificate.getContract_no());
        values.put(CR_INVOICE_NO_PK, certificate.getInvoice_no_pk());
        values.put(CR_PLACE_OF_INSPECTION, certificate.getPlace_of_inspection());
        values.put(CR_DATE_OF_INSPECTION, certificate.getDate_of_inspection());
        values.put(CR_PORT_OF_DISCHARGE, certificate.getPort_of_discharge());
        values.put(CR_MARKING_OF_BAG, certificate.getMarking_of_bag());
        values.put(CR_TOTAL_NO_OF_BAGS, certificate.getTotal_no_of_bags());
        values.put(CR_GROSS_WEIGHT, certificate.getGross_weight());
        values.put(CR_TARE_WEIGHT, certificate.getTare_weight());
        values.put(CR_NET_WEIGHT, certificate.getNet_weight());
        values.put(CR_CLEANLINESS_STATEMENT, certificate.getCleanliness_statement());
        values.put(CR_QUALITY_STATEMENT, certificate.getQuality_statement());
        values.put(CR_PACKING, certificate.getPacking());
        values.put(CR_WEIGHT, certificate.getWeight());
        values.put(CR_CONCLUSION, certificate.getConclusion());
        values.put(CR_LAST_EDITED_DATE_TIME, certificate.getLast_edited_date_time());
        return db.update(TABLE_CERTIFICATE, values, CR_INVOICE_NO_PK + "=?", new String[] {old_invoice_no});
    }

    //######################################## UPDATE QUALITY_CHECK ##################################
    public long updateQualityCheck (QualityCheck qualityCheck, String old_invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_CATEGORY, qualityCheck.getCategory());
        values.put(Q_CHECK_PARAMETER, qualityCheck.getCheck_parameter());
        values.put(Q_SPECIFICATION_IN_PARTS, qualityCheck.getSpecification_in_parts());
        values.put(Q_SPECIFICATION, qualityCheck.getSpecification());
        values.put(Q_TEST_RESULT, qualityCheck.getTest_result());
        values.put(Q_EXTRA_WELL_MILLED, qualityCheck.getExtra_well_milled());
        values.put(Q_INVOICE_NO_FK, qualityCheck.getInvoice_no_fk());
        return db.update(TABLE_QUALITY, values, Q_INVOICE_NO_FK + "=?", new String[] {old_invoice_no});
    }

    //######################################## UPDATE CONTAINER ##################################
    public long updateContainer (Container container, String old_invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_CONTAINER_NO, container.getContainer_no());
        values.put(C_CONTAINER_SIZE, container.getContainer_size());
        values.put(C_NO_OF_BAGS, container.getNo_of_bags());
        values.put(C_CONDITION, container.getCondition());
        values.put(C_INVOICE_NO_FK, container.getInvoice_no_fk());
        return db.update(TABLE_CONTAINER, values, C_INVOICE_NO_FK + "=?", new String[] {old_invoice_no});
    }

    //######################################## UPDATE DOCUMENT ##################################
    public long updateDocument (Document document, String old_invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOC_ID_PK, document.getDoc_id_pk());
        values.put(DOC_TYPE, document.getDoc_type());
        values.put(SHIPPER_NAME_FK, document.getShipper_name_fk());
        values.put(INVOICE_NO_FK, document.getInvoice_no_fk());
        values.put(LAST_EDITED_DATE_TIME_FK, document.getLast_edited_date_time_fk());
        return db.update(TABLE_DOCUMENT, values, INVOICE_NO_FK + "=?", new String[] {old_invoice_no});
    }

    //######################################## READ SINGLE DRAFT ##################################
    public Draft getSingleDraft (String invoice_no) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DRAFT + " WHERE " + DR_INVOICE_NO_PK + "=?", new String[]{invoice_no});

        if (cursor.moveToFirst())
            return new Draft(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
                    cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19),
                    cursor.getString(20));
        else
            return null;
    }

    //######################################## READ SINGLE CERTIFICATE ##################################
    public Certificate getSingleCertificate (String invoice_no) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CERTIFICATE + " WHERE " + CR_INVOICE_NO_PK + "=?", new String[]{invoice_no});
        if (cursor.moveToFirst())
            return new Certificate(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
                    cursor.getString(16), cursor.getString(17), cursor.getBlob(18), cursor.getString(19),
                    cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                    cursor.getString(24), cursor.getString(25), cursor.getString(26), cursor.getString(27),
                    cursor.getString(28));
        else
            return null;
    }

    //######################################## READ SINGLE QUALITY_CHECK ##################################
    public QualityCheck getSingleQualityCheck (String invoice_no) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUALITY + " WHERE " + Q_INVOICE_NO_FK + "=?", new String[]{invoice_no});

        if (cursor.moveToFirst())
            return new QualityCheck(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6));
        else
            return null;
    }

    //######################################## READ SINGLE CONTAINER ##################################
    public Container getSingleContainer (String invoice_no) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTAINER + " WHERE " + C_INVOICE_NO_FK + "=?", new String[]{invoice_no});

        if (cursor.moveToFirst())
            return new Container(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4));
        else
            return null;
    }

    //############################## READ ALL DOCUMENTS ##############################
    public List<Document> getAllDocuments () {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Document> list = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_DOCUMENT;
        String query = "SELECT * FROM " + TABLE_DOCUMENT + " ORDER BY " + LAST_EDITED_DATE_TIME_FK + " DESC, " + INVOICE_NO_FK + " DESC;";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                Document document = new Document();
                document.setDoc_id_pk(cursor.getString(0));
                document.setDoc_type(cursor.getString(1));
                document.setShipper_name_fk(cursor.getString(2));
                document.setInvoice_no_fk(cursor.getString(3));
                document.setLast_edited_date_time_fk(cursor.getString(4));
                list.add(document);
            } while (cursor.moveToNext());
        return list;
    }


    //######################################## READ SINGLE CERTIFICATE ##################################
    public List<Document> getSearchedDocuments (String newText) {
        Log.i(TAG, String.format("getSearchedDocuments(%s)", newText));
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Document> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_DOCUMENT + " WHERE (" + DOC_TYPE + " LIKE '%" + newText + "%' OR " +
                INVOICE_NO_FK + " LIKE '%" + newText + "%' OR " + SHIPPER_NAME_FK + " LIKE '%" + newText + "%')";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            do {
                Document document = new Document();
                document.setDoc_id_pk(cursor.getString(0));
                document.setDoc_type(cursor.getString(1));
                document.setShipper_name_fk(cursor.getString(2));
                document.setInvoice_no_fk(cursor.getString(3));
                document.setLast_edited_date_time_fk(cursor.getString(4));
                list.add(document);
            } while (cursor.moveToNext());
        return list;
    }

    //######################################## DELETE DOCUMENT ##################################
    public long deleteDocument (String invoice_no, String doc_type) {
        if (doc_type.equals("draft"))
            deleteDraft(invoice_no);
        else if (doc_type.equals("certificate")) {
            deleteContainer(invoice_no);
            deleteQualityCheck(invoice_no);
            deleteCertificate(invoice_no);
        }
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_DOCUMENT, INVOICE_NO_FK + "=?", new String[] {String.valueOf(invoice_no)});
    }

    //######################################## DELETE DRAFT ##################################
    public long deleteDraft (String invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_DRAFT, DR_INVOICE_NO_PK + "=?", new String[] {String.valueOf(invoice_no)});
    }

    //######################################## DELETE CERTIFICATE ##################################
    public long deleteCertificate (String invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_CERTIFICATE, CR_INVOICE_NO_PK + "=?", new String[] {String.valueOf(invoice_no)});
    }

    //######################################## DELETE QUALITY CHECK ##################################
    public long deleteQualityCheck (String invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_QUALITY, Q_INVOICE_NO_FK + "=?", new String[] {String.valueOf(invoice_no)});
    }

    //######################################## DELETE CONTAINER ##################################
    public long deleteContainer (String invoice_no) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_CONTAINER, C_INVOICE_NO_FK + "=?", new String[] {String.valueOf(invoice_no)});
    }
}