package Mediator;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame implements ActionListener, Mediator{
	private ColleagueCheckbox checkGuest;
	private ColleagueCheckbox checkLogin;
	private ColleagueTextField textUser;
	private ColleagueTextField textPass;
	private ColleagueButton buttonOk;
	private ColleagueButton buttonCancel;
	
	//コンストラクタ
	//Colleagueたちを生成し、配置した後に表示を行う
	public LoginFrame(String title){
		super(title);
		setBackground(Color.lightGray);
		//レイアウトマネージャーをつかって4X2のグリッドを作る
		setLayout(new GridLayout(4, 2));
		//Colleagueたちの生成
		createColleagues();
		//配置
		add(checkGuest);
		add(checkLogin);
		add(new Label("Username"));
		add(textUser);
		add(new Label("Password"));
		add(textPass);
		add(buttonOk);
		add(buttonCancel);
		//有効・無効の初期指定
		colleagueChanged();
		
		//表示
		pack();
		setVisible(true); //show非推奨のため
	}
	
	//Clleagueたちを生成する
	public void createColleagues(){
		//生成
		CheckboxGroup g = new CheckboxGroup();
		checkGuest = new ColleagueCheckbox("Guest", g, true);
		checkLogin = new ColleagueCheckbox("Login", g, false);
		textUser = new ColleagueTextField("", 10);
		textPass= new ColleagueTextField("", 10);
		textPass.setEchoChar('*');
		buttonOk = new ColleagueButton("OK");
		buttonCancel = new ColleagueButton("Cancel");
		
		//Mediatorのセット
		checkGuest.setMediator(this);
		checkLogin.setMediator(this);
		textUser.setMediator(this);
		textPass.setMediator(this);
		buttonOk.setMediator(this);
		buttonCancel.setMediator(this);
		
		//Listenerのセット
		checkGuest.addItemListener(checkGuest);
		checkLogin.addItemListener(checkLogin);
		textUser.addTextListener(textUser);
		textPass.addTextListener(textPass);
		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
	}
	
	//Collegueからの通知で角Collegueの有効無効を判定する
	public void colleagueChanged(){
		if(checkGuest.getState()){
			textUser.setColleagueEnabled(false);
			textPass.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(true);
		}else{
			textUser.setColleagueEnabled(true);
			userpassChanged();
		}
	}
	//textUserまたはtextPassの変更があった各Colleagueの有効無効を判定する
	private void userpassChanged() {
		if(textUser.getText().length() > 0){
			textPass.setColleagueEnabled(true);
			if(textPass.getText().length()>0){
				buttonOk.setColleagueEnabled(true);
			}else{
				buttonOk.setColleagueEnabled(false);
			}
		}else {
			textPass.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(false);
		}
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.toString());
		System.exit(0);
	}
}