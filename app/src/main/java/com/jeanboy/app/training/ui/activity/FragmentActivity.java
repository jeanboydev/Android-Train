package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.fragment.BlankFragment;

public class FragmentActivity extends BaseActivity implements BlankFragment.OnFragmentCallback {

    @Override
    protected String getTAG() {
        return FragmentActivity.class.getSimpleName();
    }

    private BlankFragment blankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        blankFragment = BlankFragment.newInstance("参数1", "参数2");
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, blankFragment).commit();
    }

    @Override
    public void onCallback(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}
