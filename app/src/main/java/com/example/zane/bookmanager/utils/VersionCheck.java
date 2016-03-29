package com.example.zane.bookmanager.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.VersionApi;
import com.kermit.exutils.utils.ExUtils;

/**
 * Created by Zane on 16/3/29.
 */
public class VersionCheck {
    /**
     *返回当前下载app的 versioncode
     * @param context
     * @return 版本号
     */
    private static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据fir上面的项目信息来重新判断是否需要更新版本
     * @param versionApi
     * @param context
     * @param view
     */
    public static void checkVersion(VersionApi versionApi, Context context, View view){
        //分别获得当前的版本号，版本名和fir目前最新的版本名和版本号
        int currentVersionCode = getVersionCode(MyApplication.getApplicationContext2());
        String currentVersionName = ExUtils.getVersionName(MyApplication.getApplicationContext2());
        int firVersionCode = Integer.parseInt(versionApi.getVersion());
        String firVersionName = versionApi.getVersionShort();

        //先判断版本号,传入activity类型的context
        if (currentVersionCode < firVersionCode){
            showUpdateDialog(versionApi, context);
        } else if (currentVersionCode == firVersionCode){
            if (!currentVersionName.equals(firVersionName)){
                showUpdateDialog(versionApi, context);
            }
        } else {
            Snackbar.make(view, "已经是最新版本了～", Snackbar.LENGTH_SHORT).show();
        }
    }

    public static void showUpdateDialog(final VersionApi versionApi, final Context context) {
        String title = "发现新版" + versionApi.getName() + "版本名：" + versionApi.getVersionShort();

        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(versionApi.getChangelog())
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(versionApi.getUpdate_url());   //指定网址
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);           //指定Action
                        intent.setData(uri);                            //设置Uri
                        context.startActivity(intent);        //启动Activity
                    }
                })
                .show();
    }

}
