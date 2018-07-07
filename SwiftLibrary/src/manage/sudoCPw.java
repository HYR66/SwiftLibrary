package manage;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.sf.json.JSONObject;

/**
 * sudoCPw:ӵ���ռ�Ȩ�޵��޸���
 * 
 * @author ����Swift
 *
 */
public class sudoCPw extends Application {

	public void start(Stage stage) {
		VBox rootLy = new VBox(5);

		TextField cmdTF = new TextField();
		cmdTF.setText("");
		cmdTF.setStyle("-fx-background-color: #000000;-fx-opacity: 0.8;");
		cmdTF.setMinWidth(300);
		Label infoL = new Label("ROOTȨ��");
		infoL.setTextFill(Color.RED);
		rootLy.getChildren().addAll(cmdTF, infoL);

		Scene scene = new Scene(rootLy);
		Stage primaryStage = new Stage();
		primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.back_IMG)));
		primaryStage.setScene(scene);
		primaryStage.setTitle("ROOTȨ��");
		primaryStage.show();

		// �޸������ʽ [root] [password] [old password] [user name] [new password] [new user]
		// �������ֲ��� [root] [safe-test]
		cmdTF.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER
					&& !((cmdTF.getText() == null) && (cmdTF.getText()).equals(""));
			if (valid) {
				String[] word = cmdTF.getText().split(" ");
				if (word.length == 6) {
					if (word[0].equals("root") && word[1].equals("password")) {
						// ����ROOTȨ�޸Ĳ�
						String oldWord = word[2];
						String user = word[3];
						String newWord = word[4];
						String newUser = word[5];
						if (user.equals(info.dataBase.adminName) && oldWord.equals(info.dataBase.adminPw)) {
							info.dataBase.adminName = newUser;
							info.dataBase.adminPw = newWord;
							Map<String, String> params = new HashMap<>();
							params.put("name", newUser);
							params.put("password", newWord);
							JSONObject jsonObject = JSONObject.fromObject(params);
							String jsonStr = jsonObject.toString();
							try {
								PrintWriter write = new PrintWriter(
										new OutputStreamWriter(new FileOutputStream(info.impPath.master_json)));
								write.println(utils.AESCode.getAESWord(jsonStr));
								write.close();
							} catch (Exception ex) {
								new utils.systemError("PMCSMSFC001").start(null);
								primaryStage.close();
							}
						} else {
							infoL.setText("ָ���������");
						}
					} else {
						infoL.setText("ָ�����");
					}
				} else if (word.length == 2) {
					if (word[0].equals("root") && word[1].equals("safe-test")) {
						// �������ֲ���
						primaryStage.close();
						new utils.systemError("PMCSMSFC002").start(null);
					} else {
						infoL.setText("ָ�����");
					}
				} else {
					infoL.setText("ָ�����");
				}
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
