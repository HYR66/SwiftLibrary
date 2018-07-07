package common;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * aboutMe:关于页面
 * 
 * @author 蜂鸟Swift
 *
 */
public class aboutMe extends Application {

	public void start(Stage primaryStage) {
		Stage aboutStage = new Stage();
		aboutStage.initStyle(StageStyle.UNDECORATED);
		aboutStage.initStyle(StageStyle.TRANSPARENT);
		aboutStage.initModality(Modality.APPLICATION_MODAL);

		VBox hBox = new VBox(10);
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setPrefWidth(960);
		hBox.setPrefHeight(540);
		hBox.setStyle("-fx-background-image:url(" + info.impPath.about_IMG + ");-fx-background-size:cover;");
		hBox.setPadding(new Insets(50, 50, 50, 50));
		hBox.setSpacing(10);
		hBox.setBackground(Background.EMPTY);

		Label titleL = new Label("蜂鸟图书馆解决方案");
		titleL.setTextFill(Color.WHITE);
		titleL.setStyle("-fx-font-size:30px");
		Label authorL = new Label("Powered By H16000129 周肇星");
		authorL.setTextFill(Color.WHITE);
		authorL.setStyle("-fx-font-size:20px");
		hBox.getChildren().addAll(titleL, authorL);

		Scene scene = new Scene(hBox);
		aboutStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		aboutStage.setScene(scene);
		aboutStage.setTitle("关于我们");
		aboutStage.show();

		hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 1) {
					aboutStage.close();
				}
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
