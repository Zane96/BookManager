package com.example.zane.bookmanager.presenters.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.qualifier.ContextType;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.google.zxing.Result;

import javax.inject.Inject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Zane on 16/2/14.
 * 条形码扫描，然后返回isbn码回去
 */
public class ZxingScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    public static final int resultCode = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(MyApplication.getApplicationContext2());
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.ISBN, result.getText());
        setResult(resultCode, intent);
        finish();
    }
}
