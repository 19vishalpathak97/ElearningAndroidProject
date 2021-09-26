package education.vt.elearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class videoservice extends Service {

    ProgressDialog pDialog;
    rr temp;
    private IntentSession session;
    String year=null, branch=null, sem=null, course=null, subject=null, units=null, filetype1=null,
            itemcount=null,filetype=null,storagePath=null,itemname=null,url=null;
    int length;
    boolean complete=false;

    private NotificationManager nm;
    NotificationCompat.Builder mBuilder;
    Notification notification;

   // NotificationChannel channel;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            }

            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Downloading")
                    .build();


            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,notification);


            pDialog = new ProgressDialog(videoservice.this);

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
            Bundle bundle = intent.getExtras();
            itemname = bundle.getString("itemname");
            filetype = bundle.getString("filetype");
            Toast.makeText(getApplicationContext(),itemname
                    +filetype, Toast.LENGTH_SHORT).show();
            url = bundle.getString("url");
            length=bundle.getInt("ll");

            if(filetype.equals("mp4")) {
                String extStorageDirectory = Environment
                        .getExternalStorageDirectory().toString();
                storagePath = extStorageDirectory + "/E-resource/Data/VIDEO/";
            }else if(filetype.equals("pdf"))
            {
                String extStorageDirectory = Environment
                        .getExternalStorageDirectory().toString();
                storagePath = extStorageDirectory + "/E-resource/Data/PDF/";
            }else if(filetype.equals("ppt")||filetype.equals("pptx"))
            {
                String extStorageDirectory = Environment
                        .getExternalStorageDirectory().toString();
                storagePath = extStorageDirectory + "/E-resource/Data/PPT/";
            }else
            {
                String extStorageDirectory = Environment
                        .getExternalStorageDirectory().toString();
                storagePath = extStorageDirectory + "/E-resource/Data/DOC/";
            }


            if (isOnline()) {

                temp=new rr();
                temp.execute(url.replaceAll(" ","%20"));
                Toast.makeText(getApplicationContext(), "Downloading started", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Network Unreachable", Toast.LENGTH_SHORT).show();
            }







        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
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
    public void onDestroy() {
        temp.cancel(true);
        //publishResults(1);
        super.onDestroy();
        // Log.i(LOG_TAG, "In onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }

    class rr extends AsyncTask<String, Void, InputStream> {
        //android.support.v7.app.NotificationCompat.Builder builder;
        private boolean running = true;

        @Override
        protected InputStream doInBackground(String... strings) {
            // while (running) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection u = (HttpURLConnection) url.openConnection();
                if (u.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(u.getInputStream());


                    URLConnection connection = url.openConnection();
                    connection.connect();



                    File f = new File(storagePath, itemname);

                    FileOutputStream fos = new FileOutputStream(f);
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    int total = 0;
                    if (inputStream != null) {

                        while ((len1 = inputStream.read(buffer)) > 0 && isOnline()) {

                            total += len1;
                            progressChange((int) (total * 100) / length);
                            fos.write(buffer, 0, len1);
                        }

                        if(length==total)
                        {
                            complete=true;
                        }


                    }
                    if (fos != null) {
                        fos.close();
                    }
                }
            } catch (IOException e) {
                return null;
            }

            return inputStream;


        }

        void progressChange(int progress) {


            if (progress < 100) {
                mBuilder.setProgress(100, Integer.valueOf(progress),
                        false).setContentInfo(progress + "%");
                nm.notify(12, mBuilder.build());
            } else {
                mBuilder.setContentText("Download complete")
                        .setProgress(0, 0, false).setOngoing(false).setContentInfo("");


                nm.notify(12, mBuilder.build());

            }

        }

        @Override
        protected void onCancelled() {
            running = false;
        }

        protected void onPreExecute() {


            mBuilder.setContentTitle(
                    "Downloading")
                    .setContentText("Download in progress")
                    .setSmallIcon(R.drawable.download).setContentInfo("0%");

            // mBuilder.setOngoing(true);

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            if(complete)
            {
                session.setdownloading("1");
                // android.support.v7.app.NotificationCompat.Builder builder =
                //   (android.support.v7.app.NotificationCompat.Builder) new android.support.v7.app.NotificationCompat.Builder(videoservice.this)
                mBuilder.setSmallIcon(R.drawable.download)
                        .setContentTitle("Download")
                        .setContentText("Download Successful  ");


                Intent stopIntent = new Intent(getApplicationContext(), videoservice.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
            }
            else
            {
                //mBuilder.setAutoCancel(true);

                Intent stopIntent = new Intent(getApplicationContext(), videoservice.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
                //onDestroy();

            }


        }
    }


}