package in.teachcoder.bookbarter.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.teachcoder.bookbarter.Constants;
import in.teachcoder.bookbarter.R;
import in.teachcoder.bookbarter.model.BookItem;

public class AddBookActivity extends AppCompatActivity {
    Spinner genreList;
    EditText bookTitle, bookAuthor;
    RadioGroup mode;
    Button addBook;
    DatabaseReference bookItemRef;
    String preferredMode, genre;
    int checkedRadioButtonId;
    SharedPreferences sp;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        //initializing views
        genreList = (Spinner) findViewById(R.id.book_genre);
        bookTitle = (EditText) findViewById(R.id.book_title);
        bookAuthor = (EditText) findViewById(R.id.book_author);
        mode = (RadioGroup) findViewById(R.id.radioGroup);
        addBook = (Button) findViewById(R.id.save_book_btn);
        //initializing firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        bookItemRef = database.getReference(Constants.FIREBASE_LOCATION_BOOKITEM);

        //Populating genres
        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this,
                R.array.book_genre,
                android.R.layout.simple_dropdown_item_1line);

        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genreList.setAdapter(genreAdapter);
        //Adding owner
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        userName = sp.getString(Constants.USER_NAME, "Anonymous");
        Toast.makeText(this, "Hello " + userName, Toast.LENGTH_SHORT).show();


        //Click Listener on add book button
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedRadioButtonId = mode.getCheckedRadioButtonId();
                switch (checkedRadioButtonId) {
                    case R.id.book_borrow_free:
                        preferredMode = "Free";
                        break;
                    case R.id.book_borrow_paid:
                        preferredMode = "Paid";
                        break;
                    case R.id.book_exchange:
                        preferredMode = "Exchange";
                        break;
                    default:
                        preferredMode = "Free";
                        break;
                }
                genre = (String) genreList.getSelectedItem();
                BookItem bookItem = new BookItem(bookTitle.getText().toString(), bookAuthor.getText().toString()
                        , genre, userName, preferredMode);
                bookItemRef.push().setValue(bookItem);
            }
        });

    }
}
