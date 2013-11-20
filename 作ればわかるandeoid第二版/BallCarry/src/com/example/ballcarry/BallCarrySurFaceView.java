package com.example.ballcarry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallCarrySurFaceView extends SurfaceView 
implements SurfaceHolder.Callback, Runnable{
	private static final int GOAL_HEIGHT = 150;
	private static final int START_HEIGHT = 150;
	private static final int OUT_WIDTH = 100;
	private static final int HOLE_RADIUS = 100;
	private static final int MOVE_HOLE = 1;
	private static final float mBallSize = 20.0f;

	private int deff = 10; //この値がゲームの難しさを決める
	private int mCircleX = 0;
	private int mCircleY = 0;

	private int mWidth;
	private int mHeight;

	private boolean mIsGoal = false;
	private boolean mIsGone = false;

	private boolean mIsAttached;
	private Thread mThread;

	private SurfaceHolder mHolder;
	private Canvas mCanvas = null;
	private Paint mPaint = null;
	private Path mGoalZone;
	private Path mStartZone;
	private Path mOutZoneL;
	private Path mOutZoneR;
	private Region mRegionGoalZone;
	private Region mRegionStartZone;
	private Region mRegionOutZoneL;
	private Region mRegionOutZoneR;

	//hole用の配列
	private float[] mHoleX = new float[3];
	private float[] mHoleY = new float[3];
	private Path[] mHoleZone = new Path[3];
	private Region[] mRegionHoleZone = new Region[3];
	private int[] mRand = new int[3];
	private Region mRegionWholeScreen;
	private long startTime;
	private long endTime;

	public BallCarrySurFaceView(Context context){
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder){
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);

		mWidth = getWidth();
		mHeight = getHeight();

		zoneDecide();
		holeDecide();

		newBall();

		mIsAttached = true;
		mThread = new Thread(this);
		mThread.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		mIsAttached = false;
		while(mThread.isAlive());
	}
	@Override
	public void run(){
		while(mIsAttached){
			drawGameBoard();
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(mRegionStartZone.contains((int)event.getX(), (int)event.getY())){
				newBall();
			}
			break;
		default:
			break;

		}
		return true;
	}

	private void zoneDecide(){
		mRegionWholeScreen = new Region(0, 0, mWidth, mHeight);
		mGoalZone = new Path();
		mGoalZone.addRect(OUT_WIDTH, 0, (mWidth - OUT_WIDTH), GOAL_HEIGHT, Path.Direction.CW);
		mRegionGoalZone = new Region();
		mRegionGoalZone.setPath(mGoalZone, mRegionWholeScreen);

		mStartZone = new Path();
		mStartZone.addRect(OUT_WIDTH, (mHeight-START_HEIGHT), (mWidth-OUT_WIDTH), mHeight, Path.Direction.CW);

		mRegionStartZone = new Region();
		mRegionStartZone.setPath(mStartZone, mRegionWholeScreen);

		mOutZoneL = new Path();
		mOutZoneL.addRect(0,  0, OUT_WIDTH, mHeight, Path.Direction.CW);
		mRegionOutZoneL = new Region();
		mRegionOutZoneL.setPath(mOutZoneL, mRegionWholeScreen);
	}

	private void holeDecide(){
		float heightPart = (mHeight - GOAL_HEIGHT -START_HEIGHT)/3;
		for(int i=0; i< mHoleY.length; i++){
			mHoleY[i] = GOAL_HEIGHT + heightPart/2 + heightPart*i;
		}

		for(int i=0; i< mHoleX.length; i++){
			//１から４の乱数を発生
			mRand[i] = (int)(Math.random()*4+1);
			mHoleX[i] = OUT_WIDTH + 100*mRand[i];

		}

		for(int i=0; i< mHoleZone.length; i++){
			mHoleZone[i] = new Path();
			mHoleZone[i].addCircle(mHoleX[i], mHoleY[i], HOLE_RADIUS, Path.Direction.CW);
			mRegionHoleZone[i] = new Region();
			mRegionHoleZone[i].setPath(mHoleZone[i], mRegionWholeScreen);

		}
	}

	private void drawGameBoard(){
		//メソッド
	}

	private String goaled(){
		endTime = System.currentTimeMillis();
		//経過時間
		long erapsedTime = endTime - startTime;
		int secTime = (int)(erapsedTime/1000);
		return ("Goal!"+ secTime + "秒");
	}

	private void newBall(){
		mCircleX = OUT_WIDTH + 50;
		mCircleY = mHeight - 50;
		mIsGoal = false;
		mIsGone = false;
		startTime = System.currentTimeMillis();
	}
}
