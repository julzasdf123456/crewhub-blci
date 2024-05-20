package com.lopez.julz.crmcrewhub.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LineAndMetering {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "ServiceConnectionId")
    private String ServiceConnectionId;

    @ColumnInfo(name = "TypeOfService")
    private String TypeOfService;

    @ColumnInfo(name = "MeterSealNumber")
    private String MeterSealNumber;

    @ColumnInfo(name = "IsLeadSeal")
    private String IsLeadSeal;

    @ColumnInfo(name = "MeterStatus")
    private String MeterStatus;

    @ColumnInfo(name = "MeterNumber")
    private String MeterNumber;

    @ColumnInfo(name = "Multiplier")
    private String Multiplier;

    @ColumnInfo(name = "MeterType")
    private String MeterType;

    @ColumnInfo(name = "MeterBrand")
    private String MeterBrand;

    @ColumnInfo(name = "Notes")
    private String Notes;

    @ColumnInfo(name = "ServiceDate")
    private String ServiceDate;

    @ColumnInfo(name = "UserId")
    private String UserId;

    @ColumnInfo(name = "PrivateElectrician")
    private String PrivateElectrician;

    @ColumnInfo(name = "LineLength")
    private String LineLength;

    @ColumnInfo(name = "ConductorType")
    private String ConductorType;

    @ColumnInfo(name = "ConductorSize")
    private String ConductorSize;

    @ColumnInfo(name = "ConductorUnit")
    private String ConductorUnit;

    @ColumnInfo(name = "Status")
    private String Status;

    @ColumnInfo(name = "AccountNumber")
    private String AccountNumber;

    @ColumnInfo(name = "Uploadable")
    private String Uploadable;

    public LineAndMetering() {
    }

    public LineAndMetering(@NonNull String id, String serviceConnectionId, String typeOfService, String meterSealNumber, String isLeadSeal, String meterStatus, String meterNumber, String multiplier, String meterType, String meterBrand, String notes, String serviceDate, String userId, String privateElectrician, String lineLength, String conductorType, String conductorSize, String conductorUnit, String status, String accountNumber, String uploadable) {
        this.id = id;
        ServiceConnectionId = serviceConnectionId;
        TypeOfService = typeOfService;
        MeterSealNumber = meterSealNumber;
        IsLeadSeal = isLeadSeal;
        MeterStatus = meterStatus;
        MeterNumber = meterNumber;
        Multiplier = multiplier;
        MeterType = meterType;
        MeterBrand = meterBrand;
        Notes = notes;
        ServiceDate = serviceDate;
        UserId = userId;
        PrivateElectrician = privateElectrician;
        LineLength = lineLength;
        ConductorType = conductorType;
        ConductorSize = conductorSize;
        ConductorUnit = conductorUnit;
        Status = status;
        AccountNumber = accountNumber;
        Uploadable = uploadable;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getServiceConnectionId() {
        return ServiceConnectionId;
    }

    public void setServiceConnectionId(String serviceConnectionId) {
        ServiceConnectionId = serviceConnectionId;
    }

    public String getTypeOfService() {
        return TypeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        TypeOfService = typeOfService;
    }

    public String getMeterSealNumber() {
        return MeterSealNumber;
    }

    public void setMeterSealNumber(String meterSealNumber) {
        MeterSealNumber = meterSealNumber;
    }

    public String getIsLeadSeal() {
        return IsLeadSeal;
    }

    public void setIsLeadSeal(String isLeadSeal) {
        IsLeadSeal = isLeadSeal;
    }

    public String getMeterStatus() {
        return MeterStatus;
    }

    public void setMeterStatus(String meterStatus) {
        MeterStatus = meterStatus;
    }

    public String getMeterNumber() {
        return MeterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        MeterNumber = meterNumber;
    }

    public String getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(String multiplier) {
        Multiplier = multiplier;
    }

    public String getMeterType() {
        return MeterType;
    }

    public void setMeterType(String meterType) {
        MeterType = meterType;
    }

    public String getMeterBrand() {
        return MeterBrand;
    }

    public void setMeterBrand(String meterBrand) {
        MeterBrand = meterBrand;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(String serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPrivateElectrician() {
        return PrivateElectrician;
    }

    public void setPrivateElectrician(String privateElectrician) {
        PrivateElectrician = privateElectrician;
    }

    public String getLineLength() {
        return LineLength;
    }

    public void setLineLength(String lineLength) {
        LineLength = lineLength;
    }

    public String getConductorType() {
        return ConductorType;
    }

    public void setConductorType(String conductorType) {
        ConductorType = conductorType;
    }

    public String getConductorSize() {
        return ConductorSize;
    }

    public void setConductorSize(String conductorSize) {
        ConductorSize = conductorSize;
    }

    public String getConductorUnit() {
        return ConductorUnit;
    }

    public void setConductorUnit(String conductorUnit) {
        ConductorUnit = conductorUnit;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getUploadable() {
        return Uploadable;
    }

    public void setUploadable(String uploadable) {
        Uploadable = uploadable;
    }
}
