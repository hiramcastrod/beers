package hiram.beers;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hiram.beers.adapters.BeerAdapter;
import hiram.beers.models.Beer;
import hiram.beers.services.Api;

public class MainActivity extends AppCompatActivity {
    TextView tvName, tvDescription, tvTagline;
    RecyclerView rvBeers;
    static String route= "beers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.texview_beer_name);
        tvDescription = findViewById(R.id.textview_description);
        tvTagline = findViewById(R.id.textview_tagline);
        rvBeers = findViewById(R.id.recycler_view_beers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBeers.setLayoutManager(linearLayoutManager);

        Map<String, String> header = new HashMap<>();
    Api.GetRequest("beers", null, header, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            System.out.println(response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<Beer> beers = BeerList(jsonArray);
                BeerAdapter beerAdapter = new BeerAdapter(beers, R.layout.beers_view_holder, MainActivity.this);
                rvBeers.setAdapter(beerAdapter);
            } catch (JSONException e){
                System.out.print(e);
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }, getApplicationContext());

    }

    public ArrayList<Beer> BeerList(JSONArray jsonArray){
        ArrayList<Beer> beers = new ArrayList<>();
        for(int i = 0; i<jsonArray.length()-1; i++){
            JSONObject jsonObject;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                beers.add(new Beer(
                        jsonObject.getString("name"),
                        jsonObject.getString("description"),
                        jsonObject.getString("tagline"),
                        jsonObject.getString("image_url")
                ));
            } catch (JSONException e){
                Toast.makeText(this, "Error on JSON: " + e, Toast.LENGTH_SHORT).show();
            }
        }

        return beers;
    }
}
