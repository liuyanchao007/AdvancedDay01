package com.rock.advancedday01.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.advancedday01.R;
import com.rock.advancedday01.model.Model;

import java.util.List;

/**
 * RecyclerView 为Child的位置提供了一个计算方式
 *
 */
public class TeachAdapter extends RecyclerView.Adapter<TeachAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG = TeachAdapter.class.getSimpleName();
    private List<Model> data;

    private LayoutInflater inflater;
    // 用来计算child位置
    private RecyclerView mRecycler;
    // 持有接口引用
    private OnItemClickListener listener;
    // 对外提供接口初始化方法
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TeachAdapter(Context context, List<Model> data) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *  获取数据源的数量  （itemView的数量）
     * @return
     */
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    /**
     * 创建ViewHolder
     * @param parent  itemView的父控件
     * @param viewType  根据ViewType去加载不同的布局  没有从 0 开始的限制了
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item,parent,false);
        // 导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    /**
     * 加载数据
     * @param holder  里面装的是View
     * @param position  用来获取对应的数据源
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        // 布局参数 设置高度
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = data.get(position).getHeight();
        holder.itemView.setLayoutParams(params);
    }

    /**
     * 适配器绑定到RecyclerView的时候 会将绑定适配器的RecyclerView传递过来
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler = recyclerView;
    }

    /**
     * @param v  点击的View
     */
    @Override
    public void onClick(View v) {
        // RecyclerView可以算出这是第几个child
        int childAdapterPosition = mRecycler.getChildAdapterPosition(v);
        Log.e(TAG, "onClick: " + childAdapterPosition );
        if (listener != null) {
            listener.onItemClick(childAdapterPosition,data.get(childAdapterPosition));
        }
    }

    /**
     * 接口回调
     * ① 定义接口，定义接口中的 方法
     * ② 在数据产生的地方持有接口，并提供初始化方法，在数据产生的时候调用接口中的方法
     * ③ 在需要处理数据的地方实现接口，实现接口接口中的方法，并将接口传递到数据产生的地方
     */

    public interface OnItemClickListener{

        void onItemClick(int position,Model model);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.teach_item_name);
        }
    }


}
