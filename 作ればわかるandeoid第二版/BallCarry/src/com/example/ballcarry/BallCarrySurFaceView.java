package com.example.ballcarry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallCarrySurFaceView extends SurfaceView
								implements SurfaceHolder.Callback,Runnable {
	private static final int GOAL_HEIGHT = 150;
	private static final int START_HEIGHT = 150;
	private static final int OUT_WIDTH = 100;
	private static final int HOLE_RADIUS = 100;
	private static final int MOVE_HOLE = 1;
    private static final float mBallSize = 20.0f;

	private int deff =10;	// この値がゲームの難しさを決める

	private int mCircleX =0;
    private int mCircleY =0;

    private int mWidth;
    private int mHeight;

    private boolean mIsGoal =false;
    private boolean mIsGone =false;

    private boolean mIsAttached;
    private Thread mThread;

    private SurfaceHolder mHolder;
    private Canvas mCanvas =null;
    private Paint mPaint = null;
    private Path mGoalZone;
    private Path mStartZone;
    private Path mOutZoneL;
    private Path mOutZoneR;
	private Region mRegionGoalZone;
	private Region mRegionStartZone;
	private Region mRegionOutZoneL;
	private Region mRegionOutZoneR;

	// hole用の配列
	private float[] mHoleX = new float[3];
	private float[] mHoleY = new float[3];
    private Path[] mHoleZone = new Path[3];
    private Region[] mRegionHoleZone = new Region[3];
    private int[] mRand = new int[3];

	private Region mRegionWholeScreen;

	private long startTime;
	private long endTime;


	public BallCarrySurFaceView(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		// サーフェイスフォルダの取得
        mHolder = getHolder();
        // サーフェイスイベントの通知先の指定（自身のクラス）
        mHolder.addCallback(this);
		Log.v("getWidth() ",String.valueOf(getWidth()) );
		Log.v("getHeight() ",String.valueOf(getHeight()) );
		// ここでは、getWidth(), getHeight()は取れない
		// 0になるので、setFixedSize()は意味がない。
        //mHolder.setFixedSize(getWidth(), getHeight());
	}
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
	    while(mIsAttached) {
	    	drawGameBoard();
	    }
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            if(mRegionStartZone.contains((int)event.getX(), (int)event.getY())) {
            	// 新しいボールを出す
                newBall();
                //drawGameBoard();
            }

            break;
        default:
            break;
        }
        return true;
	}
	private void holeDecide() {
		float heightPart= (mHeight - GOAL_HEIGHT - START_HEIGHT) / 3;

		for (int i = 0; i < mHoleY.length; i++) {
			mHoleY[i] = GOAL_HEIGHT + heightPart / 2 + heightPart * i;
			Log.v("mHoleY_" + i,String.valueOf(mHoleY[i]) );
 		}
		for (int i = 0; i < mHoleX.length; i++) {
			// 1から4の乱数を発生させる
			mRand[i]= (int)(Math.random()*4 + 1);
			mHoleX[i] = OUT_WIDTH  + 100 * mRand[i];
			Log.v("mHoleX_" + i,String.valueOf(mHoleX[i]) );
 		}
		for (int i = 0; i < mHoleZone.length; i++) {
			mHoleZone[i] = new Path();
			mHoleZone[i].addCircle(mHoleX[i],mHoleY[i],HOLE_RADIUS, Path.Direction.CW);
			mRegionHoleZone[i]=new Region();
			mRegionHoleZone[i].setPath(mHoleZone[i], mRegionWholeScreen);

 		}
	}

	private void zoneDecide() {
        mRegionWholeScreen=new Region(0,0,mWidth,mHeight);
		mGoalZone = new Path();
		mGoalZone.addRect(OUT_WIDTH,0,mWidth-OUT_WIDTH,GOAL_HEIGHT, Path.Direction.CW);
		mRegionGoalZone=new Region();
		mRegionGoalZone.setPath(mGoalZone, mRegionWholeScreen);

		mStartZone = new Path();
		mStartZone.addRect(OUT_WIDTH,mHeight-START_HEIGHT,mWidth-OUT_WIDTH,mHeight, Path.Direction.CW);
		mRegionStartZone=new Region();
		mRegionStartZone.setPath(mStartZone, mRegionWholeScreen);

		mOutZoneL = new Path();
		mOutZoneL.addRect(0,0,OUT_WIDTH,mHeight, Path.Direction.CW);
		mRegionOutZoneL=new Region();
		mRegionOutZoneL.setPath(mOutZoneL, mRegionWholeScreen);

		mOutZoneR = new Path();
		mOutZoneR.addRect(mWidth-OUT_WIDTH,0,mWidth,mHeight, Path.Direction.CW);
		mRegionOutZoneR=new Region();
		mRegionOutZoneR.setPath(mOutZoneR, mRegionWholeScreen);
	}

	public void drawGameBoard() {
        // ボールを落としたか、ゴールしたとき
        if ((mIsGone) || (mIsGoal)) {
        	return;
        }

		int oldCircleY = mCircleY;
		mCircleX -= BallCarryActivity.accel_x*2;
        mCircleY += BallCarryActivity.accel_y*2;

        if (mCircleY>mHeight) {
        	mCircleY=(int) (oldCircleY - mBallSize) ;
        }
        //  ホールを一つ動かす。
        if (((mHoleX[MOVE_HOLE]) < 200 ) || ((mHoleX[MOVE_HOLE]) > 500 )){
			deff *= -1;
		}
		mHoleX[MOVE_HOLE] += deff;
		mHoleZone[MOVE_HOLE] = new Path();
		mHoleZone[MOVE_HOLE].addCircle(mHoleX[MOVE_HOLE],mHoleY[MOVE_HOLE],HOLE_RADIUS, Path.Direction.CW);
		mRegionHoleZone[MOVE_HOLE]=new Region();
		mRegionHoleZone[MOVE_HOLE].setPath(mHoleZone[MOVE_HOLE], mRegionWholeScreen);

		try {
        	mCanvas = getHolder().lockCanvas();
            mCanvas.drawColor(Color.LTGRAY);

	        // Pathを描画する
            // mCanvas.drawColor(Color.MAGENTA);
            mPaint.setColor(Color.RED);
        	mCanvas.drawPath(mGoalZone, mPaint);
            mPaint.setColor(Color.CYAN);
        	mCanvas.drawPath(mStartZone, mPaint);
            mPaint.setColor(Color.BLACK);
            mCanvas.drawPath(mOutZoneL, mPaint);
            mCanvas.drawPath(mOutZoneR, mPaint);

            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(50);

            // Goal
            mCanvas.drawText(getResources().getString(R.string.goal),(int)mWidth/2 - 50,100 , mPaint);
            // Start
            mCanvas.drawText(getResources().getString(R.string.start),(int)mWidth/2 - 50,mHeight-50 , mPaint);


    		for (int i = 0; i < mHoleZone.length; i++) {
                mPaint.setColor(Color.BLACK);
                mCanvas.drawPath(mHoleZone[i], mPaint);
     		}
            if(mRegionOutZoneL.contains(mCircleX, mCircleY)) {
            	mIsGone=true;
            }
            if(mRegionOutZoneR.contains(mCircleX, mCircleY)) {
            	mIsGone=true;
            }
    		for (int i = 0; i < mRegionHoleZone.length; i++) {
    			if (mRegionHoleZone[i].contains(mCircleX, mCircleY)) {
                	mIsGone=true;
    			}
     		}
            if(mRegionGoalZone.contains(mCircleX, mCircleY)) {
        		mIsGoal = true;
            	// ゴールした
        		String msg = goaled();
                mPaint.setColor(Color.WHITE);
        		mCanvas.drawText(msg, OUT_WIDTH +10,GOAL_HEIGHT-100, mPaint);

            }
            if (!((mIsGone) || (mIsGoal))) {
            	mPaint.setColor(Color.YELLOW);
            	mCanvas.drawCircle(mCircleX, mCircleY, mBallSize, mPaint);
            }
            getHolder().unlockCanvasAndPost(mCanvas);
        } catch (Exception e) {
        	e.printStackTrace();
        }

	}

	private String goaled() {
		endTime = System.currentTimeMillis();
		// 経過時間
		long erapsedTime = endTime - startTime;
		int secTime = (int)(erapsedTime/1000);
		return ("Goal! " + secTime + "秒");

	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ


	    mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        mWidth= getWidth();
        mHeight= getHeight();

        // 盤面のゾーン決め
        zoneDecide();
        // 穴(hole）の位置決め
        holeDecide();

        Log.v("SurfaceView","Width"+mWidth);		// 480
        Log.v("SurfaceView","Height"+mHeight);		// 778

        newBall();

        mIsAttached =true;
	    mThread = new Thread(this);
        mThread.start();
        //drawGameBoard();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ
	    mIsAttached =false;
	    while(mThread.isAlive());

	}
	private void newBall() {
        mCircleX = OUT_WIDTH + 50;
        mCircleY = mHeight - 50;
		mIsGoal = false;
		mIsGone = false;
		startTime = System.currentTimeMillis();
	}

}
