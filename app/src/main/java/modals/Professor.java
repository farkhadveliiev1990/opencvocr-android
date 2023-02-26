package modals;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static modals.Const.mContext;
import static modals.Const.prefKeyProfessor;

public class Professor {
    public String id;
    public String name;
    public String password;
    public String realname;

    public void changeProfessorData () {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(prefKeyProfessor, MODE_PRIVATE).edit();
        String strKeyName = "name" + this.id;
        editor.putString(strKeyName, this.name);
        String strKeyPass = "password_" + this.id;
        editor.putString(strKeyPass, this.password);
        String strKeyReal = "realname" + this.id;
        editor.putString(strKeyReal, this.realname);
        editor.apply();
    }

    public Professor addProfessorData () {
        int maxID = getMaxID();
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyProfessor, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        strID = strID + "," + String.valueOf(maxID + 1);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", strID);
        editor.apply();

        Professor professor = new Professor();
        professor.id = String.valueOf(maxID + 1);
        professor.name = name;
        professor.password = password;
        professor.realname = realname;

        professor.changeProfessorData();

        return professor;
    }

    public boolean deleteProcessorData () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyProfessor, MODE_PRIVATE);
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
            String strKeyPass = "password" + this.id;
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
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyProfessor, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return 0;
        }

        String[] IDs = strID.split(",");

        return Integer.valueOf(IDs[IDs.length - 1]) ;
    }
}
