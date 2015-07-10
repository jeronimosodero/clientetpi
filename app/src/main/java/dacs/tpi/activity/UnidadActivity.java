package dacs.tpi.activity;

import android.support.v4.app.Fragment;
import dacs.tpi.fragment.UnidadFragment;

/**
 * Created by Jerónimo Sodero on 10/07/2015.
 */
public class UnidadActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";
    public static final String EXTRA_UNIDAD_ID =
            "dacs.tpi";


    @Override
    protected Fragment createFragment() {
        int unidadId = getIntent().getIntExtra(EXTRA_UNIDAD_ID,-1);

        return UnidadFragment.newInstance(unidadId);
    }
}
