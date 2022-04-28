package com.example.roomdatabase2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase2.adapter.SoftwareAdapter;
import com.example.roomdatabase2.database.entity.Software;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button buttonTambah;
    private AppDatabase database;
    private SoftwareAdapter softwareAdapter;
    private List<Software> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        buttonTambah = findViewById(R.id.button);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.softwareDao().getAll());
        softwareAdapter = new SoftwareAdapter(getApplicationContext(), list);
        softwareAdapter.setDialog(new SoftwareAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 :
                                Intent intent = new Intent(MainActivity.this, TambahSoftware.class);
                                intent.putExtra("sid",list.get(position).sid);
                                startActivity(intent);
                                break;
                            case 1 :
                                Software software = list.get(position);
                                database.softwareDao().delete(software);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(softwareAdapter);

        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahSoftware.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.softwareDao().getAll());
        softwareAdapter.notifyDataSetChanged();
    }
}