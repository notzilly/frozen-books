package notzilly.frozenbooks.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Book;
import notzilly.frozenbooks.model.Freezer;
import notzilly.frozenbooks.viewholder.BookViewHolder;

public class FreezerDetailActivity extends AppCompatActivity {

    private static final String TAG = "FreezerDetailActivity";

    public static final String EXTRA_FREEZER_KEY = "freezer_key";

    private String freezerKey;
    private DatabaseReference freezerRef;
    private ValueEventListener freezerListener;

    private RecyclerView booksRecycler;
    private DatabaseReference booksRef;
    private FirebaseRecyclerAdapter<Book, BookViewHolder> bookAdapter;
    private LinearLayoutManager bookManager;

    private TextView addressView;
    private TextView bookQttView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezer_detail);

        // Get freezer key from intent
        freezerKey = getIntent().getStringExtra(EXTRA_FREEZER_KEY);
        if (freezerKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_FREEZER_KEY");
        }

        // Initialize Database
        freezerRef = FirebaseDatabase.getInstance().getReference()
                .child("freezers").child(freezerKey);
        booksRef = FirebaseDatabase.getInstance().getReference()
                .child("books");

        // Initialize Views
        addressView = findViewById(R.id.freezer_address);
        bookQttView = findViewById(R.id.freezer_book_amount);

        // Initialize Manager
        bookManager = new LinearLayoutManager(FreezerDetailActivity.this);
        bookManager.setReverseLayout(true);
        bookManager.setStackFromEnd(true);

        // Initialize Recycler
        booksRecycler = findViewById(R.id.recycler_books);
        booksRecycler.setHasFixedSize(true);
        booksRecycler.setLayoutManager(bookManager);

        DividerItemDecoration itemDecor = new DividerItemDecoration(booksRecycler.getContext(), bookManager.getOrientation());
        booksRecycler.addItemDecoration(itemDecor);

        // Set up FirebaseRecyclerAdapter with the Query
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Book>()
                .setIndexedQuery(freezerRef.child("books"), booksRef, Book.class)
                .build();

        bookAdapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {
            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new BookViewHolder(inflater.inflate(R.layout.item_book, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull Book model) {
//                final DatabaseReference freezerRef = getRef(position);

                // Set click listener for the whole freezer view
//                final String freezerKey = freezerRef.getKey();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(FreezerDetailActivity.this, "clicando no livro", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.bindToBook(model);
            }
        };
        booksRecycler.setAdapter(bookAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (bookAdapter != null) {
            bookAdapter.startListening();
        }

        // Add value event listener to the freezer
        ValueEventListener freezerListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Freezer object and use the values to update the UI
                Freezer freezer = dataSnapshot.getValue(Freezer.class);

                addressView.setText(freezer.getAddress());
                bookQttView.setText(String.valueOf(freezer.getBookQtt()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Freezer failed, log a message
                Log.w(TAG, "loadFreezer:onCancelled", databaseError.toException());

                Toast.makeText(FreezerDetailActivity.this, "Não foi possível carregar Geladeiroteca.",
                        Toast.LENGTH_SHORT).show();
            }
        };

        freezerRef.addValueEventListener(freezerListener);

        this.freezerListener = freezerListener;

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (bookAdapter != null) {
            bookAdapter.stopListening();
        }

        // Remove freezer value event listener
        if(freezerListener != null) {
            freezerRef.removeEventListener(freezerListener);
        }
    }

}
