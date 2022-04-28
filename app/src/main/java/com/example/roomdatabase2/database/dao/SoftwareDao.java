package com.example.roomdatabase2.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdatabase2.database.entity.Software;

import java.util.List;

@Dao
public interface SoftwareDao {
    @Query("SELECT * FROM software")
    List<Software> getAll();

    @Query("INSERT INTO software(name,year) VALUES (:name, :year)")
    void InsertAll(String name, String year);

    @Query("UPDATE software SET name =:name, year=:year WHERE sid=:sid")
    void update(int sid, String name, String year);

    @Query("SELECT * FROM software WHERE sid=:sid")
    Software get(int sid);

    @Delete
    void delete(Software software);
}
