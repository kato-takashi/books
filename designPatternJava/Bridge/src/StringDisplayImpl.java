
public class StringDisplayImpl extends DisplayImpl{
	private String string; //表示するべき文字列
	private int width; //バイト単位で計算した文字列の「幅」　
	public StringDisplayImpl(String string){ //コンストラクタで渡された文字列Stringを、フィールドに記憶しておく。
		this.string = string; //さらにバイト単位の幅もフィールドに記憶しておいてあとで使用する
		this.width = string.getBytes().length;
	}
	public void rawOpen(){
		printLine();
	}

	public void rawPrint(){
		System.out.println("|"+ string +"|");
	}

	public void rawClose(){
		printLine();
	}
	
	private void printLine(){
		System.out.print("+");
		for(int i=0; i< width;i++ ){
			System.out.print("-");
		}
		System.out.print("+\n");
	}


}
