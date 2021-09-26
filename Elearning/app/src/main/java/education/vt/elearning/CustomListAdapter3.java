package education.vt.elearning;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter3 extends ArrayAdapter<String> {
    int i=0;

    private final Activity context;
    private final String[] itemname;
    private final String[] itemcount;


    public CustomListAdapter3(Activity context, String[] itemname, String[] itemcount) {
        super(context, R.layout.branchlayout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemcount=itemcount;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.branchlayout, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.branch);
        TextView des = (TextView) rowView.findViewById(R.id.count);
        txtTitle.setText(itemname[position]);
        //des.setText("");
        des.setText(itemcount[position]);

        return rowView;

    };
}