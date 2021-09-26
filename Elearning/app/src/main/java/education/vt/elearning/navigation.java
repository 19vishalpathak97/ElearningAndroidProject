package education.vt.elearning;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

public class navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private IntentSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        session = new IntentSession(getApplicationContext());
        /*if (isOnline()) {
            BackGround2 b = new BackGround2();
            b.execute();
        }*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {


            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }
        else {
        }
        createFolder();
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        String s1 = intent.getStringExtra("Check");


        if(s1.equals("5"))
        {
            s1 = "";
            Fragment fragment = null;
            fragment = new Downloaded();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if(s1.equals("4"))
        {
            s1 = "";
            Fragment fragment = null;
            fragment = new Download();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if(s1.equals("3"))
        {
            s1 = "";
            Fragment fragment = null;
            fragment = new Available();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if(s1.equals("2"))
        {
            s1 = "";
            Fragment fragment = null;
            fragment = new Notification();
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if(s1.equals(""))
        {
            displaySelectedScreen(R.id.nav_home);
        }



    }



    private void createFolder()
    {
        String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        File dir1 = new File(extStorageDirectory,"E-resource/Data/PDF");
        File dir2 = new File(extStorageDirectory,"E-resource/Data/PPT");
        File dir3 = new File(extStorageDirectory,"E-resource/Data/DOC");
        File dir4 = new File(extStorageDirectory,"E-resource/Data/VIDEO");
        File dir5 = new File(extStorageDirectory,"E-resource/Data/IMG");
        if(!dir1.exists()){
            if (!dir1.mkdirs()) {
                //Toast.makeText(getApplicationContext(),"Failed to allocate",Toast.LENGTH_SHORT).show();

            }
        }
        if(!dir2.exists())
            dir2.mkdirs();
        if(!dir3.exists())
            dir3.mkdirs();
        if(!dir4.exists())
            dir4.mkdirs();
        if(!dir5.exists())
            dir5.mkdirs();

    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            onBackPressed1();
        }
    }


    public void showcredit()
    {
        Fragment fragment = null;
        fragment = new Credits();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
    public void onBackPressed1()
    {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (currentFragment instanceof Downloaded || currentFragment instanceof Available || currentFragment instanceof Notification || currentFragment instanceof Download || currentFragment instanceof Credits) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Home fragment = new Home();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Exit Application?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    session.cleardata();
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showcredit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private void displaySelectedScreen(int itemId) {

        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_home:
                fragment = new Home();
                break;
            case R.id.nav_download:
                fragment = new Download();
                break;
            case R.id.nav_notification:
                fragment = new Notification();
                break;
            case R.id.nav_downloaded:
                fragment = new Downloaded();
                break;
            case R.id.nav_available:
                fragment = new Available();
                break;
            case R.id.nav_credit:
                fragment = new Credits();
                break;


        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }



    public boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}