package org.liquidByte.dboss.objects;

import java.util.Date;

public abstract class ObjectEntry {
	private final String name;
	private final String type;
	private final ObjectStateFactory facto;
	private final SyncStrategy syncStrat;

	protected ObjectEntry(String name, String type, ObjectStateFactory facto, SyncStrategy syncStrat) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name is null or empty");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type is null or empty");
		}
		if (facto == null) {
			throw new IllegalArgumentException("factory is null");
		}
		if (syncStrat == null) {
			throw new IllegalArgumentException("syncStrat is null");
		}
		this.name = name;
		this.type = type;
		this.facto = facto;
		this.syncStrat = syncStrat;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void sync(int checksum, Date timestamp, byte[] blob) {
		if (this.hasMatch(checksum, timestamp)) {
			throw new IllegalStateException("blob in sync");
		}
		this.syncStrat.sync(this, checksum, timestamp, blob);
	}

	public abstract boolean hasMatch(int checksum, Date timestamp);
	public abstract ObjectState setState(int checksum, Date timestamp);
	protected ObjectState createState(int checksum, Date timestamp) {
		return this.facto.create(this, checksum, timestamp);
	}
	
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
