package com.blueprint.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blueprint.helper.DrawHelper;

import java.text.DecimalFormat;

import static april.yun.widget.JToolbar.dp2px;

/**
 * @another 江祖赟
 * @date 2018/1/31.
 * 评分 分布 柱状图
 */
public class ScoreDistribChart extends View {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int mW;
    private int mH;
    private Paint mChartPaint;
    private Paint mTextPaint;
    private Paint mTextTipPaint;
    private float mFontHeight;
    private float mChartLongest;
    private float mPartWidth;
    private float mTextChartSpacing = dp2px(3);
    private float mChartWidth = dp2px(11);
    private RectF mPartArea;
    private float[] mScores = new float[]{.8f};
    private String[] mScoresMsg = new String[]{"80%"};
    private String[] mXMsg = new String[]{"赟"};
    private String mXMsgSuffix = "%d星";
    private float mAdjustOffset;
    private float mShowProgress;
    private float mShowEvaluator;
    private int mOrientationMode;
    private float mCanvasTranslateX;
    private float mCanvasTranslateY;
    private float mXMsgLength;

    {
        mChartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mChartPaint.setStrokeWidth(mChartWidth);
        mChartPaint.setColor(Color.parseColor("#ffdc88"));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(dp2px(12));
        mTextPaint.setColor(Color.parseColor("#8b8b99"));

        mTextTipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextTipPaint.setTextSize(dp2px(11));
        mTextTipPaint.setColor(Color.parseColor("#babacc"));

        setOnClickListener(v->{
            invalidate();
        });
        //        setScores(1, .9f, .723f, .4f, 0.5f, 0);
        //        setPadding(dp2px(10), dp2px(10), dp2px(10), dp2px(10));
    }

    public ScoreDistribChart(Context context){
        super(context);
    }

    public ScoreDistribChart(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    public ScoreDistribChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        mW = w-getPaddingLeft()-getPaddingRight();
        mH = h-getPaddingTop()-getPaddingBottom();
        super.onSizeChanged(w, h, oldw, oldh);
        mXMsgLength = mTextPaint.measureText(String.format(mXMsgSuffix, 5));
        if(mOrientationMode == VERTICAL) {
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mFontHeight = -fontMetrics.top-fontMetrics.bottom;
            mPartWidth = Math.max(mFontHeight, mChartWidth);//柱状图  短边
            mChartLongest = mW-mXMsgLength-mTextTipPaint.measureText("99.9%")-mTextChartSpacing*2;
            mPartArea = new RectF(0, 0, mW, mPartWidth);
        }else {
            mPartWidth = Math.max(mXMsgLength, mTextTipPaint.measureText("99.9%"));//柱状图  短边
            mPartWidth = Math.max(mPartWidth, mChartWidth);//柱状图  短边
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mFontHeight = -fontMetrics.top-fontMetrics.bottom;
            mChartLongest = mH-mFontHeight*2-mTextChartSpacing*2;
            mPartArea = new RectF(0, 0, mPartWidth, mH);
        }
        adjustPosition();
    }

    private void adjustPosition(){
        if(mScores != null && mScores.length>1) {
            if(mOrientationMode == VERTICAL) {
                float needHeight = mScores.length*mPartWidth;
                float adjust = mH-needHeight;
                if(adjust<0) {
                    throw new RuntimeException("数据太多了 画不下啦");
                }
                mAdjustOffset = adjust/( mScores.length-1 );
                mCanvasTranslateY = mPartWidth+mAdjustOffset;
                mCanvasTranslateX = 0;
            }else {
                float needWidth = mScores.length*mPartWidth;
                float adjust = mW-needWidth;
                if(adjust<0) {
                    throw new RuntimeException("数据太多了 画不下啦");
                }
                mAdjustOffset = adjust/( mScores.length-1 );
                mCanvasTranslateX = mPartWidth+mAdjustOffset;
                mCanvasTranslateY = 0;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getPaddingLeft()-mCanvasTranslateX, getPaddingTop()-mCanvasTranslateY);
        for(int i = mScores.length-1; i>=0; i--) {
            canvas.translate(mCanvasTranslateX, mCanvasTranslateY);
            drawPart(canvas, mXMsg[i], mScoresMsg[i], mScores[i]);
        }
        canvas.restore();
        if(mShowProgress<1) {
            //            TypeEvaluator
            mShowEvaluator += 0.02;
            mShowProgress = DrawHelper.弹球效果弹几下回到终点.getInterpolation(mShowEvaluator);
            invalidate();
        }
    }

    private void drawPart(Canvas canvas, String xMsg, String yMsg, float yPercent){
        if(mOrientationMode == VERTICAL) {
            //竖着排列
            float stopX = mXMsgLength+mTextChartSpacing+mChartLongest*yPercent*mShowProgress;
            //右侧 纵轴信息
            canvas.drawText(yMsg, stopX+mTextChartSpacing, mPartWidth/2f+mFontHeight/2f, mTextTipPaint);
            //柱状图
            canvas.drawLine(mXMsgLength+mTextChartSpacing, mPartWidth/2f, stopX, mPartWidth/2f, mChartPaint);
            //左侧 横轴信息
            canvas.drawText(xMsg, 0, mPartWidth/2f+mFontHeight/2f, mTextPaint);
        }else {
            float stopY = mH-mFontHeight-mTextChartSpacing-mChartLongest*yPercent*mShowProgress;
            //顶部纵轴信息
            canvas.drawText(yMsg, +mPartWidth/2f-mTextTipPaint.measureText(yMsg)/2f, stopY-mTextChartSpacing, mTextTipPaint);
            //柱状图
            canvas.drawLine(mPartWidth/2f, mH-mFontHeight-mTextChartSpacing, mPartWidth/2f, stopY, mChartPaint);
            //底部横轴信息
            canvas.drawText(xMsg, mPartWidth/2f-mXMsgLength/2f, mH, mTextPaint);
        }
        mTextPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(mPartArea, 0, 0, mTextPaint);
    }

    /**
     * @param scores
     *         [0-1]
     */
    public ScoreDistribChart setScores(float... scores){
        mScores = scores;
        mShowProgress = mShowEvaluator = 0;
        mScoresMsg = new String[mScores.length];
        mXMsg = new String[mScores.length];
        DecimalFormat decimalFormat = new DecimalFormat("#.#%");
        for(int i = 0; i<mScores.length; i++) {
            mScoresMsg[i] = decimalFormat.format(mScores[i]);
            mXMsg[i] = String.format(mXMsgSuffix, i+1);
        }
        if(mW>0) {
            adjustPosition();
            invalidate();
        }
        return this;
    }

    public ScoreDistribChart setXMsgSuffix(String XMsgSuffix){
        mXMsgSuffix = XMsgSuffix;
        return this;
    }

    public ScoreDistribChart setOrientationMode(int orientationMode){
        mOrientationMode = orientationMode;
        return this;
    }

    public Paint getChartPaint(){
        return mChartPaint;
    }

    public Paint getTextPaint(){
        return mTextPaint;
    }

    public Paint getTextTipPaint(){
        return mTextTipPaint;
    }
}
