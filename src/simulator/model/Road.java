package simulator.model;


import java.util.*;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject {
	private Junction originJunction;
	private int length;
	private Junction destinationJunction;
	private int maxSpeed;
	private int currentSpeedLimit;
	private int contaminationAlarmLimit;
	private Weather weatherConditions;
	private int totalContamination;
	private List<Vehicle> vehicles;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id);
		// ...
		if (maxSpeed <= 0) {
			throw new IllegalArgumentException("La velocidad máxima debe ser positiva");
		}
		if (contLimit < 0) {
			throw new IllegalArgumentException("El límite de contaminación debe ser no negativo");
		}
		if (length <= 0) {
			throw new IllegalArgumentException("La longitud debe ser positiva");
		}
		if (srcJunc == null || destJunc == null || weather == null) {
			throw new IllegalArgumentException("Los cruces y el clima no deben ser nulos");
		}

		this.originJunction = srcJunc;
		this.destinationJunction = destJunc;
		this.maxSpeed = maxSpeed;
		this.currentSpeedLimit = maxSpeed;
		this.contaminationAlarmLimit = contLimit;
		this.length = length;
		this.weatherConditions = weather;
		this.totalContamination = 0;
		this.vehicles = new ArrayList<Vehicle>();

		srcJunc.addOutgoingRoad(this,destJunc);
		destJunc.addIncomingRoad(this);
	}
	public int getLength() {
		return length;
	}
	void enter(Vehicle v) {
		if (v == null) {
			throw new IllegalArgumentException("El vehículo no debe ser nulo");
		}
		if(!(v.getSpeed()==0 || v.getLocation()==0)){
			throw new IllegalArgumentException("El vehículo no está en la posición inicial o velocidad 0");
		}
		vehicles.add(v);
	}
	void exit(Vehicle v) {
		if (v == null) {
			throw new IllegalArgumentException("El vehículo no debe ser nulo");
		}
		if(!vehicles.contains(v)){
			throw new IllegalArgumentException("El vehículo no está en la carretera");
		}
		vehicles.remove(v);
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
	Junction getOriginJunction() {
		return originJunction;
	}
	Junction getDestinationJunction() {
		return destinationJunction;
	}
	void addContamination(int c) {
		if (c < 0) {
			throw new IllegalArgumentException("La contaminación debe ser no negativa");
		}
		this.totalContamination += c;
		
	}
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);


}
