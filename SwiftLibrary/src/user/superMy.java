package user;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.json.JSONObject;

/**
 * superMy:个人信息查看器
 * 
 * @author 蜂鸟Swift
 *
 */
public class superMy extends Application {
	int nowUser;
	static ObservableList<bookData> bookData;
	Stage superStage;

	superMy(int nowUser) {
		this.nowUser = nowUser;
	}

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
		superStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		superStage.setX(primaryScreenBounds.getMinX());
		superStage.setY(primaryScreenBounds.getMinY());
		superStage.setWidth(primaryScreenBounds.getWidth());
		superStage.setHeight(primaryScreenBounds.getHeight());

		BorderPane userPane = new BorderPane();
		userPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		BorderPane toolBarP = new BorderPane();
		toolBarP.setPadding(new Insets(5, 5, 5, 5));
		toolBarP.setPrefWidth(primaryScreenBounds.getWidth());
		toolBarP.setPrefHeight(15);
		toolBarP.setStyle("-fx-background-color: #E0FFFF;-fx-opacity: 0.5;");
		userPane.setTop(toolBarP);

		Label stageTitleL = new Label("蜂鸟图书馆");
		toolBarP.setCenter(stageTitleL);

		Button closeToolBt = new Button("X");
		closeToolBt.setTextFill(Color.WHITE);
		closeToolBt.setStyle("-fx-background-color: #C71585;-fx-opacity: 1;");
		DropShadow shadowCloseBt = new DropShadow();
		closeToolBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			closeToolBt.setEffect(shadowCloseBt);
		});
		closeToolBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			closeToolBt.setEffect(null);
		});
		toolBarP.setRight(closeToolBt);

		HBox mainPane = new HBox();
		mainPane.setPadding(new Insets(30, 30, 30, 30));
		userPane.setCenter(mainPane);

		VBox naviPane = new VBox(5);
		Button homeBt = new Button("主页");
		Button seaBt = new Button("查找图书");
		Button myBt = new Button("我的图书馆");
		Button infoBt = new Button("公告");
		Button changeBt = new Button("修改密码");
		naviPane.getChildren().addAll(homeBt, seaBt, myBt, infoBt, changeBt);

		DropShadow shadowL = new DropShadow();
		shadowL.setColor(Color.AQUA);
		homeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		homeBt.setTextFill(Color.WHITE);
		homeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		homeBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		seaBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		seaBt.setTextFill(Color.WHITE);
		seaBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		seaBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		seaBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			seaBt.setEffect(shadowL);
		});
		seaBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			seaBt.setEffect(null);
		});
		myBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		myBt.setTextFill(Color.WHITE);
		myBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		myBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		myBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			myBt.setEffect(shadowL);
		});
		myBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			myBt.setEffect(null);
		});
		changeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		changeBt.setTextFill(Color.WHITE);
		changeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		changeBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		changeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			changeBt.setEffect(shadowL);
		});
		changeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			changeBt.setEffect(null);
		});
		infoBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		infoBt.setTextFill(Color.WHITE);
		infoBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		infoBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		infoBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			infoBt.setEffect(shadowL);
		});
		infoBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			infoBt.setEffect(null);
		});

		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(20, 20, 20, 20));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setStyle("-fx-background-color: WHITE;-fx-opacity: 0.5;");
		contentPane.setPrefWidth(primaryScreenBounds.getWidth() / 1.2);
		VBox detailP = new VBox(10);
		detailP.setAlignment(Pos.CENTER);
		contentPane.add(detailP, 1, 2);

		// ****************** 核心部分开始 ******************

		// ********** 信息聚合控件开始 **********

		VBox seaPane = new VBox(20);
		Label idCL = new Label(
				"学号：" + info.dataBase.name.get(nowUser) + "\t\t姓名：" + info.dataBase.trueName.get(nowUser));
		idCL.setStyle("-fx-font-size:20px;-fx-font-family:KaiTi;");
		Label idTL = new Label(
				"手机号：" + info.dataBase.phone.get(nowUser) + "\t\t信用积分：" + info.dataBase.credit.get(nowUser));
		idTL.setStyle("-fx-font-size:20px;-fx-font-family:KaiTi;");
		seaPane.getChildren().addAll(idCL, idTL);
		// ********** 信息面板结束 **********

		// ********** 图书主面板开始 **********
		HBox showPane = new HBox();
		showPane.setAlignment(Pos.CENTER);
		bookData = FXCollections.observableArrayList();
		TableView<bookData> detailTable = new TableView<>();
		detailTable.setItems(bookData);
		detailTable.setTableMenuButtonVisible(true);
		detailTable.setEditable(false);
		detailTable.setPrefWidth(primaryScreenBounds.getWidth() / 1.5);
		int widthTB = (int) (primaryScreenBounds.getWidth() / 1.5);
		TableColumn<bookData, String> bookNameCol = new TableColumn<bookData, String>("书名");
		bookNameCol.setPrefWidth(widthTB / 12.0 * 2);
		bookNameCol.setResizable(false);
		TableColumn<bookData, String> bookISBNCol = new TableColumn<bookData, String>("ISBN");
		bookISBNCol.setPrefWidth(widthTB / 12.0 * 2);
		bookISBNCol.setResizable(false);
		TableColumn<bookData, String> bookClassCol = new TableColumn<bookData, String>("分类");
		bookClassCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookClassCol.setResizable(false);
		TableColumn<bookData, String> bookAuthorCol = new TableColumn<bookData, String>("作者");
		bookAuthorCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookAuthorCol.setResizable(false);
		TableColumn<bookData, String> bookTimeCol = new TableColumn<bookData, String>("借阅时间");
		bookTimeCol.setPrefWidth(widthTB / 12.0 * 2);
		bookTimeCol.setResizable(false);
		TableColumn<bookData, String> bookLongCol = new TableColumn<bookData, String>("借阅时长");
		bookLongCol.setPrefWidth(widthTB / 12.0 * 2);
		bookLongCol.setResizable(false);
		TableColumn<bookData, String> bookPricelCol = new TableColumn<bookData, String>("定价");
		bookPricelCol.setPrefWidth(widthTB / 12.0 * 1);
		bookPricelCol.setResizable(false);

		detailTable.getColumns().addAll(bookNameCol, bookISBNCol, bookClassCol, bookAuthorCol, bookTimeCol, bookLongCol,
				bookPricelCol);

		bookNameCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookName"));
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookISBN"));
		bookClassCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookClass"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookAuthor"));
		bookTimeCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookTime"));
		bookLongCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookLong"));
		bookPricelCol.setCellValueFactory(new PropertyValueFactory<bookData, String>("bookPrice"));

		showPane.getChildren().addAll(detailTable);
		// ********** 图书主面板结束 **********

		// 图书信息写入模块
		ArrayList<String> returnBook = new ArrayList<String>();
		ArrayList<String> returnTime = new ArrayList<String>();
		java.io.File annInfo = new java.io.File(info.impPath.user_root + "\\" + info.dataBase.name.get(nowUser));
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(annInfo));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				returnBook.add(map.get("book"));
				returnTime.add(map.get("time"));
			}
			input.close();
		} catch (Exception e) {
			new utils.systemError("PUCSMBFW001").start(null);
			superStage.close();
		}

		for (int i = 0; i < returnBook.size(); i++) {
			String name = returnBook.get(i);
			int index = info.dataBase.bookName.indexOf(name);
			String ISBN = info.dataBase.bookISBN.get(index);
			String Class = info.dataBase.bookClass.get(index);
			String author = info.dataBase.bookAuthor.get(index);
			String time = returnTime.get(i);
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			String fromDate = time;
			Date dNow = new Date();
			String toDate = simpleFormat.format(dNow);
			int days = 0;
			try {
				long from = simpleFormat.parse(fromDate).getTime();
				long to = simpleFormat.parse(toDate).getTime();
				days = (int) ((to - from) / (1000 * 60 * 60 * 24));
			} catch (Exception e) {
				new utils.systemError("PUCSMBFW002").start(null);
				superStage.close();
			}
			String Long = days + "";
			String priceS = info.dataBase.bookPrice.get(index) + "";
			bookData.add(new bookData(name, ISBN, Class, author, time, Long, priceS));
		}

		detailP.getChildren().addAll(seaPane, new Label(), showPane);

		// ****************** 详细部分 ******************
		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// 双击还书事件相应
		detailTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = detailTable.getSelectionModel().getSelectedIndex();
					int indexRoot = info.dataBase.bookName.indexOf(bookData.get(index).getBookName());
					new talkInput(indexRoot).start(null);
				}
			}
		});

		// 工具栏事件相应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(superStage);
				obj.start(null);
			}
		});

		// 以下为左侧导航栏事件相应
		seaBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				superStage.close();
				new searchUI(nowUser).start(null);
			}
		});

		infoBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				superStage.close();
				new announceUI(nowUser).start(null);
			}
		});

		changeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				superStage.close();
				new changePw(nowUser).start(null);
			}
		});

		// 结束上述约定

		Scene scene = new Scene(userPane);
		superStage.setScene(scene);
		superStage.setResizable(false);
		superStage.setTitle("蜂鸟图书馆");
		superStage.initStyle(StageStyle.TRANSPARENT);
		superStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		superStage.show();

		toolBarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					new common.aboutMe().start(null);
				}
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
