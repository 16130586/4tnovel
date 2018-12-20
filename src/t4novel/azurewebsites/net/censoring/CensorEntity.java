package t4novel.azurewebsites.net.censoring;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface CensorEntity extends Serializable {

	int getCensorId();

	int getOwnerAccountId();

	String getTitle();

	String getContent();

	String getStream();

	void setOwnerAccountId(int id);

	int getOwnerTargetId();

	Timestamp getCreatedDate();

	boolean isOwnerAutoPassCensoringSystem();

	boolean isAccepted();

	void setAcceptedByCensorSystem(boolean vl);

	void loadData(ResultSet rs) throws SQLException;

}
