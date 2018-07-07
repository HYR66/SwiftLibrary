package manage;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * bookReader:图书详情信息查看
 * 
 * @author 蜂鸟Swift
 *
 */
public class bookReader extends Application {
	int nowDoing;
	int index;
	Stage detailStage = new Stage();

	bookReader() {

	}

	bookReader(int nowDoing, int index) {
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

		Label infoL = new Label("详情");
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
		Label bookPopularTL = new Label("热度\t");
		Label bookLeftTL = new Label("余量\t");
		Label bookTotalTL = new Label("总库存\t");
		Label bookPriceTL = new Label("定价\t");
		Label bookFileValidTL = new Label("附件\t");

		Label bookNameTF = new Label();
		bookNameTF.setText(info.dataBase.bookName.get(nowDoing));
		Label bookAuthorTF = new Label();
		bookAuthorTF.setText(info.dataBase.bookAuthor.get(nowDoing));
		Label bookPressTF = new Label();
		bookPressTF.setText(info.dataBase.bookPress.get(nowDoing));
		Label bookISBNTF = new Label();
		bookISBNTF.setText(info.dataBase.bookISBN.get(nowDoing));
		Label bookClassTF = new Label();
		bookClassTF.setText(info.dataBase.bookClass.get(nowDoing));

		Label bookCPosTF = new Label();
		bookCPosTF.setText(info.dataBase.bookCPos.get(nowDoing));
		Label bookPopularTF = new Label();
		bookPopularTF.setText(info.dataBase.bookPopular.get(nowDoing) + "");
		Label bookLeftTF = new Label();
		int borrowInt = info.dataBase.bookLeft.get(nowDoing);
		bookLeftTF.setText(borrowInt + "");
		Label bookTotalTF = new Label();
		bookTotalTF.setText(info.dataBase.bookTotal.get(nowDoing) + "");
		Label bookPriceTF = new Label();
		bookPriceTF.setText(info.dataBase.bookPrice.get(nowDoing) + "元");
		HBox btPaneL = new HBox(5);
		Label fileTF = new Label("附件");
		btPaneL.getChildren().addAll(fileTF);

		if (info.dataBase.fileValid.get(nowDoing)) {
			fileTF.setText("有附件");
		} else {
			fileTF.setText("无附件");
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
		bookNameTF.setStyle(" -fx-text-fill:#000000;");
		bookAuthorTF.setStyle(" -fx-text-fill:#000000;");
		bookPressTF.setStyle(" -fx-text-fill:#000000;");
		bookISBNTF.setStyle(" -fx-text-fill:#000000;");
		bookClassTF.setStyle(" -fx-text-fill:#000000;");
		bookCPosTF.setStyle(" -fx-text-fill:#000000;");
		bookPopularTF.setStyle(" -fx-text-fill:#000000;");
		bookLeftTF.setStyle(" -fx-text-fill:#000000;");
		bookTotalTF.setStyle(" -fx-text-fill:#000000;");
		bookPriceTF.setStyle(" -fx-text-fill:#000000;");
		fileTF.setStyle(" -fx-text-fill:#000000;");

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
		TextArea detailInfo = new TextArea();
		detailInfo.setWrapText(true);
		detailInfo.setEditable(false);

		String detailInfoS = "";
		java.io.File bookInfo = new java.io.File(
				info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\detail");
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(bookInfo));
			Scanner input = new Scanner(inputReader);
			int i = 0;
			while (input.hasNextLine()) {
				String read = input.nextLine();
				if (i == 0) {
					detailInfoS = detailInfoS + utils.AESCode.getOrgWord(read);
				} else {
					detailInfoS = detailInfoS + "\n" + utils.AESCode.getOrgWord(read);
				}
				i++;
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PMCBMSFR003").start(null);
			detailStage.close();
		}
		detailInfo.setText(detailInfoS);

		String borrowList = "";
		java.io.File bookBorrow = new java.io.File(
				info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\borrow");
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(bookBorrow));
			Scanner input = new Scanner(inputReader);
			borrowList = borrowList + "\t";
			while (input.hasNextLine()) {
				borrowList = borrowList + "\n\t" + utils.AESCode.getOrgWord(input.nextLine());
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PMCBMSFR004").start(null);
			detailStage.close();
		}

		Separator lineB = new Separator();
		lineB.setMinWidth(primaryScreenBounds.getWidth() / 3);
		Label borrowT = new Label("借阅者");
		borrowT.setStyle("-fx-font-size:20;");
		borrowT.setTextFill(Color.BROWN);
		Label detailBorrow = new Label();
		if (borrowList.equals("")) {
			detailBorrow.setText("\t暂无借阅者");
		} else {
			detailBorrow.setText(borrowList);
		}
		detailBorrow.setTextFill(Color.BLACK);

		Label errorL = new Label();
		errorL.setTextFill(Color.RED);
		rootPane.getChildren().addAll(mainPane, lineA, detailInfo, lineB, borrowT, detailBorrow);
		scPane.setContent(rootPane);

		HBox opPane = new HBox(5);
		Button editBt = new Button("编辑");
		Button cancelBt = new Button("返回");
		opPane.getChildren().addAll(editBt, cancelBt);

		vbox.getChildren().addAll(infoL, scPane, opPane, errorL);

		// ********************按钮事件响应开始 ********************
		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				detailStage.close();
				new detailBook(nowDoing, index).start(null);
			}
		});

		cancelBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				detailStage.close();
			}
		});

		// ******************** 按钮事件相应结束 ********************

		Scene scene = new Scene(vbox);
		scene.setFill(null);
		detailStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		detailStage.setScene(scene);
		detailStage.setTitle("详情");
		detailStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
