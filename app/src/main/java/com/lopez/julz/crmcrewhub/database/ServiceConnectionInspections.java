package com.lopez.julz.crmcrewhub.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ServiceConnectionInspections {
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "ServiceConnectionId")
    private String ServiceConnectionId;

    @ColumnInfo(name = "SEMainCircuitBreakerAsPlan")
    private String SEMainCircuitBreakerAsPlan;

    @ColumnInfo(name = "SEMainCircuitBreakerAsInstalled")
    private String SEMainCircuitBreakerAsInstalled;

    @ColumnInfo(name = "SENoOfBranchesAsPlan")
    private String SENoOfBranchesAsPlan;

    @ColumnInfo(name = "SENoOfBranchesAsInstalled")
    private String SENoOfBranchesAsInstalled;

    @ColumnInfo(name = "PoleNumber")
    private String PoleNumber;

    @ColumnInfo(name = "PoleGIEstimatedDiameter")
    private String PoleGIEstimatedDiameter;

    @ColumnInfo(name = "PoleGIHeight")
    private String PoleGIHeight;

    @ColumnInfo(name = "PoleGINoOfLiftPoles")
    private String PoleGINoOfLiftPoles;

    @ColumnInfo(name = "PoleConcreteEstimatedDiameter")
    private String PoleConcreteEstimatedDiameter;

    @ColumnInfo(name = "PoleConcreteHeight")
    private String PoleConcreteHeight;

    @ColumnInfo(name = "PoleConcreteNoOfLiftPoles")
    private String PoleConcreteNoOfLiftPoles;

    @ColumnInfo(name = "PoleHardwoodEstimatedDiameter")
    private String PoleHardwoodEstimatedDiameter;

    @ColumnInfo(name = "PoleHardwoodHeight")
    private String PoleHardwoodHeight;

    @ColumnInfo(name = "PoleHardwoodNoOfLiftPoles")
    private String PoleHardwoodNoOfLiftPoles;

    @ColumnInfo(name = "PoleRemarks")
    private String PoleRemarks;

    @ColumnInfo(name = "SDWSizeAsPlan")
    private String SDWSizeAsPlan;

    @ColumnInfo(name = "SDWSizeAsInstalled")
    private String SDWSizeAsInstalled;

    @ColumnInfo(name = "SDWLengthAsPlan")
    private String SDWLengthAsPlan;

    @ColumnInfo(name = "SDWLengthAsInstalled")
    private String SDWLengthAsInstalled;

    @ColumnInfo(name = "GeoBuilding")
    private String GeoBuilding;

    @ColumnInfo(name = "GeoTappingPole")
    private String GeoTappingPole;

    @ColumnInfo(name = "GeoMeteringPole")
    private String GeoMeteringPole;

    @ColumnInfo(name = "GeoSEPole")
    private String GeoSEPole;

    @ColumnInfo(name = "FirstNeighborName")
    private String FirstNeighborName;

    @ColumnInfo(name = "FirstNeighborMeterSerial")
    private String FirstNeighborMeterSerial;

    @ColumnInfo(name = "SecondNeighborName")
    private String SecondNeighborName;

    @ColumnInfo(name = "SecondNeighborMeterSerial")
    private String SecondNeighborMeterSerial;

    @ColumnInfo(name = "EngineerInchargeName")
    private String EngineerInchargeName;

    @ColumnInfo(name = "EngineerInchargeTitle")
    private String EngineerInchargeTitle;

    @ColumnInfo(name = "EngineerInchargeLicenseNo")
    private String EngineerInchargeLicenseNo;

    @ColumnInfo(name = "EngineerInchargeLicenseValidity")
    private String EngineerInchargeLicenseValidity;

    @ColumnInfo(name = "EngineerInchargeContactNo")
    private String EngineerInchargeContactNo;

    @ColumnInfo(name = "Status")
    private String Status;

    @ColumnInfo(name = "Inspector")
    private String Inspector;

    @ColumnInfo(name = "DateOfVerification")
    private String DateOfVerification;

    @ColumnInfo(name = "EstimatedDateForReinspection")
    private String EstimatedDateForReinspection;

    @ColumnInfo(name = "Notes")
    private String Notes;


    @ColumnInfo(name = "InspectionSchedule")
    private String InspectionSchedule;

    @ColumnInfo(name = "ReInspectionSchedule")
    private String ReInspectionSchedule;

    @ColumnInfo(name = "LightingOutlets")
    private String LightingOutlets;

    @ColumnInfo(name = "ConvenienceOutlets")
    private String ConvenienceOutlets;

    @ColumnInfo(name = "Motor")
    private String Motor;

    @ColumnInfo(name = "TotalLoad")
    private String TotalLoad;

    @ColumnInfo(name = "ContractedDemand")
    private String ContractedDemand;

    @ColumnInfo(name = "ContractedEnergy")
    private String ContractedEnergy;

    @ColumnInfo(name = "DistanceFromSecondaryLine")
    private String DistanceFromSecondaryLine;

    @ColumnInfo(name = "SizeOfSecondary")
    private String SizeOfSecondary;

    @ColumnInfo(name = "SizeOfSDW")
    private String SizeOfSDW;

    @ColumnInfo(name = "TypeOfSDW")
    private String TypeOfSDW;

    @ColumnInfo(name = "ServiceEntranceStatus")
    private String ServiceEntranceStatus;

    @ColumnInfo(name = "HeightOfSDW")
    private String HeightOfSDW;

    @ColumnInfo(name = "DistanceFromTransformer")
    private String DistanceFromTransformer;

    @ColumnInfo(name = "SizeOfTransformer")
    private String SizeOfTransformer;

    @ColumnInfo(name = "TransformerNo")
    private String TransformerNo;

    @ColumnInfo(name = "PoleNo")
    private String PoleNo;

    @ColumnInfo(name = "ConnectedFeeder")
    private String ConnectedFeeder;

    @ColumnInfo(name = "SizeOfSvcPoles")
    private String SizeOfSvcPoles;

    @ColumnInfo(name = "HeightOfSvcPoles")
    private String HeightOfSvcPoles;

    @ColumnInfo(name = "LinePassingPrivateProperty")
    private String LinePassingPrivateProperty;

    @ColumnInfo(name = "WrittenConsentByPropertyOwner")
    private String WrittenConsentByPropertyOwner;

    @ColumnInfo(name = "ObstructionOfLines")
    private String ObstructionOfLines;

    @ColumnInfo(name = "LinePassingRoads")
    private String LinePassingRoads;

    @ColumnInfo(name = "Recommendation")
    private String Recommendation;

    @ColumnInfo(name = "ForPayment")
    private String ForPayment;

    public ServiceConnectionInspections() {}

    public ServiceConnectionInspections(@NonNull String id, String serviceConnectionId, String SEMainCircuitBreakerAsPlan, String SEMainCircuitBreakerAsInstalled, String SENoOfBranchesAsPlan, String SENoOfBranchesAsInstalled, String poleNumber, String poleGIEstimatedDiameter, String poleGIHeight, String poleGINoOfLiftPoles, String poleConcreteEstimatedDiameter, String poleConcreteHeight, String poleConcreteNoOfLiftPoles, String poleHardwoodEstimatedDiameter, String poleHardwoodHeight, String poleHardwoodNoOfLiftPoles, String poleRemarks, String SDWSizeAsPlan, String SDWSizeAsInstalled, String SDWLengthAsPlan, String SDWLengthAsInstalled, String geoBuilding, String geoTappingPole, String geoMeteringPole, String geoSEPole, String firstNeighborName, String firstNeighborMeterSerial, String secondNeighborName, String secondNeighborMeterSerial, String engineerInchargeName, String engineerInchargeTitle, String engineerInchargeLicenseNo, String engineerInchargeLicenseValidity, String engineerInchargeContactNo, String status, String inspector, String dateOfVerification, String estimatedDateForReinspection, String notes, String inspectionSchedule, String reInspectionSchedule, String lightingOutlets, String convenienceOutlets, String motor, String totalLoad, String contractedDemand, String contractedEnergy, String distanceFromSecondaryLine, String sizeOfSecondary, String sizeOfSDW, String typeOfSDW, String serviceEntranceStatus, String heightOfSDW, String distanceFromTransformer, String sizeOfTransformer, String transformerNo, String poleNo, String connectedFeeder, String sizeOfSvcPoles, String heightOfSvcPoles, String linePassingPrivateProperty, String writtenConsentByPropertyOwner, String obstructionOfLines, String linePassingRoads, String recommendation, String forPayment) {
        this.id = id;
        ServiceConnectionId = serviceConnectionId;
        this.SEMainCircuitBreakerAsPlan = SEMainCircuitBreakerAsPlan;
        this.SEMainCircuitBreakerAsInstalled = SEMainCircuitBreakerAsInstalled;
        this.SENoOfBranchesAsPlan = SENoOfBranchesAsPlan;
        this.SENoOfBranchesAsInstalled = SENoOfBranchesAsInstalled;
        PoleNumber = poleNumber;
        PoleGIEstimatedDiameter = poleGIEstimatedDiameter;
        PoleGIHeight = poleGIHeight;
        PoleGINoOfLiftPoles = poleGINoOfLiftPoles;
        PoleConcreteEstimatedDiameter = poleConcreteEstimatedDiameter;
        PoleConcreteHeight = poleConcreteHeight;
        PoleConcreteNoOfLiftPoles = poleConcreteNoOfLiftPoles;
        PoleHardwoodEstimatedDiameter = poleHardwoodEstimatedDiameter;
        PoleHardwoodHeight = poleHardwoodHeight;
        PoleHardwoodNoOfLiftPoles = poleHardwoodNoOfLiftPoles;
        PoleRemarks = poleRemarks;
        this.SDWSizeAsPlan = SDWSizeAsPlan;
        this.SDWSizeAsInstalled = SDWSizeAsInstalled;
        this.SDWLengthAsPlan = SDWLengthAsPlan;
        this.SDWLengthAsInstalled = SDWLengthAsInstalled;
        GeoBuilding = geoBuilding;
        GeoTappingPole = geoTappingPole;
        GeoMeteringPole = geoMeteringPole;
        GeoSEPole = geoSEPole;
        FirstNeighborName = firstNeighborName;
        FirstNeighborMeterSerial = firstNeighborMeterSerial;
        SecondNeighborName = secondNeighborName;
        SecondNeighborMeterSerial = secondNeighborMeterSerial;
        EngineerInchargeName = engineerInchargeName;
        EngineerInchargeTitle = engineerInchargeTitle;
        EngineerInchargeLicenseNo = engineerInchargeLicenseNo;
        EngineerInchargeLicenseValidity = engineerInchargeLicenseValidity;
        EngineerInchargeContactNo = engineerInchargeContactNo;
        Status = status;
        Inspector = inspector;
        DateOfVerification = dateOfVerification;
        EstimatedDateForReinspection = estimatedDateForReinspection;
        Notes = notes;
        InspectionSchedule = inspectionSchedule;
        ReInspectionSchedule = reInspectionSchedule;
        LightingOutlets = lightingOutlets;
        ConvenienceOutlets = convenienceOutlets;
        Motor = motor;
        TotalLoad = totalLoad;
        ContractedDemand = contractedDemand;
        ContractedEnergy = contractedEnergy;
        DistanceFromSecondaryLine = distanceFromSecondaryLine;
        SizeOfSecondary = sizeOfSecondary;
        SizeOfSDW = sizeOfSDW;
        TypeOfSDW = typeOfSDW;
        ServiceEntranceStatus = serviceEntranceStatus;
        HeightOfSDW = heightOfSDW;
        DistanceFromTransformer = distanceFromTransformer;
        SizeOfTransformer = sizeOfTransformer;
        TransformerNo = transformerNo;
        PoleNo = poleNo;
        ConnectedFeeder = connectedFeeder;
        SizeOfSvcPoles = sizeOfSvcPoles;
        HeightOfSvcPoles = heightOfSvcPoles;
        LinePassingPrivateProperty = linePassingPrivateProperty;
        WrittenConsentByPropertyOwner = writtenConsentByPropertyOwner;
        ObstructionOfLines = obstructionOfLines;
        LinePassingRoads = linePassingRoads;
        Recommendation = recommendation;
        ForPayment = forPayment;
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

    public String getSEMainCircuitBreakerAsPlan() {
        return SEMainCircuitBreakerAsPlan;
    }

    public void setSEMainCircuitBreakerAsPlan(String SEMainCircuitBreakerAsPlan) {
        this.SEMainCircuitBreakerAsPlan = SEMainCircuitBreakerAsPlan;
    }

    public String getSEMainCircuitBreakerAsInstalled() {
        return SEMainCircuitBreakerAsInstalled;
    }

    public void setSEMainCircuitBreakerAsInstalled(String SEMainCircuitBreakerAsInstalled) {
        this.SEMainCircuitBreakerAsInstalled = SEMainCircuitBreakerAsInstalled;
    }

    public String getSENoOfBranchesAsPlan() {
        return SENoOfBranchesAsPlan;
    }

    public void setSENoOfBranchesAsPlan(String SENoOfBranchesAsPlan) {
        this.SENoOfBranchesAsPlan = SENoOfBranchesAsPlan;
    }

    public String getSENoOfBranchesAsInstalled() {
        return SENoOfBranchesAsInstalled;
    }

    public void setSENoOfBranchesAsInstalled(String SENoOfBranchesAsInstalled) {
        this.SENoOfBranchesAsInstalled = SENoOfBranchesAsInstalled;
    }

    public String getPoleNumber() {
        return PoleNumber;
    }

    public void setPoleNumber(String poleNumber) {
        PoleNumber = poleNumber;
    }

    public String getPoleGIEstimatedDiameter() {
        return PoleGIEstimatedDiameter;
    }

    public void setPoleGIEstimatedDiameter(String poleGIEstimatedDiameter) {
        PoleGIEstimatedDiameter = poleGIEstimatedDiameter;
    }

    public String getPoleGIHeight() {
        return PoleGIHeight;
    }

    public void setPoleGIHeight(String poleGIHeight) {
        PoleGIHeight = poleGIHeight;
    }

    public String getPoleGINoOfLiftPoles() {
        return PoleGINoOfLiftPoles;
    }

    public void setPoleGINoOfLiftPoles(String poleGINoOfLiftPoles) {
        PoleGINoOfLiftPoles = poleGINoOfLiftPoles;
    }

    public String getPoleConcreteEstimatedDiameter() {
        return PoleConcreteEstimatedDiameter;
    }

    public void setPoleConcreteEstimatedDiameter(String poleConcreteEstimatedDiameter) {
        PoleConcreteEstimatedDiameter = poleConcreteEstimatedDiameter;
    }

    public String getPoleConcreteHeight() {
        return PoleConcreteHeight;
    }

    public void setPoleConcreteHeight(String poleConcreteHeight) {
        PoleConcreteHeight = poleConcreteHeight;
    }

    public String getPoleConcreteNoOfLiftPoles() {
        return PoleConcreteNoOfLiftPoles;
    }

    public void setPoleConcreteNoOfLiftPoles(String poleConcreteNoOfLiftPoles) {
        PoleConcreteNoOfLiftPoles = poleConcreteNoOfLiftPoles;
    }

    public String getPoleHardwoodEstimatedDiameter() {
        return PoleHardwoodEstimatedDiameter;
    }

    public void setPoleHardwoodEstimatedDiameter(String poleHardwoodEstimatedDiameter) {
        PoleHardwoodEstimatedDiameter = poleHardwoodEstimatedDiameter;
    }

    public String getPoleHardwoodHeight() {
        return PoleHardwoodHeight;
    }

    public void setPoleHardwoodHeight(String poleHardwoodHeight) {
        PoleHardwoodHeight = poleHardwoodHeight;
    }

    public String getPoleHardwoodNoOfLiftPoles() {
        return PoleHardwoodNoOfLiftPoles;
    }

    public void setPoleHardwoodNoOfLiftPoles(String poleHardwoodNoOfLiftPoles) {
        PoleHardwoodNoOfLiftPoles = poleHardwoodNoOfLiftPoles;
    }

    public String getPoleRemarks() {
        return PoleRemarks;
    }

    public void setPoleRemarks(String poleRemarks) {
        PoleRemarks = poleRemarks;
    }

    public String getSDWSizeAsPlan() {
        return SDWSizeAsPlan;
    }

    public void setSDWSizeAsPlan(String SDWSizeAsPlan) {
        this.SDWSizeAsPlan = SDWSizeAsPlan;
    }

    public String getSDWSizeAsInstalled() {
        return SDWSizeAsInstalled;
    }

    public void setSDWSizeAsInstalled(String SDWSizeAsInstalled) {
        this.SDWSizeAsInstalled = SDWSizeAsInstalled;
    }

    public String getSDWLengthAsPlan() {
        return SDWLengthAsPlan;
    }

    public void setSDWLengthAsPlan(String SDWLengthAsPlan) {
        this.SDWLengthAsPlan = SDWLengthAsPlan;
    }

    public String getSDWLengthAsInstalled() {
        return SDWLengthAsInstalled;
    }

    public void setSDWLengthAsInstalled(String SDWLengthAsInstalled) {
        this.SDWLengthAsInstalled = SDWLengthAsInstalled;
    }

    public String getGeoBuilding() {
        return GeoBuilding;
    }

    public void setGeoBuilding(String geoBuilding) {
        GeoBuilding = geoBuilding;
    }

    public String getGeoTappingPole() {
        return GeoTappingPole;
    }

    public void setGeoTappingPole(String geoTappingPole) {
        GeoTappingPole = geoTappingPole;
    }

    public String getGeoMeteringPole() {
        return GeoMeteringPole;
    }

    public void setGeoMeteringPole(String geoMeteringPole) {
        GeoMeteringPole = geoMeteringPole;
    }

    public String getGeoSEPole() {
        return GeoSEPole;
    }

    public void setGeoSEPole(String geoSEPole) {
        GeoSEPole = geoSEPole;
    }

    public String getFirstNeighborName() {
        return FirstNeighborName;
    }

    public void setFirstNeighborName(String firstNeighborName) {
        FirstNeighborName = firstNeighborName;
    }

    public String getFirstNeighborMeterSerial() {
        return FirstNeighborMeterSerial;
    }

    public void setFirstNeighborMeterSerial(String firstNeighborMeterSerial) {
        FirstNeighborMeterSerial = firstNeighborMeterSerial;
    }

    public String getSecondNeighborName() {
        return SecondNeighborName;
    }

    public void setSecondNeighborName(String secondNeighborName) {
        SecondNeighborName = secondNeighborName;
    }

    public String getSecondNeighborMeterSerial() {
        return SecondNeighborMeterSerial;
    }

    public void setSecondNeighborMeterSerial(String secondNeighborMeterSerial) {
        SecondNeighborMeterSerial = secondNeighborMeterSerial;
    }

    public String getEngineerInchargeName() {
        return EngineerInchargeName;
    }

    public void setEngineerInchargeName(String engineerInchargeName) {
        EngineerInchargeName = engineerInchargeName;
    }

    public String getEngineerInchargeTitle() {
        return EngineerInchargeTitle;
    }

    public void setEngineerInchargeTitle(String engineerInchargeTitle) {
        EngineerInchargeTitle = engineerInchargeTitle;
    }

    public String getEngineerInchargeLicenseNo() {
        return EngineerInchargeLicenseNo;
    }

    public void setEngineerInchargeLicenseNo(String engineerInchargeLicenseNo) {
        EngineerInchargeLicenseNo = engineerInchargeLicenseNo;
    }

    public String getEngineerInchargeLicenseValidity() {
        return EngineerInchargeLicenseValidity;
    }

    public void setEngineerInchargeLicenseValidity(String engineerInchargeLicenseValidity) {
        EngineerInchargeLicenseValidity = engineerInchargeLicenseValidity;
    }

    public String getEngineerInchargeContactNo() {
        return EngineerInchargeContactNo;
    }

    public void setEngineerInchargeContactNo(String engineerInchargeContactNo) {
        EngineerInchargeContactNo = engineerInchargeContactNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getInspector() {
        return Inspector;
    }

    public void setInspector(String inspector) {
        Inspector = inspector;
    }

    public String getDateOfVerification() {
        return DateOfVerification;
    }

    public void setDateOfVerification(String dateOfVerification) {
        DateOfVerification = dateOfVerification;
    }

    public String getEstimatedDateForReinspection() {
        return EstimatedDateForReinspection;
    }

    public void setEstimatedDateForReinspection(String estimatedDateForReinspection) {
        EstimatedDateForReinspection = estimatedDateForReinspection;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getInspectionSchedule() {
        return InspectionSchedule;
    }

    public void setInspectionSchedule(String inspectionSchedule) {
        InspectionSchedule = inspectionSchedule;
    }

    public String getReInspectionSchedule() {
        return ReInspectionSchedule;
    }

    public void setReInspectionSchedule(String reInspectionSchedule) {
        ReInspectionSchedule = reInspectionSchedule;
    }

    public String getLightingOutlets() {
        return LightingOutlets;
    }

    public void setLightingOutlets(String lightingOutlets) {
        LightingOutlets = lightingOutlets;
    }

    public String getConvenienceOutlets() {
        return ConvenienceOutlets;
    }

    public void setConvenienceOutlets(String convenienceOutlets) {
        ConvenienceOutlets = convenienceOutlets;
    }

    public String getMotor() {
        return Motor;
    }

    public void setMotor(String motor) {
        Motor = motor;
    }

    public String getTotalLoad() {
        return TotalLoad;
    }

    public void setTotalLoad(String totalLoad) {
        TotalLoad = totalLoad;
    }

    public String getContractedDemand() {
        return ContractedDemand;
    }

    public void setContractedDemand(String contractedDemand) {
        ContractedDemand = contractedDemand;
    }

    public String getContractedEnergy() {
        return ContractedEnergy;
    }

    public void setContractedEnergy(String contractedEnergy) {
        ContractedEnergy = contractedEnergy;
    }

    public String getDistanceFromSecondaryLine() {
        return DistanceFromSecondaryLine;
    }

    public void setDistanceFromSecondaryLine(String distanceFromSecondaryLine) {
        DistanceFromSecondaryLine = distanceFromSecondaryLine;
    }

    public String getSizeOfSecondary() {
        return SizeOfSecondary;
    }

    public void setSizeOfSecondary(String sizeOfSecondary) {
        SizeOfSecondary = sizeOfSecondary;
    }

    public String getSizeOfSDW() {
        return SizeOfSDW;
    }

    public void setSizeOfSDW(String sizeOfSDW) {
        SizeOfSDW = sizeOfSDW;
    }

    public String getTypeOfSDW() {
        return TypeOfSDW;
    }

    public void setTypeOfSDW(String typeOfSDW) {
        TypeOfSDW = typeOfSDW;
    }

    public String getServiceEntranceStatus() {
        return ServiceEntranceStatus;
    }

    public void setServiceEntranceStatus(String serviceEntranceStatus) {
        ServiceEntranceStatus = serviceEntranceStatus;
    }

    public String getHeightOfSDW() {
        return HeightOfSDW;
    }

    public void setHeightOfSDW(String heightOfSDW) {
        HeightOfSDW = heightOfSDW;
    }

    public String getDistanceFromTransformer() {
        return DistanceFromTransformer;
    }

    public void setDistanceFromTransformer(String distanceFromTransformer) {
        DistanceFromTransformer = distanceFromTransformer;
    }

    public String getSizeOfTransformer() {
        return SizeOfTransformer;
    }

    public void setSizeOfTransformer(String sizeOfTransformer) {
        SizeOfTransformer = sizeOfTransformer;
    }

    public String getTransformerNo() {
        return TransformerNo;
    }

    public void setTransformerNo(String transformerNo) {
        TransformerNo = transformerNo;
    }

    public String getPoleNo() {
        return PoleNo;
    }

    public void setPoleNo(String poleNo) {
        PoleNo = poleNo;
    }

    public String getConnectedFeeder() {
        return ConnectedFeeder;
    }

    public void setConnectedFeeder(String connectedFeeder) {
        ConnectedFeeder = connectedFeeder;
    }

    public String getSizeOfSvcPoles() {
        return SizeOfSvcPoles;
    }

    public void setSizeOfSvcPoles(String sizeOfSvcPoles) {
        SizeOfSvcPoles = sizeOfSvcPoles;
    }

    public String getHeightOfSvcPoles() {
        return HeightOfSvcPoles;
    }

    public void setHeightOfSvcPoles(String heightOfSvcPoles) {
        HeightOfSvcPoles = heightOfSvcPoles;
    }

    public String getLinePassingPrivateProperty() {
        return LinePassingPrivateProperty;
    }

    public void setLinePassingPrivateProperty(String linePassingPrivateProperty) {
        LinePassingPrivateProperty = linePassingPrivateProperty;
    }

    public String getWrittenConsentByPropertyOwner() {
        return WrittenConsentByPropertyOwner;
    }

    public void setWrittenConsentByPropertyOwner(String writtenConsentByPropertyOwner) {
        WrittenConsentByPropertyOwner = writtenConsentByPropertyOwner;
    }

    public String getObstructionOfLines() {
        return ObstructionOfLines;
    }

    public void setObstructionOfLines(String obstructionOfLines) {
        ObstructionOfLines = obstructionOfLines;
    }

    public String getLinePassingRoads() {
        return LinePassingRoads;
    }

    public void setLinePassingRoads(String linePassingRoads) {
        LinePassingRoads = linePassingRoads;
    }

    public String getRecommendation() {
        return Recommendation;
    }

    public void setRecommendation(String recommendation) {
        Recommendation = recommendation;
    }

    public String getForPayment() {
        return ForPayment;
    }

    public void setForPayment(String forPayment) {
        ForPayment = forPayment;
    }
}
