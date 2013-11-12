package com.example.longdistance;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.WindowManager;

public class ScaleGestureView extends View{
	private static final float INCH_MM = 25.4f;
	private ScaleGestureDetector scGestureDetector;
	private float mYdpi = 0.0f;
	private int mSpan = 0;
	private float mFocusX = 0.0f;
	private float mFocusY = 0.0f;
	
	Paint mPaint = new Paint(); 
	public ScaleGestureView(Context context){
		super(context);
		scGestureDetector = new ScaleGestureDetector(context, new MySimpleOnScaleGestureListener());
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		mYdpi = metrics.ydpi;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		scGestureDetector.onTouchEvent(e);
		return true;
	}
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(40);
		canvas.drawText("<-"+ mSpan + "mm" + "->", mFocusX, mFocusY, mPaint);
		
	}
	
	private class MySimpleOnScaleGestureListener extends SimpleOnScaleGestureListener{
		@Override
		public boolean onScale(ScaleGestureDetector detector){
			mSpan = (int)(detector.getCurrentSpan()/ mYdpi*INCH_MM);
			mFocusX = detector.getFocusX();
			mFocusY = detector.getFocusY();
			invalidate();
			return true;
		}
		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector){			
			return true;
		}
		
		@Override
		public void onScaleEnd(ScaleGestureDetector detector){			
		}
	}
}
