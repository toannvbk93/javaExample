package example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class csvWrite extends userInfo {
	public void csv_file_write(ArrayList<userInfo> arrayUsers) throws Exception{

		try {
			// 出力先を作成する
			File file = new File(
					"C:/Users/nguyen_van_toan/Downloads/eclipse_ex/pleiades/workspace/example/info/test.csv");
			if (file.exists()) {
				FileWriter fw = new FileWriter(
						"C:/Users/nguyen_van_toan/Downloads/eclipse_ex/pleiades/workspace/example/info/test.csv",
						false);
				PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
				// 内容を指定する
				pw.print("ID, First name, Last name, Nickname, Salary");
				pw.println("");
				for (userInfo user : arrayUsers) {
					pw.print(user.getId() + "," + user.getFirstName() + "," + user.getLastName() + ","
							+ user.getNickName() + "," + user.getSalary());
					pw.println("");
				}
				// ファイルに書き出す
				pw.close();
			} else {
				throw new Exception("CSV ファイルが存在しません");
			}
		} catch (IOException ex) {
			// 例外時処理
			ex.printStackTrace();
		}
	}
}
