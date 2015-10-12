
public class P_ID {
	private static final int N = 10;			//small nの上限値
	private static final int Delta = 2;	//small deltaの上限値
	
	private static int Nodenum = 5; 
	
	private static Agent[] agent = new Agent[Nodenum];
	
	public static void main (String args[]) {
		//グラフ作成
//		for (int i = 0; i < Nodenum; i++) {
//			agent[i] = new Agent()
//		}
		agent[0] = new Agent(N, Delta, 5, 7);
		agent[1] = new Agent(N, Delta, 3, 2);
		
		System.out.println (agent[0].getvar());
		System.out.println (agent[1].getvar());
	}
}
