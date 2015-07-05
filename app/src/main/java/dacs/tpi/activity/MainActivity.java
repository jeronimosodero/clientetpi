package dacs.tpi.activity;

import android.support.v4.app.Fragment;

import dacs.tpi.fragment.MainFragment;

/**
 * Created by Jerónimo Sodero on 04/07/2015.
 */
public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
