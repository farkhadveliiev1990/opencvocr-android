package com.abbyy.mobile.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import modals.Const;

public class SelectActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        initView();
    }

    private void initView() {
        TextView lblTitle = findViewById(R.id.select_lbl_title);
        lblTitle.setText("Welcome " + Const.sProfessor.realname + "!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClickViewlBtn(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

    public void onClickEditBtn(View view) {
        Intent myIntent = new Intent(this, EditScheduleActivity.class);
        this.startActivity(myIntent);
    }
}
