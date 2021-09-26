package education.vt.elearning;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

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

public class pptview extends AppCompatActivity {
    String itemname,filetype;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptview);

        w = (WebView) findViewById(R.id.webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        itemname = bundle.getString("itemname");
        filetype = bundle.getString("filetype");
        BackGround2 b2 = new BackGround2();
        b2.execute(itemname, filetype);




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
            //this.finish();
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
                String pdf = "http://pccoerelearning.com/"+add.replaceAll("\\\\","");
                w.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
                onBackPressed();
                /*try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/gview?embedded=true&url=study.pccoercodemasters.com/"+add.replaceAll("\\\\","")));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
