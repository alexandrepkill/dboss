package org.liquidByte.dboss.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class MultiStateObjectEntry extends ObjectEntry {
	private final List<ObjectState> states;	

	protected MultiStateObjectEntry(String name, String type, ObjectStateFactory facto, SyncStrategy syncStrat) {
		super(name, type, facto, syncStrat);
		this.states = new ArrayList<ObjectState>();
	}

	public List<ObjectState> getStates() {
		return Collections.unmodifiableList(this.states);
	}

	@Override
	public ObjectState setState(int checksum, Date timestamp) {
		ObjectState state = this.createState(checksum, timestamp);
		this.states.add(state);
		return state;
	}

	@Override
	public boolean hasMatch(int checksum, Date timestamp) {
		for (ObjectState state : this.states) {
			if (state.getChecksum() == checksum && state.getTimestamp().equals(timestamp)) {
				return true;
			}
		}
		return false;
	}		
}
