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
import com.kermit.exutils.utils.ExUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/25.
 */
public class TimeClockDialogFragment extends DialogFragment {
    public static final String BOOK_READ = "bookRead";
    private static final String TAG = "TimeClockDialogFragment";
    @Bind(R.id.textview_readedpages_timeclock)
    TextView textviewReadedpagesTimeclock;
    @Bind(R.id.textview_progress_timeclock)
    TextView textviewProgressTimeclock;
    @Bind(R.id.edittext_newpages_timeclock)
    EditText edittextNewpagesTimeclock;

    private OnPositiveClickListener listener;
    private Book_Read book_read;

    public interface OnPositiveClickListener {
        void onClick(String pages);

        void onNaviClick();
    }

    public void setOnPositiveClickListener(OnPositiveClickListener listener) {
        this.listener = listener;
    }

    public static TimeClockDialogFragment newInstance(Book_Read book_read) {
        TimeClockDialogFragment fragment = new TimeClockDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_READ, book_read);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout((int) (ExUtils.getScreenWidth() * 0.8), (int) (ExUtils.getScreenHeight() * 0.8));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        book_read = (Book_Read) getArguments().getSerializable(BOOK_READ);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogfragment_clock_layout, null);
        ButterKnife.bind(this, view);

        textviewReadedpagesTimeclock.setText(book_read.getReadPages());

        String progress = String.valueOf((int)((Float.parseFloat(book_read.getReadPages()) / Float.parseFloat(book_read.getPages())) * 100));
        textviewProgressTimeclock.setText(progress + "%");

        builder.setView(view)
                        //添加button
                .setPositiveButton("打卡"
                                          , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onClick(edittextNewpagesTimeclock.getText().toString());
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
