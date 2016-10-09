package com.rock.recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rock on 2016/8/29.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private Map<Integer,View> mCacheViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mCacheViews = new HashMap<>();
    }

    public View getView(int resId){
        View view = null;
        if (mCacheViews.containsKey(resId)) {
            view = mCacheViews.get(resId);
        }else{
            view = itemView.findViewById(resId);
            mCacheViews.put(resId,view);
        }
        return view;
    }

}
