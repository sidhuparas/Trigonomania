package com.parassidhu.trigonomania.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "first_method")
public class FirstMethodModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int angle;
    private int side;

    private String valueOfAngle;
    private String valueOfSide;

    public FirstMethodModel(int angle, int side, String valueOfAngle, String valueOfSide) {
        this.angle = angle;
        this.side = side;
        this.valueOfAngle = valueOfAngle;
        this.valueOfSide = valueOfSide;
    }

    public int getId() {
        return id;
    }

    public int getAngle() {
        return angle;
    }

    public int getSide() {
        return side;
    }

    public String getValueOfAngle() {
        return valueOfAngle;
    }

    public String getValueOfSide() {
        return valueOfSide;
    }

    public void setId(int id) {
        this.id = id;
    }
}
