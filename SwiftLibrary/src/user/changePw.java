package user;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.sf.json.JSONObject;

/**
 * changePw:通过与南邮正方系统通信进行修改密码
 * 
 * @author 蜂鸟Swift
 *
 */
public class changePw extends Application {
	private static Stage checkStage;
	private Label infoL;
	private Button regBt;
	private Button backBt;
	private PasswordField pwIn;
	private PasswordField newPwIn;
	// 文字轮换动画下标
	private int indexDot = 0;
	private int userIndex = 0;
	private String getTrueName = "";

	public changePw() {

	}

	public changePw(int userIndex) {
		this.userIndex = userIndex;
	}

	public void start(Stage primaryStage) {
		// 总成界面设定
		checkStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		checkStage.setX(primaryScreenBounds.getMinX());
		checkStage.setY(primaryScreenBounds.getMinY());
		checkStage.setWidth(primaryScreenBounds.getWidth());
		checkStage.setHeight(primaryScreenBounds.getHeight());

		// 后置堆叠层面板
		StackPane loginPane = new StackPane();
		loginPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		// 一级主面板控件
		HBox mainPane = new HBox();
		mainPane.setMaxWidth(primaryScreenBounds.getWidth() / 1.6);
		mainPane.setMaxHeight(primaryScreenBounds.getHeight() * 0.6);
		mainPane.setStyle("-fx-background-radius:15px;-fx-opacity: 0.8;-fx-background-color: WHITE;");

		// 左书页面板一级控件
		StackPane leftPane = new StackPane();
		leftPane.setPrefWidth(primaryScreenBounds.getWidth() / 3.2);
		leftPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.6);
		leftPane.setStyle("-fx-background-radius:15px 0px 0px 15px;-fx-background-color: #46A3FF;");
		DropShadow shadow = new DropShadow();
		leftPane.setEffect(shadow);

		// 左书页面板二级控件
		VBox leftP = new VBox(10);
		leftP.setAlignment(Pos.CENTER);
		Label titleL = new Label();
		titleL.setText("蜂鸟图书馆");
		titleL.setTextFill(Color.WHITE);
		titleL.setStyle("-fx-font-size:35;");
		Label authorL = new Label();
		authorL.setText("雕刻时光 · 惟精惟一");
		authorL.setTextFill(Color.WHITE);
		authorL.setStyle("-fx-font-size:16;");
		leftP.getChildren().addAll(titleL, authorL);
		leftPane.getChildren().addAll(leftP);

		// 右书页面板一级控件
		VBox rightPane = new VBox(15);
		rightPane.setPadding(new Insets(40, 40, 40, 40));
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setPrefWidth(primaryScreenBounds.getWidth() / 3.2);
		rightPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.6);

		// 右书面板二级控件
		HBox RtitleP = new HBox(15);
		Label RtitleL = new Label("密码修改");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label idL = new Label("南邮学号\t:");
		idL.setStyle("-fx-font-size:15;");
		Label pwL = new Label("正方密码\t:");
		pwL.setStyle("-fx-font-size:15;");
		Label idIn = new Label(info.dataBase.name.get(userIndex));
		idIn.setStyle("-fx-font-size:15;");
		pwIn = new PasswordField();
		Label newPwL = new Label("设新密码\t:");
		newPwIn = new PasswordField();
		inputP.add(idL, 0, 0);
		inputP.add(idIn, 1, 0);
		inputP.add(pwL, 0, 1);
		inputP.add(pwIn, 1, 1);
		inputP.add(newPwL, 0, 2);
		inputP.add(newPwIn, 1, 2);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		regBt = new Button("密码修改");
		regBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		regBt.setStyle("-fx-background-color:#E0FFFF;");
		backBt = new Button("取消修改");
		backBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		backBt.setStyle("-fx-background-color: #E0FFFF;");

		// 按钮动画特效
		DropShadow shadowR = new DropShadow();
		shadowR.setColor(Color.AQUA);
		regBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			regBt.setEffect(shadowR);
		});
		regBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			regBt.setEffect(null);
		});
		regBt.setOnAction(new regHandler());
		backBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			backBt.setEffect(shadowR);
		});
		backBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			backBt.setEffect(null);
		});
		backBt.setOnAction(new backHandler());
		buttonP.getChildren().addAll(regBt, backBt);

		// 密码输入框按键相应
		newPwIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && !((pwIn.getText() == null) && (pwIn.getText()).equals(""))
					&& !(newPwIn.getText() == null) && !(newPwIn.getText().equals(""))
					&& !((idIn.getText() == null) && !((idIn.getText()).equals("")));
			if (valid) {
				regHandler enter = new regHandler();
				enter.handle(null);
			}
		});

		// 信息提示块
		GridPane infoP = new GridPane();
		infoL = new Label("蜂鸟承诺不会储存您的敏感信息");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		// 总成控件
		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		loginPane.getChildren().add(mainPane);

		Scene scene = new Scene(loginPane);
		checkStage.setScene(scene);
		checkStage.setResizable(false);
		checkStage.setTitle("蜂鸟图书馆·密码修改");
		checkStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		checkStage.show();
	}

	// 返回按钮功能事件响应
	public class backHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			new userMain(userIndex).start(null);
			checkStage.close();
		}
	}

	// 注册按钮功能事件响应
	public class regHandler implements EventHandler<ActionEvent> {
		@SuppressWarnings("deprecation")
		public void handle(ActionEvent e) {
			String userPw = pwIn.getText();
			String newPw = newPwIn.getText();
			if (userPw == null || userPw.equals("") || newPw == null || newPw.equals("")) {
				infoL.setText("请填写完整");
				return;
			}

			if (newPw.length() < 6) {
				infoL.setText("密码长度过短");
				return;
			}

			infoL.setText("请稍后，正在确认中 ");
			pwIn.setDisable(true);
			newPwIn.setDisable(true);
			regBt.setDisable(true);
			backBt.setDisable(true);

			// 文字变动动画
			Thread thread = new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(300);
						indexDot++;
						if (indexDot == 4) {
							indexDot = 0;
						}

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								if (indexDot == 0) {
									infoL.setText("请稍后，正在确认中 ");
								} else if (indexDot == 1) {
									infoL.setText("请稍后，正在确认中 >");
								} else if (indexDot == 2) {
									infoL.setText("请稍后，正在确认中 >>");
								} else {
									infoL.setText("请稍后，正在确认中 >>>");
								}
							}
						});
					} catch (InterruptedException e1) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								new utils.systemError("PLCCMBFC005").start(null);
								checkStage.close();
							}
						});
					}
				}
			});
			thread.start();

			// 联网状态验证
			new Thread(() -> {
				boolean okHTTP = true;
				Callable<String> callable = new Callable<String>() {
					@Override
					public String call() throws Exception {
						URL url = new URL("http://jwxt.njupt.edu.cn");
						InputStream in = url.openStream();
						in.close();
						return "0";
					}
				};
				ExecutorService es = Executors.newFixedThreadPool(1);
				Future<String> future = es.submit(callable);
				try {
					future.get(2000, TimeUnit.MILLISECONDS).toString();
				} catch (ExecutionException ex) {
					okHTTP = false;
				} catch (InterruptedException ex) {
					okHTTP = false;
				} catch (TimeoutException ex) {
					okHTTP = false;
				}

				if (!okHTTP) {
					thread.stop();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							infoL.setText("网络异常，请再次尝试");
							pwIn.setDisable(false);
							newPwIn.setDisable(false);
							regBt.setDisable(false);
							backBt.setDisable(false);
						}
					});
				} else {
					// 调用工具包获取状态
					try {
						getTrueName = utils.connectNUPT.getName(info.dataBase.name.get(userIndex), userPw);
					} catch (Exception ex) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								new utils.systemError("PLCCMBFC006").start(null);
								checkStage.close();
							}
						});
					}

					thread.stop();

					// 获得结果
					if (getTrueName != null && !getTrueName.equals(info.dataBase.trueName.get(userIndex))) {
						// 验证通过
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								infoL.setText("验证通过，写入新信息中");
								info.dataBase.pw.set(userIndex, newPw);
								writeInfo();
								new login.login().start(null);
								checkStage.close();
							}
						});
					} else {
						// 验证失败
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								infoL.setText("验证失败");
								pwIn.setDisable(false);
								newPwIn.setDisable(false);
								regBt.setDisable(false);
								backBt.setDisable(false);
							}
						});
					}
				}
			}).start();
		}

		// 写入注册信息
		protected void writeInfo() {
			// 生成JSON数据
			try {
				PrintWriter write = new PrintWriter(
						new OutputStreamWriter(new FileOutputStream(info.impPath.user_json)));
				for (int i = 0; i < info.dataBase.name.size(); i++) {
					Map<String, String> params = new HashMap<>();
					params.put("name", info.dataBase.name.get(i));
					params.put("password", info.dataBase.pw.get(i));
					params.put("trueName", info.dataBase.trueName.get(i));
					params.put("valid", info.dataBase.valid.get(i).toString());
					params.put("phone", info.dataBase.phone.get(i));
					params.put("credit", info.dataBase.credit.get(i) + "");
					params.put("year", info.dataBase.regYear.get(i) + "");
					params.put("month", info.dataBase.regMonth.get(i) + "");
					params.put("day", info.dataBase.regDay.get(i) + "");
					JSONObject jsonObject = JSONObject.fromObject(params);
					String jsonStr = jsonObject.toString();
					write.println(utils.AESCode.getAESWord(jsonStr));
				}
				write.close();
			} catch (Exception error) {
				new utils.systemError("PMCUMSFR001").start(null);
				checkStage.close();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
