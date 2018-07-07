package utils;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * systemError:系统容灾机制
 * 
 * @author 蜂鸟Swift
 *
 */
public class systemError extends Application {
	private String codeS = "错误代码未获取";

	public systemError() {

	}

	public systemError(String codeS) {
		this.codeS = codeS;
	}

	public void start(Stage primaryStage) {
		Stage EFStage = new Stage();
		EFStage.initStyle(StageStyle.UNDECORATED);
		EFStage.initStyle(StageStyle.TRANSPARENT);
		EFStage.initModality(Modality.APPLICATION_MODAL);

		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-background-color: ALICEBLUE;");
		hBox.setPadding(new Insets(12, 12, 12, 12));
		hBox.setSpacing(10);
		hBox.setBackground(Background.EMPTY);

		Image img = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG));
		ImageView imgV = new ImageView(img);
		imgV.setFitHeight(115);
		imgV.setFitWidth(115);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		Label infoR = new Label(
				"非常抱歉！平台监测到异常情况，并自动进入容灾维护模式\n" + "请轻敲 \"好的\"以关闭平台，如有需要请将下方的错误代码告知运维人员\n\n" + "错误代码：\t" + codeS);
		infoR.setTextFill(Color.RED);

		Button okBt = new Button("好的");
		okBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		DropShadow shadowL = new DropShadow();
		okBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			okBt.setEffect(shadowL);
		});
		okBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			okBt.setEffect(null);
		});
		okBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});

		vbox.getChildren().addAll(infoR, okBt);
		hBox.getChildren().addAll(imgV, vbox);

		Scene scene = new Scene(hBox);
		EFStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG)));
		EFStage.setScene(scene);
		EFStage.setAlwaysOnTop(true);
		EFStage.setTitle("System Error");
		EFStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
