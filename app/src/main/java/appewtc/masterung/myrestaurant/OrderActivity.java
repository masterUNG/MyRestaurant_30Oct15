package appewtc.masterung.myrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    //Explicit
    private TextView officerTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString, deskString, foodString, itemString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Bind Widget
        bindWidget();

        //Show Officer
        showOfficer();

        //Show Spinner
        showSpinner();

    }   // Main Method

    private void showSpinner() {

        String[] strDesk = new String[10];
        strDesk[0] = "A1";
        strDesk[1] = "A2";
        strDesk[2] = "A3";
        strDesk[3] = "A4";
        strDesk[4] = "A5";
        strDesk[5] = "B1";
        strDesk[6] = "B2";
        strDesk[7] = "B3";
        strDesk[8] = "B4";
        strDesk[9] = "B5";

        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                strDesk);
        deskSpinner.setAdapter(deskAdapter);



    }

    private void showOfficer() {
        officerString = getIntent().getStringExtra("Name");
        officerTextView.setText(officerString);
    }

    private void bindWidget() {
        officerTextView = (TextView) findViewById(R.id.textView);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

}   // Main Class
