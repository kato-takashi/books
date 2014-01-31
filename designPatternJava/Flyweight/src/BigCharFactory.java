import java.util.HashMap;


public class BigCharFactory {
	//既に作ったBigCharのインスタンスを管理
	private HashMap pool = new HashMap();
	//Singletonパターン
	private static BigCharFactory singletonBigCharFactory = new BigCharFactory();
//	コンストラクタ
	private BigCharFactory() {
		
	}
//	唯一のインスタンスを得る
	public static BigCharFactory getInstanBigCharFactory(){
		return singletonBigCharFactory;
	}
//	BigCharのインスタンス生成（共有）
	public synchronized BigChar getBigChar(char charname){
		BigChar bc = (BigChar)pool.get(""+charname);
		if(bc == null){
			bc = new BigChar(charname); //ここでBigCharのインスタンス生成
			pool.put("" + charname, bc);
		}
		return bc;
	}
}
