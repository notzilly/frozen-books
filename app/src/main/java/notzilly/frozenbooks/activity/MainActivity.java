package notzilly.frozenbooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.fragment.HomeFragment;
import notzilly.frozenbooks.fragment.ScanFragment;

public class MainActivity extends AppCompatActivity {

    // 3 fragments that will appear in bottom navigation bar
    private ArrayList<Fragment> fragments = new ArrayList<>(3);

    // Sign in code
    private static final int RC_SIGN_IN = 123;

    // Tag names for our 3 fragments
    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_SCANNER = "tag_frag_scanner";
    private static final String TAG_FRAGMENT_LIST_FREEZERS = "tag_frag_list_freezer";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0, TAG_FRAGMENT_HOME);
                    return true;
                case R.id.navigation_scan:
                    switchFragment(1, TAG_FRAGMENT_SCANNER);
                    return true;
                case R.id.navigation_list_freezers:
                    switchFragment(2, TAG_FRAGMENT_LIST_FREEZERS);
                    return true;
            }
            return false;
        }
    };

    // TODO: reselect item
    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {

        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return;
                case R.id.navigation_scan:
                    return;
                case R.id.navigation_list_freezers:
                    return;
            }
            return;
        }
    };

    private void buildFragList(){
        HomeFragment homeFrag = HomeFragment.newInstance();
        fragments.add(homeFrag);
        ScanFragment scanFrag = ScanFragment.newInstance();
        fragments.add(scanFrag);
        HomeFragment homeFrag2 = HomeFragment.newInstance();
        fragments.add(homeFrag2);
    }

    // tag used in FragmentManager#findFragmentByTag(String)
    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragholder, fragments.get(pos), tag)
                .commit();
    }

    // Tries to login user
    private void loginRequest() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        buildFragList();
        switchFragment(0, TAG_FRAGMENT_HOME);

        loginRequest();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {

                finish();
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
