package com.elpatika.stepic.concurrency;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.elpatika.stepic.core.IShell;
import com.google.inject.Inject;

import roboguice.util.RoboAsyncTask;

public abstract class StepicTask<T> extends RoboAsyncTask<T> {


    @Inject
    IShell mShell;
    private ProgressBar mProgressBar;
    protected final Handler handler = new Handler();

    protected StepicTask(Context context) {
        super(context);
    }


    protected void handle(final Exception ex) {
        handler.post(new Runnable() {
            public void run() {
                //todo: clarify exception
                onException(ex);
            }
        });
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() throws Exception {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
