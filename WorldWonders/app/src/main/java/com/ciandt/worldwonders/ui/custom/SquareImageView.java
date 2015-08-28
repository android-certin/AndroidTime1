package com.ciandt.worldwonders.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jfranco on 8/28/15.
 */
public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Para formar um quadrado.
        setMeasuredDimension(getMeasuredWidth() == 0 ? getMeasuredHeight() : getMeasuredWidth(), getMeasuredHeight() < getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight());
    }
}
