package com.jisce.kalyani.app.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jisce.kalyani.app.R;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class FeedbackFragment extends Fragment {
    private Button sendButton;
    private EditText EmailEditText,FeedbackEditText;
    private ScrollView scrollView;
    private String feedback_details, email_details;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollView = view.findViewById(R.id.scrollViewFeedback);
        EmailEditText = view.findViewById(R.id.userEmail);
        FeedbackEditText = view.findViewById(R.id.userFeedback);
        sendButton =view.findViewById(R.id.send_feedback_btn);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_details = EmailEditText.getText().toString();
                feedback_details = FeedbackEditText.getText().toString();

                if (TextUtils.isEmpty(email_details) && TextUtils.isEmpty(feedback_details)) {
                    Toast.makeText(getContext(),"Please Fill The Fileds",Toast.LENGTH_SHORT).show();

                }else {
                    int SDK_INT = android.os.Build.VERSION.SDK_INT;
                    if (SDK_INT > 8) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        //your codes here
                        SendEmailASyncTask task = new SendEmailASyncTask(getContext(),
                                email_details,feedback_details, scrollView);
                        task.execute();

                    }

                }
            }
        });
    }

    private static class SendEmailASyncTask extends AsyncTask<Void, Void, Void> {

        private static final String SENDGRID_API_KEY ="Put Your Api Here";
        private Context mAppContext;
        private String mMsgResponse;


        private String mTo;
        private String mFrom;
        private String mSubject;
        private String mText;
        private ScrollView scrollView ;






        public SendEmailASyncTask(Context context,
                                  String mFrom,String  mText ,View view) {
            this.mAppContext = context;
            this.mFrom = mFrom;
            this.mText = mText;
            scrollView = view.findViewById(R.id.scrollViewFeedback);


        }


        @Override
        protected Void doInBackground(Void... params) {




            try {
                SendGrid sendgrid = new SendGrid(SENDGRID_API_KEY);

                SendGrid.Email email = new SendGrid.Email();


                email.addTo("r.singha4520@gmail.com");
                email.setFrom("Gmoney-Feedback-"+ mFrom);
                email.setSubject("Feedback");
                email.setText("UserEmail: "+mFrom+" Text: "+ mText);

                SendGrid.Response response = sendgrid.send(email);
                mMsgResponse = response.getMessage();

                Log.d("SendAppExample", mMsgResponse);
            }
            catch (SendGridException e) {
                Log.e("SendAppExample", e.toString());
            }

            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mAppContext, mMsgResponse, Toast.LENGTH_SHORT).show();
        }

        public static boolean isValidContext(final Context context) {

            if (context == null) {
                return false;
            }
            if (context instanceof Activity) {
                final Activity activity = (Activity) context;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    return !activity.isDestroyed() && !activity.isFinishing();

                } else {
                    return !activity.isFinishing();
                }
            }
            return true;

        }


    }
}
