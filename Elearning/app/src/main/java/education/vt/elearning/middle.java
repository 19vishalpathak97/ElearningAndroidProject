package education.vt.elearning;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class middle extends AppCompatActivity {
String itemname1=null;
    String year, branch, sem, course, subject, units,sub_type;
    int itemcount;
    String[] itemname,itemdes;
    String filetype;
    private IntentSession session;
    ListView list;
    String downflag="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        itemcount=bundle.getInt("itemcount");
        session = new IntentSession(getApplicationContext());
        HashMap<String, String> user = session.getolddata();
        session.setdownloading("0");
        downflag=user.get(IntentSession.dowm_vid);

        branch = user.get(IntentSession.k_branch);
        year = user.get(IntentSession.k_year);
        course = user.get(IntentSession.k_course);
        sem = user.get(IntentSession.k_sem);
        subject = user.get(IntentSession.k_sub);
        sub_type = user.get(IntentSession.k_sub_type_);
        units = user.get(IntentSession.k_units);
        filetype = bundle.getString("filetype");
        itemname=new String[itemcount];
        itemdes=new String[itemcount];

        itemname = bundle.getStringArray("itemname");
        itemdes = bundle.getStringArray("itemdes");
        CustomListAdapter adapter = new CustomListAdapter(this, itemname,itemdes,filetype);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                CharSequence colors[] = new CharSequence[]{"View", "Download"};

                AlertDialog.Builder builder = new AlertDialog.Builder(middle.this);
                builder.setTitle("You want " + itemname[position] + " to");
                itemname1=itemname[position];
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = 26)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();

                            if(filetype.equals("pdf"))
                            {
                                Intent i=new Intent(middle.this,pdfview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype.equals("mp4"))
                            {
                                Intent i=new Intent(middle.this,videoview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype.equals("pptx")||filetype.equals("ppt"))
                            {
                                Intent i=new Intent(middle.this,pptview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype.equals("docx"))
                            {
                                Intent i=new Intent(middle.this,docview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }


                        } else if (which == 1) {
                            //Toast.makeText(getApplicationContext(), "it's Download part", Toast.LENGTH_SHORT).show();
                            if(filetype.equals("pdf"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype.equals("mp4"))
                            {

                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype.equals("ppt")||filetype.equals("pptx"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype.equals("docx"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }

                        }

                    }
                });
                builder.show();
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }

        });


    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

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
        Intent i5 = new Intent(middle.this,getData.class);
        Bundle bundle = new Bundle();
        bundle.putString("branch", branch);
        bundle.putString("year", year);

        bundle.putString("course", course);
        bundle.putString("sem", sem);
        bundle.putString("subject", subject);
        bundle.putString("sub_type", sub_type);
        bundle.putString("units", units);
        i5.putExtras(bundle);

        startActivity(i5);
    }

}
