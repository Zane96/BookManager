//package com.example.zane.bookmanager.view;
//
//import android.support.v7.widget.RecyclerView;
//
//import com.example.zane.bookmanager.R;
//import com.example.zane.bookmanager.presenters.adapter.ReadPlaneAdapter;
//import com.example.zane.bookmanager.presenters.adapter.WantReadAdapter;
//import com.example.zane.easymvp.view.BaseViewImpl;
//
//import butterknife.Bind;
//
///**
// * Created by Zane on 16/3/21.
// */
//public class WantReadViewpageView extends BaseViewImpl {
//
//
//    @Bind(R.id.recyclerview_readplane_fragment)
//    RecyclerView recyclerviewReadplaneFragment;
//
//    @Override
//    public int getRootViewId() {
//        return R.layout.viewpager_readplane_layout;
//    }
//
//    public void setupRecycleView(WantReadAdapter adapter, RecyclerView.LayoutManager manager){
//        recyclerviewReadplaneFragment.setAdapter(adapter);
//        recyclerviewReadplaneFragment.setLayoutManager(manager);
//    }
//}
