package education.vt.elearning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Available extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    List<String> categories1 = new ArrayList<String>();
    List<String> categories2 = new ArrayList<String>();
    List<String> categories3 = new ArrayList<String>();
    List<String> categories4 = new ArrayList<String>();
    List<String> categories5 = new ArrayList<String>();
    //List<String> categories6 = new ArrayList<String>();

    String year=null,branch=null,sem=null,course=null,subject=null,sub_type=null;
    ProgressDialog pDialog;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_available, container, false);
        pDialog=new ProgressDialog(getActivity());


        btn = (Button) v.findViewById(R.id.button);
        btn.setOnClickListener(this);
        Spinner spinner1 = (Spinner) v.findViewById(R.id.spinner1);

        categories1.add("Branch");
        categories1.add("Computer");
        categories1.add("E&TC");
        categories1.add("First Year");
        categories1.add("Mechanical");
        categories1.add("Civil");
        spinner1.setOnItemSelectedListener(Available.this);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);



        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);

        categories2.add("Year");
        categories2.add("FE");
        categories2.add("SE");
        categories2.add("TE");
        categories2.add("BE");
        categories3.add("Course");
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);




        Spinner spinner3 = (Spinner) v.findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);

        Spinner spinner4 = (Spinner) v.findViewById(R.id.spinner4);

        categories4.add("Semester");
        //categories4.add("First");
        //categories4.add("Second");
        categories5.add("Subjects");
        spinner4.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(dataAdapter4);

        Spinner spinner5 = (Spinner) v.findViewById(R.id.spinner5);

        spinner5.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories5);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(dataAdapter5);




        return v;
    }







    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
            case R.id.spinner1:
                String a=parent.getItemAtPosition(position).toString();
                if(a.equals("Computer"))
                    branch = "ComputerEngineering";
                if(a.equals("E&TC"))
                    branch = "Electronics&TelecommunicationEngineering".replaceAll("&","%26");
                if(a.equals("Mechanical"))
                    branch = "MechanicalEngineering";
                if(a.equals("Civil"))
                    branch = "CivilEngineering";
                if(a.equals("Branch"))
                    branch = "Branch";
                if(a.equals("First Year"))
                    branch = "FirstYear";

                // categories1.clear();
                break;
            case R.id.spinner2:
                year=parent.getItemAtPosition(position).toString();

                if(year.equals("Year"))
                {
                }

                else {
                    categories3.clear();
                    categories3.add("Course");
                    if(isOnline()){

                            BackGround kk = new BackGround();
                            kk.execute(branch, year);

                    }
                    else
                    {
                        Toast.makeText(getContext(), "Please Check Your Connection", Toast.LENGTH_LONG).show();

                    }

                }

                break;
            case R.id.spinner3:
                course = parent.getItemAtPosition(position).toString();
                if(course.equals("Course"))
                {
                }

                else {
                    categories4.clear();
                    categories4.add("Semester");
                    if(isOnline()){

                        BackGround3 kk = new BackGround3();
                        kk.execute(branch, year,course);

                    }
                    else
                    {
                        Toast.makeText(getContext(), "Please Check Your Connection", Toast.LENGTH_LONG).show();

                    }

                }
                //categories3.clear();
                break;
            case R.id.spinner4:
                sem=parent.getItemAtPosition(position).toString();

                if(sem.equals("Semester"))
                {
                    //Toast.makeText(getApplicationContext(),"select Semester",Toast.LENGTH_LONG).show();
                }

                else {
                    categories5.clear();
                    categories5.add("Subjects");
                    if (isOnline()) {
                       // if (isConnectedToServer("http://10.42.0.1",0)) {
                            BackGround1 kk = new BackGround1();
                            kk.execute(branch, year, course, sem);
                       /* }
                        else
                        {
                            Toast.makeText(getContext(),"Server is busy",Toast.LENGTH_SHORT).show();

                        }*/
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Please Check Your Connection", Toast.LENGTH_LONG).show();

                    }
                }
                //categories4.clear();
                break;
            case R.id.spinner5:
                subject=parent.getItemAtPosition(position).toString();

                break;


        }

    }
    public void onClick(View v) {
        if(branch.equals("Branch")||year.equals("Year")||course.equals("Course")||sem.equals("Semester")||subject.equals("Subject")) {
            Toast.makeText(getContext(),"Please enter Details",Toast.LENGTH_SHORT).show();

        }
        else
        {

            Intent i = new Intent(getActivity(), getAll.class);
            Bundle bundle = new Bundle();
            bundle.putString("branch", branch);
            bundle.putString("year", year);
            bundle.putString("course", course);
            bundle.putString("sem", sem);
            bundle.putString("subject", subject);
            //bundle.putString("sub_type", sub_type);
            i.putExtra("Check","3");
            i.putExtras(bundle);
            startActivity(i);
        }
    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Available");
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class BackGround extends AsyncTask<String, String,String> {

        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/getcourse.php");
                String urlParams = "branch=" + branch + "&year=" + year;;
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

                // Read Server Response

                while((line = reader.readLine()) != null) {
                    String[] split = line.split("<br>");
                    int x=split.length;

                    for (int i=0;i<x;i++)
                    {
                        categories3.add(split[i]);
                    }
                    sb.append(line);
                    break;
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  "Exeption there :"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exeption there :"+e.getMessage();
            }
        }
        protected void onPreExecute() {

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Fetching data...");
            pDialog.setCanceledOnTouchOutside(false);
            //pDialog.setCancelable(false);
            pDialog.show();

        }

        protected void onPostExecute(String s) {

            if(s.isEmpty())
                Toast.makeText(getContext(),"No data Available",Toast.LENGTH_SHORT).show();

            long delay=500;
            Timer time =new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();

                }
            },delay);
        }


    }



    class BackGround1 extends AsyncTask<String, String,String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String sem = params[3];
            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/subject1.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course + "&sem=" + sem;
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

                // Read Server Response

                while((line = reader.readLine()) != null) {
                    String[] split = line.split("<br>");
                    int x=split.length;

                    for (int i=0;i<x;i++)
                    {
                        categories5.add(split[i]);
                    }
                    sb.append(line);
                    break;
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  "Exeption there :"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exeption there :"+e.getMessage();
            }
        }

        protected void onPreExecute() {

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Fetching data...");
            pDialog.setCanceledOnTouchOutside(false);
            //pDialog.setCancelable(false);
            pDialog.show();

        }
        protected void onPostExecute(String s) {
            if(s.isEmpty())
                Toast.makeText(getContext(),"No data Available",Toast.LENGTH_SHORT).show();
            long delay=500;
            Timer time =new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();

                }
            },delay);
        }


    }





    class BackGround3 extends AsyncTask<String, String,String> {
        protected String doInBackground(String... params) {
            String branch = params[0];
            String year = params[1];
            String course = params[2];
            String data = "";
            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/semester.php");
                String urlParams = "branch=" + branch + "&year=" + year + "&course=" + course;
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

                // Read Server Response

                while((line = reader.readLine()) != null) {
                    String[] split = line.split("<br>");
                    int x=split.length;

                    for (int i=0;i<x;i++)
                    {
                        categories4.add(split[i]);
                    }
                    sb.append(line);
                    break;
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  "Exeption there :"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exeption there :"+e.getMessage();
            }
        }

        protected void onPreExecute() {

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Fetching data...");
            pDialog.setCanceledOnTouchOutside(false);
            //pDialog.setCancelable(false);
            pDialog.show();

        }
        protected void onPostExecute(String s) {
            if(s.isEmpty())
                Toast.makeText(getContext(),"No data Available",Toast.LENGTH_SHORT).show();
            long delay=500;
            Timer time =new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();

                }
            },delay);
        }


    }
}