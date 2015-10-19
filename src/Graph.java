import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Graph {
	//グラフを作る方法
	public static final int 
		TORUS  = 0,
		LINEAR = 1,
		RANDOM = 2;

	//メンバ
	public int n;
	public boolean List[][];			//グラフ
	
	public Graph (int n, int MethodToGenerate) {
		this.n = n;
		
		//リストのメモリ用意
		List = new boolean[n][];
		for (int i = 0; i < n; i++) { List[i] = new boolean[n]; }
		
		//グラフの作り方
		switch (MethodToGenerate) {
			case TORUS  :
				for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) { List[i][j] = false; }
				for (int i = 0; i < n; i++){
					List[(i+1+n)%n][i] = true;
					List[(i-1+n)%n][i] = true;
					List[((i+n+(int)Math.sqrt(n)))%n][i] = true;
					List[((i+n-(int)Math.sqrt(n)))%n][i] = true;
				}
				break;
			case LINEAR :
				for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) { List[i][j] = false; }
				for (int i=0; i < n; i++) {
					List[(i+1+n)%n][i] = true;
					List[(i-1+n)%n][i] = true;
				}
				break;
			case RANDOM :
				Random R = new Random();
				while (!IsConnected()) {
					for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) { List[i][j] = true; }
					for (int i = 0; i < n/2; i++) 
						for (int j = i; j < n; j++) { 
							if (R.nextInt(100) < P_ID_RandomWalk.REMOVE_LINK_PROBABILITY) {
								List[i][j] = false;
								List[j][i] = false;
							}
					}
					if (IsConnected()) System.out.println("connected");
					else System.out.println("non-connected");
				}
				break;
		}
	}
	
	//グラフは連結かどうかを判定
	private boolean IsConnected () {
		boolean[] marked = new boolean[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean found;
		
		boolean iscorrectinit = false;
		for (int initagent = 0; initagent < n; initagent++) {
			for (int i = 0; i < n; i++) if (List[initagent][i]) iscorrectinit = true;
			if (iscorrectinit) break;
		}
		if (!iscorrectinit) return false;
		
		for (int target = 0; target < n; target++) {	//各ノードにがagent[0]から到達可能かどうかを調べる
			for (int i = 0; i < n; i++) { marked[i] = false; }
			found = false;
			while (!queue.isEmpty()) queue.poll();	//キューを空にする
			queue.add(0);
			while (!queue.isEmpty()) {
				int v = queue.poll();
				if (v==target) {
					found = true;
					break;
				}
				marked[v] = true;	//vを探索済みに
				for (int i = 0; i < n; i++) {
					if (List[v][i] && !marked[i]) {		//vの隣接ノードをキューに
						queue.add(i);
					}
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}
	
	
	public void ShowGraph () {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (List[i][j]) { System.out.print(1 + " "); }
				else { System.out.print(0 + " "); }
			System.out.print ("\n");
		}
	}
}
