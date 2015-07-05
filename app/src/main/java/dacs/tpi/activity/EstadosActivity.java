package dacs.tpi.activity;

import android.support.v4.app.Fragment;
import dacs.tpi.fragment.EstadosFragment;


public class EstadosActivity extends SingleFragmentActivity {

    private static final String TAG = "MainActivity";
    public static final String EXTRA_PAQUETE_ID =
            "dacs.tpi";


    @Override
    protected Fragment createFragment() {
        int paqueteId = getIntent().getIntExtra(EXTRA_PAQUETE_ID,-1);

        return EstadosFragment.newInstance(paqueteId);
    }
}
