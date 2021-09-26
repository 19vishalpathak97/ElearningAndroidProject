package education.vt.elearning;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class listfromsd extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfromsd);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle b = getIntent().getExtras();
        final String itemname=b.getString("itemname");

        list = (ListView) findViewById(R.id.list);

        final String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        final ArrayList<String> FilesInFolder = GetFiles(extStorageDirectory+"/E-resource/Data/"+itemname+"/");
        if(FilesInFolder==null)
        {
            Toast.makeText(getApplicationContext(),"No downloaded files",Toast.LENGTH_SHORT).show();
        }
        else {


            list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, FilesInFolder));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    String Slecteditem = FilesInFolder.get(+position);
                    File file = new File(extStorageDirectory + "/E-resource/Data/" + itemname + "/",
                            Slecteditem);
                    Uri path;
                    Intent i;
                    //Uri path = Uri.fromFile(file);
                    //Uri path = FileProvider.getUriForFile(listfromsd.this, BuildConfig.APPLICATION_ID + "com.vtt.heart_hacker.vtt.provider",file);
                    //Uri path = FileProvider.getUriForFile(listfromsd.this, listfromsd.this.getApplicationContext().getPackageName() + ".com.vtt.heart_hacker.vtt.provider", file);

                    //File toInstall = new File(appDirectory, appName + ".apk");
                    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        path = FileProvider.getUriForFile(listfromsd.this, BuildConfig.APPLICATION_ID + ".provider", file);
                        i = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        i.setData(path);
                        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        listfromsd.this.startActivity(i);
                    } else {*/
                        path = Uri.fromFile(file);
                        i = new Intent(Intent.ACTION_VIEW);
                        //i.setDataAndType(path, "application/vnd.android.package-archive");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        listfromsd.this.startActivity(i);



                    //i = new Intent(Intent.ACTION_VIEW);


                    if (itemname.equals("PDF")) {
                        i.setDataAndType(path, "application/pdf");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {

                        }
                    }
                    if (itemname.equals("PPT")) {
                        i.setDataAndType(path, "application/vnd.ms-powerpoint");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {

                        }
                    }
                    if (itemname.equals("VIDEO")) {
                        i.setDataAndType(path, "video/mp4");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {

                        }
                    }
                    if (itemname.equals("IMG")) {
                        i.setDataAndType(path, "image/jpeg");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {

                        }
                    }
                    if (itemname.equals("DOC")) {
                        i.setDataAndType(path, "application/msword");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {

                        }
                    }

                }
            });
        }
    }
    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++)
                MyFiles.add(files[i].getName());
        }

        return MyFiles;
    }

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

        Intent intent = new Intent(listfromsd.this,navigation.class);

        intent.putExtra("Check","5");
        startActivity(intent);
    }

    /*public void onBackPressed()
    {
       Intent i = new Intent(getApplicationContext(),navigation.class);
        startActivity(i);

    }*/
}
