package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class EmpireId {

	private final String id;

	/**
	 * Constructor.
	 * Private to prevent non managed instantiation
	 *
	 * @param id the id of empire
	 */
	private EmpireId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public static EmpireId of(String empireId) {
		return new EmpireId(empireId);
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EmpireId empireId = (EmpireId) o;
		return Objects.equals(id, empireId.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "EmpireId{" +
			"id='" + id + '\'' +
			'}';
	}
}
