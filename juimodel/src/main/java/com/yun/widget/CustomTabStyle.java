package com.yun.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.ViewGroup;
import april.yun.ISlidingTabStrip;
import april.yun.tabstyle.JTabStyle;

/**
 * @author yun.
 * @date 2017/4/23
 * @des [一句话描述]
 * @since [https://github.com/ZuYun]
 * <p><a href="https://github.com/ZuYun">github</a>
 */
public class CustomTabStyle extends JTabStyle {

    public CustomTabStyle(ISlidingTabStrip slidingTabStrip){
        super(slidingTabStrip);
        moveStyle = 0;
    }

    @Override
    public void onDraw(Canvas canvas, ViewGroup tabsContainer, float currentPositionOffset, int lastCheckedPosition){
        calcuteIndicatorLinePosition(tabsContainer, currentPositionOffset, lastCheckedPosition);
        mIndicatorPaint.setColor(mTabStyleDelegate.getUnderlineColor());
        canvas.drawRect(padingVerticalOffect, mH-mTabStyleDelegate.getUnderlineHeight(),
                tabsContainer.getWidth()-padingVerticalOffect, mH-padingVerticalOffect, mIndicatorPaint);

        if(mTabStyleDelegate.getIndicatorColor() != Color.TRANSPARENT) {
            // draw indicator line
            calcuteIndicatorLinePosition(tabsContainer, currentPositionOffset, lastCheckedPosition);

            mIndicatorPaint.setColor(mTabStyleDelegate.getIndicatorColor());

            int underLineFixWidth = mTabStyleDelegate.getUnderLineFixWidth();
            float top = mH-mTabStyleDelegate.getIndicatorHeight()-padingVerticalOffect;
            float bottom = mH-padingVerticalOffect;
            float tabWidth = mLinePosition.y-mLinePosition.x;
            underLineFixWidth = (int)Math.min(underLineFixWidth, tabWidth);
            float left = mLinePosition.x+tabWidth/2-underLineFixWidth/2;
            float right = mLinePosition.x+tabWidth/2+underLineFixWidth/2;
            drawRoundRect(canvas, left, top, right, bottom, mTabStyleDelegate.getIndicatorHeight()/2f, mTabStyleDelegate.getIndicatorHeight()/2f, mIndicatorPaint);
        }
    }

}
