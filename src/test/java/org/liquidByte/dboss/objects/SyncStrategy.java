package org.liquidByte.dboss.objects;

import java.util.Date;

public interface SyncStrategy {
	void sync(ObjectEntry entry, int checksum, Date timestamp, byte[] blob);
}
