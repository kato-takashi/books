import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;


public class SafeFrame extends Frame implements ActionListener, Context{
	private TextField textClock = new TextField(60); //現在時刻表示
	private TextArea textScreenArea = new TextArea(10, 60); //警備センター出力
	private Button buttonUse = new Button("金庫使用"); //金庫使用ボタン
	private Button buttonAlarm = new Button("非常ベル"); // 非常ベルボタン
	private Button buttonPhoneButton = new Button("通常通話"); //通常通話ボタン
	private Button buttonExitButton = new Button("終了"); //終了ボタン
	
	private State state = DayState.getInstance(); //終了ボタン
}
