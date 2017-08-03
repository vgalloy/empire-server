package com.vgalloy.empire.webservice.exception;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6438928401765900750L;
	private static final NotFoundException INSTANCE = new NotFoundException();

	/**
	 * Constructor.
	 * Private to prevent non managed instantiation
	 */
	private NotFoundException() {
	}

	/**
	 * Build a {@link NotFoundException}.
	 *
	 * @return a instance of this exception.
	 */
	public static NotFoundException doesExist() {
		return INSTANCE;
	}
}
