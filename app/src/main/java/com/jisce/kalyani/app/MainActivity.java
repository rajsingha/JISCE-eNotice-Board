package com.jisce.kalyani.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jisce.kalyani.app.Model.Notices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AllNoticeBoardAdapter noticeBoardAdapter;
    List<Notices> noticesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticesList = new ArrayList<>();

        noticeBoardAdapter= new AllNoticeBoardAdapter(this,noticesList);
        recyclerView.setAdapter(noticeBoardAdapter);
    }
}
