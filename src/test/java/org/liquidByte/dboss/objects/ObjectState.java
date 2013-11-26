package org.liquidByte.dboss.objects;

public abstract class ObjectState {
	private final ObjectEntry entry;
	private int checksum;	
	
	protected ObjectState(ObjectEntry entry, int checksum) {
		if (entry == null) {
			throw new IllegalArgumentException("entry");
		}
		this.entry = entry;
		this.checksum = checksum;
	}
	
	public int getChecksum() {
		return checksum;
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
