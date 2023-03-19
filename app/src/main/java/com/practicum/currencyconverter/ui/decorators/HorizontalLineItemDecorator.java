package com.practicum.currencyconverter.ui.decorators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalLineItemDecorator extends RecyclerView.ItemDecoration {

    private final Paint paint = new Paint();
    private final Rect bounds = new Rect();

    @Px
    private int leftPadding = 0;

    @Px
    private int rightPadding = 0;

    public void setLeftPadding(final int leftPadding) {
        this.leftPadding = leftPadding;
    }

    public void setRightPadding(final int rightPadding) {
        this.rightPadding = rightPadding;
    }

    public void setColor(final int color) {
        paint.setColor(color);
    }

    @Override
    public void onDraw(@NonNull final Canvas c, @NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (parent.getLayoutManager() == null) {
            return;
        }

        drawLine(c, parent);
    }

    private void drawLine(final Canvas canvas, final RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;

        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft() + leftPadding;
            right = parent.getWidth() - parent.getPaddingRight() - rightPadding;
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = leftPadding;
            right = parent.getWidth() - rightPadding;
        }

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, bounds);
            final int bottom = bounds.bottom + Math.round(child.getTranslationY());

            canvas.drawLine(left, bottom, right, bottom, paint);
        }
        canvas.restore();

    }

}
