package education.vt.elearning;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
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

public class pdfview extends AppCompatActivity {
String filetype,itemname;
    //PDFView pdfView;
    ProgressDialog pDialog;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptview);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        w = (WebView) findViewById(R.id.webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pDialog = new ProgressDialog(pdfview.this);
        Bundle b=getIntent().getExtras();
        filetype=b.getString("filetype");
        itemname=b.getString("itemname");
       // Toast.makeText(getApplicationContext(),itemname+filetype,Toast.LENGTH_SHORT).show();
        if(isOnline()) {
               // pdfView = (PDFView) findViewById(R.id.pdfView);
                BackGround2 b2 = new BackGround2();
                b2.execute(itemname, filetype);
                //new rr().execute("http://10.42.0.1/project/pdf/" + itemname);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
            onBackPressed();
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
            //finish();
            onBackPressed();
            return true;
        }return super.onOptionsItemSelected(item);
    }

    class BackGround2 extends AsyncTask<String, String, String> {
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

        protected void onPreExecute() {}

        protected void onPostExecute(String s) {
            try {
                JSONArray ja = new JSONArray(s);
                JSONObject obj = ja.getJSONObject(0);
                String add=obj.getString("address");
                //new rr().execute("http://study.pccoercodemasters.com/"+add.replaceAll("\\\\",""));

                String pdf = "http://pccoerelearning.com/"+add.replaceAll("\\\\","").replaceAll("&","%26");
                //Toast.makeText(getApplicationContext(),pdf,Toast.LENGTH_SHORT).show();
                w.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
                onBackPressed();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




}
