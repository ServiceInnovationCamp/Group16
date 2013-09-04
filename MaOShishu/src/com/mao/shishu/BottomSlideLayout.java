package com.mao.shishu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BottomSlideLayout extends LinearLayout {
	Paint borderPaint;
	Paint innerPaint;

	@SuppressLint("NewApi")
	public BottomSlideLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BottomSlideLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BottomSlideLayout(Context context) {
		super(context);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		RectF drawRect = new RectF();
		drawRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

		innerPaint = new Paint();
		innerPaint.setARGB(200, 5, 5, 5);
		canvas.drawRoundRect(drawRect, 0, 0, innerPaint);

		super.dispatchDraw(canvas);
	}

}
