import java.util.Random;


public class ProbStrategy implements Strategy{
	private Random random;
	private int prevHandValue = 0;
	private int currentHandValue = 0;
	
	private int [][] history = {
			{1, 1, 1},
			{1, 1, 1},
			{1, 1, 1}
	};
	
	public ProbStrategy(int seed){
		random = new Random(seed);
	}
	
	public Hand nextHand(){
		int bet = random.nextInt(getSum(currentHandValue));
		int handvalue = 0;
		if(bet < history[currentHandValue][0]){
			handvalue = 0;
			
		}else if (bet < history[currentHandValue][0] + history[currentHandValue][1]){
			handvalue = 1;
		}else{
			handvalue = 2;
		}
	}
}
