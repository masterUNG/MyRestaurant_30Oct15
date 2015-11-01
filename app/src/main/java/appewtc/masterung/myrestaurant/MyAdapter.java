package appewtc.masterung.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 11/1/15 AD.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] iconStrings, foodStrings, priceStrings;

    public MyAdapter(Context objContext, String[] iconStrings, String[] foodStrings, String[] priceStrings) {
        this.objContext = objContext;
        this.iconStrings = iconStrings;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.food_listview, viewGroup, false);

        //Setup Title Food
        TextView foodTextView = (TextView) objView1.findViewById(R.id.txtShowFood);
        foodTextView.setText(foodStrings[i]);

        //Setup Title Price
        TextView priceTextView = (TextView) objView1.findViewById(R.id.txtShowPrice);
        priceTextView.setText(priceStrings[i]);

        //Setup Icon Image
        ImageView iconImageView = (ImageView) objView1.findViewById(R.id.imvShowIcon);
        Picasso.with(objContext).load(iconStrings[i]).resize(100, 100).into(iconImageView);


        return objView1;
    }
}   // Main Class
