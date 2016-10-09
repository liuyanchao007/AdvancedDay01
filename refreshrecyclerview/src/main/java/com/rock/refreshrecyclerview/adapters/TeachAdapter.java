package com.rock.refreshrecyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.refreshrecyclerview.R;
import com.rock.refreshrecyclerview.model.Model;

import java.util.List;



public class TeachAdapter extends RecyclerView.Adapter<TeachAdapter.ViewHolder>{

    private List<Model> data;

    private LayoutInflater inflater;

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
    }

    public void updateRes(List<Model> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<Model> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.teach_item_name);
        }
    }


}
