package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 잘 하면 fragment 1~5 합칠 수도 있을듯?
public class FragmentMovie1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie1, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);


        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        TextView rgdView = (TextView) rootView.findViewById(R.id.reservation_grade_date);
        ImageView posterView = (ImageView) rootView.findViewById(R.id.poster);

        String title = this.getArguments().getString("title");
        String image = this.getArguments().getString("image");
        float reservation_rate = this.getArguments().getFloat("reservation_rate");
        int grade = this.getArguments().getInt("grade");
        String date = this.getArguments().getString("date");

        titleView.setText(title);
        rgdView.setText(Float.toString(reservation_rate) + Integer.toString(grade) + date);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
