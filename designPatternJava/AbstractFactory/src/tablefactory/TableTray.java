package tablefactory;

import java.util.Iterator;

import factory.Item;
import factory.Tray;

public class TableTray extends Tray{
	public TableTray(String caption){
		super(caption);
	}
	
	public String makeHTML(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<td>");
		buffer.append("<table width=\"100%\" border=\"1\"><tr>");
		buffer.append("<td bgcolor=\"#CCCCCC\" align=\"center\" colspan=\""+tray.size()+"\"><b>"+ caption +"</b></td>");
		buffer.append("</tr>\n");
		buffer.append("<tr>\n");
		Iterator it = tray.iterator();
		while(it.hasNext()){
			Item item = (Item)it.next();
			buffer.append(item.makeHTML());
		}
		
		buffer.append("</tr></table>\n");
		buffer.append("</td>\n");
		return buffer.toString();
	}
}
