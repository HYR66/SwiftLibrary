package manage;

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

/**
 * userManage:�û�����
 * 
 * @author ����Swift
 *
 */
public class userManage extends Application {
	Stage userStage;
	public static ObservableList<userData> data;

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
		userStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		userStage.setX(primaryScreenBounds.getMinX());
		userStage.setY(primaryScreenBounds.getMinY());
		userStage.setWidth(primaryScreenBounds.getWidth());
		userStage.setHeight(primaryScreenBounds.getHeight());

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
		bookMBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		bookMBt.setTextFill(Color.WHITE);
		bookMBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		bookMBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
		bookMBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bookMBt.setEffect(shadowL);
		});
		bookMBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bookMBt.setEffect(null);
		});
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
		userMBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		userMBt.setTextFill(Color.WHITE);
		userMBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		userMBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
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

		// ****************** ���Ĳ��� ******************

		// ********** ������� **********

		data = FXCollections.observableArrayList();
		for (int i = 0; i < info.dataBase.name.size(); i++) {
			String name = info.dataBase.name.get(i);
			String trueName = info.dataBase.trueName.get(i);
			String phone = info.dataBase.phone.get(i);
			String creditS = info.dataBase.credit.get(i) + "";
			String validS = "";
			if (info.dataBase.valid.get(i)) {
				validS = "�";
			} else {
				validS = "����";
			}
			String timeStr = info.dataBase.regYear.get(i) + "��" + info.dataBase.regMonth.get(i) + "��"
					+ info.dataBase.regDay.get(i) + "��";
			data.add(new userData(name, trueName, phone, creditS, validS, timeStr));
		}
		TableView<userData> table = new TableView<userData>(data);
		table.setTableMenuButtonVisible(true);
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = table.getSelectionModel().getSelectedIndex();
					int indexRoot = info.dataBase.name.indexOf(data.get(index).getUserName());
					new userDetail(indexRoot, index).start(null);
				}
			}
		});
		table.setPrefWidth(primaryScreenBounds.getWidth() / 1.5);
		int widthTB = (int) (primaryScreenBounds.getWidth() / 1.5);
		table.setEditable(false);
		TableColumn<userData, String> userNameCol = new TableColumn<userData, String>("ѧ��");
		userNameCol.setPrefWidth(widthTB / 6.0);
		TableColumn<userData, String> trueNameCol = new TableColumn<userData, String>("����");
		trueNameCol.setPrefWidth(widthTB / 6.0);
		TableColumn<userData, String> userPhone = new TableColumn<userData, String>("�ֻ���");
		userPhone.setPrefWidth(widthTB / 6.0);
		TableColumn<userData, String> creditS = new TableColumn<userData, String>("���û���");
		creditS.setPrefWidth(widthTB / 6.0);
		TableColumn<userData, String> validS = new TableColumn<userData, String>("�˻�");
		validS.setPrefWidth(widthTB / 6.0);
		TableColumn<userData, String> timeCol = new TableColumn<userData, String>("ע��ʱ��");
		timeCol.setPrefWidth(widthTB / 6.0);

		table.getColumns().addAll(userNameCol, trueNameCol, userPhone, creditS, validS, timeCol);

		userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
		trueNameCol.setCellValueFactory(new PropertyValueFactory<>("userTrue"));
		userPhone.setCellValueFactory(new PropertyValueFactory<>("userPhone"));
		creditS.setCellValueFactory(new PropertyValueFactory<>("userCredit"));
		validS.setCellValueFactory(new PropertyValueFactory<>("userValid"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("userTime"));

		// ********** ��������趨 **********
		HBox showPane = new HBox();
		showPane.setAlignment(Pos.CENTER);
		showPane.getChildren().addAll(table);

		// ********** �������Ĳ����趨 **********
		VBox titleLy = new VBox();
		Label titleL = new Label("�û�����");
		titleL.setStyle("-fx-font-family:KaiTi;-fx-font-size:30px");
		titleLy.getChildren().addAll(titleL);
		detailP.getChildren().addAll(titleLy, showPane);

		// ****************** �������Ĳ��� ******************

		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("ƽ̨״̬������");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// �˵�����ť�¼���Ӧ
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(userStage);
				obj.start(null);
			}
		});

		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new adminMain().start(null);
			}
		});

		bookMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new bookManage().start(null);
			}
		});

		bookApplyBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new applyManage().start(null);
			}
		});

		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new editInfo().start(null);
			}
		});

		testBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new testManage().start(null);
			}
		});

		superBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				userStage.close();
				new superOp().start(null);
			}
		});
		// �����˵�����ť�¼���Ӧ

		Scene scene = new Scene(userPane);
		userStage.setScene(scene);
		userStage.setResizable(false);
		userStage.setTitle("����ͼ��ݹ���ƽ̨");
		userStage.initStyle(StageStyle.TRANSPARENT);
		userStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		userStage.show();

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
