package COR;

public class Main {
	public static void main(String[] args){
		Support alice = new NoSupport("Alice");
		Support bob = new LimitSupport("bob", 100);
		Support charlie = new SpecialSupport("charlie", 429);
		Support diana = new LimitSupport("diana", 200);
		Support elmo = new OddSupport("elmo");
		Support fred = new LimitSupport("fred", 300);
		
		//連鎖の形成
		alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);
		//様々なトラブルの発生
		for(int i = 0; i<500; i += 33){
			alice.support(new Trouble(i));
		}
	}
}
