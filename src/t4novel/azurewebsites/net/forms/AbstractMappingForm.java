package t4novel.azurewebsites.net.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMappingForm {
	protected List<String> errorTypes = null;
	protected Map<String, String> errors = null;

	public AbstractMappingForm() {
		super();
		errors = new HashMap<>();
		assignDefaultErrorType();
	}

	public boolean isOnError() {
		return !this.errors.isEmpty();
	}
	

	public abstract Map<String, String> getErrors();

	protected abstract void assignDefaultErrorType();
}
