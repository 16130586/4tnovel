package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;
import t4novel.azurewebsites.net.utils.StringUtil;

public class AddingNovelForm extends AbstractMappingForm {
	private String title, description;
	private int groupID;
	private NovelStatus status;
	private NovelKind kind;
	private List<NovelGenre> genres;
	private Account owner;

	public AddingNovelForm(HttpServletRequest request) {
		this.owner = (Account) request.getSession().getAttribute("account");
		setTitle((String) request.getAttribute("title"));
		setDescription((String) request.getAttribute("description"));
		setGroupID((String) request.getAttribute("group"));
		setKind((String) request.getAttribute("type-novel"));
		setGenres(getGenresFormRequest(request));
		setStatus((String) request.getAttribute("status"));
	}

	private void setStatus(String parameter) {
		try {
			setStatus(NovelStatus.getNovelStatus(Integer.parseInt(parameter)));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}

	private void setKind(String parameter) {
		this.kind  = NovelKind.getNovelKind(parameter);
	}

	private void setGroupID(String parameter) {
		try {
			setGroupID(Integer.parseInt(parameter));
		} catch (NumberFormatException e) {
			errors.put("groupIDNotFound", "GroupID not found!");
		}
	}

	private List<NovelGenre> getGenresFormRequest(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> rawGenreStrings = (List<String>) request.getAttribute("genres");
		if (rawGenreStrings == null)
			return null;
		List<NovelGenre> genres = new LinkedList<>();
		for (String rawGenre : rawGenreStrings) {
			try {
				genres.add(NovelGenre.getGenre(Integer.parseInt(rawGenre)));
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"The genre is invalid form user to serser at AddingNovelForm with getGenresFormRequest!");
			}
		}
		return genres;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			errors.put("titleEmpty", "Please make sure novel's title isn't empty!");
		} else if (StringUtil.isAllSpace(title)) {
			errors.put("titleAllSpace", "Please remove all space and insert title!");
		} else {
			this.title = title.trim();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty()) {
			errors.put("descriptionEmpty", "Please make sure novel's description isn't empty!");
		} else if (StringUtil.isAllSpace(description)) {
			errors.put("descriptionAllSpace", "Please remove all space and insert description!");
		} else {
			this.description = description.trim();
		}
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public NovelStatus getStatus() {
		return status;
	}

	public void setStatus(NovelStatus status) {
		this.status = status;
	}

	public NovelKind getKind() {
		return kind;
	}

	public void setKind(NovelKind kind) {
		this.kind = kind;
	}

	public List<NovelGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<NovelGenre> genres) {
		if (genres == null || genres.isEmpty()) {
			errors.put("genreEmpty", "Please select at least 1 genre for your novel!");
		} else
			this.genres = genres;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("title", "genre", "description", "kind" , "groupID");

	}

	@Override
	public Object getMappingData() {
		if (!errors.isEmpty()) {
			for (Entry<String, String> entry : errors.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT AddingNovelForm, getMappingData()");
		}
		Novel rs = new Novel();
		rs.setName(getTitle());
		rs.setDescription(getDescription());
		rs.setDateUp(new Date());
		rs.setStatus(getStatus());
		rs.setGenres(getGenres());
		rs.setKind(getKind());
		rs.setGroupId(getGroupID());
		rs.setOwner(owner);
		return rs;
	}

}
