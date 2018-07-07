package login;

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
 * regSuccess:注册成功弹窗回馈
 * 
 * @author 蜂鸟Swift
 *
 */
public class regSuccess extends Application {
	private String trueName = "";

	public regSuccess() {

	}

	public regSuccess(String trueName) {
		this.trueName = trueName;
	}

	public void start(Stage primaryStage) {
		// 界面总成
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

		Image img = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG));
		ImageView imgV = new ImageView(img);
		imgV.setFitHeight(100);
		imgV.setFitWidth(100);

		// 右控件内容
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		Label infoR = new Label("您好： " + trueName + "，请牢记您的密码");
		Label timeR = new Label("欢迎您加入蜂鸟图书馆的大家庭");

		// 确认按钮及特效设定
		Button okBt = new Button("确认");
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
				RegSStage.close();
				login obj = new login();
				obj.start(null);
			}
		});

		vbox.getChildren().addAll(infoR, timeR, okBt);
		hBox.getChildren().addAll(imgV, vbox);

		// 总成
		Scene scene = new Scene(hBox);
		RegSStage.getIcons()
				.add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG)));
		RegSStage.setScene(scene);
		RegSStage.setTitle("注册完成");
		RegSStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
