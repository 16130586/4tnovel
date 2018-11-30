package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class AddingMemberForm extends AbstractMappingForm {
	private int idGroup, idAcc;
	
	public AddingMemberForm(HttpServletRequest request) {
		try {
			System.out.println(" on parsing request" + request.getParameter("id-acc"));
			setIdAcc(Integer.parseInt(request.getParameter("id-acc")));
			setIdGroup(Integer.parseInt(request.getParameter("id-group")));
		} catch (Exception e) {
			errors.put("idGroupFormat", "Please format group!");
			errors.put("idAccountFormat", "Please account!");
			e.printStackTrace();		}
	}
	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public int getIdAcc() {
		return idAcc;
	}

	public void setIdAcc(int idAcc) {
		this.idAcc = idAcc;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("idGroup", "idAcc");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT AddingMemberForm, getMappingData()");
		Map<String, Integer> rs = new HashMap<>();
		rs.put("needToAddAccountId", getIdAcc());
		rs.put("addToGroupId", getIdGroup());
		return rs;
	}

}
