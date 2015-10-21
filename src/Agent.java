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
	
	//ランダムウェイポイントのための作業用変数
	private boolean havedist;	//目的地があるかどうか
	private double				//目的地 
		R,
		THETA;
	private double movedis;		//目的地までどこまで進んだか
	
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
		Random random;
		switch (Model) {
			case RandomWalk:
				random = new Random();
				double r = P_ID_RandomWalk.DISTANCE_PER_ROUND;
				double theta = random.nextInt(360) + random.nextDouble();
				this.vx = r*Math.cos(theta);
				this.vy = r*Math.sin(theta);
				this.x += this.vx; this.y += this.vy;
				this.x %= P_ID_RandomWalk.FIELD_SIZE; this.y %= P_ID_RandomWalk.FIELD_SIZE;
				break;
			case RandomWaypoint:
				random = new Random();
				if (!havedist) {
					this.R = random.nextInt(P_ID_RandomWalk.FIELD_SIZE)+random.nextDouble();
					this.THETA = random.nextInt(360)+random.nextDouble();
					movedis = 0;
					while (this.R == -1 && 	//目的地決定
							0>this.x+this.R*Math.cos(this.THETA)||this.x+this.R*Math.cos(this.THETA)>P_ID_RandomWalk.FIELD_SIZE||
							0>this.y+this.R*Math.sin(this.THETA)||this.y+this.R*Math.sin(this.THETA)>P_ID_RandomWalk.FIELD_SIZE) {
						this.R = random.nextInt(P_ID_RandomWalk.FIELD_SIZE)+random.nextDouble();
						this.THETA = random.nextInt(360)+random.nextDouble();
					}
					havedist = true;
				}
				if(P_ID_RandomWalk.DISTANCE_PER_ROUND+movedis < R){
					double vr = P_ID_RandomWalk.DISTANCE_PER_ROUND;	//ランダムに次のラウンドで動く距離を決める
					if(movedis+vr >= R) {	//超えそうになったとき制御
						vr = R-movedis;
						havedist = false;
					}
					this.vx = vr * Math.cos(this.THETA);	//移動
					this.vy = vr * Math.sin(this.THETA);
					this.x += vx;
					this.y += vy;
					movedis += vr;	//移動距離保存
				}
				else {
					double vr = R-movedis;
					havedist = false;
					this.vx = Math.cos(this.THETA);	//移動
					this.vy = Math.sin(this.THETA);	
					this.x += vx;
					this.y += vy;
					movedis += vr;
				}
				break;
			default:
				break;
		}
	}
}
