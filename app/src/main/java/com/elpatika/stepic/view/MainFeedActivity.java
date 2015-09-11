package com.elpatika.stepic.view;

import android.os.Bundle;
import android.widget.TextView;

import com.elpatika.stepic.R;
import com.elpatika.stepic.base.BaseFragmentActivity;
import com.elpatika.stepic.util.SharedPreferenceHelper;
import com.elpatika.stepic.web.AuthenticationStepicResponse;

import roboguice.inject.InjectView;

public class MainFeedActivity extends BaseFragmentActivity {

    @InjectView (R.id.tempResp)
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_feed);
        SharedPreferenceHelper sharedPreferenceHelper = mShell.getSharedPreferenceHelper();
        AuthenticationStepicResponse resp = sharedPreferenceHelper.getAuthResponseFromStore(MainFeedActivity.this);
        mTextView.setText(resp.toString());
    }
}