package com.example.rickandmorty.utils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;


import com.example.rickandmorty.R;

import androidx.core.content.ContextCompat;


public class ArchProgressWidget extends View {
    private static final int ZERO_ALPHA_MASK = 0x00ffffff;

    private static final int CIRCLE_START_ANGLE = 0;
    private static final float FULL_CIRCLE_ANGLE = 360.0f;
    private static final double MAX_PERCENT = 100d;
    private static final double ROUND_OFFSET = 0.02d;
    private static final int DEFAULT_CHART_MAIN_COLOR = R.color.purple_200;

    private Paint progressPaint = new Paint();
    private int mainColor;
    private int secondaryColor;
    private int lineWidth;
    private int[] colors;
    private float[] positions;
    private double toPosition;
    private SweepGradient gradient;
    private RectF rect;

    public ArchProgressWidget(Context context) {
        super(context);
        initWidget(null);
    }

    public ArchProgressWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(attrs);
    }

    public ArchProgressWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidget(attrs);
    }

    public ArchProgressWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWidget(attrs);
    }

    private void initWidget(AttributeSet attrs) {
        lineWidth = (int) getResources().getDimension(R.dimen.dp_size4);
        if (attrs != null) {
            TypedArray properties = getContext().obtainStyledAttributes(attrs, R.styleable.ArchProgressWidget);
            lineWidth = (int) properties.getDimension(R.styleable.ArchProgressWidget_progressBarArcWidth, lineWidth);
            properties.recycle();
        }
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeWidth(lineWidth);
        mainColor = ContextCompat.getColor(getContext(), DEFAULT_CHART_MAIN_COLOR);
        secondaryColor = mainColor & ZERO_ALPHA_MASK;
    }

    public void loadWidget(int progressPercent) {
        double progress = (float) progressPercent / MAX_PERCENT;
        double progressWithOffset = progress + ROUND_OFFSET;
        colors = new int[]{secondaryColor, mainColor, mainColor, secondaryColor};
        positions = new float[]{0f, (float) progress, (float) progressWithOffset, (float) progressWithOffset};
        toPosition = FULL_CIRCLE_ANGLE * progress;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            int width = getWidth();
            int halfSize = width / 2;
            gradient = new SweepGradient(halfSize, halfSize, colors, positions);
            rect = getRect(width);
        }
    }

    private RectF getRect(int size) {
        int boxSize = size - lineWidth;
        return new RectF(lineWidth, lineWidth, boxSize, boxSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArc(canvas);
    }

    private void drawArc(Canvas canvas) {
        progressPaint.setShader(gradient);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rect, CIRCLE_START_ANGLE, (float) toPosition, false, progressPaint);
    }
}

