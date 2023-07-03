package com.lopez.julz.crmcrewhub.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MeterInstallationDao {
    @Query("SELECT * FROM MeterInstallation")
    List<MeterInstallation> getAll();

    @Query("SELECT * FROM MeterInstallation WHERE ServiceConnectionId=:id")
    MeterInstallation getOneByServiceConnectionId(String id);

    @Query("SELECT * FROM MeterInstallation WHERE id=:id")
    MeterInstallation getOneById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MeterInstallation... meterInstallations);

    @Update
    void updateAll(MeterInstallation... meterInstallations);

    @Query("SELECT * FROM MeterInstallation WHERE UploadStatus IS NULL")
    List<MeterInstallation> getUploadable();
}
