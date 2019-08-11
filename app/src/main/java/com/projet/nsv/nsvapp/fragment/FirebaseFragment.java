package com.projet.nsv.nsvapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.projet.nsv.nsvapp.R;


public class FirebaseFragment extends Fragment {

    private Button gmailBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.fragment_firebase, container, true );

        gmailBtn = rootView.findViewById( R.id.gmail_signup );
        gmailBtn.setOnClickListener( gmailBtnListener );
        return rootView;
    }

    private View.OnClickListener gmailBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("Firebase", gmailBtn.toString());
        }
    };
}
