package example;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**************************************************************************************************
 * ファイル：main.java 概要説明：Java 練習為に
 *************************************************************************************************/

public class main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			ArrayList<userInfo> arrayUser = new ArrayList<userInfo>();
			SendEmail senEmail = new SendEmail();
			File fXmlFile = new File(
					"C:/Users/nguyen_van_toan/Downloads/eclipse_ex/pleiades/workspace/example/info/user.xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				if (doc.getDoctype() == null) {
					if (doc.getElementsByTagName("staff") != null) {
						NodeList nList = doc.getElementsByTagName("staff");
						for (int temp = 0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								userInfo user = new userInfo();
								user.setId(eElement.getAttribute("id"));
								user.setFirstName(eElement.getElementsByTagName("firstname").item(0).getTextContent());
								user.setLastName(eElement.getElementsByTagName("lastname").item(0).getTextContent());
								user.setNickName(eElement.getElementsByTagName("nickname").item(0).getTextContent());
								user.setSalary(
										(String) eElement.getElementsByTagName("salary").item(0).getTextContent());
								arrayUser.add(user);
							}else{
								throw new Exception(nNode.getNodeName() + " XML フォマットじゃない");
							}
						}
						csvWrite fileWrite = new csvWrite();
						fileWrite.csv_file_write(arrayUser);
					}
				} else {
					throw new Exception("ファイルのフォマットはXMLフォマットじゃない");
				}
			} else {
				throw new Exception("XMLファイルが存在しません");
			}
			senEmail.SendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("プログラムを実行完了しました");
		}
	}

}
