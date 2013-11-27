package org.liquidByte.dboss.objects;

import java.util.Date;

public interface ObjectStateFactory {
	ObjectState create(ObjectEntry entry, int checksum, Date timestamp);
}
