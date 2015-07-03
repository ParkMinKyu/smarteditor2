package kr.iiac.bugs.config;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class InputStreamToServletInputStream extends ServletInputStream {
	private final InputStream sourceStream;

	public InputStreamToServletInputStream(InputStream sourceStream) {
		this.sourceStream = sourceStream;
	}

	public final InputStream getSourceStream() {
		return this.sourceStream;
	}

	public void close() throws IOException {
		super.close();
		this.sourceStream.close();
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadListener(ReadListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return this.sourceStream.read();
	}

}
