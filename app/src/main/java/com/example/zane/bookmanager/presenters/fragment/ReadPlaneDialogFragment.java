package com.example.zane.bookmanager.presenters.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.zane.bookmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneDialogFragment extends DialogFragment {


    @Bind(R.id.textview_readplane_planedays)
    EditText editText;

    private static final String TAG = "ReadPlaneDialogFragment";

    private OnPositiveClickListener listener;

    public interface OnPositiveClickListener {
        void onClick(String days);

        void onNaviClick();
    }

    public void setOnPositiveClickListener(OnPositiveClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Window window = getDialog().getWindow();
//        window.setLayout((int)(ExUtils.getScreenWidth() * 0.8), (int)(ExUtils.getScreenHeight() * 0.35));
//        window.setGravity(Gravity.CENTER);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Log.i(TAG, String.valueOf(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogfragment_readplane_layout, null);
        ButterKnife.bind(this, view);
        //初始化控件


        builder.setView(view)
                        //添加button
                .setPositiveButton("确定"
                                          , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClick(editText.getText().toString());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNaviClick();
            }
        });

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
