package education.vt.elearning;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class IntentSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "pref";
    //private static final String IS_LOGIN = "IsLoggedIn";
    public static final String k_branch = "Branch";
    public static final String k_year= "Year";
    public static final String k_course = "Course";
    public static final String k_sem = "Semester";
    public static final String k_sub = "Subjects";
    public static final String k_sub_type_ = "sub_type";
    public static final String k_units = "Units";
    public static final String k_filetype = "Filetype";
    public static final String k_itemcount = "0";

    public static final String comp_count = "0";
    public static final String elect_count = "0";
    public static final String mech_count = "0";
    public static final String civil_count = "0";

    public static final String pdfc = "0";
    public static final String pptc = "0";
    public static final String imgc = "0";
    public static final String vidc = "0";
    public static final String docc = "0";

    public static final String dowm_vid = "0";

    //public static final String task = "0";


    public IntentSession(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void StoreData(String branch, String year, String course, String sem, String sub, String sub_type, String units, String filetype, String itemcount){
        //editor.putBoolean(IS_LOGIN, true);
        editor.putString(k_branch, branch);
        editor.putString(k_year, year);
        editor.putString(k_course, course);
        editor.putString(k_sem, sem);
        editor.putString(k_sub, sub);
        editor.putString(k_sub_type_, sub_type);
        editor.putString(k_units, units);
        editor.putString(k_filetype, filetype);
        editor.putString(k_itemcount, itemcount);

        editor.commit();
    }
    public void StoreCountForNoti(String a, String b, String c, String d){
        //editor.putBoolean(IS_LOGIN, true);
        editor.putString(comp_count, a);
        editor.putString(elect_count, b);
        editor.putString(mech_count, c);
        editor.putString(civil_count, d);


        editor.commit();
    }

    public void StoreContentCount(String a, String b, String c, String d, String e){
        //editor.putBoolean(IS_LOGIN, true);
        editor.putString(pdfc, a);
        editor.putString(vidc, b);
        editor.putString(docc, c);
        editor.putString(pptc, d);
        editor.putString(imgc, e);

        editor.commit();
    }

    public  void setdownloading(String a)
    {
        editor.putString(dowm_vid,a);
        editor.commit();
    }
    /*public  void settask(String a)
    {
        editor.putString(task,a);
        editor.commit();
    }*/
    /*public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }*/


    public HashMap<String, String> getolddata(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(k_branch, pref.getString(k_branch, null));
        user.put(k_year, pref.getString(k_year, null));
        user.put(k_course, pref.getString(k_course, null));
        user.put(k_sem, pref.getString(k_sem, null));
        user.put(k_sub, pref.getString(k_sub, null));
        user.put(k_sub_type_, pref.getString(k_sub_type_, null));
        user.put(k_units, pref.getString(k_units, null));
        user.put(k_filetype, pref.getString(k_filetype, null));
        user.put(k_itemcount, pref.getString(k_itemcount, "0"));


        user.put(comp_count, pref.getString(comp_count, "0"));
        user.put(elect_count, pref.getString(elect_count, "0"));
        user.put(mech_count, pref.getString(mech_count, "0"));
        user.put(civil_count, pref.getString(civil_count, "0"));

        user.put(pdfc, pref.getString(pdfc, "0"));
        user.put(vidc, pref.getString(vidc, "0"));
        user.put(docc, pref.getString(docc, "0"));
        user.put(pptc, pref.getString(pptc, "0"));
        user.put(imgc, pref.getString(imgc, "0"));

        user.put(dowm_vid, pref.getString(dowm_vid, null));

        //user.put(task, pref.getString(task, "0"));

        return user;
    }

    public void cleardata(){
        editor.clear();
        editor.commit();
        //Intent i = new Intent(_context, login.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //_context.startActivity(i);
    }

   // public boolean isLoggedIn(){
       // return pref.getBoolean(IS_LOGIN, false);
   // }


}