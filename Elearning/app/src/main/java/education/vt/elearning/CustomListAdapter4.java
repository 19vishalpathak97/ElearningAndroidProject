package education.vt.elearning;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter4 extends ArrayAdapter<String> {
    int i=0;

    private final Activity context;
    private final String[] itemname;



    public CustomListAdapter4(Activity context, String[] itemname) {
        super(context, R.layout.downloadlayout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.downloadlayout, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.listname);
        ImageView i = (ImageView)rowView.findViewById(R.id.imageView11);
        //TextView des = (TextView) rowView.findViewById(R.id.count);
        txtTitle.setText(itemname[position]);
        if(itemname[position].equals("PDF"))
            i.setImageResource(R.drawable.pdf);
        if(itemname[position].equals("VIDEO"))
            i.setImageResource(R.drawable.video);
        if(itemname[position].equals("PPT"))
            i.setImageResource(R.drawable.ppt);
        if(itemname[position].equals("DOC"))
            i.setImageResource(R.drawable.qp);
        if(itemname[position].equals("IMG"))
            i.setImageResource(R.drawable.img);


        return rowView;

    };
}