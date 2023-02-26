package com.abbyy.mobile.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abbyy.mobile.sample.Adapters.ScheduleAdapter;

import java.util.ArrayList;

import modals.Const;
import modals.Schedule;

public class EditScheduleActivity extends Activity {
    static private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();
    }

    private void initView() {
        mContext = this.getApplicationContext();

        TextView lblTitle = findViewById(R.id.edit_lbl_title);
        lblTitle.setText(Const.sProfessor.name);

        Const mConst = new Const();
        ArrayList<Schedule> allSchedule = mConst.getAllScheduleDatas();
        final ArrayList<Schedule> datas = new ArrayList<>();
        for (Schedule schedule: allSchedule) {
            if (schedule.professorID.equals(Const.sProfessor.id)) {
                datas.add(schedule);
            }
        }

        if (datas.size() > 0) {
            LinearLayout lltNodata = findViewById(R.id.edit_llt_nodata);
            lltNodata.setVisibility(View.GONE);

            ScheduleAdapter adapter = new ScheduleAdapter(EditScheduleActivity.this, datas);
            GridView grid = findViewById(R.id.edit_grd_schedule);
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Const.sSchedule = datas.get(i);
                    Intent myIntent = new Intent(mContext, AddScheduleActivity.class);
                    mContext.startActivity(myIntent);
                }
            });
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

    public void onClickAddBtn(View view) {
        Const.sSchedule = new Schedule();
        Intent myIntent = new Intent(this, AddScheduleActivity.class);
        this.startActivity(myIntent);
    }
}
