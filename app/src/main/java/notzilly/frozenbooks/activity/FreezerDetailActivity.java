package notzilly.frozenbooks.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Freezer;

public class FreezerDetailActivity extends AppCompatActivity {

    private static final String TAG = "FreezerDetailActivity";

    public static final String EXTRA_FREEZER_KEY = "freezer_key";

    private String freezerKey;
    private DatabaseReference freezerRef;
    private DatabaseReference booksRef;
    private RecyclerView booksRecycler;
    private ValueEventListener freezerListener;

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

        // Initialize Views
        addressView = findViewById(R.id.freezer_address);
        bookQttView = findViewById(R.id.freezer_book_amount);

        booksRecycler = findViewById(R.id.recycler_books);
        booksRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add value event listener to the post
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

        // Remove freezer value event listener
        if(freezerListener != null) {
            freezerRef.removeEventListener(freezerListener);
        }
    }
}
