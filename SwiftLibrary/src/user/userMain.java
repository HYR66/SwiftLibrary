package user;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * userMain:用户主界面
 * 
 * @author 蜂鸟Swift
 *
 */
public class userMain extends Application {
	Stage userStage;
	int index;

	public userMain(int index) {
		this.index = index;
	}

	public void start(Stage primaryStage) {
		userStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		userStage.setX(primaryScreenBounds.getMinX());
		userStage.setY(primaryScreenBounds.getMinY());
		userStage.setWidth(primaryScreenBounds.getWidth());
		userStage.setHeight(primaryScreenBounds.getHeight());

		BorderPane userPane = new BorderPane();
		userPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		BorderPane toolBarP = new BorderPane();
		toolBarP.setPadding(new Insets(5, 5, 5, 5));
		toolBarP.setPrefWidth(primaryScreenBounds.getWidth());
		toolBarP.setPrefHeight(15);
		toolBarP.setStyle("-fx-background-color: #E0FFFF;-fx-opacity: 0.5;");
		userPane.setTop(toolBarP);

		Label stageTitleL = new Label("蜂鸟图书馆");
		toolBarP.setCenter(stageTitleL);

		Button closeToolBt = new Button("X");
		closeToolBt.setTextFill(Color.WHITE);
		closeToolBt.setStyle("-fx-background-color: #C71585;-fx-opacity: 1;");
		DropShadow shadowCloseBt = new DropShadow();
		closeToolBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			closeToolBt.setEffect(shadowCloseBt);
		});
		closeToolBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			closeToolBt.setEffect(null);
		});
		toolBarP.setRight(closeToolBt);

		HBox mainPane = new HBox();
		mainPane.setPadding(new Insets(30, 30, 30, 30));
		userPane.setCenter(mainPane);

		VBox naviPane = new VBox(5);
		Button homeBt = new Button("主页");
		Button seaBt = new Button("查找图书");
		Button myBt = new Button("我的图书馆");
		Button infoBt = new Button("公告");
		Button changeBt = new Button("修改密码");
		naviPane.getChildren().addAll(homeBt, seaBt, myBt, infoBt, changeBt);

		DropShadow shadowL = new DropShadow();
		shadowL.setColor(Color.AQUA);
		homeBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		homeBt.setTextFill(Color.WHITE);
		homeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		homeBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		seaBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		seaBt.setTextFill(Color.WHITE);
		seaBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		seaBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		seaBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			seaBt.setEffect(shadowL);
		});
		seaBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			seaBt.setEffect(null);
		});
		myBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		myBt.setTextFill(Color.WHITE);
		myBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		myBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		myBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			myBt.setEffect(shadowL);
		});
		myBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			myBt.setEffect(null);
		});
		changeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		changeBt.setTextFill(Color.WHITE);
		changeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		changeBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		changeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			changeBt.setEffect(shadowL);
		});
		changeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			changeBt.setEffect(null);
		});
		infoBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		infoBt.setTextFill(Color.WHITE);
		infoBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		infoBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		infoBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			infoBt.setEffect(shadowL);
		});
		infoBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			infoBt.setEffect(null);
		});

		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(20, 20, 20, 20));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setStyle("-fx-background-color: WHITE;-fx-opacity: 0.5;");
		contentPane.setPrefWidth(primaryScreenBounds.getWidth() / 1.2);
		Label homeL = new Label("蜂鸟图书馆");
		homeL.setStyle("-fx-font-size:30;");
		Label homeTL = new Label(
				"当前共有 " + info.dataBase.name.size() + " 位注册用户！ 馆藏书籍 " + info.dataBase.bookName.size() + " 类！");
		homeTL.setStyle("-fx-font-size:15;");
		contentPane.add(homeL, 1, 1);
		contentPane.add(homeTL, 1, 2);

		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// 导航栏事件响应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(userStage);
				obj.start(null);
			}
		});

		seaBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new searchUI(index).start(null);
			}
		});

		myBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new superMy(index).start(null);
			}
		});

		infoBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new announceUI(index).start(null);
			}
		});

		changeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new changePw(index).start(null);
			}
		});

		// 导航栏事件响应

		Scene scene = new Scene(userPane);
		userStage.setScene(scene);
		userStage.setResizable(false);
		userStage.setTitle("蜂鸟图书馆");
		userStage.initStyle(StageStyle.TRANSPARENT);
		userStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		userStage.show();

		toolBarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					new common.aboutMe().start(null);
				}
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
