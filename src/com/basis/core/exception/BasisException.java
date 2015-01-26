package com.basis.core.exception;

import com.basis.core.constants.EMessageCode;

public class BasisException extends Exception {
	private String code;

	    /**
	     * Constructs a new exception with <code>null</code> as its detail message.
	     * The cause is not initialized, and may subsequently be initialized by a
	     * call to {@link #initCause}.
	     */
	    private BasisException() {
		super();
	    }

	    public BasisException(String code) {
			super();
			this.code=code;
		}
	    /**
	     * Constructs a new exception with the specified detail message.  The
	     * cause is not initialized, and may subsequently be initialized by
	     * a call to {@link #initCause}.
	     *
	     * @param   message   the detail message. The detail message is saved for 
	     *          later retrieval by the {@link #getMessage()} method.
	     */
	    public BasisException(String code,String message) {
		super(message);
		this.code=code;
	    }

	    /**
	     * Constructs a new exception with the specified detail message and
	     * cause.  <p>Note that the detail message associated with
	     * <code>cause</code> is <i>not</i> automatically incorporated in
	     * this exception's detail message.
	     *
	     * @param  message the detail message (which is saved for later retrieval
	     *         by the {@link #getMessage()} method).
	     * @param  cause the cause (which is saved for later retrieval by the
	     *         {@link #getCause()} method).  (A <tt>null</tt> value is
	     *         permitted, and indicates that the cause is nonexistent or
	     *         unknown.)
	     * @since  1.4
	     */
	    public BasisException(String code,String message, Throwable cause) {
	        super(message, cause);
	        this.code=code;
	    }

	    /**
	     * Constructs a new exception with the specified cause and a detail
	     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	     * typically contains the class and detail message of <tt>cause</tt>).
	     * This constructor is useful for exceptions that are little more than
	     * wrappers for other throwables (for example, {@link
	     * java.security.PrivilegedActionException}).
	     *
	     * @param  cause the cause (which is saved for later retrieval by the
	     *         {@link #getCause()} method).  (A <tt>null</tt> value is
	     *         permitted, and indicates that the cause is nonexistent or
	     *         unknown.)
	     * @since  1.4
	     */
	    public BasisException(String code,Throwable cause) {
	        super(cause);
	        this.code=code;
	    }

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
	    
}
