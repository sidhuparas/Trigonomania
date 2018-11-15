package com.parassidhu.trigonomania.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

import java.util.List;

@Dao
public interface MathDao {

    @Insert
    void insertInFirstMethod(FirstMethodModel calculation);

    @Insert
    void insertInSecondMethod(SecondMethodModel calculation);

    @Query("SELECT * FROM first_method")
    LiveData<List<FirstMethodModel>> getFirstMethodData();

    @Query("SELECT * FROM second_method")
    LiveData<List<SecondMethodModel>> getSecondMethodData();

    @Delete()
    void deleteAllFirstMethod(List<FirstMethodModel> list);

    @Delete()
    void deleteAllSecondMethod(List<SecondMethodModel> list);
}
