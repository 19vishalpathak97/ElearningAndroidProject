package education.vt.elearning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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


public class Notification extends Fragment {
    ListView list;
    String[] itemname={"FirstYear","Computer","Mechanical","Electronics","Civil"};
    String[] itemcount=new String[5];
    int itemc;
    private IntentSession session;
    String[] iname,ides,filetype;

    ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pDialog = new ProgressDialog(getActivity());
        session = new IntentSession(getContext());
        HashMap<String, String> user = session.getolddata();
        itemcount[0] = "0";//user.get(IntentSession.comp_count);
        itemcount[1] = "0";//user.get(IntentSession.mech_count);
        itemcount[2] = "0";//user.get(IntentSession.elect_count);
        itemcount[3] = "0";//user.get(IntentSession.civil_count);
        itemcount[4] = "0";
        View v= inflater.inflate(R.layout.fragment_notification, container, false);
        list = (ListView) v.findViewById(R.id.list);
        CustomListAdapter3 adapter = new CustomListAdapter3(getActivity(), itemname,itemcount);
        list.setAdapter(adapter);
        if(isOnline()) {

                BackGround2 b = new BackGround2();
                b.execute();

        }
        else
        {
            Toast.makeText(getContext(),"Please check your connection",Toast.LENGTH_SHORT).show();

        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {

                String Slecteditem = itemname[+position];
                String itemcount1 = itemcount[position];
                itemc = Integer.parseInt(itemcount1.toString());
                if(itemc==0) {
                    Toast.makeText(getContext(),"No data Available",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    iname = new String[itemc];
                    ides = new String[itemc];
                    filetype = new String[itemc];
                    if (isOnline()) {
                        BackGround3 b = new BackGround3();
                        if(Slecteditem.equals("FirstYear"))
                            b.execute("FirstYear");
                        if(Slecteditem.equals("Computer"))
                            b.execute("ComputerEngineering");
                        if(Slecteditem.equals("Mechanical"))
                            b.execute("MechanicalEngineering");
                        if(Slecteditem.equals("Electronics"))
                            b.execute("Electronics&TelecommunicationEngineering".replaceAll("&","%26"));
                        if(Slecteditem.equals("Civil"))
                            b.execute("CivilEngineering");
                    } else {
                        Toast.makeText(getContext(), "Network Unreachable", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return  v;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Notification");


    }

    class BackGround2 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
           // String branch = params[0];

            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/notdatacount.php");
                //String urlParams = "branch=" + branch ;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream oo = httpURLConnection.getOutputStream();
                //oo.write(urlParams.getBytes());
                //oo.flush();
                //oo.close();
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

            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Fetching data...");
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
                itemcount[0]=obj.getString("firstc");
                itemcount[1]=obj.getString("computerc");
                itemcount[2]=obj.getString("mechanicalc");
                itemcount[3]=obj.getString("electronicsc");
                itemcount[4]=obj.getString("civilc");

                CustomListAdapter3 adapter = new CustomListAdapter3(getActivity(), itemname,itemcount);

                list.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }
    class BackGround3 extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String branch = params[0];

            int tt;
            try {

                URL url = new URL("http://pccoerelearning.com/elearning_php/notdata.php");
                String urlParams = "branch=" + branch ;
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

            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Fetching data...");
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
int j=0;
            try {


                JSONArray ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject obj = ja.getJSONObject(i);
                    String filename = obj.getString("filename");
                    String filetype1 = obj.getString("filetype");
                    String desc = obj.getString("description");
                    //if (filetype.equals("PDF")) {
                    iname[j] = filename;
                    ides[j] = desc;
                    filetype[j] = filetype1;
                    j++;



                    //}

                }


                Intent i = new Intent(getActivity(), middle1.class);
                Bundle b = new Bundle();
                b.putInt("itemcount", itemc);
                b.putStringArray("itemname", iname);
                b.putStringArray("itemdes", ides);
                b.putStringArray("filetype", filetype);

                i.putExtra("Check","2");
                i.putExtras(b);
                startActivity(i);



            }
            catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }
}