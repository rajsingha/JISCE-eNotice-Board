package com.jisce.kalyani.app.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jisce.kalyani.app.AllNoticeBoardAdapter;
import com.jisce.kalyani.app.Model.Notices;
import com.jisce.kalyani.app.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    AllNoticeBoardAdapter noticeBoardAdapter;
    List<Notices> noticesList;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar pBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.refreshRecycle);
        pBar = view.findViewById(R.id.loading_progress);
        pBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
                        pBar.setVisibility(View.GONE);
                        noticeBoardAdapter= new AllNoticeBoardAdapter(getContext(),noticesList);
                        recyclerView.setAdapter(noticeBoardAdapter);
                    }




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
