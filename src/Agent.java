import java.util.Random;

public class Agent {
	public int
		var,		//id
		lid,		//既知のid最小値
		timer;		//タイマ
	
	public double 
		x,		//位置
		y,
		dx,		//速度
		dy;
	
	//コンストラクタ
	public Agent (int var, int timerset) {
		this.var = var;
		Random R = new Random();
		this.lid = R.nextInt(P_ID_Uniform.LID_MAX);
		this.timer = R.nextInt(timerset)+1;
	}
	//メソッド
	public void MoveAction () {

	}
}
