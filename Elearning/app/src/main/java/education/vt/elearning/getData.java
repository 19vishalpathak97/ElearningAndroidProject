package education.vt.elearning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class getData extends AppCompatActivity {
    String year, branch, sem, course, subject, units,sub_type;
    String pdfsc="0",pptsc="0",docsc="0",videosc="0",imgsc="0";
    int pdfc,pptc,docc,videoc,imgc;
    ProgressDialog pDialog;
    TextView pd,vd1,ppt1,qp2,im;
    private IntentSession session;
    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_getdata);

        pDialog = new ProgressDialog(getData.this);
        session = new IntentSession(getApplicationContext());

        HashMap<String, String> user = session.getolddata();
        pdfsc = user.get(IntentSession.pdfc);
        videosc = user.get(IntentSession.vidc);
        docsc = user.get(IntentSession.docc);
        pptsc = user.get(IntentSession.pptc);
        imgsc = user.get(IntentSession.imgc);


        if (savedInstanceState != null) {
            pdfsc = savedInstanceState.getString("pdfc");
            videosc = savedInstanceState.getString("vidc");
            pptsc = savedInstanceState.getString("pptc");
            docsc = savedInstanceState.getString("docc");
            imgsc = savedInstanceState.getString("imgc");
        }






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        Bundle bundle = getIntent().getExtras();
        branch = bundle.getString("branch");
        year = bundle.getString("year");
        course = bundle.getString("course");
        sem = bundle.getString("sem");
        subject = bundle.getString("subject");
        units = bundle.getString("units");
        sub_type=bundle.getString("sub_type");

        if(isOnline()) {
                BackGround2 b = new BackGround2();
                b.execute(branch, year, course, sem, subject, units,sub_type);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network unreachable",Toast.LENGTH_SHORT).show();
        }
        ImageView img = (ImageView) findViewById(R.id.imageView);
        ImageView vd=(ImageView)findViewById(R.id.imageView2);
        ImageView qp=(ImageView)findViewById(R.id.imageView3);
        ImageView ppt=(ImageView)findViewById(R.id.imageView4);
        ImageView imageView=(ImageView)findViewById(R.id.imageView5);

        pd=(TextView)findViewById(R.id.textView);
        vd1=(TextView)findViewById(R.id.textView2);
        ppt1=(TextView)findViewById(R.id.textView5);
        qp2=(TextView)findViewById(R.id.textView4);
        im=(TextView)findViewById(R.id.textView7);





        pd.setText("PDF:"+pdfsc);
        vd1.setText("Videos:"+videosc);
        qp2.setText("Docs:"+docsc);
        ppt1.setText("PPT:"+pptsc);
        im.setText("Images:"+imgsc);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                    Intent i = new Intent(getApplicationContext(), listall1.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("itemcount", pdfsc);
                    bundle.putString("filetype", "pdf");
                    bundle.putString("branch", branch);
                    bundle.putString("year", year);
                    bundle.putString("course", course);
                    bundle.putString("sem", sem);
                    bundle.putString("subject", subject);
                    bundle.putString("sub_type", sub_type);
                    bundle.putString("units", units);
                    session.StoreData(branch, year, course, sem, subject,sub_type, units, "pdf", pdfsc);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
                }

            }
        });
        vd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                Intent i1 = new Intent(getApplicationContext(),listall1.class);
                Bundle bundle = new Bundle();
                bundle.putString("branch", branch);
                bundle.putString("year", year);
                bundle.putString("course", course);
                bundle.putString("sem", sem);
                bundle.putString("subject", subject);
                bundle.putString("sub_type", sub_type);
                bundle.putString("units", units);
                bundle.putString("itemcount", videosc);
                bundle.putString("filetype", "mp4");
                session.StoreData(branch,year,course,sem,subject,sub_type,units,"mp4",videosc);

                i1.putExtras(bundle);
                startActivity(i1);
            }
                else
            {
                Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
            }

            }
        });
        qp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                Intent i2 = new Intent(getApplicationContext(),listall1.class);
                Bundle bundle = new Bundle();
                bundle.putString("branch", branch);
                bundle.putString("year", year);
                bundle.putString("course", course);
                bundle.putString("sem", sem);
                bundle.putString("subject", subject);
                bundle.putString("sub_type", sub_type);
                bundle.putString("units", units);
                bundle.putString("itemcount", docsc);
                bundle.putString("filetype", "docx");
                session.StoreData(branch,year,course,sem,subject,sub_type,units,"docx",docsc);

                i2.putExtras(bundle);
                startActivity(i2);
        }
                else
        {
            Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
        }
            }
        });
        ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                Intent i3 = new Intent(getApplicationContext(),listall1.class);
                Bundle bundle = new Bundle();
                bundle.putString("branch", branch);
                bundle.putString("year", year);
                bundle.putString("course", course);
                bundle.putString("sem", sem);
                bundle.putString("subject", subject);
                bundle.putString("sub_type", sub_type);
                bundle.putString("units", units);
                bundle.putString("itemcount", pptsc);
                bundle.putString("filetype", "pptx");
                session.StoreData(branch,year,course,sem,subject,sub_type,units,"ppt",pptsc);

                i3.putExtras(bundle);
                startActivity(i3);
        }
        else
        {
        Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
        }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                Intent i3 = new Intent(getApplicationContext(),listall1.class);
                Bundle bundle = new Bundle();
                bundle.putString("branch", branch);
                bundle.putString("year", year);
                bundle.putString("course", course);
                bundle.putString("sem", sem);
                bundle.putString("subject", subject);
                bundle.putString("sub_type", sub_type);
                bundle.putString("units", units);
                bundle.putString("itemcount", imgsc);
                bundle.putString("filetype", "IMG");
                session.StoreData(branch,year,course,sem,subject,sub_type,units,"IMG",imgsc);

                i3.putExtras(bundle);
                startActivity(i3);
        }
        else
        {
        Toast.makeText(getApplicationContext(),"Network Unreachable",Toast.LENGTH_SHORT).show();
        }

            }
        });



    }
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pdfc",pdfsc);
        outState.putString("vidc",videosc);
        outState.putString("pptc",pptsc);
        outState.putString("imgc",imgsc);
        outState.putString("docc",docsc);
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


    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(this,navigation.class);

        intent.putExtra("Check","4");
        startActivity(intent);
    }



    class BackGround2 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String sem = params[3];
            String subject = params[4];
            String unit = params[5];
            String sub_type = params[6];
            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getcount.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course + "&sem=" + sem + "&subject=" + subject+"&sub_type="+sub_type + "&unit=" + unit;
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

            pDialog = new ProgressDialog(getData.this);
            pDialog.setMessage("Fetching data...");
            pDialog.setCanceledOnTouchOutside(false);
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
                    pdfsc=obj.getString("pdfcount");
                    videosc=obj.getString("vidcount");
                    docsc=obj.getString("doccount");
                    pptsc=obj.getString("pptcount");
                    imgsc=obj.getString("imgcount");
                pd.setText("PDF:"+pdfsc);
                vd1.setText("Videos:"+videosc);
                qp2.setText("Docs:"+docsc);
                ppt1.setText("PPT:"+pptsc);
                im.setText("Images:"+imgsc);
                session.StoreContentCount(pdfsc,videosc,docsc,pptsc,imgsc);
                pdfc=Integer.parseInt(pdfsc.toString());
                videoc=Integer.parseInt(videosc.toString());
                docc=Integer.parseInt(docsc.toString());
                pptc=Integer.parseInt(pptsc.toString());
                imgc=Integer.parseInt(imgsc.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }


}

