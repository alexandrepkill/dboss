package org.liquidByte.dboss.objects;

import java.util.Date;

public abstract class ObjectState {
	private final ObjectEntry entry;
	private int checksum;
	private Date timestamp;

	protected ObjectState(ObjectEntry entry, int checksum) {
		this(entry, checksum, new Date());
	}
	protected ObjectState(ObjectEntry entry, int checksum, Date timestamp) {
		if (entry == null) {
			throw new IllegalArgumentException("entry");
		}
		if (timestamp == null) {
			throw new IllegalArgumentException("timestamp is null");
		}
		this.entry = entry;
		this.checksum = checksum;
		this.timestamp = timestamp;
	}
	
	public int getChecksum() {
		return checksum;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public abstract byte[] getBlob();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + checksum;
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
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
		ObjectState other = (ObjectState) obj;
		if (checksum != other.checksum)
			return false;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		return true;
	}
}
