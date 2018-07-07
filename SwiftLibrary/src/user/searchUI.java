package user;

import java.util.ArrayList;
import java.util.List;
import utils.AutoCompleteTextField;
import utils.AutoCompleteTextFieldBuilder;
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
import javafx.scene.control.ComboBox;
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

/**
 * searchUI:图书搜索界面
 * 
 * @author 蜂鸟Swift
 *
 */
public class searchUI extends Application {
	Stage bookStage;
	int userIndex;
	static ObservableList<manage.manageData> bookData;

	searchUI(int userIndex) {
		this.userIndex = userIndex;
	}

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
		homeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			homeBt.setEffect(shadowL);
		});
		homeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			homeBt.setEffect(null);
		});
		seaBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		seaBt.setTextFill(Color.WHITE);
		seaBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		seaBt.setPrefHeight(primaryScreenBounds.getHeight() / 9.5);
		myBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
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

		// ****************** 详情 ******************

		// ********** 搜索面板 **********
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

		// **** 融合搜索框 ****

		TextField searchTF = new TextField();
		searchTF.setMinHeight(40);
		searchTF.setMinWidth(primaryScreenBounds.getWidth() / 2);
		List<String> testList = new ArrayList<String>();
		for (int i = 0; i < info.dataBase.bookName.size(); i++) {
			testList.add(info.dataBase.bookName.get(i));
		}
		for (int i = 0; i < info.dataBase.bookAuthor.size(); i++) {
			testList.add(info.dataBase.bookAuthor.get(i));
		}
		for (int i = 0; i < info.dataBase.bookISBN.size(); i++) {
			testList.add(info.dataBase.bookISBN.get(i));
		}
		for (int i = 0; i < info.dataBase.bookPress.size(); i++) {
			testList.add(info.dataBase.bookPress.get(i));
		}
		AutoCompleteTextField autoSearch = AutoCompleteTextFieldBuilder.build(searchTF);
		autoSearch.setCacheDataList(testList);
		// **** 搜索框 ****

		Button searchBt = new Button("融合搜索");
		searchBt.setMinHeight(40);
		searchBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		searchBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			searchBt.setEffect(shadowD);
		});
		searchBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			searchBt.setEffect(null);
		});
		seaPane.getChildren().addAll(backToBt, new Label("\t"), searchTF, searchBt);
		// ********** 搜索框 **********

		// ********** 分类搜索框 **********

		HBox classPane = new HBox(10);
		Label currentCL = new Label("分类:");
		currentCL.setStyle("-fx-font-size:20px");
		ComboBox<String> selectClassCB = new ComboBox<String>();
		selectClassCB.setPrefHeight(30);
		selectClassCB.setPrefWidth(primaryScreenBounds.getWidth() / 8);
		for (int i = 0; i < info.dataBase.className.size(); i++) {
			selectClassCB.getItems().add(info.dataBase.className.get(i));
		}
		Button seaInClassBt = new Button("查找");
		seaInClassBt.setPrefHeight(30);
		seaInClassBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		seaInClassBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			seaInClassBt.setEffect(shadowD);
		});
		seaInClassBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			seaInClassBt.setEffect(null);
		});
		classPane.getChildren().addAll(currentCL, selectClassCB, seaInClassBt);
		// ********** 分类搜索框 **********

		// ********** 书目控件 **********
		HBox showPane = new HBox();
		showPane.setAlignment(Pos.CENTER);
		bookData = FXCollections.observableArrayList();
		for (int i = 0; i < info.dataBase.bookName.size(); i++) {
			bookData.add(new manage.manageData(info.dataBase.bookName.get(i), info.dataBase.bookISBN.get(i),
					info.dataBase.bookClass.get(i), info.dataBase.bookAuthor.get(i), info.dataBase.bookPress.get(i),
					info.dataBase.bookLeft.get(i) + "", info.dataBase.bookTotal.get(i) + "",
					info.dataBase.bookPrice.get(i) + ""));
		}
		TableView<manage.manageData> detailTable = new TableView<>(bookData);
		detailTable.setTableMenuButtonVisible(true);
		detailTable.setEditable(false);
		detailTable.setPrefWidth(primaryScreenBounds.getWidth() / 1.5);
		int widthTB = (int) (primaryScreenBounds.getWidth() / 1.5);
		TableColumn<manage.manageData, String> bookNameCol = new TableColumn<manage.manageData, String>("书目");
		bookNameCol.setPrefWidth(widthTB / 12.0 * 2);
		bookNameCol.setResizable(false);
		TableColumn<manage.manageData, String> bookISBNCol = new TableColumn<manage.manageData, String>("ISBN");
		bookISBNCol.setPrefWidth(widthTB / 12.0 * 2);
		bookISBNCol.setResizable(false);
		TableColumn<manage.manageData, String> bookClassCol = new TableColumn<manage.manageData, String>("分类");
		bookClassCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookClassCol.setResizable(false);
		TableColumn<manage.manageData, String> bookAuthorCol = new TableColumn<manage.manageData, String>("作者");
		bookAuthorCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookAuthorCol.setResizable(false);
		TableColumn<manage.manageData, String> bookPressCol = new TableColumn<manage.manageData, String>("出版社");
		bookPressCol.setPrefWidth(widthTB / 12.0 * 2);
		bookPressCol.setResizable(false);
		TableColumn<manage.manageData, String> bookLeftCol = new TableColumn<manage.manageData, String>("余量");
		bookLeftCol.setPrefWidth(widthTB / 12.0 * 1);
		bookLeftCol.setResizable(false);
		TableColumn<manage.manageData, String> bookTotalCol = new TableColumn<manage.manageData, String>("总库存");
		bookTotalCol.setPrefWidth(widthTB / 12.0 * 1);
		TableColumn<manage.manageData, String> bookPriceCol = new TableColumn<manage.manageData, String>("定价");
		bookPriceCol.setPrefWidth(widthTB / 12.0 * 1);
		bookTotalCol.setResizable(false);

		detailTable.getColumns().addAll(bookNameCol, bookISBNCol, bookClassCol, bookAuthorCol, bookPressCol,
				bookLeftCol, bookTotalCol, bookPriceCol);

		bookNameCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookName"));
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookISBN"));
		bookClassCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookClass"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookAuthor"));
		bookPressCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookPress"));
		bookLeftCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookLeft"));
		bookTotalCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookTotal"));
		bookPriceCol.setCellValueFactory(new PropertyValueFactory<manage.manageData, String>("bookPrice"));

		showPane.getChildren().addAll(detailTable);
		// ********** 书目显示面板 **********

		Button newBt = new Button("图书荐购");
		newBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		newBt.setTextFill(Color.WHITE);
		DropShadow shadowSave = new DropShadow();
		newBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			newBt.setEffect(shadowSave);
		});
		newBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			newBt.setEffect(null);
		});

		detailP.getChildren().addAll(seaPane, new Label(), classPane, showPane, newBt);

		// ****************** 详情 ******************
		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// 按钮事件响应
		newBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new bookApply(userIndex).start(null);
			}
		});

		detailTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = detailTable.getSelectionModel().getSelectedIndex();
					int indexRoot = info.dataBase.bookName.indexOf(bookData.get(index).getBookName());
					new bookDetail(indexRoot, userIndex, index).start(null);
				}
			}
		});

		backToBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookData.clear();
				for (int i = 0; i < info.dataBase.bookName.size(); i++) {
					bookData.add(new manage.manageData(info.dataBase.bookName.get(i), info.dataBase.bookISBN.get(i),
							info.dataBase.bookClass.get(i), info.dataBase.bookAuthor.get(i),
							info.dataBase.bookPress.get(i), info.dataBase.bookLeft.get(i) + "",
							info.dataBase.bookTotal.get(i) + "", info.dataBase.bookPrice.get(i) + ""));
				}
				detailTable.setItems(bookData);
			}
		});

		searchBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookData.clear();
				String inputS = searchTF.getText();
				for (int i = 0; i < info.dataBase.bookName.size(); i++) {
					String name = info.dataBase.bookName.get(i);
					String ISBN = info.dataBase.bookISBN.get(i);
					String Class = info.dataBase.bookClass.get(i);
					String author = info.dataBase.bookAuthor.get(i);
					String bookPress = info.dataBase.bookPress.get(i);
					String leftS = info.dataBase.bookLeft.get(i) + "";
					String totalS = info.dataBase.bookTotal.get(i) + "";
					String priceS = info.dataBase.bookPrice.get(i) + "";
					Boolean valid = name.contains(inputS) || inputS.contains(name) || ISBN.contains(inputS)
							|| inputS.contains(ISBN) || Class.contains(inputS) || inputS.contains(Class)
							|| author.contains(inputS) || inputS.contains(author) || bookPress.contains(inputS)
							|| inputS.contains(bookPress);
					if (valid) {
						bookData.add(
								new manage.manageData(name, ISBN, Class, author, bookPress, leftS, totalS, priceS));
					}
				}
				detailTable.setItems(bookData);
			}
		});

		seaInClassBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookData.clear();
				int inputIndex = selectClassCB.getSelectionModel().getSelectedIndex();
				if (inputIndex != -1) {
					String className = info.dataBase.className.get(inputIndex);
					for (int i = 0; i < info.dataBase.bookName.size(); i++) {
						String name = info.dataBase.bookName.get(i);
						String ISBN = info.dataBase.bookISBN.get(i);
						String Class = info.dataBase.bookClass.get(i);
						String author = info.dataBase.bookAuthor.get(i);
						String bookPress = info.dataBase.bookPress.get(i);
						String leftS = info.dataBase.bookLeft.get(i) + "";
						String totalS = info.dataBase.bookTotal.get(i) + "";
						String priceS = info.dataBase.bookPrice.get(i) + "";
						Boolean valid = Class.equals(className);
						if (valid) {
							bookData.add(
									new manage.manageData(name, ISBN, Class, author, bookPress, leftS, totalS, priceS));
						}
					}
					detailTable.setItems(bookData);
				} else {
					infoL.setText("请先选择分类");
				}
			}
		});

		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(bookStage);
				obj.start(null);
			}
		});

		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new userMain(userIndex).start(null);
			}
		});

		myBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new superMy(userIndex).start(null);
			}
		});

		infoBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new announceUI(userIndex).start(null);
			}
		});

		changeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new changePw(userIndex).start(null);
			}
		});
		// 结束事件响应

		Scene scene = new Scene(userPane);
		bookStage.setScene(scene);
		bookStage.setResizable(false);
		bookStage.setTitle("蜂鸟图书馆");
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

	public static void main(String[] args) {
		Application.launch(args);
	}
}
