package com.rock.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rock.recyclerview.adapters.TeachAdapter;
import com.rock.recyclerview.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 在差量更新的时候 自带动画系统
 *
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity implements TeachAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private TeachAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.teach_recycler);
        // 设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        // 合并单元格 spanSize 每一个item占多少个位置
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int spanSize = 1;
                switch (adapter.getItemViewType(position)) {
                    case 100:
                        spanSize = 3;
                        break;
                }
                return spanSize;
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);
        // 绑定适配器
        adapter = new TeachAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);
        // 设置动画系统
        TeachItemAnimator animator = new TeachItemAnimator();
        // 修改移除动画时长
        animator.setRemoveDuration(3 * 1000);
        // 修改添加动画的时长
        animator.setAddDuration(2 * 1000);

        mRecyclerView.setItemAnimator(animator);
    }

    private List<Model> getData() {
        List<Model> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Model model = new Model();
            model.setName("天气冷了->" + i);

            if (i % 4 == 0) {
                model.setType(100);
            }

            data.add(model);
        }
        return data;
    }

    @Override
    public void onItemClick(int position) {
//        adapter.removeItem(position);
        adapter.insertItem(position);
    }
}
