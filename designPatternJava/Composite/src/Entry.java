public abstract class Entry {
	public abstract String getName(); //名前を取得
	public abstract int getSize(); //サイズを取得

	public Entry add(Entry entry) throws FileTreatmentException{ //エントリを追加する
		throw new FileTreatmentException();
	}
	public void printList(){ //一覧を表示する
		printList("");
	}
	protected abstract void printList(String prefix); //prefixを前につけて一覧を表示する
	public String toString(){
		return getName() + "{"+ getSize() +"}";
	}

}
