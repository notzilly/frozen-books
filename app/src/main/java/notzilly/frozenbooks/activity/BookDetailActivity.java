package notzilly.frozenbooks.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    public static final String TAG = "BookDetailActivity";

    public static final String EXTRA_BOOK_KEY = "book_key";

    private DatabaseReference bookRef;
    private ValueEventListener bookListener;
    private String bookKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookKey = getIntent().getStringExtra(EXTRA_BOOK_KEY);
        if(bookKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_BOOK_KEY");
        }

        // Initialize Database
        bookRef = FirebaseDatabase.getInstance().getReference()
                .child("books").child(bookKey);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add value event listener to the post
        ValueEventListener bookListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Book object and use the values to update the UI
                Book book = dataSnapshot.getValue(Book.class);

                Toast.makeText(BookDetailActivity.this,
                        "Livro: " + book.getTitle() + " " + book.getIsbn(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Book failed, log a message
                Log.w(TAG, "loadBook:onCancelled", databaseError.toException());

                Toast.makeText(BookDetailActivity.this, "Não foi possível carregar o livro.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        bookRef.addValueEventListener(bookListener);

        this.bookListener = bookListener;
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Remove book value event listener
        if (bookListener != null) {
            bookRef.removeEventListener(bookListener);
        }
    }

}
