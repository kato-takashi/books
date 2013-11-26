import factory.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.exit(0);
		}
		Factory factory = Factory.getFactory(args[0]);
		Link asahi = factory.createLink("朝日新聞", "http://www.asahi.com/");
		Link yomiuri = factory.createLink("読売新聞", "http://www.yomiuri.co.jp/");
		Link us_yahoo = factory.createLink("Yahoo!USA", "http://www.yahoo.com/");
		Link jp_yahoo = factory.createLink("Yahoo!JAPAN", "http://www.yahoo.co.jp/");
		Link google = factory.createLink("Google", "https://www.google.co.jp/");
		Link exite = factory.createLink("Excite", "http://www.excite.co.jp/");
		
		Tray traynews = factory.createTray("新聞");
		traynews.add(asahi);
		traynews.add(yomiuri);
		
		Tray trayyahoo = factory.createTray("yahoo");
		trayyahoo.add(us_yahoo);
		trayyahoo.add(jp_yahoo);
		
		Tray traysearch = factory.createTray("サーチエンジン");
		traysearch.add(trayyahoo);
		traysearch.add(exite);
		traysearch.add(google);
		
		Page page = factory.createPage("LinkPage", "結城　浩");
		page.add(traynews);
		page.add(traysearch);
		page.output();
		
	}

}
