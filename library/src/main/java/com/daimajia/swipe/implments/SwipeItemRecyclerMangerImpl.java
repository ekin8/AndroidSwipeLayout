package com.daimajia.swipe.implments;

import android.view.View;

import com.daimajia.swipe.SwipeLayout;

import androidx.recyclerview.widget.RecyclerView;

/**
 * SwipeItemRecyclerMangerImpl is a helper class to help the RecyclerView to maintain open status.
 */
public class SwipeItemRecyclerMangerImpl extends SwipeItemMangerImpl {

    protected RecyclerView.Adapter mAdapter;

    public SwipeItemRecyclerMangerImpl(RecyclerView.Adapter adapter) {
        super(adapter);
        this.mAdapter = adapter;
    }

    @Override
    public void bindView(View target, int position) {
        int resId = getSwipeLayoutId(position);

        OnLayoutListener onLayoutListener = new OnLayoutListener(position);
        SwipeLayout swipeLayout = (SwipeLayout) target.findViewById(resId);
        if (swipeLayout == null)
            throw new IllegalStateException("can not find SwipeLayout in target view");

        if (swipeLayout.getTag(resId) == null) {
            SwipeMemory swipeMemory = new SwipeMemory(position);
            swipeLayout.addSwipeListener(swipeMemory);
            swipeLayout.addOnLayoutListener(onLayoutListener);
            swipeLayout.setTag(resId, new ValueBox(position, swipeMemory, onLayoutListener));
            mShownLayouts.add(swipeLayout);
        } else {
            ValueBox valueBox = (ValueBox) swipeLayout.getTag(resId);
            valueBox.swipeMemory.setPosition(position);
            valueBox.onLayoutListener.setPosition(position);
            valueBox.position = position;
        }
    }

    @Override
    public void initialize(View target, int position) {

    }

    @Override
    public void updateConvertView(View target, int position) {

    }

}
