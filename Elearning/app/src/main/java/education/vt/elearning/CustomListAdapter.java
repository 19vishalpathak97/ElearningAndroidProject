package education.vt.elearning;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
    int i=0;

    private final Activity context;
    private final String[] itemname;
    private final String[] itemdes;
    private final String filetype;
    //private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, String[] itemdes, String filetype) {
        super(context, R.layout.list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemdes=itemdes;
        this.filetype=filetype;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
        TextView des = (TextView) rowView.findViewById(R.id.des);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

if(filetype.equals("pdf")) {
    imageView.setImageResource(R.drawable.pdf);
}
else if(filetype.equals("mp4"))
{
    imageView.setImageResource(R.drawable.video);
}
else if(filetype.equals("docx"))
{
    imageView.setImageResource(R.drawable.qp);
}
else if(filetype.equals("ppt")||filetype.equals("pptx"))
{
    imageView.setImageResource(R.drawable.ppt);
}
else if(filetype.equals("IMG"))
{
    imageView.setImageResource(R.drawable.img);
}


    //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

    txtTitle.setText(itemname[position]);
        des.setText(itemdes[position]);
    // Toast.makeText(getContext(),""+itemname[position],Toast.LENGTH_SHORT).show();

    //  extratxt.setText("Description "+itemname[position]);
            return rowView;

    };
}