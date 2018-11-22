package t4novel.azurewebsites.net.sercurities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SercureURLEngine {
	private static final Map<String, HttpMethodSercure[]> urlSercureEngine = new HashMap<>();

	public static void loadURLPatterns(String fileUrl) {
		File xmlFile = new File(fileUrl);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = dbFactory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc == null) {
			System.out.println("cannot loading url-sercurity Engine then shutting down server");
			System.exit(1);
		}

		loadingMethodSercure(doc, urlSercureEngine);

	}

	private static void loadingMethodSercure(Document doc, Map<String, HttpMethodSercure[]> container) {
		Node rootNode = doc.getFirstChild();
		NodeList urlNodes = ((Element) rootNode).getElementsByTagName("url");
		int maxChilds = urlNodes.getLength();

		// <url>
		for (int i = 0; i < maxChilds; i++) {
			Node current = urlNodes.item(i);
			if (current.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if ("url".equals(current.getNodeName())) {
				String urlPattern = ((Element) current).getAttribute("pattern");
				NodeList methodNodes = ((Element) current).getElementsByTagName("method");
				// <method>
				for (int j = 0; j < methodNodes.getLength(); j++) {
					if (current.getNodeType() != Node.ELEMENT_NODE)
						continue;
					Node currentMethodNode = methodNodes.item(j);
					String methodName = ((Element) currentMethodNode).getAttribute("name");

					Node loginChildNode = getNodeName("login", currentMethodNode);
					Node dbConnectionNeededNode = getNodeName("connection", currentMethodNode);

					boolean loginNeeded = getLoginNeeded(loginChildNode);
					boolean dbConnectionNeeded = getDbConnectionNeeded(dbConnectionNeededNode);

					List<Role> roles = getRolesRequired(currentMethodNode);

					applyUriToAllowedListOfRole(roles, urlPattern);

					HttpMethodSercure httpSercure = new HttpMethodSercure(methodName, loginNeeded, dbConnectionNeeded,
							roles);
					HttpMethodSercure[] methodsOnContainer = container.get(urlPattern);
					if (methodsOnContainer == null) {
						methodsOnContainer = new HttpMethodSercure[methodNodes.getLength()];
						methodsOnContainer[j] = httpSercure;
						container.put(urlPattern, methodsOnContainer);
					} else {
						methodsOnContainer[j] = httpSercure;
					}
				}
			}
		}
	}

	private static Node getNodeName(String name, Node parent) {
		NodeList childs = parent.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node current = childs.item(i);
			if (current.getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (current.getNodeName().equals(name))
				return current;
		}
		return null;
	}

	private static void applyUriToAllowedListOfRole(List<Role> roles, String urlPattern) {
		for (int i = 0; i < roles.size(); i++) {
			Role r = roles.get(i);
			r.addAllowedUri(urlPattern);
		}
	}

	private static List<Role> getRolesRequired(Node parent) {
		NodeList roleNodeList = ((Element) parent).getElementsByTagName("role");
		int maxSize = roleNodeList.getLength();
		List<Role> roles = new ArrayList<>(maxSize);
		int i = 0;
		for (Node n = roleNodeList.item(i); i < maxSize; i++, n = roleNodeList.item(i)) {
			if (n.getNodeType() != Node.ELEMENT_NODE)
				continue;
			roles.add(Role.getRole(Integer.parseInt(getTextContent(n))));
		}
		return roles;
	}

	private static boolean getDbConnectionNeeded(Node current) {
		String textValue = getTextContent(current);
		try {
			return Boolean.parseBoolean(textValue);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	private static boolean getLoginNeeded(Node current) {
		String textValue = getTextContent(current);
		try {
			return Boolean.parseBoolean(textValue);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	private static String getTextContent(Node n) {
		return ((Element) n).getTextContent();
	}

	public static String debugString() {
		String rs = "";
		for (Entry<String, HttpMethodSercure[]> entry : urlSercureEngine.entrySet()) {
			rs += entry.getKey() + "    " + Arrays.toString(entry.getValue()) + "\n\n";
		}
		return rs;
	}

	public static boolean isNeedLoginUrl(String path, String method) {
		HttpMethodSercure[] methodsOfPath = urlSercureEngine.get(path);
		if (methodsOfPath == null)
			return false;
		for (int i = 0; i < methodsOfPath.length; i++) {
			HttpMethodSercure currentMethod = methodsOfPath[i];
			if (method.equalsIgnoreCase(currentMethod.getName())) {
				return currentMethod.isLoginNeeded();
			}
		}
		return false;
	}

	public static boolean isNeedDbConnection(String path, String method) {
		HttpMethodSercure[] methodsOfPath = urlSercureEngine.get(path);
		if (methodsOfPath == null)
			return false;
		for (int i = 0; i < methodsOfPath.length; i++) {
			HttpMethodSercure currentMethod = methodsOfPath[i];
			if (method.equalsIgnoreCase(currentMethod.getName())) {
				return currentMethod.isDbConnectionNeeded();
			}
		}
		return false;
	}

	public static boolean isOnAllowedUrl(Role role, String path) {
		for(String url : role.getAllowedUris()) {
			if(url.equalsIgnoreCase(path))
				return true;
		}
		return false;
	}
}
