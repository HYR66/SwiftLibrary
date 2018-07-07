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
 * examInfo:告知申请人测评结果
 * 
 * @author 蜂鸟Swift
 *
 */
public class examInfo extends Application {
	private boolean pass;
	private int score;
	private Stage lastStage;

	examInfo() {

	}

	examInfo(boolean pass, int score, Stage lastStage) {
		this.pass = pass;
		this.score = score;
		this.lastStage = lastStage;
	}

	public void start(Stage primaryStage) {
		// 界面总成
		Stage RegSStage = new Stage();
		RegSStage.initStyle(StageStyle.UNDECORATED);
		RegSStage.initStyle(StageStyle.TRANSPARENT);
		RegSStage.initModality(Modality.APPLICATION_MODAL);

		// 一级界面管理器
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-background-color: ALICEBLUE;");
		hBox.setPadding(new Insets(12, 12, 12, 12));
		hBox.setSpacing(10);
		hBox.setBackground(Background.EMPTY);

		// 对话框图标
		Image imgA = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG));
		Image imgB = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG));
		ImageView imgVA = new ImageView(imgA);
		ImageView imgVB = new ImageView(imgB);

		imgVA.setFitHeight(100);
		imgVA.setFitWidth(100);

		imgVB.setFitHeight(100);
		imgVB.setFitWidth(100);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		// 右侧提示信息
		Label infoR = new Label("您的测评得分为：\t" + score);
		Label moreR = new Label();
		if (pass) {
			moreR.setText("请继续以进行注册吧~");
		} else {
			moreR.setText("未通过测评，请返回");
		}

		// 确认按钮及其个性化定制
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
				lastStage.close();
				if (pass) {
					new register().start(null);
				} else {
					new login().start(null);
				}
			}
		});

		// 界面总成
		vbox.getChildren().addAll(infoR, moreR, okBt);
		if (pass) {
			hBox.getChildren().add(imgVA);
		} else {
			hBox.getChildren().add(imgVB);
		}
		hBox.getChildren().add(vbox);

		Scene scene = new Scene(hBox);
		if (pass) {
			RegSStage.getIcons()
					.add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG)));
		} else {
			RegSStage.getIcons()
					.add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.err_icon_IMG)));
		}

		RegSStage.setScene(scene);
		RegSStage.setTitle("测评结果");
		RegSStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
