package com.fr.loadandrefresh.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.fr.loadandrefresh.R;
import com.fr.loadandrefresh.view.fragment.ClassifiedFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment mClassifiedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mClassifiedFragment = getSupportFragmentManager()
                    .getFragment(savedInstanceState, "ClassifiedFragment");
        } else {
            mClassifiedFragment = ClassifiedFragment.getInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, mClassifiedFragment)
                .commit();
    }

    /**
     * 当活动被回收时，存储当前的状态。
     *
     * @param outState 状态数据。
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // 存储 Fragment 的状态。
        getSupportFragmentManager().putFragment(outState, "ClassifiedFragment", mClassifiedFragment);
    }
}
