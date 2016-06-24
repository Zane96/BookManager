package com.example.zane.bookmanager.presenters.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book_Read;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/23.
 */
public class ChangePlaneDialogFragment extends DialogFragment {

    @Bind(R.id.textview_planedays_changplane)
    TextView textviewPlanedaysChangplane;
    @Bind(R.id.edittext_newplane_changplane)
    EditText edittextNewplaneChangplane;

    public static final String BOOK_READ = "bookRead";
    private static final String TAG = "ChangePlaneDialogFragment";

    private OnPositiveClickListener listener;
    private Book_Read book_read;

    public interface OnPositiveClickListener {
        void onClick(String days);

        void onNaviClick();
    }

    public void setOnPositiveClickListener(OnPositiveClickListener listener) {
        this.listener = listener;
    }

    public static ChangePlaneDialogFragment newInstance(Book_Read book_read){
        ChangePlaneDialogFragment fragment = new ChangePlaneDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_READ, book_read);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        book_read = (Book_Read)getArguments().getSerializable(BOOK_READ);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogfragment_changplane_layout, null);
        ButterKnife.bind(this, view);

        textviewPlanedaysChangplane.setText(String.valueOf(book_read.getPlaneDays()) + "天");

        builder.setView(view)
                        //添加button
                .setPositiveButton("修改"
                                          , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClick(edittextNewplaneChangplane.getText().toString());
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
