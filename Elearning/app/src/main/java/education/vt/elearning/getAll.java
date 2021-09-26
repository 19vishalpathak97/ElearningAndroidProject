package education.vt.elearning;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class getAll extends Activity {
    String year, branch, sem, course, subject, units,sub_type;
    String totalsc;
    int totalc;
    String[] itemname,itemdes,filetype;
    ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);
        pDialog = new ProgressDialog(getAll.this);
        Bundle bundle = getIntent().getExtras();
        branch = bundle.getString("branch");
        year = bundle.getString("year");
        course = bundle.getString("course");
        sem = bundle.getString("sem");
        //sub_type = bundle.getString("sub_type");
        subject = bundle.getString("subject");
        if(isOnline()) {
            //if (isConnectedToServer("http://10.42.0.1",0)) {
                BackGround2 b = new BackGround2();
                b.execute(branch, year, course, sem, subject);
            /*}
            else
            {
                Toast.makeText(getApplicationContext(),"Server is busy",Toast.LENGTH_SHORT).show();

            }*/
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network unreachable",Toast.LENGTH_SHORT).show();
            //onBackPressed();
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(this,navigation.class);

        intent.putExtra("Check","3");
        startActivity(intent);
    }




    class BackGround2 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String sem = params[3];
            String subject = params[4];
            //String sub_type = params[5];

            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getcountall.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course + "&sem=" + sem + "&subject=" + subject ;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream oo = httpURLConnection.getOutputStream();
                oo.write(urlParams.getBytes());
                oo.flush();
                oo.close();
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exeption there :" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exeption there :" + e.getMessage();
            }
        }

        protected void onPreExecute() {

            pDialog = new ProgressDialog(getAll.this);
            pDialog.setMessage("Fetching data...");
            pDialog.show();

        }

        protected void onPostExecute(String s) {
            long delay = 0;
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();

                }
            }, delay);


            try {
                JSONArray ja=new JSONArray(s);

                JSONObject obj=ja.getJSONObject(0);
                totalsc=obj.getString("totalcount");


                totalc=Integer.parseInt(totalsc.toString());
                itemname=new String[totalc];
                itemdes=new String[totalc];
                filetype=new String[totalc];
                if (isOnline()) {

                        BackGround3 b = new BackGround3();
                        b.execute(branch, year, course, sem, subject);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Network unreachable",Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }



    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class BackGround3 extends AsyncTask<String, String,String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String sem = params[3];
            String subject = params[4];
            //String sub_type = params[5];

            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getdataall.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course + "&sem=" + sem + "&subject=" + subject;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream oo = httpURLConnection.getOutputStream();
                oo.write(urlParams.getBytes());
                oo.flush();
                oo.close();
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exeption there :" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exeption there :" + e.getMessage();
            }
        }

        protected void onPreExecute() {

            pDialog = new ProgressDialog(getAll.this);
            pDialog.setMessage("Fetching data...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        protected void onPostExecute(String s) {
            long delay = 0;
            int j = 0, k = 0, l = 0, m = 0;

            try {


                JSONArray ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject obj = ja.getJSONObject(i);
                    String filename = obj.getString("filename");
                    String filetype1 = obj.getString("filetype");
                    String desc = obj.getString("description");
                    //if (filetype.equals("PDF")) {
                        itemname[j] = filename;
                        itemdes[j] = desc;
                        filetype[j] = filetype1;
                        j++;

                    //}

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();

                }
            }, delay);

           Intent i = new Intent(getAll.this, middle1.class);
            Bundle b = new Bundle();

            b.putInt("itemcount", totalc);
            b.putStringArray("itemname", itemname);
            b.putStringArray("itemdes", itemdes);
            b.putStringArray("filetype", filetype);
            i.putExtras(b);i.putExtra("Check","3");
            startActivity(i);


        }
    }



}
