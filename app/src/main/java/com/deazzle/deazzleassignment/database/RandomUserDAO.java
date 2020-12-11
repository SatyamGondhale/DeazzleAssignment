package com.deazzle.deazzleassignment.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.deazzle.deazzleassignment.model.Example;
import com.deazzle.deazzleassignment.model.Result;

import java.util.List;

@Dao
public interface RandomUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(RandomUser user);

 /*   @Query("Select COUNT(*) from randomuser")
    int getCount();

    @Query("SELECT * FROM randomuser")
    List<Result> getCacheData();*/

    @Query("DELETE FROM randomuser")
    public void emptyTable();
}
