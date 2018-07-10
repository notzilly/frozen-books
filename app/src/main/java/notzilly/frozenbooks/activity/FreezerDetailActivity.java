package notzilly.frozenbooks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import notzilly.frozenbooks.R;

public class FreezerDetailActivity extends AppCompatActivity {

    public static final String EXTRA_FREEZER_KEY = "freezer_key";

    private String freezerKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezer_detail);

        // Get freezer key from intent
        freezerKey = getIntent().getStringExtra(EXTRA_FREEZER_KEY);
        if (freezerKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_FREEZER_KEY");
        }

        Toast.makeText(FreezerDetailActivity.this, freezerKey, Toast.LENGTH_SHORT).show();
    }
}
