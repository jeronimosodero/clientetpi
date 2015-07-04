package dacs.tpi.activity;

import android.support.v4.app.Fragment;
import dacs.tpi.fragment.EstadosFragment;


public class MainActivity extends SingleFragmentActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected Fragment createFragment() {
       return EstadosFragment.newInstance();
    }
}
