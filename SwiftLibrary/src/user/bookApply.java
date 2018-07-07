package user;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.json.JSONObject;

/**
 * bookApply:图书荐购
 * 
 * @author 蜂鸟Swift
 *
 */
public class bookApply extends Application {
	int nowUser;
	Stage detailStage = new Stage();

	bookApply() {

	}

	bookApply(int nowUser) {
		this.nowUser = nowUser;
	}

	public void start(Stage primaryStage) {
		Stage detailStage = new Stage();
		detailStage.initStyle(StageStyle.UNDECORATED);
		detailStage.initStyle(StageStyle.TRANSPARENT);
		detailStage.initModality(Modality.APPLICATION_MODAL);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		detailStage.setWidth(primaryScreenBounds.getWidth() / 2);
		detailStage.setHeight(primaryScreenBounds.getHeight() / 2);
		VBox vbox = new VBox();
		vbox.setStyle("-fx-background-radius:10px 10px 10px 10px;-fx-background-color: #FAEBD7;");
		vbox.setPadding(new Insets(20, 20, 20, 20));
		vbox.setSpacing(10);

		Label infoL = new Label("图书荐购");
		infoL.setStyle("-fx-font-size:30;");
		infoL.setTextFill(Color.BROWN);

		ScrollPane scPane = new ScrollPane();
		scPane.setPadding(new Insets(20, 20, 20, 20));
		scPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scPane.setStyle("-fx-background:transparent;");

		scPane.getStyleClass().add("edge-to-edge");

		VBox rootPane = new VBox(10);
		GridPane mainPane = new GridPane();
		mainPane.setVgap(10);
		mainPane.setHgap(10);

		Label bookNameTL = new Label("书名\t");
		Label bookAuthorTL = new Label("作者\t");
		Label bookPressTL = new Label("出版社\t");
		Label bookISBNTL = new Label("ISBN\t\t");

		TextField bookNameTF = new TextField();
		TextField bookAuthorTF = new TextField();
		TextField bookPressTF = new TextField();
		TextField bookISBNTF = new TextField();

		bookNameTL.setTextFill(Color.BLACK);
		bookAuthorTL.setTextFill(Color.BLACK);
		bookPressTL.setTextFill(Color.BLACK);
		bookISBNTL.setTextFill(Color.BLACK);

		mainPane.add(bookNameTL, 0, 1);
		mainPane.add(bookAuthorTL, 0, 2);
		mainPane.add(bookPressTL, 0, 3);
		mainPane.add(bookISBNTL, 0, 4);

		mainPane.add(bookNameTF, 1, 1);
		mainPane.add(bookAuthorTF, 1, 2);
		mainPane.add(bookPressTF, 1, 3);
		mainPane.add(bookISBNTF, 1, 4);

		Label errorL = new Label();
		errorL.setTextFill(Color.RED);
		rootPane.getChildren().addAll(mainPane);
		scPane.setContent(rootPane);

		HBox opPane = new HBox(5);
		Button editBt = new Button("提交");
		Button cancelBt = new Button("取消");

		opPane.getChildren().addAll(editBt, cancelBt);

		vbox.getChildren().addAll(infoL, scPane, opPane, errorL);

		// ******************** 事件响应 ********************
		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				boolean notValid = bookNameTF.getText().equals("") || bookNameTF.getText() == null
						|| bookAuthorTF.getText().equals("") || bookAuthorTF.getText() == null
						|| bookPressTF.getText().equals("") || bookPressTF.getText() == null
						|| bookISBNTF.getText().equals("") || bookISBNTF.getText() == null;
				if (notValid) {
					errorL.setText("请输入完整");
				} else {
					try {
						RandomAccessFile randomFile = new RandomAccessFile(info.impPath.applybook_json, "rw");
						long fileLength = randomFile.length();
						randomFile.seek(fileLength);
						Map<String, String> params = new HashMap<>();
						params.put("name", bookNameTF.getText());
						params.put("author", bookAuthorTF.getText());
						params.put("press", bookPressTF.getText());
						params.put("ISBN", bookISBNTF.getText());
						params.put("user", info.dataBase.name.get(nowUser));
						JSONObject jsonObject = JSONObject.fromObject(params);
						String jsonStr = jsonObject.toString();
						randomFile.writeBytes(utils.AESCode.getAESWord(jsonStr) + "\r\n");
						randomFile.close();
					} catch (Exception e) {
						new utils.systemError("PUCBMSFW001").start(null);
						detailStage.close();
					}
					detailStage.close();
				}
			}
		});

		cancelBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				detailStage.close();
			}
		});

		// ******************** 结束事件响应 ********************

		Scene scene = new Scene(vbox);
		scene.setFill(null);
		detailStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		detailStage.setScene(scene);
		detailStage.setTitle("图书荐购");
		detailStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
