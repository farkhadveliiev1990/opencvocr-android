package com.abbyy.mobile.sample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abbyy.mobile.sample.R;

import java.util.ArrayList;

import modals.Const;
import modals.Professor;
import modals.Schedule;

public class ScheduleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Schedule> datas;

    public ScheduleAdapter(Context c, ArrayList<Schedule> datas) {
        mContext = c;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Schedule schedule = datas.get(position);
        Const mConst = new Const();
        ArrayList<Professor> professors = mConst.getAllProfessorDatas();
        Professor professor = mConst.getProffromID(professors, schedule.professorID);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.item_schedule, null);

            TextView lblIndex = grid.findViewById(R.id.schedule_lbl_index);
            TextView lblProfessor = grid.findViewById(R.id.schedule_lbl_professor);
            TextView lblClass = grid.findViewById(R.id.schedule_lbl_class);
            TextView lblTime = grid.findViewById(R.id.schedule_lbl_time);

            lblIndex.setText(String.valueOf(position + 1));
            lblProfessor.setText(professor.name);
            String time = String.format("%02d : %02d", schedule.hour, schedule.minute);
            lblTime.setText(time);
            lblClass.setText(schedule.className);
        } else {
            grid = convertView;
        }

        return grid;
    }
}
