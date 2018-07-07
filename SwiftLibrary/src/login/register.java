package login;

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

/**
 * register:注册信息输入
 * 
 * @author 蜂鸟Swift
 *
 */
public class register extends Application {
	private static Stage regStage;
	private TextField nameIn;
	private PasswordField pwAIn;
	private PasswordField pwBIn;
	private TextField phoneIn;
	private Label infoL;

	public void start(Stage primaryStage) {
		// 总成界面设定
		regStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		regStage.setX(primaryScreenBounds.getMinX());
		regStage.setY(primaryScreenBounds.getMinY());
		regStage.setWidth(primaryScreenBounds.getWidth());
		regStage.setHeight(primaryScreenBounds.getHeight());

		// 后置堆叠层面板
		StackPane regPane = new StackPane();
		regPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		// 一级主面板控件
		HBox mainPane = new HBox();
		mainPane.setMaxWidth(primaryScreenBounds.getWidth() / 1.5);
		mainPane.setMaxHeight(primaryScreenBounds.getHeight() * 0.7);
		mainPane.setStyle("-fx-background-radius:15px;-fx-opacity: 0.8;-fx-background-color: WHITE;");

		// 左书页面板一级控件
		StackPane leftPane = new StackPane();
		leftPane.setPrefWidth(primaryScreenBounds.getWidth() / 3);
		leftPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.7);
		leftPane.setStyle("-fx-background-radius:15px 0px 0px 15px;-fx-background-color: #46A3FF;");
		DropShadow shadow = new DropShadow();
		leftPane.setEffect(shadow);

		VBox leftP = new VBox(10);
		leftP.setAlignment(Pos.CENTER);
		Label titleL = new Label();
		titleL.setText("蜂鸟图书馆");
		titleL.setTextFill(Color.WHITE);
		titleL.setStyle("-fx-font-size:35;");
		Label authorL = new Label("雕刻时光 · 惟精惟一");
		authorL.setTextFill(Color.WHITE);
		authorL.setStyle("-fx-font-size:16;");
		leftP.getChildren().addAll(titleL, authorL);
		leftPane.getChildren().addAll(leftP);

		// 右书页面板一级控件
		VBox rightPane = new VBox(15);
		rightPane.setPadding(new Insets(40, 40, 40, 40));
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setPrefWidth(primaryScreenBounds.getWidth() / 3);
		rightPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.7);

		HBox RtitleP = new HBox(15);
		Label RtitleL = new Label("注册");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label nameL = new Label("学号\t:");
		nameL.setStyle("-fx-font-size:15;");
		Label pwAL = new Label("密码\t:");
		pwAL.setStyle("-fx-font-size:15;");
		Label pwBL = new Label("再次密码\t:");
		pwBL.setStyle("-fx-font-size:15;");
		Label phoneL = new Label("手机号\t:");
		phoneL.setStyle("-fx-font-size:15;");
		nameIn = new TextField();
		pwAIn = new PasswordField();
		pwBIn = new PasswordField();
		phoneIn = new TextField();
		inputP.add(nameL, 0, 0);
		inputP.add(nameIn, 1, 0);
		inputP.add(pwAL, 0, 1);
		inputP.add(pwAIn, 1, 1);
		inputP.add(pwBL, 0, 2);
		inputP.add(pwBIn, 1, 2);
		inputP.add(phoneL, 0, 3);
		inputP.add(phoneIn, 1, 3);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		Button okBt = new Button("注册");
		okBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		okBt.setStyle("-fx-background-color: #E0FFFF;");
		Button backBt = new Button("返回");
		backBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		backBt.setStyle("-fx-background-color:#E0FFFF;");

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
		backBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			backBt.setEffect(shadowR);
		});
		backBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			backBt.setEffect(null);
		});
		backBt.setOnAction(new backHandler());
		buttonP.getChildren().addAll(okBt, backBt);

		// 手机输入框按键相应
		phoneIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && nameIn.getText() != null && !nameIn.getText().equals("")
					&& pwAIn.getText() != null && !pwAIn.getText().equals("") && pwBIn.getText() != null
					&& !pwBIn.getText().equals("") && phoneIn.getText() != null && !phoneIn.getText().equals("");
			if (valid) {
				okHandler enter = new okHandler();
				enter.handle(null);
			}
		});

		// 信息提示块
		GridPane infoP = new GridPane();
		infoL = new Label(" ");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		regPane.getChildren().add(mainPane);

		// 总成控件
		Scene scene = new Scene(regPane);
		regStage.setScene(scene);
		regStage.setResizable(false);
		regStage.setTitle("蜂鸟图书馆·注册");
		regStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		regStage.show();
	}

	// 返回按钮事件响应
	public class backHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			login obj = new login();
			obj.start(null);
			regStage.close();
		}
	}

	// 注册按钮事件响应
	public class okHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			boolean valid = nameIn.getText() == null || nameIn.getText().equals("") || pwAIn.getText() == null
					|| pwBIn.getText().equals("") || pwBIn.getText() == null || pwBIn.getText().equals("")
					|| phoneIn.getText() == null || phoneIn.getText().equals("");
			if (valid) {
				infoL.setText("请输入完整信息");
			} else {
				String nameS = nameIn.getText();
				String pwAS = pwAIn.getText();
				String pwBS = pwBIn.getText();
				String phoneS = phoneIn.getText();
				if (info.dataBase.name.contains(nameS) || info.dataBase.adminName.equals(nameS)) {
					infoL.setText("该学号已注册");
					nameIn.setText("");
					pwAIn.setText("");
					pwBIn.setText("");
					phoneIn.setText("");
				} else if (!pwAS.equals(pwBS)) {
					infoL.setText("两次密码输入不服");
					pwAIn.setText("");
					pwBIn.setText("");
				} else if (pwAS.length() < 6) {
					infoL.setText("密码长度不可短于六位");
					pwAIn.setText("");
					pwBIn.setText("");
				} else if (phoneS.length() != 11) {
					infoL.setText("手机号非法");
				} else {
					new checkNUPT(nameS, pwAS, phoneS).start(null);
					regStage.close();
				}
			}

		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
