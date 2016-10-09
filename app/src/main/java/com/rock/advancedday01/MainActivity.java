package com.rock.advancedday01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.rock.advancedday01.adapters.TeachAdapter;
import com.rock.advancedday01.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * RecylerView 是一个ListView更加灵活，更加高效的适配器类型控件。
 * RecyclerView在Android在Android是在Android5.0正式推出的，实际上在Android4.4的时候，源码
 * 中就包含RecyclerView了.
 * RecyclerView 同样是一个适配器类型的控件，适配器类型的控件需要进行适配器绑定，但是RecyclerView
 * 和其它的适配器类型控件还有一些微妙的区别，它还需要一个LayoutManager（布局管理器）进行
 * 页面的控制，展示。
 * RecyclerView中默认提供了三种布局管理器：
 * ① LinearLayoutManager  线性的  支持水平和竖直方向  （ListView 效果，也可以做成横向ListView效果）
 * ② GridLayoutManager    网格的  支持水平和竖直方向   （GridView效果，也可以选择水平样式）
 * ③ StaggeredGridLayoutManager 分布的（参差不齐） 瀑布流效果,在使用的时候去指定不同的高度或宽度
 *
 * RecyclerView的简单使用：
 * ① 先引人RecyclerView （V7 兼容到API7  Android2.1 ）  搜索recycler  选择 com.android.support:recyclerview-v7
 * ② 布局中声明，在Java代码中进行初始化
 * ③ 设置布局管理器
 * ④ 绑定适配器
 *
 *
 * RecyclerView的LinearLayoutManager会有一个特性，item最外层的布局参数是有效的
 * 修复方案：① 在item布局的最外层的参数上指定具体的值
 *           ② 导入itemView的时候，指定没有parent （不推荐）
 *
 * 如何创建一个RecyclerView的Adapter
 * ① 创建一个类继承自RecyclerView.Adapter，同时需要传递一个ViewHolder的泛型
 * ② 创建ViewHolder，创建一个类继承自RecyclerView.ViewHolder,类需要创建一个匹配父类的构造
 * ③ 重写Adapter中的方法
 *      3.1 getItemCount 获取数据源的个数，获取item的数量
 *      3.2 onCreateViewHolder 创建ViewHolder，导入item的布局，实例化itemView
 *      3.3 onBindViewHolder   绑定ViewHolder  加载数据
 *
 * RecyclerView为开发者提供了强大的复用机制，但是有得必有失，它将所有的点击事件都阉割了（没有提供默认点击方式）
 * 接下来我们就需要自己为RecyclerView实现点击：
 *
 *
 */
public class MainActivity extends AppCompatActivity implements TeachAdapter.OnItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
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
        // 设置一个布局管理器
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 参数 ①  Context  ② spanCount 横向的时候是 行的个数  纵向的时候是  列的个数
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        // 设置布局排版方向
//        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        // 绑定适配器
        adapter = new TeachAdapter(this,getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    /**
     * 获取数据源
     * @return
     */
    private List<Model> getData() {
        List<Model> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Model model = new Model();
            model.setName("猴子请来的救兵->" + i);
            model.setHeight((int) (Math.random() * 200 + 200));
            data.add(model);
        }
        return data;
    }

    @Override
    public void onItemClick(int position, Model model) {
        Log.e(TAG, "onItemClick: " + position );
    }
}
