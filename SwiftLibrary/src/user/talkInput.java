package user;

import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
 * talkInput:评论
 * 
 * @author 蜂鸟Swift
 *
 */
public class talkInput extends Application {
	int nowDoing;

	talkInput() {

	}

	talkInput(int nowDoing) {
		this.nowDoing = nowDoing;
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

		Image img = new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG));
		ImageView imgV = new ImageView(img);
		imgV.setFitHeight(100);
		imgV.setFitWidth(100);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		Label infoR = new Label("请输入您对这本书评价");
		TextField timeR = new TextField();

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
			public void handle(ActionEvent event) {
				if (timeR.getText().equals("")) {

				} else {
					try {
						RandomAccessFile randomFile = new RandomAccessFile(
								info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\talk", "rw");
						long fileLength = randomFile.length();
						randomFile.seek(fileLength);
						randomFile.writeBytes(utils.AESCode.getAESWord(timeR.getText()) + "\r\n");
						randomFile.close();
					} catch (Exception e) {
						new utils.systemError("PUCTMSFW001").start(null);
						;
						RegSStage.close();
					}
				}
				RegSStage.close();
			}
		});

		vbox.getChildren().addAll(infoR, timeR, okBt);
		hBox.getChildren().addAll(imgV, vbox);

		Scene scene = new Scene(hBox);
		RegSStage.getIcons()
				.add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.pass_icon_IMG)));
		RegSStage.setScene(scene);
		RegSStage.setTitle("书评");
		RegSStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
