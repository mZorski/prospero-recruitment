package org.example.prospero.data.services.exception;

public class RestApiFetchException extends RuntimeException {

	public RestApiFetchException() {
		super();
	}

	public RestApiFetchException(String message) {
		super(message);
	}

	public RestApiFetchException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestApiFetchException(Throwable cause) {
		super(cause);
	}

	protected RestApiFetchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
