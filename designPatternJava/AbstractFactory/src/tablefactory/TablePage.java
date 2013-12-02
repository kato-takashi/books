package tablefactory;

import java.util.Iterator;

import factory.Item;
import factory.Page;

public class TablePage extends Page{
	public TablePage(String title, String author){
		super(title, author);
	}
	
	public String makeHTML(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head><meta charset=\"UTF-8\"><title>"+ title +"</title></head>\n");
		buffer.append("<boady>\n");
		buffer.append("<h1>"+ title +"</h1>\n");
		buffer.append("<table width=\"80%\" border=\"3\">\n");
		Iterator it = content.iterator();
		while(it.hasNext()){
			Item item = (Item)it.next();
			buffer.append(item.makeHTML());
		}
		buffer.append("</table>\n");
		buffer.append("<hr><adress>"+ author +"</adress>");
		buffer.append("</body></html>\n");
		return buffer.toString();
	}
}
