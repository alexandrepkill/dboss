package org.liquidByte.dboss.objects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ObjectEntry {
	private final String name;
	private final String type;
	private final Set<ObjectState> states;
	private final boolean delta;

	protected ObjectEntry(String name, String type) {
		this(name, type, false);
	}
	protected ObjectEntry(String name, String type, boolean delta) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type");
		}
		this.name = name;
		this.type = type;
		this.states = new HashSet<ObjectState>();
		this.delta = delta;
	}
	protected ObjectEntry(String name, String type, List<ObjectState> states) {
		this(name, type, false, states);
	}	
	protected ObjectEntry(String name, String type, boolean delta, List<ObjectState> states) {
		this(name, type, delta);
		for(ObjectState state : states) {
			this.states.add(state);
		}
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public boolean isDelta() {
		return delta;
	}
	public void absorb(ObjectState state) {
		byte[] blob = state.getBlob();
		ObjectState absorbedState = this.newState(state.getChecksum(), blob);
		if (this.states.contains(absorbedState)) {
			throw new IllegalStateException("state already known by object");
		}
		this.sync(blob);
	}
	
	protected abstract ObjectState newState(int checksum, byte[] blob);
	protected abstract void sync(byte[] blob);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectEntry other = (ObjectEntry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
