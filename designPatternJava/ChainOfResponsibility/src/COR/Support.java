package COR;

public abstract class Support {
	private String name; //このトラブル解決者の名前
	private Support next; //たらい回し先

	public Support(String name){ //トラブル解決者の生成
		this.name = name;
	}

	public Support setNext(Support next){ //たらい回し先を設定
		this.next = next;
		return next;
	}
	
	public final void support(Trouble trouble){
		if(resolve(trouble)){
			done(trouble);
		}else if(next != null){
			next.support(trouble);
		}else {
			fail(trouble);
		}
	}
	
	public String toString(){ //文字列表現
		return "[" + name + "]";
	}
	
	protected abstract boolean resolve(Trouble trouble); //解決用のメソッド
	protected void done(Trouble trouble){ //解決
		System.out.println(trouble +" is resolved by "+ this +".");
	}
	protected void fail(Trouble trouble){ //未解決
		System.out.println(trouble + " cannot be resolved.");
	}
	
}
