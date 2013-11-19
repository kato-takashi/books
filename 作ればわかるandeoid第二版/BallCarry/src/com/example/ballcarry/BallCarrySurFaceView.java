package com.example.ballcarry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.SurfaceHolder;

public class BallCarrySurFaceView extends SufaceView
implements SurfaceHolder.Callback, Readable{
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
	private Path[] mholeZone = new Path[3];
	private Region[] mRegionHoleZone = new Region[3];
	private int[] mRabd = new int[3];
	private Region mRegionWholeScreen;
	private long startTime;
	private long endTime;
	
	public BallCarrySurFaceView(Context context){
		super(context);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder){
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
		
	}
}
