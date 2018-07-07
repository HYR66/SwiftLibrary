package manage;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
 * bookManage:ͼ��������
 * 
 * @author ����Swift
 *
 */
public class bookManage extends Application {
	Stage bookStage;
	static ObservableList<manageData> bookData;

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

		Label stageTitleL = new Label("����ͼ��ݹ���ƽ̨");
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
		Button homeBt = new Button("��ҳ");
		Button bookMBt = new Button("ͼ�����");
		Button superBt = new Button("����ǰ̨");
		Button bookApplyBt = new Button("��������");
		Button userMBt = new Button("�û�����");
		Button editBt = new Button("����༭");
		Button testBt = new Button("��������");
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
		bookMBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
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
		superBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		superBt.setTextFill(Color.WHITE);
		superBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		superBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		superBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			superBt.setEffect(shadowL);
		});
		superBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			superBt.setEffect(null);
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

		// ****************** ���Ĳ��ֿ�ʼ ******************

		// ********** ������忪ʼ **********
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

		// **** ������ʼ ****

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
		// **** ��������� ****

		Button searchBt = new Button("�ں�����");
		searchBt.setMinHeight(40);
		searchBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		searchBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			searchBt.setEffect(shadowD);
		});
		searchBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			searchBt.setEffect(null);
		});
		seaPane.getChildren().addAll(backToBt, new Label("\t"), searchTF, searchBt);
		// ********** ���������� **********

		// ********** ���������忪ʼ **********

		HBox classPane = new HBox(10);
		Label currentCL = new Label("�������:");
		currentCL.setStyle("-fx-font-size:20px");
		ComboBox<String> selectClassCB = new ComboBox<String>();
		selectClassCB.setPrefHeight(30);
		selectClassCB.setPrefWidth(primaryScreenBounds.getWidth() / 8);
		for (int i = 0; i < info.dataBase.className.size(); i++) {
			selectClassCB.getItems().add(info.dataBase.className.get(i));
		}
		Button seaInClassBt = new Button("����");
		seaInClassBt.setPrefHeight(30);
		seaInClassBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		seaInClassBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			seaInClassBt.setEffect(shadowD);
		});
		seaInClassBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			seaInClassBt.setEffect(null);
		});
		Button delClassBt = new Button("ɾ������");
		delClassBt.setPrefHeight(30);
		delClassBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		delClassBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delClassBt.setEffect(shadowD);
		});
		delClassBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delClassBt.setEffect(null);
		});
		Label creatCL = new Label("�����·��ࣺ");
		creatCL.setStyle("-fx-font-size:20px");
		TextField creatTF = new TextField();
		creatTF.setPrefHeight(30);
		Button submitBt = new Button("�ύ");
		submitBt.setPrefHeight(30);
		submitBt.setStyle("-fx-background-color:Silver;-fx-opacity: 0.8;");
		submitBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			submitBt.setEffect(shadowD);
		});
		submitBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			submitBt.setEffect(null);
		});
		classPane.getChildren().addAll(currentCL, selectClassCB, seaInClassBt, delClassBt, new Label("\t"), creatCL,
				creatTF, submitBt);
		// ********** ������������� **********

		// ********** ͼ������忪ʼ **********
		HBox showPane = new HBox();
		showPane.setAlignment(Pos.CENTER);
		bookData = FXCollections.observableArrayList();
		for (int i = 0; i < info.dataBase.bookName.size(); i++) {
			bookData.add(new manageData(info.dataBase.bookName.get(i), info.dataBase.bookISBN.get(i),
					info.dataBase.bookClass.get(i), info.dataBase.bookAuthor.get(i), info.dataBase.bookPress.get(i),
					info.dataBase.bookLeft.get(i) + "", info.dataBase.bookTotal.get(i) + "",
					info.dataBase.bookPrice.get(i) + ""));
		}
		TableView<manageData> detailTable = new TableView<>();
		detailTable.setItems(bookData);
		detailTable.setTableMenuButtonVisible(true);
		detailTable.setEditable(false);
		detailTable.setPrefWidth(primaryScreenBounds.getWidth() / 1.5);
		int widthTB = (int) (primaryScreenBounds.getWidth() / 1.5);
		TableColumn<manageData, String> bookNameCol = new TableColumn<manageData, String>("����");
		bookNameCol.setPrefWidth(widthTB / 12.0 * 2);
		bookNameCol.setResizable(false);
		TableColumn<manageData, String> bookISBNCol = new TableColumn<manageData, String>("ISBN");
		bookISBNCol.setPrefWidth(widthTB / 12.0 * 2);
		bookISBNCol.setResizable(false);
		TableColumn<manageData, String> bookClassCol = new TableColumn<manageData, String>("����");
		bookClassCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookClassCol.setResizable(false);
		TableColumn<manageData, String> bookAuthorCol = new TableColumn<manageData, String>("����");
		bookAuthorCol.setPrefWidth(widthTB / 12.0 * 1.5);
		bookAuthorCol.setResizable(false);
		TableColumn<manageData, String> bookPressCol = new TableColumn<manageData, String>("������");
		bookPressCol.setPrefWidth(widthTB / 12.0 * 2);
		bookPressCol.setResizable(false);
		TableColumn<manageData, String> bookLeftCol = new TableColumn<manageData, String>("����");
		bookLeftCol.setPrefWidth(widthTB / 12.0 * 1);
		bookLeftCol.setResizable(false);
		TableColumn<manageData, String> bookTotalCol = new TableColumn<manageData, String>("�ܿ��");
		bookTotalCol.setPrefWidth(widthTB / 12.0 * 1);
		bookTotalCol.setResizable(false);
		TableColumn<manageData, String> bookPricelCol = new TableColumn<manageData, String>("����");
		bookPricelCol.setPrefWidth(widthTB / 12.0 * 1);
		bookPricelCol.setResizable(false);

		detailTable.getColumns().addAll(bookNameCol, bookISBNCol, bookClassCol, bookAuthorCol, bookPressCol,
				bookLeftCol, bookTotalCol, bookPricelCol);

		bookNameCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookName"));
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookISBN"));
		bookClassCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookClass"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookAuthor"));
		bookPressCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookPress"));
		bookLeftCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookLeft"));
		bookTotalCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookTotal"));
		bookPricelCol.setCellValueFactory(new PropertyValueFactory<manageData, String>("bookPrice"));

		showPane.getChildren().addAll(detailTable);
		// ********** ͼ���������� **********

		Button newBt = new Button("����ͼ��");
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

		// ****************** ��ϸ���� ******************
		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("ƽ̨״̬������");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// �Ҳ�˵����水ť�¼���Ӧ

		detailTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = detailTable.getSelectionModel().getSelectedIndex();
					int indexRoot = info.dataBase.bookName.indexOf(bookData.get(index).getBookName());
					new bookReader(indexRoot, index).start(null);
				}
			}
		});

		backToBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookData.clear();
				searchTF.setText("");
				for (int i = 0; i < info.dataBase.bookName.size(); i++) {
					bookData.add(new manageData(info.dataBase.bookName.get(i), info.dataBase.bookISBN.get(i),
							info.dataBase.bookClass.get(i), info.dataBase.bookAuthor.get(i),
							info.dataBase.bookPress.get(i), info.dataBase.bookLeft.get(i) + "",
							info.dataBase.bookTotal.get(i) + "", info.dataBase.bookPrice.get(i) + ""));
				}
			}
		});

		// �����¼���Ӧ
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
						bookData.add(new manageData(name, ISBN, Class, author, bookPress, leftS, totalS, priceS));
					}
				}
			}
		});

		// ���������¼���Ӧ
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
							bookData.add(new manageData(name, ISBN, Class, author, bookPress, leftS, totalS, priceS));
						}
					}
				} else {
					infoL.setText("����ѡ��һ�������ǩ");
				}
			}
		});

		// ɾ������Ŀ¼
		delClassBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int inputIndex = selectClassCB.getSelectionModel().getSelectedIndex();
				if (inputIndex != -1) {
					String className = info.dataBase.className.get(inputIndex);
					if (!info.dataBase.bookClass.contains(className)) {
						info.dataBase.className.remove(inputIndex);
						selectClassCB.getItems().clear();
						for (int i = 0; i < info.dataBase.className.size(); i++) {
							selectClassCB.getItems().add(info.dataBase.className.get(i));
						}
						java.io.File bookClaaP = new java.io.File(info.impPath.book_class_json);
						try {
							PrintWriter write = new PrintWriter(
									new OutputStreamWriter(new FileOutputStream(bookClaaP)));
							for (int i = 0; i < info.dataBase.className.size(); i++) {
								write.println(utils.AESCode.getAESWord(info.dataBase.className.get(i)));
							}
							write.close();
						} catch (Exception error) {
							new utils.systemError("PMCBMSFD001").start(null);
							bookStage.close();
						}
					} else {
						infoL.setText("��������ͼ�飬��ֹɾ��");
					}
				}
			}
		});

		// �½������¼���Ӧ
		submitBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String inputTF = creatTF.getText();
				boolean valid = true;
				for (int i = 0; i < inputTF.length(); i++) {
					if (!Character.isLetterOrDigit(inputTF.charAt(i))) {
						valid = false;
					}
				}
				if (valid) {
					if (info.dataBase.className.contains(inputTF)) {
						infoL.setText("����Ŀ¼�Ѿ�����");
						creatTF.setText("");
					} else {
						info.dataBase.className.add(inputTF);
						selectClassCB.getItems().clear();
						for (int i = 0; i < info.dataBase.className.size(); i++) {
							selectClassCB.getItems().add(info.dataBase.className.get(i));
						}
						java.io.File bookClaaP = new java.io.File(info.impPath.book_class_json);
						try {
							PrintWriter write = new PrintWriter(
									new OutputStreamWriter(new FileOutputStream(bookClaaP)));
							for (int i = 0; i < info.dataBase.className.size(); i++) {
								write.println(utils.AESCode.getAESWord(info.dataBase.className.get(i)));
							}
							write.close();
						} catch (Exception error) {
							new utils.systemError("PMCBMSFD002").start(null);
							bookStage.close();
						}
					}
				} else {
					infoL.setText("���ƴ��ڷǷ��ַ�");
					creatTF.setText("");
				}
			}
		});

		// �½�ͼ���¼���Ӧ
		newBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new newBook().start(null);
			}
		});

		// �������¼���Ӧ
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(bookStage);
				obj.start(null);
			}
		});

		// ����Ϊ��ർ�����¼���Ӧ
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

		superBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bookStage.close();
				new superOp().start(null);
			}
		});
		// ��������Լ��

		Scene scene = new Scene(userPane);
		bookStage.setScene(scene);
		bookStage.setResizable(false);
		bookStage.setTitle("����ͼ��ݹ���ƽ̨");
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
