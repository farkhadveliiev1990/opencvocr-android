package modals;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Const {

    static public Context mContext;
    static public String prefKeyProfessor = "PROFESSOR";
    static public String prefKeySchedule = "SCHEDULE";
    static public String prefKeyStudent = "STUDENT";

    static public Professor sProfessor;
    static public Student sStudent;
    static public Schedule sSchedule;
    static public String[] classrooms = new String[] {
       "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414"
    } ;

    public ArrayList<Professor> getAllProfessorDatas () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyProfessor, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return new ArrayList<>();
        }

        String[] indivialIDs = strID.split(",");
        ArrayList<Professor> professors = new ArrayList<>();

        for (String indivialID: indivialIDs) {
            if (indivialID.equals("")) {
                continue;
            }

            Professor professor = new Professor();

            professor.id = indivialID;
            String strKeyName = "name" + indivialID;
            professor.name = prefs.getString(strKeyName, "");
            String strKeyPass = "password_" + indivialID;
            professor.password = prefs.getString(strKeyPass, "");
            String strKeyReal = "realname" + indivialID;
            professor.realname = prefs.getString(strKeyReal, "");
            professors.add(professor);
        }
        return professors;
    }

    public ArrayList<Student> getAllStudentsDatas () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeyStudent, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return new ArrayList<>();
        }

        String[] indivialIDs = strID.split(",");
        ArrayList<Student> students = new ArrayList<>();

        for (String indivialID: indivialIDs) {
            if (indivialID.equals("")) {
                continue;
            }

            Student student = new Student();

            student.id = indivialID;
            String strKeyName = "name" + indivialID;
            student.name = prefs.getString(strKeyName, "");
            String strKeyCardID = "cardid" + indivialID;
            student.cardID = prefs.getString(strKeyCardID, "");
            String strKeyClassroomID = "classroomid" + indivialID;
            student.classroomID = prefs.getString(strKeyClassroomID, "");

            students.add(student);
        }
        return students;
    }

    public ArrayList<Schedule> getAllScheduleDatas () {
        SharedPreferences prefs = mContext.getSharedPreferences(prefKeySchedule, MODE_PRIVATE);
        String strID = prefs.getString("id", "");
        if (strID.equals("")) {
            return new ArrayList<>();
        }

        String[] indivialIDs = strID.split(",");
        ArrayList<Schedule> schedules = new ArrayList<>();

        for (String indivialID: indivialIDs) {
            if (indivialID.equals("")) {
                continue;
            }
            Schedule schedule = new Schedule();

            schedule.id = indivialID;
            String strKeyName = "classname" + indivialID;
            schedule.className = prefs.getString(strKeyName, "");
            String strKeyProfessorID = "professorid" + indivialID;
            schedule.professorID = prefs.getString(strKeyProfessorID, "");
            String strKeyHour = "hour" + indivialID;
            schedule.hour = prefs.getInt(strKeyHour, 0);
            String strKeyMinute = "minute" + indivialID;
            schedule.minute = prefs.getInt(strKeyMinute, 0);

            schedules.add(schedule);
        }
        return schedules;
    }

    public Professor getProffromID (ArrayList<Professor> professors, String profID) {
        for (Professor professor: professors) {
            if (professor.id.equals(profID)) {
                return professor;
            }
        }
        return new Professor();
    }
}
