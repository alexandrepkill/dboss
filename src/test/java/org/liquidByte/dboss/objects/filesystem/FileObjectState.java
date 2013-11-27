package org.liquidByte.dboss.objects.filesystem;

import java.io.File;
import java.util.Date;

import org.liquidByte.dboss.objects.ObjectEntry;
import org.liquidByte.dboss.objects.ObjectState;

public class FileObjectState extends ObjectState {
	private final File file;	
	
	public FileObjectState(ObjectEntry entry, int checksum, File file) {
		this(entry, checksum, new Date(), file);
	}
	protected FileObjectState(ObjectEntry entry, int checksum, Date timestamp, File file) {
		super(entry, checksum, timestamp);
		if (file == null) {
			throw new IllegalArgumentException("file is null");
		}
		this.file = file;
	}

	@Override
	public byte[] getBlob() {
		// TODO Auto-generated method stub
		return null;
	}
}
