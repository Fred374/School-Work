package com.example.assign3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onStart() {
        super.onStart();

        Button btnA = (Button)getActivity().findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               TextView lbl = (TextView)getActivity().findViewById(R.id.btnA);
               TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
               statusarea.append(lbl.getText());
            }
        });
        Button btnB = (Button)getActivity().findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.btnB);
                TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
                statusarea.append(lbl.getText());
            }
        });
        Button btnC = (Button)getActivity().findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.btnC);
                TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
                statusarea.append(lbl.getText());
            }
        });
        Button btnD = (Button)getActivity().findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.btnD);
                TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
                statusarea.append(lbl.getText());
            }
        });
        Button btnE = (Button)getActivity().findViewById(R.id.btnE);
        btnE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.btnE);
                TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
                statusarea.append(lbl.getText());
            }
        });
        Button btnF = (Button)getActivity().findViewById(R.id.btnF);
        btnF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.btnF);
                TextView statusarea = (TextView)getActivity().findViewById(R.id.lblFragment1);
                statusarea.append(lbl.getText());
            }
        });
    }
}