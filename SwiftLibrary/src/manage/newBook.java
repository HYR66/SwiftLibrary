package manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
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
 * newBook:新建图书
 * 
 * @author 蜂鸟Swift
 *
 */
public class newBook extends Application {
	Stage detailStage = new Stage();
	TextArea detailInfo;

	newBook() {

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

		Label infoL = new Label("新建书目");
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
		Label bookClassTL = new Label("分类\t");
		Label bookCPosTL = new Label("馆藏地\t");
		Label bookPriceTL = new Label("定价\t");
		Label bookTotalTL = new Label("总库存\t");

		TextField bookNameTF = new TextField();
		TextField bookAuthorTF = new TextField();
		TextField bookPressTF = new TextField();
		TextField bookISBNTF = new TextField();
		ComboBox<String> bookClassTF = new ComboBox<String>();
		for (int i = 0; i < info.dataBase.className.size(); i++) {
			bookClassTF.getItems().add(info.dataBase.className.get(i));
		}
		TextField bookCPosTF = new TextField();
		TextField bookPriceTF = new TextField();
		TextField bookTotalTF = new TextField();

		bookNameTL.setStyle(" -fx-text-fill:#000000;");
		bookAuthorTL.setStyle(" -fx-text-fill:#000000;");
		bookPressTL.setStyle(" -fx-text-fill:#000000;");
		bookISBNTL.setStyle(" -fx-text-fill:#000000;");
		bookClassTL.setStyle(" -fx-text-fill:#000000;");
		bookCPosTL.setStyle(" -fx-text-fill:#000000;");
		bookPriceTL.setStyle(" -fx-text-fill:#000000;");
		bookTotalTL.setStyle(" -fx-text-fill:#000000;");

		mainPane.add(bookNameTL, 0, 1);
		mainPane.add(bookAuthorTL, 0, 2);
		mainPane.add(bookPressTL, 0, 3);
		mainPane.add(bookISBNTL, 0, 4);
		mainPane.add(bookClassTL, 0, 5);
		mainPane.add(bookCPosTL, 0, 6);
		mainPane.add(bookPriceTL, 0, 7);
		mainPane.add(bookTotalTL, 0, 8);

		mainPane.add(bookNameTF, 1, 1);
		mainPane.add(bookAuthorTF, 1, 2);
		mainPane.add(bookPressTF, 1, 3);
		mainPane.add(bookISBNTF, 1, 4);
		mainPane.add(bookClassTF, 1, 5);
		mainPane.add(bookCPosTF, 1, 6);
		mainPane.add(bookPriceTF, 1, 7);
		mainPane.add(bookTotalTF, 1, 8);

		Separator lineA = new Separator();
		lineA.setMinWidth(primaryScreenBounds.getWidth() / 3);
		detailInfo = new TextArea();

		Label errorL = new Label();
		errorL.setTextFill(Color.RED);
		rootPane.getChildren().addAll(mainPane, lineA, detailInfo);
		scPane.setContent(rootPane);

		HBox opPane = new HBox(5);
		Button submitBt = new Button("提交");
		Button cancelBt = new Button("取消");
		opPane.getChildren().addAll(submitBt, cancelBt);

		vbox.getChildren().addAll(infoL, scPane, opPane, errorL);

		// ******************** 按钮事件响应 ********************

		submitBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Boolean bookNameVa = bookNameTF.getText() == null || bookNameTF.getText().equals("");
				Boolean bookAuthorVa = bookAuthorTF.getText() == null || bookAuthorTF.getText().equals("");
				Boolean bookPressVa = bookPressTF.getText() == null || bookPressTF.getText().equals("");
				Boolean bookClassVa = bookClassTF.getSelectionModel().getSelectedItem() == null
						|| bookClassTF.getSelectionModel().getSelectedItem().equals("");
				Boolean bookCPosVa = bookCPosTF.getText() == null || bookCPosTF.getText().equals("");
				Boolean bookISBNVa = bookISBNTF.getText() == null || bookISBNTF.equals("");
				Boolean bookTotalVa = bookTotalTF.getText() == null || bookTotalTF.getText().equals("");
				Boolean bookPriceVa = bookPriceTF.getText() == null || bookPriceTF.getText().equals("");
				Boolean detailInfoVa = detailInfo.getText() == null || detailInfo.getText().equals("");
				if (bookNameVa || bookAuthorVa || bookPressVa || bookClassVa || bookCPosVa || bookTotalVa || bookPriceVa
						|| bookISBNVa || detailInfoVa) {
					errorL.setText("请输入完整信息");
				} else if (info.dataBase.bookISBN.contains(bookISBNTF.getText())) {
					errorL.setText("ISBN号已经存在");
				} else {
					boolean intValid = true;
					for (int i = 0; i < bookTotalTF.getText().length(); i++) {
						if (!Character.isDigit(bookTotalTF.getText().charAt(i))) {
							intValid = false;
							break;
						}
					}
					for (int i = 0; i < bookPriceTF.getText().length(); i++) {
						if (Character.isDigit(bookPriceTF.getText().charAt(i))
								|| bookPriceTF.getText().charAt(i) == '.') {
							// 合法
						} else {
							intValid = false;
							break;
						}
					}
					if (!intValid) {
						errorL.setText("请核验库存及定价");
					} else {
						info.dataBase.bookName.add(bookNameTF.getText());
						info.dataBase.bookAuthor.add(bookAuthorTF.getText());
						info.dataBase.bookPress.add(bookPressTF.getText());
						info.dataBase.bookISBN.add(bookISBNTF.getText());
						info.dataBase.bookClass.add(bookClassTF.getValue().toString());
						info.dataBase.bookCPos.add(bookCPosTF.getText());
						info.dataBase.bookPopular.add(0);
						info.dataBase.bookLeft.add(Integer.parseInt(bookTotalTF.getText()));
						info.dataBase.bookTotal.add(Integer.parseInt(bookTotalTF.getText()));
						info.dataBase.bookPrice.add(Double.parseDouble(bookPriceTF.getText()));
						info.dataBase.fileValid.add(false);
						bookManage.bookData.add(new manageData(bookNameTF.getText(), bookISBNTF.getText(),
								bookClassTF.getValue().toString(), bookAuthorTF.getText(), bookPressTF.getText(),
								Integer.parseInt(bookTotalTF.getText()) + "",
								Integer.parseInt(bookTotalTF.getText()) + "",
								Double.parseDouble(bookPriceTF.getText()) + ""));
						fileWork();
						detailStage.close();
					}
				}
			}
		});

		cancelBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				detailStage.close();
			}
		});
		// ******************** 结束按键事件响应 ********************

		Scene scene = new Scene(vbox);
		scene.setFill(null);
		detailStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		detailStage.setScene(scene);
		detailStage.setTitle("新建书目");
		detailStage.show();
	}

	protected void fileWork() {
		try {
			PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info.impPath.book_json)));
			for (int i = 0; i < info.dataBase.bookName.size(); i++) {
				// 生成JSON数据
				Map<String, String> params = new HashMap<>();
				params.put("name", info.dataBase.bookName.get(i));
				params.put("author", info.dataBase.bookAuthor.get(i));
				params.put("press", info.dataBase.bookPress.get(i));
				params.put("ISBN", info.dataBase.bookISBN.get(i));
				params.put("Class", info.dataBase.bookClass.get(i));
				params.put("position", info.dataBase.bookCPos.get(i));
				params.put("popular", info.dataBase.bookPopular.get(i) + "");
				params.put("left", info.dataBase.bookLeft.get(i) + "");
				params.put("total", info.dataBase.bookTotal.get(i) + "");
				params.put("price", info.dataBase.bookPrice.get(i) + "");
				params.put("file", info.dataBase.fileValid.get(i).toString());
				JSONObject jsonObject = JSONObject.fromObject(params);
				String jsonStr = jsonObject.toString();
				String jsonAES = utils.AESCode.getAESWord(jsonStr);
				write.println(jsonAES);
			}
			write.close();
		} catch (Exception e) {
			new utils.systemError("PMCNMFFW001").start(null);
			detailStage.close();
		}

		File newRoot = new File(
				info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(info.dataBase.bookISBN.size() - 1));
		newRoot.mkdirs();

		File newFile = new File(info.impPath.book_root + "\\"
				+ info.dataBase.bookISBN.get(info.dataBase.bookISBN.size() - 1) + "\\detail");

		File talkFile = new File(info.impPath.book_root + "\\"
				+ info.dataBase.bookISBN.get(info.dataBase.bookISBN.size() - 1) + "\\talk");

		File borrowFile = new File(info.impPath.book_root + "\\"
				+ info.dataBase.bookISBN.get(info.dataBase.bookISBN.size() - 1) + "\\borrow");

		try {
			newFile.createNewFile();
			talkFile.createNewFile();
			borrowFile.createNewFile();
			PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info.impPath.book_root
					+ "\\" + info.dataBase.bookISBN.get(info.dataBase.bookISBN.size() - 1) + "\\detail")));
			Scanner input = new Scanner(detailInfo.getText().trim());
			while (input.hasNextLine()) {
				write.println(utils.AESCode.getAESWord(input.nextLine()));
			}
			input.close();
			write.close();
		} catch (Exception e) {
			new utils.systemError("PMCNMFFW002").start(null);
			detailStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
