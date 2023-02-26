package com.abbyy.mobile.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import modals.Const;

public class AddScheduleActivity extends Activity {
    private EditText txtClassroom, txtHour, txtMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        initView();
    }

    private void initView() {
        TextView lblTitle = findViewById(R.id.add_lbl_title);
        lblTitle.setText(Const.sProfessor.name);

        txtClassroom = findViewById(R.id.add_txt_classroom);
        txtHour = findViewById(R.id.add_txt_hour);
        txtMinute = findViewById(R.id.add_txt_minute);

        Button btnAdd = findViewById(R.id.add_btn_add);

        if (!(Const.sSchedule.id == null)) {
            txtClassroom.setText(Const.sSchedule.className);
            txtHour.setText(String.valueOf(Const.sSchedule.hour));
            txtMinute.setText(String.valueOf(Const.sSchedule.minute));

            btnAdd.setText("SAVE");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClickCancelBtn(View view) {
        Intent myIntent = new Intent(this, EditScheduleActivity.class);
        this.startActivity(myIntent);
    }

    public void onClickAddBtn(View view) {
        String strClass = txtClassroom.getText().toString();
        String strHour = txtHour.getText().toString();
        String strMinute = txtMinute.getText().toString();

        if (strClass.equals("") || strHour.equals("") || strMinute.equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Input datas have some errors.");
            builder1.setTitle("Waring!!!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return;
        }

        Const.sSchedule.className = strClass;
        Const.sSchedule.hour = Integer.valueOf(strHour);
        Const.sSchedule.minute = Integer.valueOf(strMinute);
        Const.sSchedule.professorID = Const.sProfessor.id;
        if (Const.sSchedule.id == null) {
            Const.sSchedule.addScheduleData();
        } else {
            Const.sSchedule.changeScheduleData();
        }

        Intent myIntent = new Intent(this, EditScheduleActivity.class);
        this.startActivity(myIntent);
    }
}
