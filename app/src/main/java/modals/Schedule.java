package modals;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static modals.Const.mContext;
import static modals.Const.prefKeySchedule;

public class Schedule {
    public String id;
    public String className;
    public String professorID;
    public int hour;
    public int minute;

    public void changeScheduleData () {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(prefKeySchedule, MODE_PRIVATE).edit();
        String strKeyClass = "classname" + this.id;
        editor.putString(strKeyClass, this.className);
        String strKeyProf = "professorid" + this.id;
        editor.putString(strKeyProf, this.professorID);
        String strKeyHour = "hour" + this.id;
        editor.putInt(strKeyHour, this.hour);
        String strKeyMinute = "minute" + this.id;
        editor.putInt(strKeyMinute, this.minute);
        editor.apply();
    }

    public Schedule addScheduleData () {
        int maxID = getMaxID();
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeySchedule, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        strID = strID + "," + String.valueOf(maxID + 1);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", strID);
        editor.apply();

        Schedule schedule = new Schedule();
        schedule.id = String.valueOf(maxID + 1);
        schedule.className = className;
        schedule.professorID = professorID;
        schedule.hour = hour;
        schedule.minute = minute;

        schedule.changeScheduleData();

        return schedule;
    }

    public boolean deleteProcessorData () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeySchedule, MODE_PRIVATE);
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
            String strKeyClass = "classname" + this.id;
            editor.remove(strKeyClass);
            String strKeyProf = "professorid" + this.id;
            editor.remove(strKeyProf);
            String strKeyHour = "hour" + this.id;
            editor.remove(strKeyHour);
            String strKeyMinute = "minute" + this.id;
            editor.remove(strKeyMinute);
            editor.apply();

            return true;
        } else {
            return false;
        }
    }

    private int getMaxID () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeySchedule, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return 0;
        }

        String[] IDs = strID.split(",");

        return Integer.valueOf(IDs[IDs.length - 1]) ;
    }
    
}
