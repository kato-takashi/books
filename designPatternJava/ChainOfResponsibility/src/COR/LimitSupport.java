package COR;

public class LimitSupport extends Support{
	private int limit; //この番号未満なら解決
	public LimitSupport(String name, int limit){ //コンストラクタ
		super(name);
		this.limit = limit;
	}
	
	protected boolean resolve(Trouble trouble){ //解決用メソッド
		if(trouble.getNumber() < limit){
			return true;
		}else{
			return false;
		}
	}
}
