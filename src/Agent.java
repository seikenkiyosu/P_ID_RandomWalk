
public class Agent {
	//メンバ
	//既知の情報
	private int N;
	private int Delta;
	
	private int var;		//id
	private int lid;		//既知のid最小値
	private int timer;		//タイマ
	private Agent neigh[];	//隣接ノード
	
	//コンストラクタ
	public Agent (int N, int Delta, int var, int timerset) {
		this.N = N; this.Delta = Delta;
		this.var = var;
		this.timer = timerset;
	}
	//メソッド
	public int getvar() {
		return var;
	}
}
