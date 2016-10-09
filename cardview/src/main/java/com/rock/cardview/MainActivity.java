package com.rock.cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

/**
 * CardView 材料设计风格的布局   卡片布局
 * CardView 主要提供的特性  阴影 和  圆角
 * 简单使用：
 * ① 引入CardView
 * ② 声明CardView
 *
 */
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private CardView mCard;
    private SeekBar mElevation;
    private SeekBar mRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCard = (CardView) findViewById(R.id.teach_card);
        mElevation = (SeekBar) findViewById(R.id.card_elevation);
        mRadius = (SeekBar) findViewById(R.id.card_radius);
        // 设置拖动监听
        mRadius.setOnSeekBarChangeListener(this);
        mElevation.setOnSeekBarChangeListener(this);
    }

    /**
     * 当进度发生改变的时候进行调用  无论是手动触发还是代码操作
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.card_radius:
                // 更改圆角
                mCard.setRadius(progress);
                break;
            case R.id.card_elevation:
                // 更改海拔
                mCard.setCardElevation(progress);
                break;
        }
    }

    /**
     * 当触摸到SeekBar的时候就进行调用
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 当触摸完成，离开的时候进行调用
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
