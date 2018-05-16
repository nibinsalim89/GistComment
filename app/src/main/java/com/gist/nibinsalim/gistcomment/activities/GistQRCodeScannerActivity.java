package com.gist.nibinsalim.gistcomment.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gist.nibinsalim.gistcomment.R;
import com.gist.nibinsalim.gistcomment.utils.GistConstants;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
/*
 * @screen GistQRCodeScannerActivity
 * @author Nibin Salim
 * @Description Scan the QR code to get the gist id and pass that to the gist activity
 */
public class GistQRCodeScannerActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA =1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //checks for camera permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //request camera permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
            //show a toast message
            Toast.makeText(this, getString(R.string.toast_msg_camera_permission), Toast.LENGTH_SHORT).show();

        }{
            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
            setContentView(mScannerView);                // Set the scanner view as the content view
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        mScannerView.stopCamera(); // Stop camera and start a new activity
        Intent gistCommentIntent = new Intent(this, GistActivity.class);
        gistCommentIntent.putExtra(GistConstants.GIST_ID, result.getText());
        startActivity(gistCommentIntent);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
                    setContentView(mScannerView);                // Set the scanner view as the content view

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    if (ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.CAMERA)) {
                        Toast.makeText(this, getString(R.string.toast_msg_camera_permission), Toast.LENGTH_SHORT).show();

                    } else {

                    }
                }
                return;
            }
        }
    }

    private void checkCameraPermission(){


    }

}