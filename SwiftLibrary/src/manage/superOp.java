package manage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.TextField;
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
 * superOp:超级前台中控管理平台
 * 
 * @author 蜂鸟Swift
 *
 */
public class superOp extends Application {
	Stage bookStage;
	Label infoL;
	static ObservableList<borrowData> borrowData;
	int stuStr = -1;

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
		bookStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		bookStage.setX(primaryScreenBounds.getMinX());
		bookStage.setY(primaryScreenBounds.getMinY());
		bookStage.setWidth(primaryScreenBounds.getWidth());
		bookStage.setHeight(primaryScreenBounds.getHeight());

		BorderPane userPane = new BorderPane();
		userPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		BorderPane toolBarP = new BorderPane();
		toolBarP.setPadding(new Insets(5, 5, 5, 5));
		toolBarP.setPrefWidth(primaryScreenBounds.getWidth());
		toolBarP.setPrefHeight(15);
		toolBarP.setStyle("-fx-background-color: #E0FFFF;-fx-opacity: 0.5;");
		userPane.setTop(toolBarP);

		Label stageTitleL = new Label("蜂鸟图书馆管理平台");
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
		Button bookMBt = new Button("图书管理");
		Button superBt = new Button("超级前台");
		Button bookApplyBt = new Button("荐购处理");
		Button userMBt = new Button("用户管理");
		Button editBt = new Button("公告编辑");
		Button testBt = new Button("评测试题");
		naviPane.getChildren().addAll(homeBt, bookMBt, superBt, bookApplyBt, userMBt, editBt, testBt);

		DropShadow shadowL = new DropShadow();
		shadowL.setColor(Color.AQUA);
		homeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		homeBt.setTextFill(Color.WHITE);
		homeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		homeBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		homeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			homeBt.setEffect(shadowL);
		});
		homeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			homeBt.setEffect(null);
		});
		superBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		superBt.setTextFill(Color.WHITE);
		superBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		superBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		superBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			superBt.setEffect(shadowL);
		});
		superBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			superBt.setEffect(null);
		});
		bookMBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		bookMBt.setTextFill(Color.WHITE);
		bookMBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		bookMBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		bookApplyBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		bookApplyBt.setTextFill(Color.WHITE);
		bookApplyBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		bookApplyBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		bookApplyBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bookApplyBt.setEffect(shadowL);
		});
		bookApplyBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bookApplyBt.setEffect(null);
		});
		userMBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		userMBt.setTextFill(Color.WHITE);
		userMBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		userMBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		userMBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			userMBt.setEffect(shadowL);
		});
		userMBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			userMBt.setEffect(null);
		});
		editBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		editBt.setTextFill(Color.WHITE);
		editBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		editBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		editBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			editBt.setEffect(shadowL);
		});
		editBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			editBt.setEffect(null);
		});
		testBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		testBt.setTextFill(Color.WHITE);
		testBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		testBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		testBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			testBt.setEffect(shadowL);
		});
		testBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			testBt.setEffect(null);
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

		// ********** 搜索面板开始 **********
		HBox seaPane = new HBox(20);
		Button backToBt = new Button();
		backToBt.setPrefSize(40, 40);
		backToBt.setStyle("-fx-background-image:url(" + info.impPath.back_IMG + ");-fx-background-size: 40px;");
		DropShadow shadowD = new DropShadow();
		backToBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			backToBt.setEffect(shadowD);
		});
		backToBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			backToBt.setEffect(null);
		});

		// **** 搜索框开始 ****

		Label idCL = new Label("学号：");
		idCL.setStyle("-fx-font-size:25px");
		TextField idTF = new TextField();
		idTF.setPrefHeight(40);
		idTF.setPrefWidth(primaryScreenBounds.getWidth() / 6);
		Label nameCL = new Label("ISBN：");
		nameCL.setStyle("-fx-font-size:25px");
		TextField nameTF = new TextField();
		nameTF.setPrefHeight(40);
		nameTF.setPrefWidth(primaryScreenBounds.getWidth() / 6);
		Button searchBt = new Button("借阅");
		searchBt.setMinHeight(40);
		searchBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		searchBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			searchBt.setEffect(shadowD);
		});
		searchBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			searchBt.setEffect(null);
		});
		seaPane.getChildren().addAll(backToBt, new Label("\t"), idCL, idTF, new Label(" "), nameCL, nameTF, searchBt);
		// ********** 搜索面板结束 **********

		// ********** 分类管理面板结束 **********

		// ********** 图书主面板开始 **********
		HBox showPane = new HBox();
		showPane.setAlignment(Pos.CENTER);
		borrowData = FXCollections.observableArrayList();
		TableView<borrowData> detailTable = new TableView<>();
		detailTable.setItems(borrowData);
		detailTable.setTableMenuButtonVisible(true);
		detailTable.setEditable(false);
		detailTable.setPrefWidth(primaryScreenBounds.getWidth() / 1.5);
		int widthTB = (int) (primaryScreenBounds.getWidth() / 1.5);
		TableColumn<borrowData, String> bookNameCol = new TableColumn<borrowData, String>("书名");
		bookNameCol.setPrefWidth(widthTB / 12 * 2);
		bookNameCol.setResizable(false);
		TableColumn<borrowData, String> bookISBNCol = new TableColumn<borrowData, String>("ISBN");
		bookISBNCol.setPrefWidth(widthTB / 12 * 2);
		bookISBNCol.setResizable(false);
		TableColumn<borrowData, String> bookClassCol = new TableColumn<borrowData, String>("分类");
		bookClassCol.setPrefWidth(widthTB / 12 * 1.5);
		bookClassCol.setResizable(false);
		TableColumn<borrowData, String> bookAuthorCol = new TableColumn<borrowData, String>("作者");
		bookAuthorCol.setPrefWidth(widthTB / 12 * 1.5);
		bookAuthorCol.setResizable(false);
		TableColumn<borrowData, String> bookTimeCol = new TableColumn<borrowData, String>("借阅时间");
		bookTimeCol.setPrefWidth(widthTB / 12 * 2);
		bookTimeCol.setResizable(false);
		TableColumn<borrowData, String> bookLongCol = new TableColumn<borrowData, String>("借阅时长");
		bookLongCol.setPrefWidth(widthTB / 12 * 2);
		bookLongCol.setResizable(false);
		TableColumn<borrowData, String> bookPricelCol = new TableColumn<borrowData, String>("定价");
		bookPricelCol.setPrefWidth(widthTB / 12 * 1);
		bookPricelCol.setResizable(false);

		detailTable.getColumns().addAll(bookNameCol, bookISBNCol, bookClassCol, bookAuthorCol, bookTimeCol, bookLongCol,
				bookPricelCol);

		bookNameCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookName"));
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookISBN"));
		bookClassCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookClass"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookAuthor"));
		bookTimeCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookTime"));
		bookLongCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookLong"));
		bookPricelCol.setCellValueFactory(new PropertyValueFactory<borrowData, String>("bookPrice"));

		showPane.getChildren().addAll(detailTable);
		// ********** 图书主面板结束 **********

		detailP.getChildren().addAll(seaPane, new Label(), showPane);

		// ****************** 详细部分 ******************
		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// 双击还书事件相应
		detailTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					if (stuStr == -1) {
						infoL.setText("内部逻辑错误，建议重启模块");
					} else {
						int index = detailTable.getSelectionModel().getSelectedIndex();
						int indexRoot = info.dataBase.bookName.indexOf(borrowData.get(index).getBookName());
						double aMoney = Double.parseDouble(borrowData.get(index).getBookPrice());
						int time = Integer.parseInt(borrowData.get(index).getBookLong());
						returnBook(stuStr, indexRoot);
						if (time > 30) {
							new needMoney((time - 30) * aMoney * 0.1).start(null);
							info.dataBase.credit.set(stuStr, info.dataBase.credit.get(stuStr) - 2);
						} else {
							info.dataBase.credit.set(stuStr, info.dataBase.credit.get(stuStr) + 2);
						}

						try {
							PrintWriter write = new PrintWriter(
									new OutputStreamWriter(new FileOutputStream(info.impPath.user_json)));
							for (int i = 0; i < info.dataBase.name.size(); i++) {
								Map<String, String> params = new HashMap<>();
								params.put("name", info.dataBase.name.get(i));
								params.put("password", info.dataBase.pw.get(i));
								params.put("trueName", info.dataBase.trueName.get(i));
								params.put("valid", info.dataBase.valid.get(i).toString());
								params.put("phone", info.dataBase.phone.get(i));
								params.put("credit", info.dataBase.credit.get(i) + "");
								params.put("year", info.dataBase.regYear.get(i) + "");
								params.put("month", info.dataBase.regMonth.get(i) + "");
								params.put("day", info.dataBase.regDay.get(i) + "");
								JSONObject jsonObject = JSONObject.fromObject(params);
								String jsonStr = jsonObject.toString();
								write.println(utils.AESCode.getAESWord(jsonStr));
							}
							write.close();
						} catch (Exception error) {
							new utils.systemError("PUCSMBFT004").start(null);
							bookStage.close();
						}

						// 更新表格
						borrowData.clear();

						ArrayList<String> returnBook = new ArrayList<String>();
						ArrayList<String> returnTime = new ArrayList<String>();
						java.io.File annInfo = new java.io.File(
								info.impPath.user_root + "\\" + info.dataBase.name.get(stuStr));
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
							new utils.systemError("PUCSMBFW003").start(null);
							bookStage.close();
						}

						for (int i = 0; i < returnBook.size(); i++) {
							String name = returnBook.get(i);
							int indexB = info.dataBase.bookName.indexOf(name);
							String ISBN = info.dataBase.bookISBN.get(indexB);
							String Class = info.dataBase.bookClass.get(indexB);
							String author = info.dataBase.bookAuthor.get(indexB);
							String timeB = returnTime.get(i);
							SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
							String fromDate = timeB;
							Date dNow = new Date();
							String toDate = simpleFormat.format(dNow);
							int days = 0;
							try {
								long from = simpleFormat.parse(fromDate).getTime();
								long to = simpleFormat.parse(toDate).getTime();
								days = (int) ((to - from) / (1000 * 60 * 60 * 24));
							} catch (Exception e) {
								new utils.systemError("PUCSMBFW003").start(null);
								bookStage.close();
							}
							String Long = days + "";
							String priceS = info.dataBase.bookPrice.get(index) + "";
							borrowData.add(new borrowData(name, ISBN, Class, author, timeB, Long, priceS));
						}
						// 更新完成
					}
				}
			}
		});

		// 主界面事件响应
		backToBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				borrowData.clear();
				idTF.setText("");
				nameTF.setText("");
				stuStr = -1;
			}
		});

		// 融合操作
		searchBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (nameTF.getText() != null && !nameTF.getText().equals("")) {
					// 若输入ISBN号 表示借书
					int indexSTU = info.dataBase.name.indexOf(idTF.getText());
					int indexBOOK = info.dataBase.bookISBN.indexOf(nameTF.getText());
					if (indexSTU == -1 || indexBOOK == -1) {
						infoL.setText("查无此书/人");
					} else if (!info.dataBase.valid.get(indexSTU)) {
						infoL.setText("账号冻结中");
					} else if (info.dataBase.bookLeft.get(indexBOOK) == 0) {
						infoL.setText("该书目库存为空");
					} else {
						// 启动借书模块
						borrowMain(indexSTU, indexBOOK);
					}
				}

				// 更新表格
				borrowData.clear();

				int indexSTU = info.dataBase.name.indexOf(idTF.getText());
				if (indexSTU == -1) {
					infoL.setText("查无此人");
					return;
				}

				stuStr = indexSTU;
				ArrayList<String> returnBook = new ArrayList<String>();
				ArrayList<String> returnTime = new ArrayList<String>();
				java.io.File annInfo = new java.io.File(
						info.impPath.user_root + "\\" + info.dataBase.name.get(indexSTU));
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
					new utils.systemError("PUCSMBFW003").start(null);
					bookStage.close();
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
						bookStage.close();
					}
					String Long = days + "";
					String priceS = info.dataBase.bookPrice.get(index) + "";
					borrowData.add(new borrowData(name, ISBN, Class, author, time, Long, priceS));
				}
			}
		});

		// 工具栏事件相应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(bookStage);
				obj.start(null);
			}
		});

		// 以下为左侧导航栏事件相应
		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new adminMain().start(null);
			}
		});

		bookApplyBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new applyManage().start(null);
			}
		});

		userMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new userManage().start(null);
			}
		});

		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new editInfo().start(null);
			}
		});

		testBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new testManage().start(null);
			}
		});

		bookMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new bookManage().start(null);
			}
		});
		// 结束上述约定

		Scene scene = new Scene(userPane);
		bookStage.setScene(scene);
		bookStage.setResizable(false);
		bookStage.setTitle("蜂鸟图书馆管理平台");
		bookStage.initStyle(StageStyle.TRANSPARENT);
		bookStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		bookStage.show();

		toolBarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					new common.aboutMe().start(null);
				}
			}
		});
	}

	// 还书模块
	protected void returnBook(int nowUser, int nowDoing) {
		try {
			// 已借书目
			ArrayList<String> returnBook = new ArrayList<String>();
			ArrayList<String> returnTime = new ArrayList<String>();
			java.io.File annInfo = new java.io.File(info.impPath.user_root + "\\" + info.dataBase.name.get(nowUser));
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(annInfo));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				returnBook.add(map.get("book"));
				returnTime.add(map.get("time"));
			}
			input.close();

			// 写入个人借阅记录
			int indexBook = returnBook.indexOf(info.dataBase.bookName.get(nowDoing));
			returnBook.remove(indexBook);
			returnTime.remove(indexBook);
			PrintWriter write = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(info.impPath.user_root + "\\" + info.dataBase.name.get(nowUser))));
			for (int i = 0; i < returnBook.size(); i++) {
				Map<String, String> params = new HashMap<>();
				params.put("book", returnBook.get(i));
				params.put("time", returnTime.get(i));
				JSONObject jsonObject = JSONObject.fromObject(params);
				String jsonStr = jsonObject.toString();
				write.println(utils.AESCode.getAESWord(jsonStr));
			}
			write.close();

			// 读取书目借阅人员
			java.io.File allBookFile = new java.io.File(
					info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\borrow");
			ArrayList<String> bookAll = new ArrayList<String>();
			InputStreamReader inputReaderB = new InputStreamReader(new FileInputStream(allBookFile));
			Scanner inputB = new Scanner(inputReaderB);
			while (inputB.hasNextLine()) {
				bookAll.add(utils.AESCode.getOrgWord(inputB.nextLine()));
			}
			inputB.close();

			// 写入借阅人员
			int userIndex = bookAll.indexOf(info.dataBase.name.get(nowUser));
			bookAll.remove(userIndex);
			PrintWriter writeB = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\borrow")));
			for (int i = 0; i < bookAll.size(); i++) {
				writeB.println(utils.AESCode.getAESWord(bookAll.get(i)));
			}
			writeB.close();

			// 写入新的书目库存书目
			info.dataBase.bookLeft.set(nowDoing, info.dataBase.bookLeft.get(nowDoing) + 1);
			PrintWriter writeD = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info.impPath.book_json)));
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
				writeD.println(jsonAES);
			}
			writeD.close();

			infoL.setText("还书成功");
		} catch (Exception e) {
			new utils.systemError("PUCSMBFW004").start(null);
			bookStage.close();
		}
	}

	// 借书模块
	protected void borrowMain(int nowUser, int nowDoing) {
		try {
			// 已借书目
			ArrayList<String> returnBook = new ArrayList<String>();
			ArrayList<String> returnTime = new ArrayList<String>();
			java.io.File annInfo = new java.io.File(info.impPath.user_root + "\\" + info.dataBase.name.get(nowUser));
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(annInfo));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				returnBook.add(map.get("book"));
				returnTime.add(map.get("time"));
			}
			input.close();

			// 异常检测
			if (returnBook.contains(info.dataBase.bookName.get(nowDoing))) {
				infoL.setText("该书目已被借阅");
				return;
			}
			if (info.dataBase.bookLeft.get(nowDoing) == 0) {
				infoL.setText("该书目库存不足");
				return;
			}

			// 写入个人借阅记录
			returnBook.add(info.dataBase.bookName.get(nowDoing));
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			returnTime.add(ft.format(dNow));
			PrintWriter write = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(info.impPath.user_root + "\\" + info.dataBase.name.get(nowUser))));
			for (int i = 0; i < returnBook.size(); i++) {
				Map<String, String> params = new HashMap<>();
				params.put("book", returnBook.get(i));
				params.put("time", returnTime.get(i));
				JSONObject jsonObject = JSONObject.fromObject(params);
				String jsonStr = jsonObject.toString();
				write.println(utils.AESCode.getAESWord(jsonStr));
			}
			write.close();

			// 读取书目借阅人员
			java.io.File allBookFile = new java.io.File(
					info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\borrow");
			ArrayList<String> bookAll = new ArrayList<String>();
			InputStreamReader inputReaderB = new InputStreamReader(new FileInputStream(allBookFile));
			Scanner inputB = new Scanner(inputReaderB);
			while (inputB.hasNextLine()) {
				bookAll.add(utils.AESCode.getOrgWord(inputB.nextLine()));
			}
			inputB.close();

			// 写入借阅人员
			bookAll.add(info.dataBase.name.get(nowUser));
			PrintWriter writeB = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					info.impPath.book_root + "\\" + info.dataBase.bookISBN.get(nowDoing) + "\\borrow")));
			for (int i = 0; i < bookAll.size(); i++) {
				writeB.println(utils.AESCode.getAESWord(bookAll.get(i)));
			}
			writeB.close();

			// 写入新的书目库存书目
			info.dataBase.bookLeft.set(nowDoing, info.dataBase.bookLeft.get(nowDoing) - 1);
			info.dataBase.bookPopular.set(nowDoing, info.dataBase.bookPopular.get(nowDoing) + 1);
			PrintWriter writeD = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info.impPath.book_json)));
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
				writeD.println(jsonAES);
			}
			writeD.close();

			infoL.setText("借书成功");
		} catch (Exception e) {
			new utils.systemError("PUCSMBFW001").start(null);
			bookStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
