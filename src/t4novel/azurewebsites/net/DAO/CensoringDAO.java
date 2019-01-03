package t4novel.azurewebsites.net.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import t4novel.azurewebsites.net.censoring.CensorEntity;
import t4novel.azurewebsites.net.censoring.CensorEntityFactory;

public class CensoringDAO {
	private Connection cnn;

	public CensoringDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}

	public List<CensorEntity> getAllUnCensoringEntities() throws SQLException {
		CensorEntityFactory censorFactory = CensorEntityFactory.getInstance();
		List<CensorEntity> ret = new LinkedList<>();
		String query = "SELECT * FROM CENSORING WHERE OUT_DATE IS NULL";
		PreparedStatement stmt = cnn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ret.add(censorFactory.create(rs, cnn));
		}
		rs.close();
		stmt.close();
		return ret;
	}

	public CensorEntity getCensorEntityByIdAndStream(int id, String stream) throws SQLException {
		CensorEntityFactory censorFactory = CensorEntityFactory.getInstance();
		CensorEntity result = null;
		String query = "select * from CENSORING where TARGET_ID = ? and STREAM = ?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.setString(2, stream);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) result = censorFactory.create(rs, cnn);
		rs.close();
		stmt.close();
		return result;
	}
	
	public void insertCensor(CensorEntity entity) throws SQLException {
		String query = "INSERT INTO CENSORING(TARGET_ID,OWNER_ID,STREAM) VALUES(? , ? , ?)";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, entity.getCensorId());
		stmt.setInt(2, entity.getOwnerAccountId());
		stmt.setString(3, entity.getStream());
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteCensor(CensorEntity entity) throws SQLException {
		String query = "DELETE FROM CENSORING WHERE TARGET_ID=? AND HOST_ID=? AND STREAM=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setInt(1, entity.getCensorId());
		stmt.setInt(2, entity.getOwnerAccountId());
		stmt.setString(3, entity.getStream());
		stmt.executeUpdate();
		stmt.close();
	}

	public void onCensoringEventUpdate(CensorEntity entity) throws SQLException {
		String query = "UPDATE CENSORING SET IS_PUBLISHED=? , OUT_DATE=? WHERE TARGET_ID=? AND OWNER_ID=? AND STREAM=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBoolean(1, entity.isAccepted());
		stmt.setTimestamp(2,
				new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")).getTimeInMillis()));
		stmt.setInt(3, entity.getCensorId());
		stmt.setInt(4, entity.getOwnerAccountId());
		stmt.setString(5, entity.getStream());
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void unCensoringAllNovelByAccountID(int accountID) throws SQLException {
		String query = "UPDATE CENSORING SET IS_PUBLISHED=? WHERE OWNER_ID=?";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBoolean(1, false);
		stmt.setInt(2, accountID);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void unCensoringAllNovelByGroupID(int groupID) throws SQLException {
		String query = "UPDATE CENSORING SET IS_PUBLISHED= ? FROM CHAP INNER JOIN LN ON CHAP.ID_NOVEL = LN.ID INNER JOIN CENSORING ON CHAP.ID = CENSORING.TARGET_ID WHERE LN.IDGROUP = ? AND CENSORING.STREAM='chapter';";
		PreparedStatement stmt = cnn.prepareStatement(query);
		stmt.setBoolean(1, false);
		stmt.setInt(2, groupID);
		stmt.executeUpdate();
		stmt.close();
	}
}
