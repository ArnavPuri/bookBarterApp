package in.teachcoder.bookbarter.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.teachcoder.bookbarter.Constants;
import in.teachcoder.bookbarter.R;
import in.teachcoder.bookbarter.model.BookItem;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addBookBtn;
    ListView booksList;
    TextView emptyListView;
    FirebaseListAdapter<BookItem> myAdapter;
    SharedPreferences sp;
    String userName;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBookBtn = (FloatingActionButton) findViewById(R.id.add_book_button);
        booksList = (ListView) findViewById(R.id.books_list);
        emptyListView = (TextView) findViewById(R.id.emptylist_view);


        //shared preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        location = sp.getString(Constants.USER_LOCATION, "None");
        Toast.makeText(this, location, Toast.LENGTH_SHORT).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookItemRef = database.getReference(Constants.FIREBASE_LOCATION_BOOKITEM);
        DatabaseReference cityRef = database.getReference(Constants.FIREBASE_LOCATION_BOOKITEM).child(location);


        myAdapter = new FirebaseListAdapter<BookItem>(this, BookItem.class,
                R.layout.bookitem_row_layout, cityRef) {
            @Override
            protected void populateView(View v, BookItem model, int position) {
                TextView title = (TextView) v.findViewById(R.id.book_title);
                title.setText(model.getTitle());
                TextView author = (TextView) v.findViewById(R.id.book_author);
                author.setText(model.getAuthor());
                TextView owner = (TextView) v.findViewById(R.id.book_owner);
                owner.setText(model.getOwner());

            }
        };

        booksList.setAdapter(myAdapter);
        booksList.setEmptyView(emptyListView);
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddBookActivity.class);
                i.putExtra(Constants.USER_LOCATION, location);
                startActivity(i);
            }
        });


    }
}
