package notzilly.frozenbooks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    // 3 fragments that will appear in bottom navigation bar
    private ArrayList<Fragment> fragments = new ArrayList<>(3);

    // TODO: tag names
    private static final String TAG_FRAGMENT_HOME1 = "tag_frag_home1";
    private static final String TAG_FRAGMENT_HOME2 = "tag_frag_home2";
    private static final String TAG_FRAGMENT_HOME3 = "tag_frag_home3";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0, TAG_FRAGMENT_HOME1);
                    return true;
                case R.id.navigation_scan:
                    switchFragment(1, TAG_FRAGMENT_HOME2);
                    return true;
                case R.id.navigation_notifications:
                    switchFragment(2, TAG_FRAGMENT_HOME3);
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
                case R.id.navigation_notifications:
                    return;
            }
            return;
        }
    };

    private void buildFragList(){
        HomeFragment homeFrag = HomeFragment.newInstance();
        fragments.add(homeFrag);
        HomeFragment homeFrag1 = HomeFragment.newInstance();
        fragments.add(homeFrag1);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        buildFragList();
        switchFragment(0, TAG_FRAGMENT_HOME1);
    }

}
