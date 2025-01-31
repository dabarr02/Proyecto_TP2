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
	private int distancia_total;
	
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
		  return;
		}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		if(!status.equals(VehicleStatus.TRAVELING)) {
			return;
		}
		this.location+=this.speed;
		if(this.location>=this.road.getLength()) {
			this.location=this.road.getLength();
			
		}
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
	void moveToNextRoad() {
		// TODO
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
