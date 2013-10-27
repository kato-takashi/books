package com.example.hurryup;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HurryUpActivity extends Activity {
	// ������ImageButton
	static final int imageButtons[] = { R.id.num_button1, R.id.num_button2,
			R.id.num_button3, R.id.num_button4, R.id.num_button5,
			R.id.num_button6, R.id.num_button7, R.id.num_button8,
			R.id.num_button9, R.id.num_button10, R.id.num_button11,
			R.id.num_button12, };
	// �����̉摜
	static final int numImages[] = { R.drawable.num1, R.drawable.num2,
			R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6,
			R.drawable.num7, R.drawable.num8, R.drawable.num9,
			R.drawable.num10, R.drawable.num11, R.drawable.num12, };
	// ���Z�q��ImageButton
	static final int opeButtons[] = { R.id.ope_button1, R.id.ope_button2,
			R.id.ope_button3, R.id.ope_button4, };

	// ���Z�q�̉摜
	static final int opeImages[] = { R.drawable.plus, R.drawable.minus,
			R.drawable.mul, R.drawable.div, };

	// �t�B�[���h
	private int mAnswer = 0;
	private int mCount = 0; // �o��ςݐ��@�S����10��
	private int mRightCount = 0; // ����
	private int mAvalue = 0;
	private int mBvalue = 0;
	private int mOpe = 0;

	AnswerNum nums[] = new AnswerNum[imageButtons.length];
	AnswerNum opes[] = new AnswerOpe[opeButtons.length];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hurry_up);
		createNums();
		createOpes();
		setStartButtnListener();
	}

	private void createNums() {
		for (int i = 0; i < imageButtons.length; ++i) {
			imageButton imgbtn = (ImageButton) findViewById(imageButtons[i]);
			nums[i] = new AnswerNum(imgbtn, i);
		}
	}

	private void createOpes() {
		for (int i = 0; i < opeButtons.length; ++i) {
			imageButton imgbtn = (ImageButton) findViewById(opeButtons[i]);
			ope[i] = new AnswerNum(imgbtn, i);
		}
	}

	private void setStartButtnListener() {
		// TODO �����������ꂽ���\�b�h
		Button btn = (Button) findViewById(R.id.start_button);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startGame();
			}
		});
	}

	private void startGame() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		mCount = 0;
		mRightCount = 0;
		TextView textResult =(TextView)findViewById(R.id.text_result);
		textResult.setText("");
		startChronometer();
		syutudai();
	}
	
	private void syutudai(){
		//TODO�����������ꂽ���\�b�h�E�X�^�u
		clearAnswer();
		//�o��@�i�������o���j���W�b�N
		int a;
		int b;
		int swap;
		int ope;
		while(true){
			a = (int)(Math.random()*12+1);
			b = (int)(Math.random()*12+1);
			ope = (int)(Math.random()*4+1);
			if((ope%2) == 0){
				if(b > a){
					swap = b;
					b = a;
					a = swap;
				}
				if(ope == 4){
					if((a%b) != 0){
						continue;
					}
				}
			}
			break;
		}switch(ope){
		case 1: //�����Z
			mAnswer = a+b;
			break;
		case 2: //�����Z
			mAnswer = a-b;
			break;
		case 3: //�����Z
			mAnswer = a*b;
			break;
		case 4: //����Z
			mAnswer = a/b;
			break;
		}
		Resouces res = getResouces();
		String ansString = res.getString(R.string.answer_is) + " " + mAnswer;
		TextView textAnswer = (TextView)findViewById(R.id.text_answer);
		textAnswer.setText(ansString);
		
		private void clearAnswer(){
			mAvalue = 0;
			mBvalue = 0;
			mOpe = 0;
		}
	}

}
