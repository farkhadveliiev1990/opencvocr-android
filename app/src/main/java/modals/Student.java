package modals;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static modals.Const.mContext;
import static modals.Const.prefKeyStudent;

public class Student {
    public String id;
    public String cardID;
    public String name;
    public String classroomID;

    public void changeStudentData () {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(prefKeyStudent, MODE_PRIVATE).edit();
        String strKeyName = "name" + this.id;
        editor.putString(strKeyName, this.name);
        String strKeyPass = "cardid" + this.id;
        editor.putString(strKeyPass, this.cardID);
        String strKeyReal = "classroomid" + this.id;
        editor.putString(strKeyReal, this.classroomID);
        editor.apply();
    }

    public Student addStudentData () {
        int maxID = getMaxID();
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyStudent, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        strID = strID + "," + String.valueOf(maxID + 1);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", strID);
        editor.apply();

        Student student = new Student();
        student.id = String.valueOf(maxID + 1);
        student.name = name;
        student.cardID = cardID;
        student.classroomID = classroomID;

        student.changeStudentData();

        return student;
    }

    public boolean deleteProcessorData () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyStudent, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return false;
        }

        String[] strIDs = strID.split(",");
        ArrayList<String> IDs = new ArrayList<>(Arrays.asList(strIDs));
        if (IDs.contains(this.id)) {
            IDs.remove(this.id);
            String strNewID = "";
            for (String s: IDs) {
                strNewID = strNewID + "," + s;
            }
            SharedPreferences.Editor editor = prefs.edit();
            String strKeyName = "name" + this.id;
            editor.remove(strKeyName);
            String strKeyPass = "password_" + this.id;
            editor.remove(strKeyPass);
            String strKeyReal = "realname" + this.id;
            editor.remove(strKeyReal);
            editor.apply();

            return true;
        } else {
            return false;
        }
    }

    private int getMaxID () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyStudent, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return 0;
        }

        String[] IDs = strID.split(",");

        return Integer.valueOf(IDs[IDs.length - 1]) ;
    }
}
