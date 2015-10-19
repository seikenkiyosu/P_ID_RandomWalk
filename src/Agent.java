import java.util.Random;

public class Agent {
	public int
		var,		//id
		lid,		//既知のid最小値
		timer;		//タイマ
	
	public double 
		x,		//位置
		y,
		vx,		//速度
		vy;
	
	
	public static final int 
		RandomWalk = 0,
		RandomWaypoint = 1;
	
	//コンストラクタ	
	public Agent () {
		Random R = new Random();
		//idの設定
		while (true) {
			this.var = R.nextInt(P_ID_RandomWalk.LID_MAX);
			if (!P_ID_RandomWalk.idlist[this.var]) { P_ID_RandomWalk.idlist[this.var] = true; break; }
		}
		
		//false idもひくように
		this.lid = R.nextInt(P_ID_RandomWalk.LID_MAX);
		
		this.timer = R.nextInt(P_ID_RandomWalk.s)+1;
		
		//場所初期化
		this.x = R.nextInt(P_ID_RandomWalk.FIELD_SIZE) + R.nextDouble();
		this.y = R.nextInt(P_ID_RandomWalk.FIELD_SIZE) + R.nextDouble();
	}

	//メソッド
	public boolean IsLeader () {
		return var==lid ? true : false;
	}
	
	//モデルごとの移動
	public void MoveAction (int Model) {
		switch (Model) {
		case RandomWalk:
			Random R = new Random();
			double r = P_ID_RandomWalk.DISTANCE_PER_ROUND;
			double theta = R.nextInt(360) + R.nextDouble();
			this.vx = r*Math.cos(theta);
			this.vy = r*Math.sin(theta);
			this.x += this.vx;
			this.y += this.vy;
			break;
		case RandomWaypoint:
			
			break;
		default:
			break;
		}
	}
}
