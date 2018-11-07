package com.parassidhu.trigonomania.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class MathDatabase extends RoomDatabase {

    abstract MathDao mathDao();

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
