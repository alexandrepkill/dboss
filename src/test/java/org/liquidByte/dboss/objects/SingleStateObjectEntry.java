package org.liquidByte.dboss.objects;

import java.util.Date;

public abstract class SingleStateObjectEntry extends ObjectEntry {
	private ObjectState state;

	protected SingleStateObjectEntry(String name, String type, ObjectStateFactory facto, SyncStrategy syncStrat) {
		super(name, type, facto, syncStrat);
	}
	
	public ObjectState getState() {
		return this.state;
	}

	@Override
	public ObjectState setState(int checksum, Date timestamp) {
		if (this.state != null) {
			throw new IllegalStateException("one state is definable for this object");
		}
		ObjectState newState = this.createState(checksum, timestamp);
		this.state = newState;
		return newState;
	}

	@Override
	public boolean hasMatch(int checksum, Date timestamp) {
		return this.state != null && this.state.getChecksum() == checksum;
	}
}
