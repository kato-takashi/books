
public class BigString {
	//「大きな文字」の配列
	private BigChar[] bigChars;
	//コンストラクタ
	public BigString(String string){
		bigChars = new BigChar[string.length()];
		BigCharFactory factory = BigCharFactory.getInstanBigCharFactory();
		for(int i= 0; i<bigChars.length; i++){
			bigChars[i] = factory.getBigChar(string.charAt(i));
		}
	}
	//	表示
	public void print(){
		for(int i =0; i<bigChars.length; i++){
			bigChars[i].print();
		}
	}
}
