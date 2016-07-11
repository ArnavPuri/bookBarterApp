package in.teachcoder.bookbarter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.teachcoder.bookbarter.model.BookItem;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addBookBtn;
    ListView booksList;
    FirebaseListAdapter<BookItem> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBookBtn = (FloatingActionButton) findViewById(R.id.add_book_button);
        booksList = (ListView) findViewById(R.id.books_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookItemRef = database.getReference(Constants.FIREBASE_LOCATION_BOOKITEM);
        myAdapter = new FirebaseListAdapter<BookItem>(this, BookItem.class,
                R.layout.bookitem_row_layout, bookItemRef) {
            @Override
            protected void populateView(View v, BookItem model, int position) {
                TextView title = (TextView) v.findViewById(R.id.book_title);
                title.setText(model.getTitle());
                TextView author = (TextView) v.findViewById(R.id.book_author);
                author.setText(model.getAuthor());

            }
        };

        booksList.setAdapter(myAdapter);
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(i);
            }
        });


    }
}
