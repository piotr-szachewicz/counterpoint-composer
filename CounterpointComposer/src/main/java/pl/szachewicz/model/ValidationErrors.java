package pl.szachewicz.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {
	private final List<String> errors = new ArrayList<String>();

	public void addError(String errorMsg) {
		errors.add(errorMsg);
	}

	public List<String> getErrors() {
		return errors;
	}
}
