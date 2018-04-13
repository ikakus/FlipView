package ikakus.com.flipview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by ikakus on 9/25/17.
 */

public class FlipView extends FrameLayout {
    public static final int DISTANCE = 4000;
    private ViewPager mViewPager;
    private FrameLayout mCardA;
    private FrameLayout mCardB;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context mContext;
    private int mSelectedPage;

    public FlipView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FlipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlipView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlipView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(ikakus.com.flipview.R.layout.flip_view_layout, this, true);

        mSectionsPagerAdapter = new SectionsPagerAdapter();

        mCardA = findViewById(ikakus.com.flipview.R.id.sideA);
        mCardB = findViewById(ikakus.com.flipview.R.id.sideB);
        mViewPager = findViewById(ikakus.com.flipview.R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        int distance = DISTANCE;
        float scale = getResources().getDisplayMetrics().density;
        mCardA.setCameraDistance(distance * scale);
        mCardB.setCameraDistance(distance * scale);

        mViewPager.addOnPageChangeListener(new FlipOnPageChangeListener());
    }

    public void switchToA() {
        mViewPager.setCurrentItem(0);
    }

    public void switchToB() {
        mViewPager.setCurrentItem(1);
    }

    public void toggle() {
        if (mSelectedPage == 0) {
            switchToB();
        } else if (mSelectedPage == 1) {
            switchToA();
        }
    }

    public void setViewA(View viewA) {
        mCardA.removeAllViews();
        mCardA.addView(viewA);
    }

    public void setViewB(View viewB) {
        mCardB.removeAllViews();
        mCardB.addView(viewB);
    }

    private class FlipOnPageChangeListener implements ViewPager.OnPageChangeListener {
        float rotation = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            setRotation(position, positionOffset);
            setSide();
        }

        @Override
        public void onPageSelected(int position) {
            mSelectedPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        private void setRotation(int position, float positionOffset) {
            rotation = positionOffset * 100;
            rotation = rotation * 2;

            if (rotation > 180) {
                rotation = 180;
            }
            if (position == 1) {
                rotation += 180;
            }
            mCardA.setRotationY(-rotation);
            mCardB.setRotationY(-rotation - 180);
        }

        private void setSide() {
            float r1 = mCardA.getRotationY();
            if (r1 < -90) {
                mCardA.setVisibility(View.GONE);
            } else {
                mCardA.setVisibility(View.VISIBLE);
            }

            float r2 = mCardB.getRotationY();
            if ((r2 * -1) - 180 > 90) {
                mCardB.setVisibility(View.VISIBLE);
            } else {
                mCardB.setVisibility(View.GONE);
            }
        }
    }

    public class SectionsPagerAdapter extends PagerAdapter {

        public SectionsPagerAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            View view = new View(mContext);
            collection.addView(view);
            return view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SIDE A";
                case 1:
                    return "SIDE B";
            }
            return null;
        }
    }
}
