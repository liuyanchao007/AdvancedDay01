package com.rock.recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.recyclerview.R;
import com.rock.recyclerview.model.Model;

import java.util.List;

/**
 * Created by Rock on 2016/8/29.
 */
public class TeachAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {


    private static final String TAG = TeachAdapter.class.getSimpleName();
    private List<Model> data;

    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TeachAdapter(Context context, List<Model> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    // 获取数据源个数
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
    // 创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case 0:
                itemView = inflater.inflate(R.layout.item,parent,false);
                break;
            case 100:
                itemView = inflater.inflate(R.layout.item_two,parent,false);
                break;
        }
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    /**
     * 如果需要进行多布局的加载，需要重写getItemViewType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    public Model getItem(int position){
        return data.get(position);
    }

    // 绑定ViewHolder 绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                TextView mText = (TextView) holder.getView(R.id.teach_item_name);
                mText.setText(getItem(position).getName());
                break;
            case 100:
                // 显示图片

                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        // 计算itemView在RecyclerView中的位置
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(v);
        Log.e(TAG, "onClick: " );
        if (listener != null) {
            // 获取RecyclerView的item的动画
            RecyclerView.ItemAnimator itemAnimator = mRecyclerView.getItemAnimator();
            // 如果动画正在执行中，我们直接返回，不允许继续操作数据
            if (itemAnimator.isRunning()) {
                return;
            }

            listener.onItemClick(childAdapterPosition);
        }
    }

    /**
     * 删除一条数据
     */
    public void removeItem(int position){
        data.remove(position);
        // 暴力刷新
//        notifyDataSetChanged();
        // 差量刷新
        notifyItemRemoved(position);
    }

    public void insertItem(int position) {
        Model model = new Model();
        model.setName("我是猴子请来的救兵" + ((int) (Math.random() * 100)));
        data.add(position, model);
        // 通知适配器刷新
        notifyItemInserted(position);
    }

    public interface OnItemClickListener{

        void onItemClick(int position);

    }

}
