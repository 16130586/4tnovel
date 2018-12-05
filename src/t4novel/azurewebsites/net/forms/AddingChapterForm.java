package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.utils.StringUtil;

public class AddingChapterForm extends AbstractMappingForm {

	private String title, content;
	private int inVol, inNovel;

	public AddingChapterForm(HttpServletRequest request) {
		setTitle(request.getParameter("title"));
		setContent(request.getParameter("content"));
		try {
			setInNovel(Integer.parseInt(request.getParameter("in-novel")));
			setInVol(Integer.parseInt(request.getParameter("in-vol")));
		} catch (Exception e) {
			errors.put("inNovelNumberFormat", "Please format inNovel!");
			errors.put("inVolNumberFormat", "Please format inVol!");
			e.printStackTrace();
		}
	}
	
	public AddingChapterForm() {
	}

	@Override
	public String toString() {
		return title + "," + content + ", " + inNovel + ", " + inVol;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			errors.put("titleEmpty", "Tên chương không được bỏ trống!");
		} else if (StringUtil.isAllSpace(title)) {
			errors.put("titleAllSpace", "Xóa tất cả khoảng trắng và điền tên chương!");
		} else {
			this.title = title.trim();
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null || content.isEmpty()) {
			errors.put("contentEmpty", "Nội dung chương không được bỏ trống!");
		} else if (StringUtil.isAllSpace(content)) {
			errors.put("contentAllSpace", "Xóa tất cả khoảng trắng và điền nội dung chương!");
		} else {
			this.content = content.trim();
		}
	}

	public int getInVol() {
		return inVol;
	}

	public void setInVol(int inVol) {
		this.inVol = inVol;
	}

	public int getInNovel() {
		return inNovel;
	}

	public void setInNovel(int inNovel) {
		this.inNovel = inNovel;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("title", "content", "inNovel", "inVol");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT AddingVolForm, getMappingData()");
		Chap rs = new Chap();
		rs.setDateUp(new java.util.Date());
		rs.setTitle(getTitle());
		rs.setContent(getContent());
		rs.setVolOwnerId(getInVol());
		rs.setNovelOwnerId(getInNovel());
		return rs;
	}

}
