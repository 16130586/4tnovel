package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ViewDAO {
	private Connection cnn;
	private final String insertQuery = "INSERT INTO VIEWING (ACCID,STREAM,TARGET_ID) VALUES (?,?,?)";

	public ViewDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public void inserNewView(int accId, String stream, int targetId) throws SQLException {
		PreparedStatement stmt = cnn.prepareStatement(insertQuery);
		stmt.setInt(1, accId);
		stmt.setString(2, stream);
		stmt.setInt(3, targetId);
		stmt.executeUpdate();
		stmt.close();
	}

	public HashMap<Integer, Integer> statistic(String firstDate, String lastDate) throws SQLException {
		Date date = new Date();
		String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
		if (isOver(currentDate, lastDate)) {
			lastDate = currentDate;
		}

		String query = "select datepart(hour, VIEWING.VIEWDATE) AS E, count(VIEWING.VIEWDATE) / DATEDIFF(day, ?, ?) AS C from VIEWING where VIEWING.VIEWDATE between ? and ? and VIEWING.STREAM = ? and VIEWING.ACCID < 0 group by datepart(hour, VIEWING.VIEWDATE)";
		PreparedStatement stmt = cnn.prepareStatement(insertQuery);
		HashMap<Integer, Integer> dataTable = new HashMap<>();

		stmt.setString(1, firstDate);
		stmt.setString(2, lastDate);
		stmt.setString(3, firstDate);
		stmt.setString(4, lastDate);
		stmt.setString(5, "global");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			dataTable.put(rs.getInt(1), rs.getInt(2));
		}
		stmt.close();
		rs.close();
		return dataTable;
	}

	public boolean isOver(String currentDate, String lastDate) {
		String[] currentDateSplit = currentDate.split("/");
		String[] lastDateSplit = lastDate.split("/");

		if (currentDateSplit[2].compareTo(lastDateSplit[2]) == 0) {
			if (currentDateSplit[0].compareTo(lastDateSplit[0]) == 0) {
				if (currentDateSplit[1].compareTo(lastDateSplit[1]) < 0) {
					return true;
				}
			}
			if (currentDateSplit[0].compareTo(lastDateSplit[0]) < 0) {
				return true;
			}
		}
		if (currentDateSplit[2].compareTo(lastDateSplit[2]) < 0) {
			return true;
		}
		return false;
	}

}
