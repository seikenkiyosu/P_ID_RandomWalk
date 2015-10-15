
public class Interaction {
	public static void interaction(Agent x, Agent y){
		//1
		if (x.lid > x.var) {
			x.lid = x.var;
		}
		
		//2～10
		if (x.lid < y.lid) {
			y.lid = x.lid;
			x.timer = y.timer = max(x.timer-1,0);
		}
		else if (x.lid > y.lid) {
			x.lid = y.lid;
			x.timer = y.timer = max(y.timer-1,0);
		}
		else {
			x.timer = max(x.timer-1, y.timer-1, 0);
		}
		
		//11～16
		if (x.var==x.lid || y.var==y.lid) {
			x.timer = y.timer = P_ID_Uniform.s;
		}
		else if (x.timer == 0) {
			x.lid = y.lid = min(x.var, y.var);
			x.timer = y.timer = P_ID_Uniform.s;
		}
	}
	
	
	//交流に用いるメソッド
	private static int max (int a, int b) {
		return a >= b ? a : b;
	}
	private static int max (int a, int b, int c) {
		return max (a, b) > max(b, c) ? max(a, b) : max(b, c); 
	}
	
	private static int min (int a, int b) {
		return a < b ? a : b;
	}
}
