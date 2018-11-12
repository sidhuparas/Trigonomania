package com.parassidhu.trigonomania.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "second_method")
public class SecondMethodModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int side1, side2;

    private String valueOfSide1, valueOfSide2;

    public SecondMethodModel(int side1, int side2, String valueOfSide1, String valueOfSide2) {
        this.side1 = side1;
        this.side2 = side2;
        this.valueOfSide1 = valueOfSide1;
        this.valueOfSide2 = valueOfSide2;
    }

    public int getId() {
        return id;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public String getValueOfSide1() {
        return valueOfSide1;
    }

    public String getValueOfSide2() {
        return valueOfSide2;
    }

    public void setId(int id) {
        this.id = id;
    }
}
