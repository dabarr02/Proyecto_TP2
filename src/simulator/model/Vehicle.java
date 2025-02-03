package simulator.model;
import java.util.*;

import org.json.JSONObject;


public class Vehicle extends SimulatedObject{
	private List<Junction> itinerary;
	private int maxSpeed;
	private int speed;
	private VehicleStatus status;
	private Road road;
	private int location;
	private int contClass; //grado de contaminacion
	private int totalCO2;
	private int distanciaTotal;
	private int destJunction; //indice del siguiente cruce
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		  super(id);
		  //compobaciones
		  if(maxSpeed<=0 )  {
			  throw new IllegalArgumentException("La velocidad maxima tiene que ser positiva.");
		  }else if (contClass<0 || contClass > 10 ) {
			  throw new IllegalArgumentException("ContClass debe ser un valor en [0,10].");
		  }else if (itinerary.size()<2) {
			  throw new IllegalArgumentException("El itinerario debe tener al menos dos elementos.");
		  }
		  // Copia profunda de del itinerario
		  this.itinerary = new ArrayList<Junction>() ;
		  for (Junction GO:itinerary) {
			  this.itinerary.add(GO.copy());
		  }
		  //Inicializar atributos
		  this.maxSpeed=maxSpeed;
		  this.contClass=contClass;
		  this.destJunction=0;
		  return;
		}

	@Override
	void advance(int time) {
		if(time<0) {
			throw new IllegalArgumentException("El tiempo debe ser positivo.");
		}
		// TODO Auto-generated method stub
		if(!status.equals(VehicleStatus.TRAVELING)) {
			return;
		}
		int oldLocation = this.location;
		this.location= Math.min(this.location+this.speed, this.road.getLength());
		this.distanciaTotal+=this.location-oldLocation;
		int c =this.contClass*this.distanciaTotal;
		this.totalCO2+=	c;	
		this.road.addContamination(c);
		if(this.location==this.road.getLength()) {
			this.speed=0;
			this.status=VehicleStatus.WAITING;
			this.itinerary.get(this.destJunction).enter(this);
			this.destJunction++;
		}
		return;
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
	void moveToNextRoad() {
		// TODO
		if(!(this.status.equals(VehicleStatus.WAITING )|| this.status.equals(VehicleStatus.PENDING))) {
			throw new IllegalArgumentException("El vehiculo no esta esperando.");
		}
		if(this.road!=null || this.destJunction>0) {
			this.road.exit(this);
		}else if(this.destJunction==this.itinerary.size()-1) {
			this.status=VehicleStatus.ARRIVED;
			this.location=0;
			this.speed=0;
			this.road=null;
			return;
		}
			this.road=this.itinerary.get(this.destJunction).roadTo(this.itinerary.get(this.destJunction+1));
			this.road.enter(this);
			this.status=VehicleStatus.TRAVELING;
			this.location=0;
			return;
		
	}
	
	
	void setSpeed(int s) {
		if(s<0) {
			throw new IllegalArgumentException("La velocidad tiene que ser positiva.");
		}
		
		 this.speed=Math.min(this.maxSpeed,s);
		 return;
	}
	
	void setContaminationClass(int c) {
		if (contClass<0 || contClass > 10 ) {
			  throw new IllegalArgumentException("ContClass debe ser un valor en [0,10].");
		}
		
		 this.contClass=c;
		 return;
	}

	public List<Junction> getItinerary() {
		List<Junction> ret = new ArrayList<Junction>() ;
		  for (Junction GO:itinerary) {
			  ret.add(GO.copy());
		  }
		  return ret;
	}

	private void setItinerary(List<Junction> itinerary) {
		this.itinerary = new ArrayList<Junction>() ;
		  for (Junction GO:itinerary) {
			  this.itinerary.add(GO.copy());
		  }
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	private void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	private void setStatus(VehicleStatus status) {
		this.status = status;
		if(!status.equals(VehicleStatus.TRAVELING)) {
			setSpeed(0);
		}
	}

	public Road getRoad() {
		return road;
	}

	private void setRoad(Road road) {
		this.road = road;
	}

	public int getLocation() {
		return location;
	}

	private void setLocation(int location) {
		this.location = location;
	}

	public int getContClass() {
		return contClass;
	}

	private void setContClass(int contClass) {
		this.contClass = contClass;
	}

	public int getTotalCO2() {
		return totalCO2;
	}

	private void setTotalCO2(int totalCO2) {
		this.totalCO2 = totalCO2;
	}

	public int getSpeed() {
		return speed;
	}
	
}
