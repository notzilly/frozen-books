package notzilly.frozenbooks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import notzilly.frozenbooks.R;

public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_KEY = "book_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
    }
}
