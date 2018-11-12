package com.parassidhu.trigonomania.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

@Database(entities = {FirstMethodModel.class, SecondMethodModel.class}, version = 1, exportSchema = false)
public abstract class MathDatabase extends RoomDatabase {

    public abstract MathDao mathDao();

    private static MathDatabase INSTANCE;

    private static String DATABASE_NAME = "math_database";

    public static MathDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (MathDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MathDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
