
public class Hand {
	public static final int HANDVALUE_GUU = 0; //グーを表す値
	public static final int HANDVALUE_CHO = 1; //チョキを表す値
	public static final int HANDVALUE_PAA = 2; //パーを表す値
	public static final Hand[] hand = {
		new Hand(HANDVALUE_GUU),
		new Hand(HANDVALUE_CHO),
		new Hand(HANDVALUE_PAA)
	};
	private static final String[] name = {
		"グー", "チョキ", "パー" //じゃんけんの手の文字列表現
	};

	private int handvalue;
	private Hand(int handvalue){
		this.handvalue = handvalue; //じゃんけんの手の値
	}

	public static Hand getHand(int handvalue){ //値からインスタンスを得る
		return hand[handvalue];
	}

	public boolean isStrongerThan(Hand h){ //thisがhより強い土ときrue
		return fight(h) == 1;
	}

	public boolean isWeakerThan(Hand h){ //thisがhよりよわいとき土岐true
		return fight(h) == -1;
	}

	private int fight(Hand h){
		if(this == h){
			return 0;
		}else if((this.handvalue + 1) % 3 == h.handvalue){
			return 1;
		}else{
			return -1;
		}
	}
	
	public String toString(){
		return name[handvalue];
	}
}
