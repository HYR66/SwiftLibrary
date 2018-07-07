package login;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
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
import javafx.scene.control.TextField;
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
 * login:登录界面
 * 
 * @author 蜂鸟Swift
 *
 */
public class login extends Application {
	private static Stage loginStage;
	private Label infoL;
	private TextField idIn;
	private PasswordField pwIn;

	public void start(Stage primaryStage) {
		// 总成界面设定
		loginStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		loginStage.setX(primaryScreenBounds.getMinX());
		loginStage.setY(primaryScreenBounds.getMinY());
		loginStage.setWidth(primaryScreenBounds.getWidth());
		loginStage.setHeight(primaryScreenBounds.getHeight());

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
		Label RtitleL = new Label("登录");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label idL = new Label("账户\t:");
		idL.setStyle("-fx-font-size:15;");
		Label pwL = new Label("密码\t:");
		pwL.setStyle("-fx-font-size:15;");
		idIn = new TextField();
		pwIn = new PasswordField();
		inputP.add(idL, 0, 0);
		inputP.add(idIn, 1, 0);
		inputP.add(pwL, 0, 1);
		inputP.add(pwIn, 1, 1);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		Button okBt = new Button("登录");
		okBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		okBt.setStyle("-fx-background-color: #E0FFFF;");
		Button regBt = new Button("注册");
		regBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		regBt.setStyle("-fx-background-color:#E0FFFF;");

		// 按钮动画特效
		DropShadow shadowR = new DropShadow();
		shadowR.setColor(Color.AQUA);
		okBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			okBt.setEffect(shadowR);
		});
		okBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			okBt.setEffect(null);
		});
		okBt.setOnAction(new okHandler());
		regBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			regBt.setEffect(shadowR);
		});
		regBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			regBt.setEffect(null);
		});
		regBt.setOnAction(new regHandler());
		buttonP.getChildren().addAll(okBt, regBt);

		// 密码输入框按键相应
		pwIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && !((pwIn.getText() == null) && (pwIn.getText()).equals(""))
					&& !((idIn.getText() == null) && !((idIn.getText()).equals("")));
			if (valid) {
				okHandler enter = new okHandler();
				enter.handle(null);
			}
		});

		// 信息提示块
		GridPane infoP = new GridPane();
		infoL = new Label("");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		// 总成控件
		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		loginPane.getChildren().add(mainPane);

		Scene scene = new Scene(loginPane);
		loginStage.setScene(scene);
		loginStage.setResizable(false);
		loginStage.setTitle("蜂鸟图书馆·登录");
		loginStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		loginStage.show();
	}

	// 注册按钮功能事件响应
	public class regHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			fileReader();
			if (examCheck.question.size() == 0) {
				// 题库为空
				new register().start(null);
				loginStage.close();
			} else {
				// 注册前进行资格测定
				new examCheck().start(null);
				loginStage.close();
			}
		}
	}

	// 提交按钮功能事件响应
	public class okHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			boolean emptyIn = idIn.getText().equals("") || idIn.getText() == null || pwIn.getText().equals("")
					|| pwIn.getText() == null;
			if (emptyIn) {
				infoL.setText("请输入完整");
			} else {
				if (idIn.getText().equals(info.dataBase.adminName) && pwIn.getText().equals(info.dataBase.adminPw)) {
					new manage.adminMain().start(null);
					loginStage.close();
				} else {
					String idS = idIn.getText().toUpperCase();
					String pwS = pwIn.getText();
					if (info.dataBase.name.contains(idS)) {
						int index = info.dataBase.name.indexOf(idS);
						if (info.dataBase.pw.get(index).equals(pwS)) {
							if (info.dataBase.valid.get(index)) {
								user.userMain stuUI = new user.userMain(index);
								stuUI.start(null);
								loginStage.close();
							} else {
								loginFail fail = new loginFail();
								fail.start(null);
								loginStage.close();
							}
						} else {
							infoL.setText("请核验密码");
							pwIn.setText("");
						}
					} else {
						infoL.setText("账户尚未注册");
						idIn.setText("");
						pwIn.setText("");
					}
				}
			}
		}
	}

	// 读取数据资源文件
	private void fileReader() {
		examCheck.question.clear();
		examCheck.choiceA.clear();
		examCheck.choiceB.clear();
		examCheck.choiceC.clear();
		examCheck.choiceD.clear();
		java.io.File examF = new java.io.File(info.impPath.exam_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(examF));
			Scanner input = new Scanner(inputReader);
			examCheck.passScore = utils.AESCode.getOrgWord(input.nextLine());
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				examCheck.question.add(map.get("question"));
				examCheck.choiceA.add(map.get("choiceA"));
				examCheck.choiceB.add(map.get("choiceB"));
				examCheck.choiceC.add(map.get("choiceC"));
				examCheck.choiceD.add(map.get("choiceD"));
				examCheck.key.add(map.get("key"));
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR006").start(null);
			loginStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
