public class P_ID_RandomWalk {
	public static final int 
		FIELD_SIZE = 100,				//フィールドサイズ
		REMOVE_LINK_PROBABILITY = 50,
		DISTANCE_FOR_INTERACTION = 1, 	//交流するための距離
		DISTANCE_PER_ROUND = 1,
		
		N = 1000,						//個体数の上限値
		Delta = 30,						//最大次数の上限値 
		
		n = 50,						//個体数
		delta = 4,						//グラフの最大次数
		s = 100,							//タイマの上限値
	
		LID_MAX = 2*n;					//最大のID(初期状況のlidに用いる)
	
	public static Boolean idlist[] = new Boolean[LID_MAX];
	
	public static void main (String args[]) {
		//初期化
		Graph graph = new Graph(n, Graph.TORUS);
		Agent[] agent = new Agent[n];
		for (int i = 0; i < LID_MAX; i++) idlist[i] = false;
		for (int i = 0; i < n; i++) agent[i] = new Agent();
		
		int CT = 0, HT = 0;
		boolean CT_count_flag = true, HT_count_flag = false;
		
		while (true) {
			if (IsSafeConfiguration(agent)) { 
				if (CT_count_flag) System.out.println("CT = " + CT);
				CT_count_flag = false; 
				HT_count_flag = true; 
			}
			
			//維持時間終了
			if (!CT_count_flag && !IsSafeConfiguration(agent)) { 
				break; 
			}
			
			//initiatorとresponderを決める
			for (int initiator = 0; initiator < n; initiator++)
				for (int responder = 0; responder < n; responder++)
					//交流できるなら交流
					if (graph.List[initiator][responder] &&
						distance(agent[initiator], agent[responder]) <= DISTANCE_FOR_INTERACTION &&
						initiator != responder) {
						Interaction.interaction(agent[initiator], agent[responder]);
						if (CT_count_flag) CT++;		//交流ごとにカウント
						if (HT_count_flag) HT++;
					}
			
			//各agent移動
			for (int i = 0; i < n; i++) agent[i].MoveAction(Agent.RandomWalk);
		}

		System.out.println("HT = " + HT);

	}
	
	
	//安定状況かの判別
	private static boolean IsSafeConfiguration(Agent agent[]){
		//最小のidとそのノードを見つける
		int min = LID_MAX+1;
		Agent v_min = null;
		for (int i = 0; i < n; i++) 
			if (min > agent[i].var) {
				min = agent[i].var;
				v_min = agent[i];
			}
		
		//v.lid = id(v_min)かどうか
		for(int i=0; i < n; i++) 
			if (agent[i].lid != v_min.var) {
//				for( int j = 0; j < n; j++) System.out.print(agent[j].lid + "\t");
//				System.out.println("\n");
				return false; 
			}
		
		//セーフタイムかどうか
		for(int i=0; i < n; i++)
			if(agent[i].timer < n/2){
				return false;
			}
		
		//v_min.timer = t_maxかどうか
		if (v_min.timer != s) {
			return false; 
		}
		
		//全部条件満たしてたらtrueを返す
		return true;
	}
	
	//距離を返す
	private static double distance (Agent initiator, Agent responder) {
		return Math.sqrt( (initiator.x - responder.x)*(initiator.x - responder.x)-(initiator.y - responder.y)*(initiator.y - responder.y) );
	}
}
