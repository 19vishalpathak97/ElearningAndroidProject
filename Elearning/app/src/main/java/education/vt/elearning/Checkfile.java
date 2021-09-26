package education.vt.elearning;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Checkfile extends AppCompatActivity {
    private IntentSession session;
    String filename,filetype;
    Button b;
    File f;
    int len;
    int length=1;
    ImageView i;

    TextView t1,t2;
    int fileLength;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkfile);
        i=(ImageView)findViewById(R.id.imageView8);
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView6);
        session = new IntentSession(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        filename=bundle.getString("itemname");
        filetype=bundle.getString("filetype");
        b=(Button)findViewById(R.id.button2);
        b.setEnabled(false);

        if(filetype.equals("pdf"))
        {
            i.setImageResource(R.drawable.pdf);
        }
        else if(filetype.equals("mp4"))
        {
            i.setImageResource(R.drawable.video);
        }
        else if(filetype.equals("docx"))
        {
            i.setImageResource(R.drawable.qp);
        }
        else if(filetype.equals("ppt")||filetype.equals("pptx"))
        {
            i.setImageResource(R.drawable.ppt);
        }
        else if(filetype.equals("IMG"))
        {
            i.setImageResource(R.drawable.img);
        }
        t1.setText(filename);
        t2.setText("Downloading Started");

        String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        //storagePath = extStorageDirectory + "/E-resource/Data/VIDEO/"+filename;
        if(filetype.equals("mp4")) {
            f = new File(extStorageDirectory + "/E-resource/Data/VIDEO/" + filename);
        }else if(filetype.equals("pdf"))
        {
            f = new File(extStorageDirectory + "/E-resource/Data/PDF/" + filename);
        }else if(filetype.equals("ppt")||filetype.equals("pptx"))
        {
            f = new File(extStorageDirectory + "/E-resource/Data/PPT/" + filename);
        }else
        {
            f = new File(extStorageDirectory + "/E-resource/Data/DOC/" + filename);
        }






        //Toast.makeText(getApplicationContext(),filename+filetype,Toast.LENGTH_SHORT).show();
        if (isMyServiceRunning(videoservice.class)||isMyServiceRunning(videoservice2.class)) { }
        else {

            BackGround2 b = new BackGround2();
            b.execute(filename, filetype);
        }



    }


    class BackGround2 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {

            String itemname1 = params[0];
            String filetype1 = params[1];
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getAdd.php");
                String urlParams =  "filename=" + itemname1.replaceAll("&","%26") + "&filetype=" + filetype1;
                // Toast.makeText(getApplicationContext(),itemname1+filetype1,Toast.LENGTH_SHORT).show();
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream oo = httpURLConnection.getOutputStream();
                oo.write(urlParams.getBytes());
                oo.flush();
                oo.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
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

        protected void onPostExecute(String s) {
            try {
                //Toast.makeText(getApplicationContext(), "hello2", Toast.LENGTH_SHORT).show();

                JSONArray ja = new JSONArray(s);
                JSONObject obj = ja.getJSONObject(0);
                add = obj.getString("address");
                //Toast.makeText(getApplicationContext(),add,Toast.LENGTH_SHORT).show();
                BackGround20 kk=new BackGround20();
                kk.execute("http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
               // new rr().execute("http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
            } catch (Exception e) {
            }


        }

    }




    class BackGround20 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {

            String urll = params[0];
        //    String filetype1 = params[1];
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/length.php");
                String urlParams =  "url=" + urll;
                // Toast.makeText(getApplicationContext(),itemname1+filetype1,Toast.LENGTH_SHORT).show();
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream oo = httpURLConnection.getOutputStream();
                oo.write(urlParams.getBytes());
                oo.flush();
                oo.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
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

        protected void onPostExecute(String s) {
            try {
                //Toast.makeText(getApplicationContext(), "hello2", Toast.LENGTH_SHORT).show();

                JSONArray ja = new JSONArray(s);
                JSONObject obj = ja.getJSONObject(0);
                length = Integer.parseInt(obj.getString("length"));
                //Toast.makeText(getApplicationContext(),add,Toast.LENGTH_SHORT).show();
                //BackGround20 kk=new BackGround20();
                //kk.execute("http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
                 new rr().execute("http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
            } catch (Exception e) {
            }


        }

    }




    /*private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
                int progress = bundle.getInt("progress");
                    if (progress==1) {
                        b.setEnabled(false);
                        t2.setText("Download Complete");
                    }

            }
        }
    };*/

    public  void  band(View v)
    {
        HashMap<String, String> user = session.getolddata();
        String vvv = user.get(IntentSession.dowm_vid);
        int vv=Integer.parseInt(vvv);
        if (vv==1)
        {
            //b.setEnabled(false);
        }
        else {
            if(isMyServiceRunning(videoservice.class))
            {
                Intent stopIntent = new Intent(Checkfile.this, videoservice.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
            }
            else if(isMyServiceRunning(videoservice2.class)) {
                Intent stopIntent = new Intent(Checkfile.this, videoservice2.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
            }
        }
    }



    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            //finish();
            onBackPressed();
            return true;
        }return super.onOptionsItemSelected(item);
    }

    class rr extends AsyncTask<String, Void, InputStream> {


        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection u = (HttpURLConnection) url.openConnection();
                if (u.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(u.getInputStream());


                    URLConnection connection = url.openConnection();
                    connection.connect();
                    fileLength = connection.getContentLength();
//Toast.makeText(getApplication(),fileLength,Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                return null;
            }

            return inputStream;


        }


        @Override
        protected void onPostExecute(InputStream inputStream) {

            if (f.exists()) {
                len = (int) f.length();
                //Toast.makeText(getApplicationContext(),fileLength+"  "+len, Toast.LENGTH_SHORT).show();

                if (fileLength == len) {
                    Toast.makeText(getApplicationContext(), "File downloaded", Toast.LENGTH_SHORT).show();
                } else {
                    b.setEnabled(true);
                    t2.setText("Started resumed downloading");
                    Intent i = new Intent(getBaseContext(), videoservice2.class);
                    Bundle b = new Bundle();
                    b.putString("filetype", filetype);
                    b.putString("itemname", filename);
                    b.putInt("halfdown", len);
                    b.putInt("ll",length);
                    b.putString("url","http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
                    i.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    i.putExtras(b);
                    startService(i);
                }

            } else {
                // Toast.makeText(getApplicationContext(), "hiiii", Toast.LENGTH_SHORT).show();
                b.setEnabled(true);
                Intent i = new Intent(getBaseContext(), videoservice.class);
                Bundle b1 = new Bundle();
                b1.putString("filetype", filetype);
                b1.putString("itemname", filename);
                b1.putInt("ll",length);
                //Toast.makeText(getApplicationContext(),filename+filetype, Toast.LENGTH_SHORT).show();
                b1.putString("url","http://pccoerelearning.com/" + add.replaceAll("\\\\", ""));
                i.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                i.putExtras(b1);
                startService(i);
                // b.setEnabled(false);

            }
        }
    }

}

