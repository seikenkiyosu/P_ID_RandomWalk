import java.util.Random;

import org.omg.CORBA.PUBLIC_MEMBER;

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
	public Agent () {
		Random R = new Random();
		//idの設定
		while (true) {
			this.var = R.nextInt(P_ID_Uniform.LID_MAX);
			if (!P_ID_Uniform.idlist[this.var]) { P_ID_Uniform.idlist[this.var] = true; break; }
		}
		
		//false idもふ￥ひくように
		this.lid = R.nextInt(P_ID_Uniform.LID_MAX);
		
		this.timer = R.nextInt(P_ID_Uniform.s)+1;
	}

	//メソッド
	public boolean IsLeader () {
		return var==lid ? true : false;
	}
	
	public void MoveAction () {

	}
}
