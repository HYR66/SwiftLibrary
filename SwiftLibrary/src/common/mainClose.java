package common;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.login;

/**
 * mainClose:复写关闭按钮功能导航
 * 
 * @author 蜂鸟Swift
 *
 */
public class mainClose extends Application {
	Stage lastStage = new Stage();

	mainClose() {

	}

	public mainClose(Stage lastStage) {
		this.lastStage = lastStage;
	}

	public void start(Stage primaryStage) {
		Stage closeStage = new Stage();
		closeStage.initStyle(StageStyle.UNDECORATED);
		closeStage.initStyle(StageStyle.TRANSPARENT);
		closeStage.initModality(Modality.APPLICATION_MODAL);
		VBox vbox = new VBox();
		vbox.setStyle("-fx-background-radius:10px 10px 10px 10px;-fx-background-color: #00FFFF;");
		vbox.setPadding(new Insets(20, 20, 20, 20));
		vbox.setSpacing(10);

		Label infoL = new Label("接下来 ?");
		infoL.setStyle("-fx-font-size:30;");
		infoL.setTextFill(Color.BROWN);
		GridPane btP = new GridPane();
		btP.setAlignment(Pos.CENTER);
		btP.setHgap(20);

		VBox closeP = new VBox(5);
		closeP.setAlignment(Pos.CENTER);
		Button closeBt = new Button();
		closeBt.setPrefSize(100, 100);
		closeBt.setStyle("-fx-background-image:url(" + info.impPath.exit_IMG + ");-fx-background-size: 100px;");
		Label closeL = new Label("退出平台");
		closeL.setTextFill(Color.BLUEVIOLET);
		closeP.getChildren().addAll(closeBt, closeL);
		DropShadow shadowL = new DropShadow();
		closeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			closeBt.setEffect(shadowL);
		});
		closeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			closeBt.setEffect(null);
		});
		btP.add(closeP, 0, 0);

		VBox exitP = new VBox(10);
		exitP.setAlignment(Pos.CENTER);
		Button exitBt = new Button();
		exitBt.setPrefSize(100, 100);
		exitBt.setStyle("-fx-background-image:url(" + info.impPath.login_out_IMG + ");-fx-background-size: 100px;");
		Label exitL = new Label("注销账户");
		exitL.setTextFill(Color.BLUEVIOLET);
		exitP.getChildren().addAll(exitBt, exitL);
		exitBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			exitBt.setEffect(shadowL);
		});
		exitBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			exitBt.setEffect(null);
		});
		btP.add(exitP, 1, 0);

		VBox homeP = new VBox(10);
		homeP.setAlignment(Pos.CENTER);
		Button homeBt = new Button();
		homeBt.setPrefSize(100, 100);
		homeBt.setStyle("-fx-background-image:url(" + info.impPath.back_IMG + ");-fx-background-size: 100px;");
		Label homeL = new Label("取消返回");
		homeL.setTextFill(Color.BLUEVIOLET);
		homeP.getChildren().addAll(homeBt, homeL);
		homeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			homeBt.setEffect(shadowL);
		});
		homeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			homeBt.setEffect(null);
		});
		btP.add(homeP, 2, 0);

		closeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		exitBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				closeStage.close();
				lastStage.close();
				login obj = new login();
				obj.start(null);
			}
		});

		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				closeStage.close();
			}
		});

		vbox.getChildren().addAll(infoL, btP);
		Scene scene = new Scene(vbox);
		scene.setFill(null);
		closeStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.exit_IMG)));
		closeStage.setScene(scene);
		closeStage.setTitle("系统/账户");
		closeStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
