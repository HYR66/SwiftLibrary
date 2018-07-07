package login;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import net.sf.json.JSONObject;

/**
 * splash:首次运行欢迎动画及数据目录检查
 * 
 * @author 蜂鸟Swift
 *
 */
public class splash extends Application {
	// 核心界面组件
	private static Stage splash;
	private Label welcomeL;
	// 文字轮换属性参数
	private int nowIndex = 0;
	private String[] resources = { "Hallo", "Hello", "您 好", "Hola", "こんにちは", "안녕하세요", "dobrý den", "Bonjour", "Hej",
			"Ciao", "Γεια σου" };
	// 颜色轮换属性参数
	private boolean goUp = true;
	private boolean goAlpha = true;
	private int addCol = 0; // Color增值
	private int alpha = 150; // 透明度设置

	public void start(Stage primaryStage) {
		// 以下为UI界面设定
		splash = new Stage();
		splash.setAlwaysOnTop(true);
		splash.initStyle(StageStyle.TRANSPARENT);
		splash.initModality(Modality.APPLICATION_MODAL);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		splash.setX(primaryScreenBounds.getMinX());
		splash.setY(primaryScreenBounds.getMinY());
		splash.setWidth(primaryScreenBounds.getWidth());
		splash.setHeight(primaryScreenBounds.getHeight());

		VBox root = new VBox();
		root.setFillWidth(true);
		root.setBackground(Background.EMPTY);
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color:#33CCFF;");

		welcomeL = new Label(resources[nowIndex]);
		welcomeL.setTextFill(Color.WHITE);
		welcomeL.setStyle("-fx-font-size:80;");

		root.getChildren().addAll(welcomeL);
		Scene scene = new Scene(root);
		splash.setScene(scene);
		splash.setTitle("蜂鸟图书馆");
		splash.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));

		// 监测是否是首次运行
		File rootDic = new File(info.impPath.data_root);
		if (!rootDic.exists()) {
			rootDic.mkdirs();
			splash.show();
			// 设置切换界面定时器
			new Thread(() -> {
				fileWork();
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							new utils.systemError("PLCSMSFN001").start(null);
							splash.close();
						}
					});
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new loading().start(null);
						splash.close();
					}
				});
			}).start();
		} else {
			// 进入正常欢迎界面
			new loading().start(null);
		}

		// 设置文字轮换渐变动画
		FadeTransition ft = new FadeTransition(Duration.millis(1500), welcomeL);
		ft.setFromValue(0.1);
		ft.setToValue(1.0);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);

		FadeTransition ft2 = new FadeTransition(Duration.millis(500), welcomeL);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.1);
		ft2.setCycleCount(Timeline.INDEFINITE);
		ft2.setAutoReverse(true);

		// 文字轮换线程
		new Thread(() -> {
			try {
				while (true) {
					// 当前文字下标设定
					nowIndex++;
					if (nowIndex == resources.length) {
						nowIndex = 0;
					}
					// 更新主进程UI中文字
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							welcomeL.setText(resources[nowIndex]);
							ft.play();
						}
					});
					Thread.sleep(1500);
					ft.stop();
					Thread.sleep(1500);
					ft2.play();
					Thread.sleep(500);
					ft2.stop();
				}
			} catch (InterruptedException e) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new utils.systemError("PLCSMSFC002").start(null);
						splash.close();
					}
				});
			}
		}).start();

		// 颜色轮换线程
		new Thread(() -> {
			try {
				while (true) {
					if (goUp)
						addCol = addCol + 1;
					else
						addCol = addCol - 1;

					if (addCol == 125)
						goUp = false;
					if (addCol == 0)
						goUp = true;

					if (goAlpha)
						alpha = alpha + 1;
					else
						alpha = alpha - 1;

					if (alpha == 255)
						goAlpha = false;
					if (alpha == 100)
						goAlpha = true;

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							root.setStyle("-fx-background-color:rgb(51,204," + (255 - addCol) + ");-fx-opacity: "
									+ (alpha / 255.0) + ";");
						}
					});
					Thread.sleep(42);
				}
			} catch (InterruptedException e) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new utils.systemError("PLCSMSFC003").start(null);
						splash.close();
					}
				});
			}
		}).start();
	}

	// 首次运行创建必要文件资源
	private void fileWork() {
		// 全局根目录新建
		File rootDic = new File(info.impPath.data_root);
		if (!rootDic.exists()) {
			rootDic.mkdirs();
		}

		// 用户资源目录创建
		File userDic = new File(info.impPath.user_root);
		if (!userDic.exists()) {
			userDic.mkdirs();
		}

		// 管理员密码等信息文件创建
		File loginMa = new File(info.impPath.master_json);
		if (!loginMa.exists()) {
			// 生成JSON数据
			Map<String, String> params = new HashMap<>();
			params.put("name", "admin");
			params.put("password", "admin");
			JSONObject jsonObject = JSONObject.fromObject(params);
			String jsonStr = jsonObject.toString();
			try {
				// JSON数据加密
				String jsonAES = utils.AESCode.getAESWord(jsonStr);
				// 写入JSON数据
				loginMa.createNewFile();
				RandomAccessFile randomFile = new RandomAccessFile(info.impPath.master_json, "rw");
				long fileLength = randomFile.length();
				randomFile.seek(fileLength);
				randomFile.writeBytes(jsonAES);
				randomFile.close();
			} catch (Exception e) {
				new utils.systemError("PLCSMFFW004").start(null);
				splash.close();
			}
		}

		// 用户密码等信息文件创建
		File loginInfo = new File(info.impPath.user_json);
		if (!loginInfo.exists()) {
			try {
				loginInfo.createNewFile();
			} catch (IOException e) {
				new utils.systemError("PLCSMFFW005").start(null);
				splash.close();
			}
		}

		// 图书主目录创建
		File bookDic = new File(info.impPath.book_root);
		if (!bookDic.exists()) {
			bookDic.mkdirs();
		}

		// 图书信息文件创建
		File bookInfo = new File(info.impPath.book_json);
		if (!bookInfo.exists()) {
			try {
				bookInfo.createNewFile();
			} catch (IOException e) {
				new utils.systemError("PLCSMFFW006").start(null);
				splash.close();
			}
		}

		// 书库分类文件创建
		File classList = new File(info.impPath.book_class_json);
		if (!classList.exists()) {
			try {
				classList.createNewFile();
			} catch (IOException e) {
				new utils.systemError("PLCSMFFW007").start(null);
				splash.close();
			}
		}

		// 管理主目录创建
		File manageDic = new File(info.impPath.manage_root);
		if (!manageDic.exists()) {
			manageDic.mkdirs();
		}

		// 告知公告文件
		File annInfo = new File(info.impPath.announce_txt);
		if (!annInfo.exists()) {
			try {
				annInfo.createNewFile();
			} catch (IOException e) {
				new utils.systemError("PLCSMFFW008").start(null);
				splash.close();
			}
		}

		// 图书建购文件
		File applyTXT = new File(info.impPath.applybook_json);
		if (!applyTXT.exists()) {
			try {
				applyTXT.createNewFile();
			} catch (IOException e) {
				new utils.systemError("PLCSMFFW009").start(null);
				splash.close();
			}
		}

		// 注册测试试题目录
		File examTXT = new File(info.impPath.exam_json);
		if (!examTXT.exists()) {
			try {
				examTXT.createNewFile();
				RandomAccessFile randomFile = new RandomAccessFile(info.impPath.exam_json, "rw");
				long fileLength = randomFile.length();
				randomFile.seek(fileLength);
				randomFile.writeBytes(utils.AESCode.getAESWord("0") + "\r\n");
				randomFile.close();
			} catch (Exception e) {
				new utils.systemError("PLCSMFFW010").start(null);
				splash.close();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
