package dacs.tpi.activity;

import android.support.v4.app.Fragment;

import dacs.tpi.fragment.MainFragment;

/**
 * Created by Jer�nimo Sodero on 04/07/2015.
 */
public class MainActivity extends SingleFragmentActivity {
    public static final String APP_NAME = "dacs.tpi";
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
