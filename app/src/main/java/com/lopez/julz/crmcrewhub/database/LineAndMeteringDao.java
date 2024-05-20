package com.lopez.julz.crmcrewhub.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LineAndMeteringDao {
    @Query("SELECT * FROM LineAndMetering WHERE ServiceConnectionId=:serviceConnectionId")
    LineAndMetering getOne(String serviceConnectionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LineAndMetering... lineAndMeterings);

    @Update
    void update(LineAndMetering... lineAndMeterings);

    @Query("SELECT * FROM LineAndMetering WHERE Uploadable='Yes'")
    List<LineAndMetering> getUploadables();
}
