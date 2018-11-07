package com.parassidhu.trigonomania;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "trig_values")
public class MathData {

    @PrimaryKey
    private int id;

}
