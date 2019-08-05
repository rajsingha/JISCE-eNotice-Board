package com.jisce.kalyani.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jisce.kalyani.app.Model.Notices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    AllNoticeBoardAdapter noticeBoardAdapter;
    List<Notices> noticesList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.refreshRecycle);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticesList = new ArrayList<>();

        swipeRefreshLayout.setOnRefreshListener(this);

        getData();


    }
    public void getData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Notices");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot noticesdataSnapshot: dataSnapshot.getChildren()){
                        Notices not = noticesdataSnapshot.getValue(Notices.class);
                        noticesList.add(not);


                        swipeRefreshLayout.setRefreshing(false);
                    }
                    noticeBoardAdapter= new AllNoticeBoardAdapter(MainActivity.this,noticesList);
                    recyclerView.setAdapter(noticeBoardAdapter);



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onRefresh() {
        noticesList.clear();
        getData();


    }
}
