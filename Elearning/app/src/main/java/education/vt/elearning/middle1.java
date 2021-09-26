package education.vt.elearning;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class middle1 extends AppCompatActivity {
    String itemname1=null,filetype1;
    String year, branch, sem, course, subject, units,sub_type;

    int itemcount;
    String[] itemname,itemdes,filetype;
    private IntentSession session;
    ListView list;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);

        Intent intent = getIntent();
        check = intent.getStringExtra("Check");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        itemcount=bundle.getInt("itemcount");
        session = new IntentSession(getApplicationContext());
        HashMap<String, String> user = session.getolddata();
        session.setdownloading("0");
        branch = user.get(IntentSession.k_branch);
        year = user.get(IntentSession.k_year);
        course = user.get(IntentSession.k_course);
        sem = user.get(IntentSession.k_sem);
        subject = user.get(IntentSession.k_sub);
        // = user.get(IntentSession.k_sub_type_);
        units = user.get(IntentSession.k_units);
        itemname=new String[itemcount];
        itemdes=new String[itemcount];
        filetype=new String[itemcount];


        itemname = bundle.getStringArray("itemname");
        itemdes = bundle.getStringArray("itemdes");
        filetype = bundle.getStringArray("filetype");
        //check = intent.getStringExtra("Check");




        CustomListAdapter1 adapter = new CustomListAdapter1(this, itemname,itemdes,filetype);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                CharSequence colors[] = new CharSequence[]{"View", "Download"};

                AlertDialog.Builder builder = new AlertDialog.Builder(middle1.this);
                builder.setTitle("You want " + itemname[position] + " to");
                itemname1=itemname[position];
                filetype1=filetype[position];
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT).show();

                            if(filetype1.equals("pdf"))
                            {
                                Intent i=new Intent(middle1.this,pdfview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype1);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype1.equals("mp4"))
                            {
                                Intent i=new Intent(middle1.this,videoview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype1);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype1.equals("ppt") || filetype1.equals("pptx"))
                            {
                                Intent i=new Intent(middle1.this,pptview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype1);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(filetype1.equals("docx"))
                            {
                                Intent i=new Intent(middle1.this,docview.class);
                                Bundle b=new Bundle();
                                b.putString("filetype",filetype1);
                                b.putString("itemname",itemname1);
                                i.putExtras(b);
                                startActivity(i);
                            }


                        } else if (which == 1) {
                            //Toast.makeText(getApplicationContext(), "it's Download part", Toast.LENGTH_SHORT).show();
                            if(filetype1.equals("pdf"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype1);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype1.equals("mp4"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype1);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype1.equals("ppt")||filetype1.equals("pptx"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype1);
                                b.putString("itemname", itemname1);
                                i.putExtras(b);
                                startActivity(i);

                            }
                            if(filetype1.equals("docx"))
                            {
                                Intent i = new Intent(getApplicationContext(), Checkfile.class);
                                Bundle b = new Bundle();
                                b.putString("filetype", filetype1);
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

        Intent intent = new Intent(this,navigation.class);
        if(check.equals("3"))
            intent.putExtra("Check","3");
        else
            intent.putExtra("Check","2");
        startActivity(intent);
    }
}
