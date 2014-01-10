package visitor;

import java.util.Iterator;

public abstract class Entry implements Element {
	public abstract String getName(); //名前を彫る
	public abstract int getSize(); //サイズを得る
	public Entry add(Entry entry) throws FileTreatmentException{ //エントリを追加する
		throw new FileTreatmentException();
	}
	public Iterator iteterator() throws FileTreatmentException{ //Iteratorの生成
		throw new FileTreatmentException();
	}
	
	public String toString(){
		return getName() + "("+getSize()+")";
	}
}
