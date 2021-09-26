package education.vt.elearning;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
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

public class listall1 extends Activity {

    String year, branch, sem, course, subject, units,sub_type;
    String itemcount;
    int itemcount1;
    String[] pdfname=null ,pdfdes=null;
    String filetype;
    ProgressDialog pDialog;
    String s=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall1);
        pDialog = new ProgressDialog(listall1.this);
        Bundle bundle = getIntent().getExtras();
        branch = bundle.getString("branch");
        year = bundle.getString("year");
        course = bundle.getString("course");
        sem = bundle.getString("sem");
        subject = bundle.getString("subject");
        sub_type = bundle.getString("sub_type");
        units = bundle.getString("units");
        itemcount=bundle.getString("itemcount");
        filetype=bundle.getString("filetype");
        itemcount1=Integer.parseInt(itemcount.toString());
        pdfname=new String[itemcount1];
        pdfdes=new String[itemcount1];

        if(itemcount1==0)
        {
            Toast.makeText(getApplicationContext(),"No data available",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(listall1.this,getData.class);
            Bundle b = new Bundle();
            b.putString("branch", branch);
            b.putString("year", year);
            b.putString("course", course);
            b.putString("sem", sem);
            b.putString("subject", subject);
            b.putString("units", units);
            b.putString("sub_type", sub_type);
            i.putExtras(b);
            startActivity(i);
        }
        else {
            if(isOnline()) {
                //if (isConnectedToServer("http://10.42.0.1",0)) {
                    BackGround2 b = new BackGround2();
                    b.execute(branch, year, course, sem, subject, units,filetype,sub_type);
                /*}
                else
                {
                    Toast.makeText(getApplicationContext(),"Server is busy",Toast.LENGTH_SHORT).show();

                }*/
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        onBackPressed();
    }


    class BackGround2 extends AsyncTask<String, String,String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String sem = params[3];
            String subject = params[4];
            String unit = params[5];
            String filetype = params[6];
            String sub_type = params[7];
            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getdata.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course + "&sem=" + sem + "&subject=" + subject + "&unit=" + unit + "&filetype=" + filetype+"&sub_type="+sub_type;

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

            pDialog = new ProgressDialog(listall1.this);
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
                    //String filetype = obj.getString("filetype");
                    String desc = obj.getString("description");
                      //if (filetype.equals("pdf")) {
                        pdfname[j] = filename;
                        pdfdes[j] = desc;
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

            Intent i = new Intent(listall1.this, middle.class);
            Bundle b = new Bundle();
            b.putInt("itemcount", itemcount1);
            b.putStringArray("itemname", pdfname);
            b.putStringArray("itemdes", pdfdes);
            b.putString("filetype", filetype);
            i.putExtras(b);
           // Toast.makeText(getApplicationContext(),"yrd",Toast.LENGTH_SHORT).show();
            startActivity(i);


        }
    }


    }


