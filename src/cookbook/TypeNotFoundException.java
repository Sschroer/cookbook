package cookbook;

/**
 * This is the TypeNotFound custom exception.
 * @author Stephen Schroer
 *
 */
public class TypeNotFoundException extends Exception {

	private static final long serialVersionUID = -5282490839749925859L;

	public TypeNotFoundException() {
		super();
	}

	public TypeNotFoundException(String message) {
		super(message);
		
	}

	public TypeNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public TypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public TypeNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
