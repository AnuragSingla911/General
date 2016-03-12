package example.general.android.com.generalexample.ui.layoutmanager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import example.general.android.com.generalexample.R;


/**
 * Created by Prabhoo on 9/6/15.
 */
public class PageIndicater extends RadioGroup implements ViewPager.OnPageChangeListener {

    private int count;

    public PageIndicater(Context context) {
        super(context);
    }

    public PageIndicater(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIndicatorCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Indicator count cannot less than zero.");
        } else if( count <= 1) {
            setVisibility(View.GONE);
            return;
        } else {
            setVisibility(View.VISIBLE);
        }
        this.count = count;
        removeAllViews();
        invalidateIndicator();
    }

    public int getIndicatorCount() {
        return count;
    }

    private void invalidateIndicator() {
        int count = getIndicatorCount();
        final float scale = getResources().getDisplayMetrics().density;
        for (int i = 0; i < count; i++) {
            int size = (int) (8 * scale + 0.5f);
            int margin = (int) (2 * scale + 0.5f);
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setClickable(false);
            radioButton.setBackgroundResource(R.drawable.indicator_bg);
            radioButton.setButtonDrawable(android.R.color.transparent);
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(size, size);
            lp.setMargins(margin, margin, margin, margin);
            radioButton.setLayoutParams(lp);
            addView(radioButton);
        }
    }

    public void setCurrentItem(int index) {
        if((getChildAt(index)) != null)
            ((RadioButton) getChildAt(index)).setChecked(true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int index) {
        setCurrentItem(index);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case ViewPager.SCROLL_STATE_IDLE:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;

            case ViewPager.SCROLL_STATE_SETTLING:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
    }
}

