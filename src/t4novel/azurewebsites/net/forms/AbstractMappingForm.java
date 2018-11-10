package t4novel.azurewebsites.net.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	

	public Map<String, String> getErrors(){
		Map<String, String> filteredError = new HashMap<>();
		for (String errorType : errorTypes) {
			for (Entry<String, String> entry : errors.entrySet()) {
				String errorName = entry.getKey();
				if (errorName.startsWith(errorType)) {
					filteredError.put(errorType, entry.getValue());
					break;
				}
			}
		}
		return filteredError;
	}

	protected abstract void assignDefaultErrorType();
}
