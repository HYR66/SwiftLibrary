package manage;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * needMoney:超期需要赔偿金
 * 
 * @author 蜂鸟Swift
 *
 */
public class needMoney extends Application {
	double pay = 0;

	public needMoney() {

	}

	public needMoney(double pay) {
		this.pay = pay;
	}

	public void start(Stage primaryStage) {
		Stage RegSStage = new Stage();
		RegSStage.initStyle(StageStyle.UNDECORATED);
		RegSStage.initStyle(StageStyle.TRANSPARENT);
		RegSStage.initModality(Modality.APPLICATION_MODAL);

		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-background-color: ALICEBLUE;");
		hBox.setPadding(new Insets(12, 12, 12, 12));
		hBox.setSpacing(10);
		hBox.setBackground(Background.EMPTY);

		Image img = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG));
		ImageView imgV = new ImageView(img);
		imgV.setFitHeight(100);
		imgV.setFitWidth(100);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		Label infoR = new Label("该用户需要支付违约金：" + pay + "元");
		Label moreR = new Label("请管理员代为收取");

		Button okBt = new Button("完成");
		okBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		DropShadow shadowL = new DropShadow();
		okBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			okBt.setEffect(shadowL);
		});
		okBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			okBt.setEffect(null);
		});
		okBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		vbox.getChildren().addAll(infoR, moreR, okBt);
		hBox.getChildren().addAll(imgV, vbox);

		Scene scene = new Scene(hBox);
		RegSStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG)));
		RegSStage.setScene(scene);
		RegSStage.setTitle("违约金");
		RegSStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
