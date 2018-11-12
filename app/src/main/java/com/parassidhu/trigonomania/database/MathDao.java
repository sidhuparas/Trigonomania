package com.parassidhu.trigonomania.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

@Dao
public interface MathDao {

    @Insert
    void insertInFirstMethod(FirstMethodModel calculation);

    @Insert
    void insertInSecondMethod(SecondMethodModel calculation);
}
