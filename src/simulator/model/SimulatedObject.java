package simulator.model;

import org.json.JSONObject;

public abstract class SimulatedObject {

	protected String id;

	SimulatedObject(String id) {
		if ( id == null || id.isBlank() )
			throw new IllegalArgumentException("the 'id' must be a nonempty string.");
		else
			id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	abstract void advance(int time);

	abstract public JSONObject report();
}
