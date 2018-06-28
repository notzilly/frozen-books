package notzilly.frozenbooks.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.fragment.HomeFragment;
import notzilly.frozenbooks.fragment.ListFreezersFragment;
import notzilly.frozenbooks.fragment.ScanFragment;

public class MainActivity extends AppCompatActivity {

    // 3 fragments that will appear in bottom navigation bar
    private ArrayList<Fragment> fragments = new ArrayList<>(3);

    // Firebase authentication
    private FirebaseAuth mAuth;

    // Tag names for our 3 fragments
    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_SCANNER = "tag_frag_scanner";
    private static final String TAG_FRAGMENT_LIST_FREEZERS = "tag_frag_list_freezer";

    // Switches fragments whenever the user clicks on BottomNavigationView's buttons
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

    // Builds fragment list with different fragments
    private void buildFragList(){
        HomeFragment homeFrag = HomeFragment.newInstance();
        fragments.add(homeFrag);
        ScanFragment scanFrag = ScanFragment.newInstance();
        fragments.add(scanFrag);
        ListFreezersFragment listFreezersFrag = ListFreezersFragment.newInstance();
        fragments.add(listFreezersFrag);
    }

    // Replaces fragment currently displayed
    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragholder, fragments.get(pos), tag)
                .commit();
    }

    // Tries to login user anonymously
    private void anonymousLoginRequest() {
        // Fetches current user
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            // If user is logged, display welcome back toast
            Toast.makeText(this, "Bem-vindo de volta!", Toast.LENGTH_SHORT).show();
        } else {
            // Tries to sign in user anonymously
            mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(MainActivity.this, "Bem-vindo, novo usuário!", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Não foi possível logar",
                                Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        anonymousLoginRequest();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        buildFragList();
        switchFragment(0, TAG_FRAGMENT_HOME);
    }

}
