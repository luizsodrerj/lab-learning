package biju.faces;

import java.io.IOException;
import java.io.InputStream;

import org.primefaces.model.file.UploadedFile;

import biju.util.IOUtil;

public class UploadedFileImpl implements UploadedFile {

	private byte[] content;
	
	
	
	public UploadedFileImpl(byte[] content) {
		super();
		this.content = content;
	}

	@Override
	public byte[] getContent() {
		return content;
	}

	@Override
	public String getContentType() {
		return IOUtil.IMAGE_PNG;
	}

	@Override
	public String getFileName() {
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public void write(String filePath) throws Exception {
	}

	@Override
	public void delete() throws IOException {
	}

}
