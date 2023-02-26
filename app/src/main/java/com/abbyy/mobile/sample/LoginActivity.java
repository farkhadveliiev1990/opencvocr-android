package com.abbyy.mobile.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import modals.Const;
import modals.Professor;

public class LoginActivity extends Activity {

    private EditText txtUserName, txtPassword;
    private ArrayList<Professor> professors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login );

        initUIView();
    }

    private void initUIView() {
        if (Const.mContext == null) {
            Const.mContext = this.getApplicationContext();
        }

        txtUserName = findViewById(R.id.login_txt_username);
        txtPassword = findViewById(R.id.login_txt_password);

        Const mConst = new Const();
        professors = mConst.getAllProfessorDatas();
        if (professors.size() == 0) {
            String[] names = {"Richard Lanier", "Mike Briscoe", "Michael Rodriguez", "Flora Barnes",
                    "Alice Rios", "Angelo Brewer", "Randolph Fitzgerald", "Douglas Wilkerson", "Marc Morton", "Kristy Holloway"};
            int count = 0;
            for (String name: names) {
                count++;
                Professor professor = new Professor();
                professor.id = String.valueOf(count);
                professor.name = name;
                professor.realname = name;
                professor.password = "123456";

                professor.addProfessorData();
                professors.add(professor);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClickCancelBtn(View view) {
        this.finish();
        System.exit(0);
    }

    public void onClickLoginBtn(View view) {
        String strUsername = txtUserName.getText().toString();
        String strPassword = txtPassword.getText().toString();

        for (Professor professor: professors) {
            if (strUsername.equals(professor.name) && strPassword.equals(professor.password)) {
                Const.sProfessor = professor;
                Intent myIntent = new Intent(this, SelectActivity.class);
                this.startActivity(myIntent);
                return;
            }
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Username or Password is Wrong. Please try it again.");
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
    }
}
