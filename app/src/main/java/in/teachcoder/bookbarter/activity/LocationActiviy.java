package in.teachcoder.bookbarter.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.teachcoder.bookbarter.Constants;
import in.teachcoder.bookbarter.R;

public class LocationActiviy extends AppCompatActivity {
    ListView citiesListView;
    EditText cityName;
    Button citySubmit;
    FirebaseListAdapter<String> citiesListAdapter;
    SharedPreferences sp;
    SharedPreferences.Editor spe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        citiesListView = (ListView) findViewById(R.id.location_list);
        cityName = (EditText) findViewById(R.id.city_input);
        citySubmit = (Button) findViewById(R.id.add_city_button);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference citiesRef = database.getReference(Constants.FIREBASE_LOCATION_CITIES);

        citiesListAdapter = new FirebaseListAdapter<String>(this, String.class,
                android.R.layout.simple_list_item_1, citiesRef) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView cityName = (TextView) v.findViewById(android.R.id.text1);
                cityName.setText(model);
            }
        };

        citiesListView.setAdapter(citiesListAdapter);

        citySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citiesRef.child(cityName.getText().toString()).setValue(cityName.getText().toString());
            }
        });

        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LocationActiviy.this, MainActivity.class);
                spe = sp.edit();
                spe.putString(Constants.USER_LOCATION, citiesListAdapter.getItem(i));
                spe.apply();
                startActivity(intent);
            }
        });
    }
}
