package com.lopez.julz.crmcrewhub.api;

import com.lopez.julz.crmcrewhub.classes.Barangays;
import com.lopez.julz.crmcrewhub.classes.Login;
import com.lopez.julz.crmcrewhub.classes.Towns;
import com.lopez.julz.crmcrewhub.database.Crew;
import com.lopez.julz.crmcrewhub.database.LineAndMetering;
import com.lopez.julz.crmcrewhub.database.MeterInstallation;
import com.lopez.julz.crmcrewhub.database.ServiceConnectionInspections;
import com.lopez.julz.crmcrewhub.database.ServiceConnections;
import com.lopez.julz.crmcrewhub.database.StationCrews;
import com.lopez.julz.crmcrewhub.database.StationCrewsDao;
import com.lopez.julz.crmcrewhub.database.TicketRepositories;
import com.lopez.julz.crmcrewhub.database.Tickets;
import com.lopez.julz.crmcrewhub.database.TimeFrames;
import com.lopez.julz.crmcrewhub.database.TimeFramesDao;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RequestPlaceHolder {
    @POST("login")
    Call<Login> login(@Body Login login);

    @GET("get-towns")
    Call<List<Towns>> getTowns();

    @GET("get-barangays")
    Call<List<Barangays>> getBarangays();

    @GET("get-all-crew")
    Call<List<Crew>> getAllCrew();

    /**
     * SERVICE CONNECTIONS
     * @return
     */

    @GET("get-for-energization-data")
    Call<List<ServiceConnections>> getForEnergizationData(@Query("CrewAssigned") String crew);

    @GET("get-inspections-for-energization-data")
    Call<List<ServiceConnectionInspections>> getInspectionsForEnergizationData(@Query("CrewAssigned") String crew);

    @POST("update-energized")
    Call<ServiceConnections> uploadServiceConnection(@Body ServiceConnections serviceConnections);

    @POST("create-timeframes")
    Call<TimeFrames> uploadTimeFrames(@Body TimeFrames timeFrames);

    @GET("update-downloaded-service-connection-status")
    Call<Void> updateDownloadedServiceConnectionStatus(@Query("CrewAssigned")String crewAssigned, @Query("User") String User, @Query("Status") String Status);

    @POST("notify-downloaded")
    Call<Void> notifyDownloaded(@Query("ServiceAccountName")String ServiceAccountName, @Query("ContactNumber") String ContactNumber, @Query("id") String id);

    @POST("receive-meter-installations")
    Call<MeterInstallation> uploadMeterInstallations(@Body MeterInstallation meterInstallation);

    @POST("receive-line-and-metering")
    Call<LineAndMetering> uploadLineAndMetering(@Body LineAndMetering lineAndMetering);

    /**
     * Tickets
     */
    @GET("get-ticket-types")
    Call<List<TicketRepositories>> getTicketTypes();

    @GET("get-downloadable-tickets")
    Call<List<Tickets>> getDownloadbleTickets(@Query("CrewAssigned") String CrewAssigned);

    @GET("update-downloaded-tickets-status")
    Call<Void> updateDownloadedStatus(@Query("CrewAssigned") String CrewAssigned, @Query("UserId") String UserId);

    @POST("upload-tickets")
    Call<Tickets> uploadTickets(@Body Tickets tickets);

    @GET("get-crews-from-station")
    Call<List<StationCrews>> getCrewsFromStation(@Query("CrewLeader") String CrewLeader);

    @GET("get-archive-tickets")
    Call<List<Tickets>> getArchiveTickets(@Query("CrewAssigned") String CrewAssigned);

    @GET("get-files")
    Call<ResponseBody> getFiles(@Query("id") String id);

    @Multipart
    @POST("save-uploaded-images")
    Call<ResponseBody> saveUploadedImages(@Query("svcId") String svcId, @Part List<MultipartBody.Part> files);
}
