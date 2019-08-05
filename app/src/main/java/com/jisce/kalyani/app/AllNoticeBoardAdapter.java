package com.jisce.kalyani.app;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jisce.kalyani.app.Model.Notices;

import java.util.List;

public class AllNoticeBoardAdapter extends RecyclerView.Adapter<AllNoticeBoardAdapter.AllNoticeBoardViewHolder> {

    Context mContext;
    List<Notices> noticesList;

    public AllNoticeBoardAdapter(Context mContext, List<Notices> noticesList) {
        this.mContext = mContext;
        this.noticesList = noticesList;
    }


    @NonNull
    @Override
    public AllNoticeBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_notice_board_items_activity, parent, false);
        AllNoticeBoardViewHolder allNoticeBoardViewHolder = new
                AllNoticeBoardViewHolder(view);
        return allNoticeBoardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllNoticeBoardViewHolder holder, int position) {
        Notices notices = noticesList.get(position);

        holder.subjectTxt.setText(notices.getSubject());
        holder.deptTxt.setText(notices.getDept());
        holder.detailsTxt.setText(notices.getDetails());
        holder.linkTxt.setText(notices.getLink());

    }

    @Override
    public int getItemCount() {
        return noticesList.size();
    }

    class AllNoticeBoardViewHolder extends RecyclerView.ViewHolder{

      TextView subjectTxt,deptTxt,detailsTxt,linkTxt;
      public AllNoticeBoardViewHolder( View itemView) {
          super(itemView);

          subjectTxt = itemView.findViewById(R.id.subjectTextView);
          deptTxt = itemView.findViewById(R.id.deptTextView);
          detailsTxt = itemView.findViewById(R.id.textTextView);
          linkTxt = itemView.findViewById(R.id.linkTxt);
      }
  }
}
