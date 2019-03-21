package util;

import java.util.Collection;

public final class Checker {

	public static boolean isNumber(String value) {
		if (isNull(value)) {
			return false;
		}
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isInt(String value) {
		if (isNull(value)) {
			return false;
		}
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static <E> boolean isNull(Collection<E> coll) {
		if (coll != null && coll.size() > 0) {
			return false;
		}
		return true;
	}

	/*
	 * пустой input типа text, если она пуста, возвращает пустую строку с "==" все
	 * работало, заменил на equals. Проверил: без " "".equals(object) " уловие не
	 * работает правильно
	 */

	public static boolean isNull(Object object) {
		if (object == null || "".equals(object)) {
			return true;
		}
		return false;
	}

	public static boolean areNull(Object... objects) {
		for (Object object : objects) {
			if (isNull(object)) {
				return true;
			}
		}
		return false;
	}
}
