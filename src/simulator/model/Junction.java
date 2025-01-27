package simulator.model;

import java.util.*;

import org.json.JSONObject;

public class Junction extends SimulatedObject {
	private List<Road> carreteras_in;
	private Map<Juction,Road>carreteras_out;
	private List<List<Vehicle>> lista_colas;
	private Map<Road,List<Vehicle>> map_road;
	private int luz;
	private int green_paso;
	private LightSwitchingStrategy light_strat;
	private DequeuingStrategy colaStrat;
	private int x;
	private int y;
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		  super(id);
		  if(lsStrategy == null || dqStrategy == null) {
			  throw new IllegalArgumentException("No se admiten estrategias nulas.");
		  }else if( xCoor<0 || yCoor <0) {
			  throw new IllegalArgumentException("No se admiten coordenadas negativas.");
		  }
		}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
	
	Junction copy () {
		Junction ret = new Junction(this._id,this.light_strat,this.colaStrat,this.x,this.y);
		return ret;
	}

}
