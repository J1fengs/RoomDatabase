package com.example.roomdatabase2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabase2.database.entity.Software;

import java.util.ArrayList;
import java.util.List;

public class TambahSoftware extends AppCompatActivity {
    private EditText editName, editYear;
    private Button btnSave;
    private AppDatabase database;
    private boolean isEdit = false;
    private int sid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_software);
        editName = findViewById(R.id.editTextSoftName);
        editYear = findViewById(R.id.editTextSoftRelease);
        btnSave = findViewById(R.id.buttonSave);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        sid = intent.getIntExtra("sid", 0);
        if(sid>0){
            isEdit = true;
            Software software = database.softwareDao().get(sid);
            editYear.setText(software.year);
            editName.setText(software.sName);

        }else{
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit){
                    database.softwareDao().update(sid, editName.getText().toString(), editYear.getText().toString());
                }else{
                    database.softwareDao().InsertAll(editName.getText().toString(), editYear.getText().toString());
                }
                finish();
            }
        });
    }
}