package com.jisce.kalyani.app;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.borjabravo.readmoretextview.ReadMoreTextView;
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
    public void onBindViewHolder(@NonNull final AllNoticeBoardViewHolder holder, final int position) {
        Notices notices = noticesList.get(position);

        holder.subjectTxt.setText(notices.getSubject());
        holder.deptTxt.setText(notices.getDept());
        holder.detailsTxt.setText(notices.getDetails());
        holder.linkTxt.setText(notices.getLink());
        holder.dateTxt.setText("Date : "+ notices.getDate());
        holder.linkTxt.setSelected(true);
        holder.linkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = holder.linkTxt.getText().toString();
                if (URLUtil.isValidUrl(url)){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    mContext.startActivity(i);
                }else {
                    Toast.makeText(mContext,"This is not a valid Url",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return noticesList.size();
    }

    class AllNoticeBoardViewHolder extends RecyclerView.ViewHolder{

      TextView deptTxt,detailsTxt,linkTxt,dateTxt;
      LinearLayout linearLayout;
      ReadMoreTextView subjectTxt;
      public AllNoticeBoardViewHolder( View itemView) {
          super(itemView);

          subjectTxt = itemView.findViewById(R.id.subjectTextView);
          deptTxt = itemView.findViewById(R.id.deptTextView);
          detailsTxt = itemView.findViewById(R.id.textTextView);
          linkTxt = itemView.findViewById(R.id.linkTxt);
          dateTxt = itemView.findViewById(R.id.dateTextView);
          linearLayout = itemView.findViewById(R.id.linearLayout);
      }
  }
}
