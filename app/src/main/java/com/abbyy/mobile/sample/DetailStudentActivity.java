package com.abbyy.mobile.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abbyy.mobile.sample.Adapters.ScheduleAdapter;

import java.util.ArrayList;

import modals.Const;
import modals.Schedule;

public class DetailStudentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);

        initView();
    }

    private void initView() {
        TextView lblTitle = findViewById(R.id.detail_lbl_title);
        TextView lblClassroom = findViewById(R.id.detail_lbl_classroom);
        TextView lblCardID = findViewById(R.id.detail_lbl_cardid);

        lblTitle.setText(Const.sStudent.name);
        lblClassroom.setText("Student Name: " + Const.sStudent.classroomID);
        lblCardID.setText("Student Name: " + Const.sStudent.cardID);

        Const mConst = new Const();
        ArrayList<Schedule> allSchedule = mConst.getAllScheduleDatas();
        ArrayList<Schedule> datas = new ArrayList<>();
        for (Schedule schedule: allSchedule) {
            if (schedule.className.equals(Const.sStudent.classroomID)) {
                datas.add(schedule);
            }
        }

        if (datas.size() > 0) {
            LinearLayout lltNodata = findViewById(R.id.detail_llt_nodata);
            lltNodata.setVisibility(View.GONE);

            ScheduleAdapter adapter = new ScheduleAdapter(DetailStudentActivity.this, datas);
            GridView grid = findViewById(R.id.detail_grd_schedule);
            grid.setAdapter(adapter);
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

    public void onClickBackBtn(View view) {
        Intent myIntent = new Intent(this, SelectActivity.class);
        this.startActivity(myIntent);
    }
}
