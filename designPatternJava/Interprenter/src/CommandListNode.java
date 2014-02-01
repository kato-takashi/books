import java.util.ArrayList;

//<command> ::= <repeat command> | <primitive command>
public class CommandListNode extends Node{
	private ArrayList list = new ArrayList(); 
	private Node node;
	public void parse(Context context) throws ParseException {
		while(true){
			if(context.currentToken()==null){
				throw new ParseException("Missing 'end'");
			}else if(context.currentToken().equals("end")){
				context.skipToken("end");
				break;
			}else{
				Node commandNode = new CommandNode();
				commandNode.parse(context);
				list.add(commandNode);
			}
		}
	}
	public String toString(){
		return list.toString();
	}
}
