package t4novel.azurewebsites.net.censoring;

import java.sql.Timestamp;

public interface CensorEntity {

	int getCensorId();

	int getOwnerId();

	String getTitle();

	String getContent();

	String getStream();

	Timestamp getCreatedDate();

	boolean isOwnerAutoPassCensoringSystem();
}
