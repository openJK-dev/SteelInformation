package com.hdh.steelinformation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * 设置分割线
 * Created by huangdianhua on 2016/10/11 14:07.
 */
public class AdvanceDecoration extends RecyclerView.ItemDecoration {
    private int orientation;
    private Paint mPaint;
    private int mItemSize = 1;
    private int columnNum;

    public AdvanceDecoration(Context context, int orientation, int columnNum) {
        this.orientation = orientation;
        this.columnNum = columnNum;
        mItemSize = (int) TypedValue.applyDimension(mItemSize, TypedValue.COMPLEX_UNIT_DIP, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
         /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHDeraction(c, parent);
        drawVDeraction(c, parent);
    }

    /**
     * 绘制水平方向的分割线
     *
     * @param c
     * @param parent
     */
    private void drawHDeraction(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - columnNum; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mItemSize;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 绘制垂直方向的分割线
     *
     * @param c
     * @param parent
     */
    private void drawVDeraction(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mItemSize;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (OrientationHelper.HORIZONTAL == orientation) {
            outRect.set(0, 0, mItemSize, 0);
        } else {
            outRect.set(0, 0, 0, mItemSize);
        }
    }
}
