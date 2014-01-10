package visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class FileFindVisitor extends Visitor{
	private String filetype;
	private ArrayList found = new ArrayList();

	public FileFindVisitor(String filetype){ //.txtのように拡張子を.つきで指定
		this.filetype = filetype;
	}
	public Iterator getFoundfile(){
		return found.iterator(); //見つかったファイルを得る
	}
	public void visit(File file){ //ファイルを訪問した時に呼ばれる
		if(file.getName().endsWith(filetype)){
			found.add(file);
		}

	}

	public void visit(Directory directory){ //ディレクトリを訪問した時に呼ばれる
		Iterator it = directory.iterator();
		while(it.hasNext()){
			Entry entry = (Entry)it.next();
			entry.accept(this);
		}
	}
}
