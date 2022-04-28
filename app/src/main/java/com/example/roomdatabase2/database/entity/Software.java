package com.example.roomdatabase2.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Software {
    @PrimaryKey
    public int sid;

    @ColumnInfo(name = "name")
    public String sName;

    public String year;
}
