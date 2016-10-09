package com.rock.refreshrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.rock.refreshrecyclerview.adapters.TeachAdapter;
import com.rock.refreshrecyclerview.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback,PullToRefreshBase.OnRefreshListener2{

    private static final int UPDATE_RES = 100;
    private static final long DELAY_TIME = 2 * 1000;
    private static final int ADD_RES = 150;
    private PullToRefreshRecyclerView mRefresh;
    private RecyclerView mRecyclerView;
    private TeachAdapter adapter;

    private Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mRefresh = (PullToRefreshRecyclerView) findViewById(R.id.teach_pull_recycler);
        mRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mRefresh.setOnRefreshListener(this);



        // 获取RecyclerView
        mRecyclerView = mRefresh.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new TeachAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);

    }

    private List<Model> getData() {
        List<Model> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Model model = new Model();
            model.setName("猴子请来的救兵->" + i);
            data.add(model);
        }
        return data;
    }

    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mHandler.sendEmptyMessageDelayed(UPDATE_RES,DELAY_TIME);
    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mHandler.sendEmptyMessageDelayed(ADD_RES,DELAY_TIME);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_RES:
                adapter.updateRes(getData());
                mRefresh.onRefreshComplete();
                break;
            case ADD_RES:
                adapter.addRes(getData());
                mRefresh.onRefreshComplete();
                break;
        }
        return false;
    }
}
