package game;


import java.util.*;


public class Gamer {
	private int money; //所持金
	private List fruits = new ArrayList(); //フルール
	private Random random = new Random();//乱数発生器
	private static String[] fruitsname = {"りんご", "ぶどう", "ばなな", "みかん"};//フルーツの名前の表

	public Gamer(int money){ //コンストラクタ
		this.money = money;
	}
	public int getMony(){ //現在の所持金を得る
		return money;
	}

	public void bet(){//かける ゲームの進行
		int dice = random.nextInt(6) + 1; //サイコロを振る
		if(dice == 1){//1の目　所持金が増える
			money += 100;
			System.out.println("所持金が増えました。");
		}else if(dice == 2){ //2つの目　所持金が半分になる
			money /= 2;
			System.out.println("所持金が半分になりました。");
		}else if(dice == 6){ //6つの目　フルーツをもらう
			String f = getFruit();
			System.out.println("フルーツ（" + f + "）をもらいました");
			fruits.add(f);	
		}else{//それ以外　何も置きない
			System.out.println("何も起こりませんでした。");}
	}
	
	public Memento createMemento(){ //スナップショットを撮る
		Memento m = new Memento(money);
		Iterator it = fruits.iterator();
		while(it.hasNext()){
			String f = (String)it.next();
			if(f.startsWith("おいしい")){ //フルーツはおいしいものだけ保存
				m.addFruit(f);
			}
		}
		return m;		
	}

	public void restoreMemento(Memento memento) {   // アンドゥを行う
        this.money = memento.money;
        this.fruits = memento.getFruits();
    }
	public String toString(){
		return "[money = "+money + ", fruits = " + fruits +"]";
	}
	private String getFruit(){
		String prefix = "";
		return prefix + fruitsname[random.nextInt(fruitsname.length)];
	}
}
