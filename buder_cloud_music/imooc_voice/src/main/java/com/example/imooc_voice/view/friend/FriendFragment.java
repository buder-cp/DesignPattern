package com.example.imooc_voice.view.friend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imooc_voice.R;
import com.example.imooc_voice.api.MockData;
import com.example.imooc_voice.api.RequestCenter;
import com.example.imooc_voice.model.friend.BaseFriendModel;
import com.example.imooc_voice.model.friend.FriendBodyValue;
import com.example.imooc_voice.view.friend.adapter.FriendRecyclerAdapter;
import com.example.lib_common_ui.recyclerview.wrapper.LoadMoreWrapper;
import com.example.lib_network.okhttp.listener.DisposeDataListener;
import com.example.lib_network.okhttp.utils.ResponseEntityToModule;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页发现fragment
 */
/**
 * 首页发现fragment
 */
public class FriendFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, LoadMoreWrapper.OnLoadMoreListener {

    private Context mContext;
    /*
     * UI
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FriendRecyclerAdapter mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    /*
     * data
     */
    private BaseFriendModel mRecommandData;
    private List<FriendBodyValue> mDatas = new ArrayList<>();

    public static Fragment newInstance() {
        FriendFragment fragment = new FriendFragment();
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discory_layout, null);
        mSwipeRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //发请求更新UI
        requestData();
    }

    //下拉刷新接口
    @Override public void onRefresh() {
        requestData();
    }

    //加载更多接口
    @Override public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        RequestCenter.requestFriendData(new DisposeDataListener() {
            @Override public void onSuccess(Object responseObj) {
                BaseFriendModel moreData = (BaseFriendModel) responseObj;
                //追加数据到adapter中
                mDatas.addAll(moreData.data.list);
                mLoadMoreWrapper.notifyDataSetChanged();
            }

            @Override public void onFailure(Object reasonObj) {
                //显示请求失败View,显示mock数据
                onSuccess(
                        ResponseEntityToModule.parseJsonToModule(MockData.FRIEND_DATA, BaseFriendModel.class));
            }
        });
    }

    //请求数据
    private void requestData() {
        RequestCenter.requestFriendData(new DisposeDataListener() {
            @Override public void onSuccess(Object responseObj) {
                mRecommandData = (BaseFriendModel) responseObj;
                //更新UI
                updateView();
            }

            @Override public void onFailure(Object reasonObj) {
                //显示请求失败View,显示mock数据
                onSuccess(ResponseEntityToModule.parseJsonToModule(MockData.FRIEND_DATA,
                        BaseFriendModel.class));
            }
        });
    }

    //更新UI
    private void updateView() {
        mSwipeRefreshLayout.setRefreshing(false); //停止刷新
        mDatas = mRecommandData.data.list;
        mAdapter = new FriendRecyclerAdapter(mContext, mDatas);
        //加载更多初始化
        mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }
}

