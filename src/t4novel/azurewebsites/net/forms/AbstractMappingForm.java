package t4novel.azurewebsites.net.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractMappingForm {
	protected List<String> errorTypes = null;
	protected Map<String, String> errors = null;

	public AbstractMappingForm() {
		super();
		errors = new HashMap<>();
		assignDefaultErrorType();
	}

	/**
	 * @author 16130586
	 * @return trả về xem form có dính lỗi trong 1 tập lỗi không!
	 */
	public boolean isOnError() {
		return !this.errors.isEmpty();
	}

	/**
	 * @author 16130586
	 * @return trả về 1 error message của 1 loại lỗi thuộc form
	 */
	public Map<String, String> getErrors() {
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

	/***
	 * @author 16130586
	 * @return Một form luôn luôn có 1 tập các lỗi, mỗi lỗi thuộc 1 type lỗi liên
	 *         quan đến 1 field của form vì vậy khi khai báo một mapping form class
	 *         phải override và khai báo tập lỗi, để làm đầu vào cho hàm getErrors()
	 */
	protected abstract void assignDefaultErrorType();

	/**
	 * @author 16130586
	 * @return Một form luôn luôn có mục đích là submit để tạo ra java object
	 *         instance của 1 class, vì vậy khi khai bào mapping form class phải
	 *         override và khai báo ra instance nào được trả lại từ tập dữ liệu đầu
	 *         vào của người dùng instance object đã định nghĩa ở concrete class
	 */
	public abstract Object getMappingData();

	public void applyErrorsToUI(HttpServletRequest request) {
		for (Entry<String, String> errorType : getErrors().entrySet()) {
			String errorName = errorType.getKey().concat("Error");
			request.setAttribute(errorName, errorType.getValue());
		}
	}
}
