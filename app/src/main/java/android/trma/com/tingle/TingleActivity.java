package android.trma.com.tingle;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// @Todo: Switch to RecyclerView and not ListView anymore
// @Todo: Implement a Search function
// @Todo: Draw a Launcher Icon
// @Todo: Documentation

/**
 * The main Tingle Activity - it's using to show the Tingle Fragment
 *  - And List Fragment if it's in landscape mode
 * @see TingleFragment
 * @see ListFragment
 * @author Troels Madsen (trma)
 */
public class TingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        FragmentManager fm = getSupportFragmentManager();

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment left = fm.findFragmentById(R.id.tingle_activity);
            if (left == null) {
                left = new TingleFragment();
                fm.beginTransaction()
                        .add(R.id.tingle_activity, left)
                        .commit();
            }

            Fragment right = fm.findFragmentById(R.id.list_activity);
            if (right == null) {
                right = new ListFragment();
                fm.beginTransaction()
                        .add(R.id.list_activity, right)
                        .commit();
            }
        }

        else {
            Fragment fragment = fm.findFragmentById(R.id.tingle_activity);
            if (fragment == null) {
                fragment = new TingleFragment();
                fm.beginTransaction()
                        .add(R.id.tingle_activity, fragment)
                        .commit();
            }
        }
    }

}


