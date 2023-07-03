package com.lopez.julz.crmcrewhub.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MeterInstallation {
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "ServiceConnectionId")
    private String ServiceConnectionId;

    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "NewMeterNumber")
    private String NewMeterNumber;

    @ColumnInfo(name = "NewMeterBrand")
    private String NewMeterBrand;

    @ColumnInfo(name = "NewMeterSize")
    private String NewMeterSize;

    @ColumnInfo(name = "NewMeterType")
    private String NewMeterType;

    @ColumnInfo(name = "NewMeterAmperes")
    private String NewMeterAmperes;

    @ColumnInfo(name = "NewMeterInitialReading")
    private String NewMeterInitialReading;

    @ColumnInfo(name = "NewMeterLineToNeutral")
    private String NewMeterLineToNeutral;

    @ColumnInfo(name = "NewMeterLineToGround")
    private String NewMeterLineToGround;

    @ColumnInfo(name = "NewMeterNeutralToGround")
    private String NewMeterNeutralToGround;

    @ColumnInfo(name = "DateInstalled")
    private String DateInstalled;

    @ColumnInfo(name = "NewMeterMultiplier")
    private String NewMeterMultiplier;

    @ColumnInfo(name = "TransfomerCapacity")
    private String TransfomerCapacity;

    @ColumnInfo(name = "TransformerID")
    private String TransformerID;

    @ColumnInfo(name = "PoleID")
    private String PoleID;

    @ColumnInfo(name = "CTSerialNumber")
    private String CTSerialNumber;

    @ColumnInfo(name = "NewMeterRemarks")
    private String NewMeterRemarks;

    @ColumnInfo(name = "OldMeterNumber")
    private String OldMeterNumber;

    @ColumnInfo(name = "OldMeterBrand")
    private String OldMeterBrand;

    @ColumnInfo(name = "OldMeterSize")
    private String OldMeterSize;

    @ColumnInfo(name = "OldMeterType")
    private String OldMeterType;

    @ColumnInfo(name = "DateRemoved")
    private String DateRemoved;

    @ColumnInfo(name = "ReasonForChanging")
    private String ReasonForChanging;

    @ColumnInfo(name = "OldMeterMultiplier")
    private String OldMeterMultiplier;

    @ColumnInfo(name = "OldMeterRemarks")
    private String OldMeterRemarks;

    @ColumnInfo(name = "InstalledBy")
    private String InstalledBy;

    @ColumnInfo(name = "CheckedBy")
    private String CheckedBy;

    @ColumnInfo(name = "Witness")
    private String Witness;

    @ColumnInfo(name = "BLCIRepresentative")
    private String BLCIRepresentative;

    @ColumnInfo(name = "ApprovedBy")
    private String ApprovedBy;

    @ColumnInfo(name = "RemovedBy")
    private String RemovedBy;

    @ColumnInfo(name = "CustomerSignature")
    private String CustomerSignature;

    @ColumnInfo(name = "WitnessSignature")
    private String WitnessSignature;

    @ColumnInfo(name = "InstalledBySignature")
    private String InstalledBySignature;

    @ColumnInfo(name = "ApprovedBySignature")
    private String ApprovedBySignature;

    @ColumnInfo(name = "CheckedBySignature")
    private String CheckedBySignature;

    @ColumnInfo(name = "BLCIRepresentativeSignature")
    private String BLCIRepresentativeSignature;

    @ColumnInfo(name = "UploadStatus")
    private String UploadStatus;

    public MeterInstallation() {}

    public MeterInstallation(@NonNull String id, String serviceConnectionId, String type, String newMeterNumber, String newMeterBrand, String newMeterSize, String newMeterType, String newMeterAmperes, String newMeterInitialReading, String newMeterLineToNeutral, String newMeterLineToGround, String newMeterNeutralToGround, String dateInstalled, String newMeterMultiplier, String transfomerCapacity, String transformerID, String poleID, String CTSerialNumber, String newMeterRemarks, String oldMeterNumber, String oldMeterBrand, String oldMeterSize, String oldMeterType, String dateRemoved, String reasonForChanging, String oldMeterMultiplier, String oldMeterRemarks, String installedBy, String checkedBy, String witness, String BLCIRepresentative, String approvedBy, String removedBy, String customerSignature, String witnessSignature, String installedBySignature, String approvedBySignature, String checkedBySignature, String BLCIRepresentativeSignature, String uploadStatus) {
        this.id = id;
        ServiceConnectionId = serviceConnectionId;
        Type = type;
        NewMeterNumber = newMeterNumber;
        NewMeterBrand = newMeterBrand;
        NewMeterSize = newMeterSize;
        NewMeterType = newMeterType;
        NewMeterAmperes = newMeterAmperes;
        NewMeterInitialReading = newMeterInitialReading;
        NewMeterLineToNeutral = newMeterLineToNeutral;
        NewMeterLineToGround = newMeterLineToGround;
        NewMeterNeutralToGround = newMeterNeutralToGround;
        DateInstalled = dateInstalled;
        NewMeterMultiplier = newMeterMultiplier;
        TransfomerCapacity = transfomerCapacity;
        TransformerID = transformerID;
        PoleID = poleID;
        this.CTSerialNumber = CTSerialNumber;
        NewMeterRemarks = newMeterRemarks;
        OldMeterNumber = oldMeterNumber;
        OldMeterBrand = oldMeterBrand;
        OldMeterSize = oldMeterSize;
        OldMeterType = oldMeterType;
        DateRemoved = dateRemoved;
        ReasonForChanging = reasonForChanging;
        OldMeterMultiplier = oldMeterMultiplier;
        OldMeterRemarks = oldMeterRemarks;
        InstalledBy = installedBy;
        CheckedBy = checkedBy;
        Witness = witness;
        this.BLCIRepresentative = BLCIRepresentative;
        ApprovedBy = approvedBy;
        RemovedBy = removedBy;
        CustomerSignature = customerSignature;
        WitnessSignature = witnessSignature;
        InstalledBySignature = installedBySignature;
        ApprovedBySignature = approvedBySignature;
        CheckedBySignature = checkedBySignature;
        this.BLCIRepresentativeSignature = BLCIRepresentativeSignature;
        UploadStatus = uploadStatus;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNewMeterNumber() {
        return NewMeterNumber;
    }

    public void setNewMeterNumber(String newMeterNumber) {
        NewMeterNumber = newMeterNumber;
    }

    public String getNewMeterBrand() {
        return NewMeterBrand;
    }

    public void setNewMeterBrand(String newMeterBrand) {
        NewMeterBrand = newMeterBrand;
    }

    public String getNewMeterSize() {
        return NewMeterSize;
    }

    public void setNewMeterSize(String newMeterSize) {
        NewMeterSize = newMeterSize;
    }

    public String getNewMeterType() {
        return NewMeterType;
    }

    public void setNewMeterType(String newMeterType) {
        NewMeterType = newMeterType;
    }

    public String getNewMeterAmperes() {
        return NewMeterAmperes;
    }

    public void setNewMeterAmperes(String newMeterAmperes) {
        NewMeterAmperes = newMeterAmperes;
    }

    public String getNewMeterInitialReading() {
        return NewMeterInitialReading;
    }

    public void setNewMeterInitialReading(String newMeterInitialReading) {
        NewMeterInitialReading = newMeterInitialReading;
    }

    public String getNewMeterLineToNeutral() {
        return NewMeterLineToNeutral;
    }

    public void setNewMeterLineToNeutral(String newMeterLineToNeutral) {
        NewMeterLineToNeutral = newMeterLineToNeutral;
    }

    public String getNewMeterLineToGround() {
        return NewMeterLineToGround;
    }

    public void setNewMeterLineToGround(String newMeterLineToGround) {
        NewMeterLineToGround = newMeterLineToGround;
    }

    public String getNewMeterNeutralToGround() {
        return NewMeterNeutralToGround;
    }

    public void setNewMeterNeutralToGround(String newMeterNeutralToGround) {
        NewMeterNeutralToGround = newMeterNeutralToGround;
    }

    public String getDateInstalled() {
        return DateInstalled;
    }

    public void setDateInstalled(String dateInstalled) {
        DateInstalled = dateInstalled;
    }

    public String getNewMeterMultiplier() {
        return NewMeterMultiplier;
    }

    public void setNewMeterMultiplier(String newMeterMultiplier) {
        NewMeterMultiplier = newMeterMultiplier;
    }

    public String getTransfomerCapacity() {
        return TransfomerCapacity;
    }

    public void setTransfomerCapacity(String transfomerCapacity) {
        TransfomerCapacity = transfomerCapacity;
    }

    public String getTransformerID() {
        return TransformerID;
    }

    public void setTransformerID(String transformerID) {
        TransformerID = transformerID;
    }

    public String getPoleID() {
        return PoleID;
    }

    public void setPoleID(String poleID) {
        PoleID = poleID;
    }

    public String getCTSerialNumber() {
        return CTSerialNumber;
    }

    public void setCTSerialNumber(String CTSerialNumber) {
        this.CTSerialNumber = CTSerialNumber;
    }

    public String getNewMeterRemarks() {
        return NewMeterRemarks;
    }

    public void setNewMeterRemarks(String newMeterRemarks) {
        NewMeterRemarks = newMeterRemarks;
    }

    public String getOldMeterNumber() {
        return OldMeterNumber;
    }

    public void setOldMeterNumber(String oldMeterNumber) {
        OldMeterNumber = oldMeterNumber;
    }

    public String getOldMeterBrand() {
        return OldMeterBrand;
    }

    public void setOldMeterBrand(String oldMeterBrand) {
        OldMeterBrand = oldMeterBrand;
    }

    public String getOldMeterSize() {
        return OldMeterSize;
    }

    public void setOldMeterSize(String oldMeterSize) {
        OldMeterSize = oldMeterSize;
    }

    public String getOldMeterType() {
        return OldMeterType;
    }

    public void setOldMeterType(String oldMeterType) {
        OldMeterType = oldMeterType;
    }

    public String getDateRemoved() {
        return DateRemoved;
    }

    public void setDateRemoved(String dateRemoved) {
        DateRemoved = dateRemoved;
    }

    public String getReasonForChanging() {
        return ReasonForChanging;
    }

    public void setReasonForChanging(String reasonForChanging) {
        ReasonForChanging = reasonForChanging;
    }

    public String getOldMeterMultiplier() {
        return OldMeterMultiplier;
    }

    public void setOldMeterMultiplier(String oldMeterMultiplier) {
        OldMeterMultiplier = oldMeterMultiplier;
    }

    public String getOldMeterRemarks() {
        return OldMeterRemarks;
    }

    public void setOldMeterRemarks(String oldMeterRemarks) {
        OldMeterRemarks = oldMeterRemarks;
    }

    public String getInstalledBy() {
        return InstalledBy;
    }

    public void setInstalledBy(String installedBy) {
        InstalledBy = installedBy;
    }

    public String getCheckedBy() {
        return CheckedBy;
    }

    public void setCheckedBy(String checkedBy) {
        CheckedBy = checkedBy;
    }

    public String getWitness() {
        return Witness;
    }

    public void setWitness(String witness) {
        Witness = witness;
    }

    public String getBLCIRepresentative() {
        return BLCIRepresentative;
    }

    public void setBLCIRepresentative(String BLCIRepresentative) {
        this.BLCIRepresentative = BLCIRepresentative;
    }

    public String getApprovedBy() {
        return ApprovedBy;
    }

    public void setApprovedBy(String approvedBy) {
        ApprovedBy = approvedBy;
    }

    public String getRemovedBy() {
        return RemovedBy;
    }

    public void setRemovedBy(String removedBy) {
        RemovedBy = removedBy;
    }

    public String getCustomerSignature() {
        return CustomerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        CustomerSignature = customerSignature;
    }

    public String getWitnessSignature() {
        return WitnessSignature;
    }

    public void setWitnessSignature(String witnessSignature) {
        WitnessSignature = witnessSignature;
    }

    public String getInstalledBySignature() {
        return InstalledBySignature;
    }

    public void setInstalledBySignature(String installedBySignature) {
        InstalledBySignature = installedBySignature;
    }

    public String getApprovedBySignature() {
        return ApprovedBySignature;
    }

    public void setApprovedBySignature(String approvedBySignature) {
        ApprovedBySignature = approvedBySignature;
    }

    public String getCheckedBySignature() {
        return CheckedBySignature;
    }

    public void setCheckedBySignature(String checkedBySignature) {
        CheckedBySignature = checkedBySignature;
    }

    public String getBLCIRepresentativeSignature() {
        return BLCIRepresentativeSignature;
    }

    public void setBLCIRepresentativeSignature(String BLCIRepresentativeSignature) {
        this.BLCIRepresentativeSignature = BLCIRepresentativeSignature;
    }

    public String getUploadStatus() {
        return UploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        UploadStatus = uploadStatus;
    }
}
