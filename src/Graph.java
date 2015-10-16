
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
				
				break;
			case RANDOM :
//				while (IsConnected())
				
				break;
		}
	}
	
	//グラフは連結かどうかを判定
	private boolean IsConnected () {
		return true;
	}
	
	public void MoveAction (int MoveModel) {
		
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
