package listfactory;

import java.util.Iterator;

import factory.Item;
import factory.Page;

public class ListPage extends Page{
	public ListPage(String title, String author){
		super(title, author);
	}
	public String makeHTML(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head><meta charset=\"UTF-8\"><title>"+ title +"</title></head>\n");
		buffer.append("<boady>\n");
		buffer.append("<h1>"+ title +"</h1>\n");
		buffer.append("<ul>\n");
		Iterator it = content.iterator();
		while(it.hasNext()){
			Item item = (Item)it.next();
			buffer.append(item.makeHTML());
		}
		buffer.append("</ul>\n");
		buffer.append("<hr><adress>"+ author +"</adress>");
		buffer.append("</body></html>\n");
		return buffer.toString();
	}
}
