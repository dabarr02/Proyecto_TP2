package simulator.model;

import java.util.*;

import org.json.JSONObject;

public class Junction extends SimulatedObject {
	private List<Road> inRoads;
	private Map<Junction,Road>outRoads;
	//Lista de colas para las carreteras entrantes
	private List<List<Vehicle>> queLists;
	private Map<Road,List<Vehicle>> roadMap;
	private int light;
	private int greenPaso;
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

	public void addIncomingRoad(Road r) {
		if(r == null) {
			throw new IllegalArgumentException("No se admiten carreteras nulas.");
		}
		if(r.getDestinationJunction() != this) {
			throw new IllegalArgumentException("La carretera no llega a este cruce.");
		}
		//añadimos carretera a la lista de carreteras entrantes
		inRoads.add(r);
		ArrayList<Vehicle> list = new ArrayList<Vehicle>();
		queLists.add(list);
		roadMap.put(r, list);
		return;
	}
	public void addOutgoingRoad(Road r,Junction j) {
		if(r == null || j == null) {
			throw new IllegalArgumentException("No se admiten carreteras o cruces nulos.");
		}
		//añadimos carretera a la lista de carreteras salientes
		outRoads.put(j, r);
		return;
	}
	Road roadTo(Junction j) {
		if(j == null) {
			throw new IllegalArgumentException("No se admiten cruces nulos.");
		}
		//devolvemos la carretera que va al cruce j
		return outRoads.get(j);
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
	void enter(Vehicle v) {
		if(v == null) {
			throw new IllegalArgumentException("No se admiten vehiculos nulos.");
		}
		//añadimos vehiculo a la cola
		Road r= v.getRoad();
		roadMap.get(r).add(v);
		return;
	}
}
