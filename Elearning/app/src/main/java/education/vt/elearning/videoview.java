package education.vt.elearning;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.VideoView;

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
import java.util.HashMap;

public class videoview extends Activity {
    ProgressDialog pDialog;
    VideoView videoview;
    BackGround2 b2 = new BackGround2();
    private IntentSession session;
    String year, branch, sem, course, subject, units,filetype1,itemcount,filetype,itemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        session = new IntentSession(getApplicationContext());
        HashMap<String, String> user = session.getolddata();
        branch = user.get(IntentSession.k_branch);
        year = user.get(IntentSession.k_year);
        course = user.get(IntentSession.k_course);
        sem = user.get(IntentSession.k_sem);
        subject = user.get(IntentSession.k_sub);
        units = user.get(IntentSession.k_units);
        filetype1 = user.get(IntentSession.k_filetype);
        itemcount = user.get(IntentSession.k_itemcount);
        Bundle bundle = getIntent().getExtras();
        itemname = bundle.getString("itemname");
        filetype = bundle.getString("filetype");



        videoview = (VideoView) findViewById(R.id.videoView5);
        pDialog = new ProgressDialog(videoview.this);
        pDialog.setTitle("Welcome by VT");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        b2.execute(itemname, filetype);



    }

    @Override
    public void onBackPressed()
    {

        b2.cancel(true);
        System.exit(0);
    }



    class BackGround2 extends AsyncTask<String, String, String> {
        boolean a=false;

        protected String doInBackground(String... params) {

            String itemname1 = params[0];
            String filetype1 = params[1];

            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getAdd.php");
                String urlParams =  "filename=" + itemname1.replaceAll("&","%26") + "&filetype=" + filetype1;
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
                    if (isCancelled())
                        break;

                   // if(a==true)
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
        public  void cancell(boolean a1)
        {
            this.a=a1;
        }
        protected void onPreExecute() {}

        protected void onPostExecute(String s) {
            try {
                JSONArray ja = new JSONArray(s);
                JSONObject obj = ja.getJSONObject(0);
                String add=obj.getString("address");

                Intent i=new Intent(videoview.this,playvideo.class);
                Bundle b=new Bundle();
                b.putString("lulu","http://pccoerelearning.com/"+add.replaceAll("\\\\",""));
                i.putExtras(b);
                startActivity(i);

/*
                try {
                    MediaController mediacontroller = new MediaController(
                            videoview.this);
                    mediacontroller.setAnchorView(videoview);
                    Uri video = Uri.parse("http://pccoerelearning.com/"+add.replaceAll("\\\\",""));
                    videoview.setMediaController(mediacontroller);
                    videoview.setVideoURI(video);

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

                videoview.requestFocus();
                videoview.setOnPreparedListener(new OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        pDialog.dismiss();
                        if (!isCancelled())
                        videoview.start();
                    }
                });*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
