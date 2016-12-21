package net.ysakaguchi.appmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ysakaguchi on 2016/12/22.
 */

public class AppLayoutManager extends RecyclerView.LayoutManager {

    private final int PAGE_ITEM_NUM = 8;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        final View lastTopView = getChildCount() > 0 ? getChildAt(0) : null;
        final int lastTop = lastTopView != null ? lastTopView.getTop() : getPaddingTop();
        final int lastLeft = lastTopView != null ? lastTopView.getLeft() : getPaddingLeft();
        final int firstPosition = lastTopView != null ? getPosition(lastTopView) : 0;

        detachAndScrapAttachedViews(recycler);

        int left = lastLeft;
        int right;
        int bottom;
        int top = lastTop;

        int pageLeft = lastLeft;

        final int parentRight = getWidth() - getPaddingRight();

        final int count = state.getItemCount();
        for (int i = 0; firstPosition + i < count; i++) {
            final int currentPos = firstPosition + i;
            final boolean isCurrentUpper = isUpper(currentPos);
            final boolean isOnScreenItem = left < parentRight;

            if (!isOnScreenItem) {
                if (isCurrentUpper) {
                    continue;
                } else {
                    break;
                }
            }

            final View v = recycler.getViewForPosition(currentPos);
            addView(v, i);
            measureChildWithMargins(v, 0, 0);
            right = left + getDecoratedMeasuredWidth(v);
            bottom = top + getDecoratedMeasuredHeight(v);
            layoutDecorated(v, left, top, right, bottom);

            // for next position
            final boolean isNextUpper = isUpper((currentPos + 1));
            if (isCurrentUpper && isNextUpper) {
                // upper -> upper
                left = right;
            } else if (isCurrentUpper) {
                // upper -> lower
                left = pageLeft;
                top = bottom;
            } else if (isNextUpper) {
                // lower -> upper
                left = right;
                top = lastTop;
                pageLeft = left;
            } else {
                // lower -> lower
                left = right;
            }
        }
    }

    private boolean isUpper(int position) {
        return position % PAGE_ITEM_NUM < 4;
    }
}
