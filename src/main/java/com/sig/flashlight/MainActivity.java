package com.sig.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    private ImageButton mLightOnBtn = null;
    private ImageButton mLightOffBtn = null;
    private Camera mCamera = null;
    private Camera.Parameters mParams = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLightOnBtn = (ImageButton) this.findViewById(R.id.btn_light_on);
        mLightOffBtn = (ImageButton) this.findViewById(R.id.btn_light_off);
        View.OnClickListener mListener = new ClickListener();
        mLightOnBtn.setOnClickListener(mListener);
        mLightOffBtn.setOnClickListener(mListener);

        mCamera = Camera.open();
        mParams = mCamera.getParameters();
    }

    @Override
    protected void onDestroy() {
        if (mCamera != null)
            mCamera.release();
        super.onDestroy();
    }

    private void openFlashLight() {
        mParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCamera.setParameters(mParams);
    }

    private void closeFlashLight() {
        mParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCamera.setParameters(mParams);
    }

    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_light_on:
                    openFlashLight();
                    mLightOnBtn.setVisibility(View.GONE);
                    mLightOffBtn.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_light_off:
                    closeFlashLight();
                    mLightOffBtn.setVisibility(View.GONE);
                    mLightOnBtn.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }
}
