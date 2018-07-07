package manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.json.JSONObject;

/**
 * detailBook:编辑书籍信息
 * 
 * @author 蜂鸟Swift
 *
 */
public class detailBook extends Application {
	int nowDoing;
	int index;
	Stage detailStage = new Stage();
	TextArea detailInfo;

	detailBook() {

	}

	detailBook(int nowDoing, int index) {
		this.nowDoing = nowDoing;
		this.index = index;
	}

	public void start(Stage primaryStage) {
		// 界面总成
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

		Label infoL = new Label("编辑");
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
		Label bookAuthorTL = new Label("作者");
		Label bookPressTL = new Label("出版社\t");
		Label bookISBNTL = new Label("ISBN\t\t");
		Label bookClassTL = new Label("分类\t");
		Label bookCPosTL = new Label("馆藏地\t");
		Label bookPopularTL = new Label("热度\t");
		Label bookLeftTL = new Label("余量\t");
		Label bookTotalTL = new Label("总库存\t");
		Label bookPriceTL = new Label("价格\t");
		Label bookFileValidTL = new Label("附件\t");

		TextField bookNameTF = new TextField();
		bookNameTF.setText(info.dataBase.bookName.get(nowDoing));
		TextField bookAuthorTF = new TextField();
		bookAuthorTF.setText(info.dataBase.bookAuthor.get(nowDoing));
		TextField bookPressTF = new TextField();
		bookPressTF.setText(info.dataBase.bookPress.get(nowDoing));
		Label bookISBNTF = new Label();
		bookISBNTF.setText(info.dataBase.bookISBN.get(nowDoing));
		ComboBox<String> bookClassTF = new ComboBox<String>();
		for (int i = 0; i < info.dataBase.className.size(); i++) {
			bookClassTF.getItems().add(info.dataBase.className.get(i));
		}
		int selectIndex = info.dataBase.className.indexOf(info.dataBase.bookClass.get(nowDoing));
		bookClassTF.getSelectionModel().select(selectIndex);
		TextField bookCPosTF = new TextField();
		bookCPosTF.setText(info.dataBase.bookCPos.get(nowDoing));
		Label bookPopularTF = new Label();
		bookPopularTF.setText(info.dataBase.bookPopular.get(nowDoing) + "");
		Label bookLeftTF = new Label();
		int borrowInt = info.dataBase.bookLeft.get(nowDoing);
		bookLeftTF.setText(borrowInt + "");
		TextField bookTotalTF = new TextField();
		bookTotalTF.setText(info.dataBase.bookTotal.get(nowDoing) + "");
		TextField bookPriceTF = new TextField();
		bookPriceTF.setText(info.dataBase.bookPrice.get(nowDoing) + "");
		HBox btPaneL = new HBox(5);
		Button uploadFBt = new Button("上传");
		Button removeFBt = new Button("移除");
		Button downloadFBt = new Button("下载");
		btPaneL.getChildren().addAll(uploadFBt, removeFBt, downloadFBt);

		if (info.dataBase.fileValid.get(nowDoing)) {
			uploadFBt.setText("更新");
		} else {
			removeFBt.setDisable(true);
			downloadFBt.setDisable(true);
		}

		bookNameTL.setStyle(" -fx-text-fill:#000000;");
		bookAuthorTL.setStyle(" -fx-text-fill:#000000;");
		bookPressTL.setStyle(" -fx-text-fill:#000000;");
		bookISBNTL.setStyle(" -fx-text-fill:#000000;");
		bookClassTL.setStyle(" -fx-text-fill:#000000;");
		bookCPosTL.setStyle(" -fx-text-fill:#000000;");
		bookPopularTL.setStyle(" -fx-text-fill:#000000;");
		bookLeftTL.setStyle(" -fx-text-fill:#000000;");
		bookTotalTL.setStyle(" -fx-text-fill:#000000;");
		bookPriceTL.setStyle(" -fx-text-fill:#000000;");
		bookFileValidTL.setStyle(" -fx-text-fill:#000000;");
		bookISBNTF.setStyle(" -fx-text-fill:#000000;");
		bookPopularTF.setStyle(" -fx-text-fill:#000000;");
		bookLeftTF.setStyle(" -fx-text-fill:#000000;");

		mainPane.add(bookNameTL, 0, 1);
		mainPane.add(bookAuthorTL, 0, 2);
		mainPane.add(bookPressTL, 0, 3);
		mainPane.add(bookISBNTL, 0, 4);
		mainPane.add(bookClassTL, 0, 5);
		mainPane.add(bookCPosTL, 0, 6);
		mainPane.add(bookPopularTL, 0, 7);
		mainPane.add(bookLeftTL, 0, 8);
		mainPane.add(bookTotalTL, 0, 9);
		mainPane.add(bookPriceTL, 0, 10);
		mainPane.add(bookFileValidTL, 0, 11);

		mainPane.add(bookNameTF, 1, 1);
		mainPane.add(bookAuthorTF, 1, 2);
		mainPane.add(bookPressTF, 1, 3);
		mainPane.add(bookISBNTF, 1, 4);
		mainPane.add(bookClassTF, 1, 5);
		mainPane.add(bookCPosTF, 1, 6);
		mainPane.add(bookPopularTF, 1, 7);
		mainPane.add(bookLeftTF, 1, 8);
		mainPane.add(bookTotalTF, 1, 9);
		mainPane.add(bookPriceTF, 1, 10);
		mainPane.add(btPaneL, 1, 11);

		Separator lineA = new Separator();
		lineA.setMinWidth(primaryScreenBounds.getWidth() / 3);
		detailInfo = new TextArea();

		String detailInfoS = "";
		java.io.File bookInfo = new java.io.File(
				info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\detail");
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(bookInfo));
			Scanner input = new Scanner(inputReader);
			int i = 0;
			while (input.hasNextLine()) {
				if (i == 0) {
					detailInfoS = detailInfoS + utils.AESCode.getOrgWord(input.nextLine());
				} else {
					detailInfoS = detailInfoS + "\n" + utils.AESCode.getOrgWord(input.nextLine());
				}
				i++;
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PMCDMSFR001").start(null);
			detailStage.close();
		}
		detailInfo.setText(detailInfoS);

		Label errorL = new Label();
		errorL.setTextFill(Color.RED);
		rootPane.getChildren().addAll(mainPane, lineA, detailInfo);
		scPane.setContent(rootPane);

		HBox opPane = new HBox(5);
		Button delBt = new Button("删除书名");
		Button submitBt = new Button("提交更新");
		Button cancelBt = new Button("取消操作");
		opPane.getChildren().addAll(delBt, submitBt, cancelBt);

		vbox.getChildren().addAll(infoL, scPane, opPane, errorL);

		// ******************** 按钮事件相应 ********************
		delBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (info.dataBase.bookLeft != info.dataBase.bookTotal) {
					info.dataBase.bookName.remove(nowDoing);
					info.dataBase.bookAuthor.remove(nowDoing);
					info.dataBase.bookPress.remove(nowDoing);
					info.dataBase.bookISBN.remove(nowDoing);
					info.dataBase.bookClass.remove(nowDoing);
					info.dataBase.bookCPos.remove(nowDoing);
					info.dataBase.bookPopular.remove(nowDoing);
					info.dataBase.bookLeft.remove(nowDoing);
					info.dataBase.bookTotal.remove(nowDoing);
					info.dataBase.fileValid.remove(nowDoing);
					info.dataBase.bookPrice.remove(nowDoing);
					bookManage.bookData.remove(index);
					fileWork();
					detailStage.close();
				} else {
					errorL.setText("存在借出，暂不可删除");
				}
			}
		});

		uploadFBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Stage selectStage = new Stage();
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ZIP File", "*.zip"));
				fileChooser.setTitle("上传附件");
				File file = fileChooser.showOpenDialog(selectStage);
				String pathTo = info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\file.zip";
				File fileDic = new File(pathTo);
				if (file != null) {
					try {
						if (fileDic.exists()) {
							fileDic.delete();
						}
						Files.copy(file.toPath(), fileDic.toPath());
						info.dataBase.fileValid.set(nowDoing, true);
						fileWork();
						errorL.setText("上传成功");
					} catch (IOException e) {
						new utils.systemError("PMCDMSFR002").start(null);
						detailStage.close();
					}
				}
			}
		});

		removeFBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (info.dataBase.fileValid.get(nowDoing)) {
					File appendixF = new File(
							info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\file.zip");
					appendixF.delete();
					errorL.setText("移除成功");
					removeFBt.setDisable(true);
					downloadFBt.setDisable(true);
					info.dataBase.fileValid.set(nowDoing, false);
					fileWork();
				}
			}
		});

		downloadFBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Stage selectStage = new Stage();
				DirectoryChooser directoryChooser = new DirectoryChooser();
				File selectedDirectory = directoryChooser.showDialog(selectStage);
				if (selectedDirectory != null) {
					try {
						String pathDic = selectedDirectory.getCanonicalPath();
						File source = new File(
								info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\file.zip");
						File aim = new File(pathDic + "\\file.zip");
						Files.copy(source.toPath(), aim.toPath());
						errorL.setText("下载完成");
					} catch (IOException e1) {
						new utils.systemError("PMCDMSFR003").start(null);
						detailStage.close();
					}
				} else {
					errorL.setText("请选择路径");
				}
			}
		});

		submitBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Boolean bookNameVa = bookNameTF.getText() == null || bookNameTF.getText().equals("");
				Boolean bookAuthorVa = bookAuthorTF.getText() == null || bookAuthorTF.getText().equals("");
				Boolean bookPressVa = bookPressTF.getText() == null || bookPressTF.getText().equals("");
				Boolean bookClassVa = bookClassTF.getSelectionModel().getSelectedItem() == null
						|| bookClassTF.getSelectionModel().getSelectedItem().equals("");
				Boolean bookCPosVa = bookCPosTF.getText() == null || bookCPosTF.getText().equals("");
				Boolean bookTotalVa = bookTotalTF.getText() == null || bookTotalTF.getText().equals("");
				Boolean bookPriceVa = bookPriceTF.getText() == null || bookPriceTF.getText().equals("");
				Boolean detailInfoVa = detailInfo.getText() == null || detailInfo.getText().equals("");
				if (bookNameVa || bookAuthorVa || bookPressVa || bookClassVa || bookCPosVa || bookTotalVa || bookPriceVa
						|| detailInfoVa) {
					errorL.setText("请输入完整信息");
				} else {
					boolean intValid = true;
					for (int i = 0; i < bookTotalTF.getText().length(); i++) {
						if (!Character.isDigit(bookTotalTF.getText().charAt(i))) {
							intValid = false;
							break;
						}
					}
					if (!intValid) {
						errorL.setText("请检查库存量");
					} else {
						info.dataBase.bookName.set(nowDoing, bookNameTF.getText());
						info.dataBase.bookAuthor.set(nowDoing, bookAuthorTF.getText());
						info.dataBase.bookPress.set(nowDoing, bookPressTF.getText());
						info.dataBase.bookClass.set(nowDoing, bookClassTF.getValue().toString());
						info.dataBase.bookCPos.set(nowDoing, bookCPosTF.getText());
						info.dataBase.bookPrice.set(nowDoing, Double.parseDouble(bookPriceTF.getText()));
						int nowLeft = info.dataBase.bookLeft.get(nowDoing) + Integer.parseInt(bookTotalTF.getText())
								- info.dataBase.bookTotal.get(nowDoing);
						info.dataBase.bookLeft.set(nowDoing, nowLeft);
						info.dataBase.bookTotal.set(nowDoing, Integer.parseInt(bookTotalTF.getText()));
						bookManage.bookData.set(index,
								new manageData(bookNameTF.getText(), info.dataBase.bookISBN.get(nowDoing),
										bookClassTF.getValue().toString(), bookAuthorTF.getText(),
										bookPressTF.getText(), nowLeft + "",
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
				new bookReader().start(null);
			}
		});
		// ******************** 按钮事件相应 ********************

		Scene scene = new Scene(vbox);
		scene.setFill(null);
		detailStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		detailStage.setScene(scene);
		detailStage.setTitle("编辑");
		detailStage.show();
	}

	// 更新书籍相关全部信息
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
			new utils.systemError("PMCDMSFR004").start(null);
			detailStage.close();
		}

		try {
			PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\detail")));
			String orgStr = detailInfo.getText().trim();
			Scanner input = new Scanner(orgStr);
			while (input.hasNextLine()) {
				write.println(utils.AESCode.getAESWord(input.nextLine()));
			}
			input.close();
			write.close();
		} catch (Exception e) {
			new utils.systemError("PMCDMSFR005").start(null);
			detailStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
