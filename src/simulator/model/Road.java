package simulator.model;


import java.util.*;

import org.json.JSONObject;

public class Road extends SimulatedObject {
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
		this.vehicles = new ArrayList<>();

		srcJunc.addOutgoingRoad(this,destJunc);
		destJunc.addIncomingRoad(this);
	}
	public int getLength() {
		return length;
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

}
