package login;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.json.JSONObject;

/**
 * loading:启动入口并读取数据
 * 
 * @author 蜂鸟Swift
 *
 */
public class loading extends Application {
	private static Stage loadSplash;

	public void start(Stage primaryStage) {
		// 核心界面部分
		loadSplash = new Stage();
		loadSplash.setAlwaysOnTop(true);
		loadSplash.initStyle(StageStyle.TRANSPARENT);
		loadSplash.initModality(Modality.APPLICATION_MODAL);

		// 读取数据资源
		fileReader();

		VBox root = new VBox();
		root.setFillWidth(true);
		root.setBackground(Background.EMPTY);
		root.setAlignment(Pos.BASELINE_CENTER);
		Image img = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.splash_GIF));
		ImageView imgV = new ImageView(img);

		root.getChildren().addAll(imgV);
		Scene scene = new Scene(root);
		loadSplash.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		loadSplash.setScene(scene);
		loadSplash.setTitle("蜂鸟图书馆");
		loadSplash.show();

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(3000);
				if (loadSplash.isShowing()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							loadSplash.close();
							new login().start(null);
						}
					});
				}
			} catch (Exception exp) {
				new utils.systemError("PLCLMSFC001").start(null);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadSplash.close();
					}
				});
			}
		});
		thread.start();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	/** 必备数据文件读取 */
	private static void fileReader() {
		// 读取管理员数据
		java.io.File loginMa = new java.io.File(info.impPath.master_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(loginMa));
			Scanner input = new Scanner(inputReader);
			String jsonAES = input.nextLine();
			input.close();
			String jsonStr = utils.AESCode.getOrgWord(jsonAES);
			@SuppressWarnings("unchecked")
			Map<String, String> map = JSONObject.fromObject(jsonStr);
			info.dataBase.adminName = map.get("name");
			info.dataBase.adminPw = map.get("password");
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR002").start(null);
			loadSplash.close();
		}

		// 读取用户信息数据
		java.io.File loginInfo = new java.io.File(info.impPath.user_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(loginInfo));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				info.dataBase.name.add(map.get("name"));
				info.dataBase.pw.add(map.get("password"));
				info.dataBase.trueName.add(map.get("trueName"));
				if (map.get("valid").equals("true")) {
					info.dataBase.valid.add(true);
				} else {
					info.dataBase.valid.add(false);
				}
				info.dataBase.phone.add(map.get("phone"));
				int creditIn = Integer.parseInt(map.get("credit"));
				info.dataBase.credit.add(creditIn);
				int year = Integer.parseInt(map.get("year"));
				info.dataBase.regYear.add(year);
				int month = Integer.parseInt(map.get("month"));
				info.dataBase.regMonth.add(month);
				int day = Integer.parseInt(map.get("day"));
				info.dataBase.regDay.add(day);
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR003").start(null);
			loadSplash.close();
		}

		// 图书信息读取
		java.io.File bookInfo = new java.io.File(info.impPath.book_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(bookInfo));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				info.dataBase.bookName.add(map.get("name"));
				info.dataBase.bookAuthor.add(map.get("author"));
				info.dataBase.bookPress.add(map.get("press"));
				info.dataBase.bookISBN.add(map.get("ISBN"));
				info.dataBase.bookClass.add(map.get("Class"));
				info.dataBase.bookCPos.add(map.get("position"));
				int popular = Integer.parseInt(map.get("popular"));
				info.dataBase.bookPopular.add(popular);
				int left = Integer.parseInt(map.get("left"));
				info.dataBase.bookLeft.add(left);
				int total = Integer.parseInt(map.get("total"));
				info.dataBase.bookTotal.add(total);
				double price = Double.parseDouble(map.get("price"));
				info.dataBase.bookPrice.add(price);
				if (map.get("file").equals("true")) {
					info.dataBase.fileValid.add(true);
				} else {
					info.dataBase.fileValid.add(false);
				}
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR004").start(null);
			loadSplash.close();
		}

		// 分类目录读取
		java.io.File classList = new java.io.File(info.impPath.book_class_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(classList));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				info.dataBase.className.add(jsonStr);
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR005").start(null);
			loadSplash.close();
		}

	}
}
