package education.vt.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class Downloaded extends Fragment {
    String[] itemname={"PDF","VIDEO","PPT","DOC"};
    ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v= inflater.inflate(R.layout.fragment_downloaded, container, false);
        CustomListAdapter4 adapter = new CustomListAdapter4(getActivity(), itemname);
        list = (ListView) v.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {

                String Slecteditem = itemname[+position];

                    Intent i =new Intent(getActivity(),listfromsd.class);
                    Bundle b = new Bundle();
                    b.putString("itemname",Slecteditem);
                    i.putExtras(b);
                    startActivity(i);

            }
        });
        return v;
    }
    //@Override
    //@Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Downloaded");
    }
}