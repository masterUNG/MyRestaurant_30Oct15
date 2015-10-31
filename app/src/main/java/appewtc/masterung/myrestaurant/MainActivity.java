package appewtc.masterung.myrestaurant;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;
    private String TAG = "Restaurant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create & Connected Database
        objManageTABLE = new ManageTABLE(this);

        //Tester Add Value
        //testAddValue();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    } // Main Method

    private void synJSONtoSQLite() {

        //1. Setup Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 1;
        while (intTimes <= 2) {

            //2. Create InputStream
            InputStream objInputStream = null;
            String strJSON = null;
            String strURLuser = "http://swiftcodingthai.com/30oct/php_get_data_master.php";
            String strURLfood = "http://swiftcodingthai.com/30oct/php_get_data_food.php";
            HttpPost objHttpPost;

            try {

                HttpClient objHttpClient = new DefaultHttpClient();

                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLfood);
                        break;
                    default:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                }   // switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();


            } catch (Exception e) {
                Log.d(TAG, "InputStream ==> " + e.toString());
            }

            //3. Create JSON to String
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   // while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(TAG, "strJSON ==> " + e.toString());
            }

            //4. Update to SQlite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject object = objJsonArray.getJSONObject(i);

                    switch (intTimes) {
                        case 1:

                            //Update to userTABLE
                            String strUser = object.getString("User");
                            String strPassword = object.getString("Password");
                            String strName = object.getString("Name");
                            objManageTABLE.addUser(strUser, strPassword, strName);

                            break;
                        case 2:

                            //Update to foodTABLE
                            String strFood = object.getString("Food");
                            String strSource = object.getString("Source");
                            String strPrice = object.getString("Price");
                            objManageTABLE.addFood(strFood, strSource, strPrice);

                            break;
                    } //switch

                }   //for

            } catch (Exception e) {
                Log.d(TAG, "Update ==> " + e.toString());
            }

            intTimes += 1;
        }   // While

    }   // synJSONtoSQLite

    private void testAddValue() {

        //Test Add User
        objManageTABLE.addUser("testUser", "testPass", "ทดสอบชื่อ");
        //Test Add Food
        objManageTABLE.addFood("testFood", "testSource", "testPrice");

    }

}   // Main Class
