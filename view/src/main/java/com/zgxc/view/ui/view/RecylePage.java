package com.zgxc.view.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class RecylePage extends ViewGroup {
    private float mMinAlpha = 0.2f;
    private float mScale = 0.8f;

    private boolean mIsBeginMove = false;
    private float mIsMoveOffset = 5f;
    private float mMovePercent = 0f;
    private float mLastX = 0f;
    private float mLastY = 0f;
    private float mDownx = 0f;
    private float mDowny = 0f;
    private float mOffsetX = 0f;
    private boolean isChangeOrder = false;
    private ValueAnimator mValueAnimal;


    public RecylePage(Context context) {
        this(context, null);
    }

    public RecylePage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public RecylePage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init() {

    }






    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMeasure", "执行了------onMeasure------");

        //先测量所有子控件
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);


    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
            return width;
        }

        for (int i = 0; i < getChildCount(); i ++) {
            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            width += childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
            return height;
        }
        for (int i = 0; i < getChildCount(); i ++) {
            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            height = Math.max(childAt.getMeasuredHeight(),
                    childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
        }
        return height;
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        for (int j = 0; j < getChildCount(); j++) {
            View childAt = getChildAt(j);
            setChildLayout(childAt,j);
        }
    }

    private int getLayoutBuildLine(View view) {
        int i = indexOfChild(view);
        if (i > 2) {
            throw new IllegalArgumentException("不能传入3个以上的子控件");
        }
        int measuredWidth = getMeasuredWidth();

        int baseLeftLine;
        int baseMiddleLine;
        int baseRightLine;
        int baseLine = 0;

        baseLeftLine=  measuredWidth / 4;
        baseRightLine = measuredWidth / 4 + measuredWidth / 2;
        baseMiddleLine = measuredWidth / 2;

        LayoutParam layoutParam = (LayoutParam) view.getLayoutParams();
        Log.e("move:",  mMovePercent + "");

        switch (layoutParam.from) {
            case 0:
                baseLine = baseLeftLine;
                if (layoutParam.to == 1) {
                    baseLine = baseLeftLine + (int) ((baseRightLine - baseLeftLine) * -mMovePercent);
                }else if (layoutParam.to == 2){
                    baseLine = baseLeftLine + (int)((baseMiddleLine - baseLeftLine) * mMovePercent);
                }
                break;

            case 1:
                baseLine = baseRightLine;


                if (layoutParam.to == 0) {
                    baseLine = baseRightLine - (int)((baseRightLine - baseLeftLine) * mMovePercent);
                }else if (layoutParam.to == 2){
                    baseLine = baseRightLine - (int)((baseRightLine - baseMiddleLine) * -mMovePercent);
                }
                break;

            case 2:
                baseLine = baseMiddleLine;

                if (layoutParam.to == 1) {
                    baseLine = baseMiddleLine + (int)((baseMiddleLine - baseLeftLine) * mMovePercent);
                }else if (layoutParam.to == 0){
                    baseLine = baseMiddleLine + (int)((baseRightLine - baseMiddleLine) * mMovePercent);
                }

                break;
        }
        return baseLine;

    }

    private void setChildLayout(View childAt, int i) {
        int childMeasuredWidth = childAt.getMeasuredWidth();
        int childMeasuredHeight = childAt.getMeasuredHeight();

        LayoutParam layoutParams = (LayoutParam) childAt.getLayoutParams();

        childAt.setAlpha(layoutParams.alpha);
        childAt.setScaleX(layoutParams.scale);
        childAt.setScaleY(layoutParams.scale);


        int left = getLayoutBuildLine(childAt) - childMeasuredWidth / 2;
        int top = layoutParams.topMargin + getPaddingTop();
        int right = getLayoutBuildLine(childAt) + childMeasuredWidth / 2;
        int bottom = layoutParams.topMargin + childMeasuredHeight;

        childAt.layout(left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }




    @Override
    public void addView(View child, int index, LayoutParams params) {
        int childCount = getChildCount();
        LayoutParam lp;

        if (params instanceof LayoutParam) {
             lp = (LayoutParam) params;
        }else {
             lp = new LayoutParam(params);
        }
        if (childCount > 2) {
            throw new IllegalArgumentException("不能传入3个以上的子控件");
        }
        if (childCount < 2) {
            lp.alpha = mMinAlpha;
            lp.scale = mScale;
        }else {
            lp.alpha = 1f;
            lp.scale = 1f;
        }

        lp.from = index == -1 ? getChildCount() : index;
        super.addView(child, index, lp);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        float x = ev.getX();
        float y = ev.getY();

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mDownx = x;
            mDowny = y;
            mLastX = x;
            mLastY = y;
            abortAnimal();

        }else if (ev.getAction() == MotionEvent.ACTION_MOVE) {

            if (Math.abs(x - mLastX) > mIsMoveOffset || Math.abs(y - mLastY) > mIsMoveOffset) {
                mIsBeginMove = true;
                mLastX = x;
                mLastY = y;
            }
        }else if (ev.getAction() == MotionEvent.ACTION_UP) {
            mIsBeginMove = false;
            return handleActionUp(x, y);

        }
        return mIsBeginMove;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            float offsetX = x - mLastX;
            mOffsetX += offsetX;
            startMoveItem();
            abortAnimal();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.e("Down", "执行了");
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                Log.e("Move", "执行了");

            }

        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            mIsBeginMove = false;
            handleActionUp(x, y);


        }
        mLastX = x;
        mLastY = y;
        return true;
    }


    private boolean handleActionUp(float x, float y) {

        float offsetX = x - mDownx;
        float offsetY = y - mDowny;

        if (Math.abs(offsetX) < mIsMoveOffset && Math.abs(offsetY) < mIsMoveOffset) {
            View hitView = findHitView(x, y);
            if (hitView != null) {
                LayoutParam layoutParams = (LayoutParam) hitView.getLayoutParams();
                int i = layoutParams.from;
                if (i == 2) {
                    return false;
                }else {

                    setSection(i);
                    return true;
                }
            }

        }
        fixOffSetValue();
        return false;

    }

    private void fixOffSetValue() {
        if (mMovePercent > .5f || mMovePercent < -.5f) {
            startValueAnimal(mOffsetX, mOffsetX > 0? getWidth(): -getWidth());

        }else {
            startValueAnimal(mOffsetX, 0);

        }
    }

    private void startValueAnimal(float start, float end) {
        mValueAnimal = ValueAnimator.ofFloat(start, end);
        mValueAnimal.setDuration(200);
        mValueAnimal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
                mOffsetX = animatedValue;
                startMoveItem();

            }

        });

        mValueAnimal.start();
    }
    private void abortAnimal() {
        if (mValueAnimal != null && mValueAnimal.isRunning()) {
            mValueAnimal.cancel();
        }
    }

    private void startMoveItem() {
        mMovePercent = mOffsetX / getWidth();
        onMoveItem();
        updateChildOrder();
        updateChildScaleAndAlpha();
        requestLayout();

    }

    private void updateChildScaleAndAlpha() {
        for (int i = 0; i < getChildCount(); i ++) {
            View child = getChildAt(i);
            updateScaleAndAlpha(child);
        }


    }

    private void updateScaleAndAlpha(View child) {
        LayoutParam layoutParams = (LayoutParam) child.getLayoutParams();

        switch (layoutParams.from) {
            case 0:
                if (layoutParams.to  == 1 || layoutParams.to == 0) {
                    setAsBottom(child);
                }else if (layoutParams.to == 2){
                    layoutParams.scale = mScale + (1f - mScale) * mMovePercent;
                    layoutParams.alpha = mMinAlpha + (1f - mMinAlpha) * mMovePercent;
                }
                break;
            case 1:

                if (layoutParams.to == 0 || layoutParams.to == 1) {
                    setAsBottom(child);
                }else if (layoutParams.to == 2) {
                    layoutParams.scale = mScale + (1f - mScale) * -mMovePercent;
                    layoutParams.alpha = mMinAlpha + (1f - mMinAlpha) * -mMovePercent;
                }

                break;

            case 2:
                layoutParams.scale = 1f - (1f - mScale) * Math.abs(mMovePercent);
                layoutParams.alpha = 1f - (1f - mMinAlpha) * Math.abs(mMovePercent);

                break;

        }


    }

    private void setAsBottom(View child) {
        changeOrder(indexOfChild(child), 0);

    }

    private void updateChildOrder() {

        if (Math.abs(mMovePercent) > .5f) {
            if (!isChangeOrder) {

                changeOrder(1,2);
                isChangeOrder = true;
            }
        }else {
            if (isChangeOrder) {
                changeOrder(1,2);
                isChangeOrder = false;
            }
        }


    }

    protected boolean pointInView(View view, float[] points) {
        //对齐
        points[0] -= view.getLeft();
        points[1] -= view.getTop();

        Matrix matrix = view.getMatrix();

        if (!matrix.isIdentity()) {
            matrix.invert(matrix);
            matrix.mapPoints(points);
        }

        return points[0] >= 0 &&
                points[1] >= 0 &&
                points[0] < view.getWidth() &&
                points[1] < view.getHeight();

    }


    private View findHitView(float x, float y) {

        for (int i = 0; i < getChildCount(); i ++) {
            View child = getChildAt(i);
            if (pointInView(child,new float[]{x,y})) {
                return child;
            }
        }
        return null;
    }

    private void setSection(int index) {
        if (indexOfChild(getChildAt(getChildCount() - 1)) == index || getChildCount() == 0 || (mValueAnimal != null && mValueAnimal.isRunning())) {
            return;
        }
        float start = mOffsetX;
        float end = 0f;
        if (index == 0) {
            end = new Float(getWidth());
        }else if (index == 1){
            end = new Float(-getWidth());

        }
        startValueAnimal(start, end);

    }

    private void changeOrder(int fromIndex, int toIndex) {
        if (fromIndex == toIndex || fromIndex >= getChildCount() || toIndex >= getChildCount()) {
            return;
        }
        if (fromIndex > toIndex) {
            int temp = fromIndex;
            fromIndex = toIndex;
            toIndex = temp;
        }

        View from = getChildAt(fromIndex);
        View to = getChildAt(toIndex);

        detachViewFromParent(toIndex);
        detachViewFromParent(fromIndex);

        attachViewToParent(to, fromIndex, to.getLayoutParams());
        attachViewToParent(from, toIndex, from.getLayoutParams());
        invalidate();

    }


    private void onMoveItem() {
        int childCount = getChildCount();

        if (Math.abs(mMovePercent) >= 1f) {
            for(int i = 0; i < childCount; i ++) {
                View child = getChildAt(i);
                LayoutParam layoutParam = (LayoutParam) child.getLayoutParams();
                layoutParam.from = layoutParam.to;
            }
            mOffsetX %= new Float(getWidth());
            mMovePercent %= 1f;
            isChangeOrder = false;

        }else {


            for (int i = 0; i < childCount; i ++) {
                View child = getChildAt(i);
                LayoutParam layoutParam = (LayoutParam) child.getLayoutParams();
                    switch (layoutParam.from) {
                        case 0: //左边
                            layoutParam.to =mMovePercent > 0 ?
                                   2 : 1;

                            break;

                        case 1: //右边
                            layoutParam.to =mMovePercent > 0 ?
                                    0 : 2;
                            break;

                        case 2: //中间
                            layoutParam.to =mMovePercent > 0 ?
                                    1 : 0;

                            break;
                }

            }
        }


    }




    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParam(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParam(lp);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParam(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }



    public class LayoutParam extends MarginLayoutParams {

        float alpha = 0f;
        float scale = 0f;
        public int from;
        public int to;

        public LayoutParam(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParam(int width, int height) {
            super(width, height);
        }

        public LayoutParam(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParam(LayoutParams source) {
            super(source);
        }
    }


}
