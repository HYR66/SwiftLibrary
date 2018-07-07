package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * register:ע����Ϣ����
 * 
 * @author ����Swift
 *
 */
public class register extends Application {
	private static Stage regStage;
	private TextField nameIn;
	private PasswordField pwAIn;
	private PasswordField pwBIn;
	private TextField phoneIn;
	private Label infoL;

	public void start(Stage primaryStage) {
		// �ܳɽ����趨
		regStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		regStage.setX(primaryScreenBounds.getMinX());
		regStage.setY(primaryScreenBounds.getMinY());
		regStage.setWidth(primaryScreenBounds.getWidth());
		regStage.setHeight(primaryScreenBounds.getHeight());

		// ���öѵ������
		StackPane regPane = new StackPane();
		regPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		// һ�������ؼ�
		HBox mainPane = new HBox();
		mainPane.setMaxWidth(primaryScreenBounds.getWidth() / 1.5);
		mainPane.setMaxHeight(primaryScreenBounds.getHeight() * 0.7);
		mainPane.setStyle("-fx-background-radius:15px;-fx-opacity: 0.8;-fx-background-color: WHITE;");

		// ����ҳ���һ���ؼ�
		StackPane leftPane = new StackPane();
		leftPane.setPrefWidth(primaryScreenBounds.getWidth() / 3);
		leftPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.7);
		leftPane.setStyle("-fx-background-radius:15px 0px 0px 15px;-fx-background-color: #46A3FF;");
		DropShadow shadow = new DropShadow();
		leftPane.setEffect(shadow);

		VBox leftP = new VBox(10);
		leftP.setAlignment(Pos.CENTER);
		Label titleL = new Label();
		titleL.setText("����ͼ���");
		titleL.setTextFill(Color.WHITE);
		titleL.setStyle("-fx-font-size:35;");
		Label authorL = new Label("���ʱ�� �� Ω��Ωһ");
		authorL.setTextFill(Color.WHITE);
		authorL.setStyle("-fx-font-size:16;");
		leftP.getChildren().addAll(titleL, authorL);
		leftPane.getChildren().addAll(leftP);

		// ����ҳ���һ���ؼ�
		VBox rightPane = new VBox(15);
		rightPane.setPadding(new Insets(40, 40, 40, 40));
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setPrefWidth(primaryScreenBounds.getWidth() / 3);
		rightPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.7);

		HBox RtitleP = new HBox(15);
		Label RtitleL = new Label("ע��");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label nameL = new Label("ѧ��\t:");
		nameL.setStyle("-fx-font-size:15;");
		Label pwAL = new Label("����\t:");
		pwAL.setStyle("-fx-font-size:15;");
		Label pwBL = new Label("�ٴ�����\t:");
		pwBL.setStyle("-fx-font-size:15;");
		Label phoneL = new Label("�ֻ���\t:");
		phoneL.setStyle("-fx-font-size:15;");
		nameIn = new TextField();
		pwAIn = new PasswordField();
		pwBIn = new PasswordField();
		phoneIn = new TextField();
		inputP.add(nameL, 0, 0);
		inputP.add(nameIn, 1, 0);
		inputP.add(pwAL, 0, 1);
		inputP.add(pwAIn, 1, 1);
		inputP.add(pwBL, 0, 2);
		inputP.add(pwBIn, 1, 2);
		inputP.add(phoneL, 0, 3);
		inputP.add(phoneIn, 1, 3);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		Button okBt = new Button("ע��");
		okBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		okBt.setStyle("-fx-background-color: #E0FFFF;");
		Button backBt = new Button("����");
		backBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		backBt.setStyle("-fx-background-color:#E0FFFF;");

		// ��ť������Ч
		DropShadow shadowR = new DropShadow();
		shadowR.setColor(Color.AQUA);
		okBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			okBt.setEffect(shadowR);
		});
		okBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			okBt.setEffect(null);
		});
		okBt.setOnAction(new okHandler());
		backBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			backBt.setEffect(shadowR);
		});
		backBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			backBt.setEffect(null);
		});
		backBt.setOnAction(new backHandler());
		buttonP.getChildren().addAll(okBt, backBt);

		// �ֻ�����򰴼���Ӧ
		phoneIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && nameIn.getText() != null && !nameIn.getText().equals("")
					&& pwAIn.getText() != null && !pwAIn.getText().equals("") && pwBIn.getText() != null
					&& !pwBIn.getText().equals("") && phoneIn.getText() != null && !phoneIn.getText().equals("");
			if (valid) {
				okHandler enter = new okHandler();
				enter.handle(null);
			}
		});

		// ��Ϣ��ʾ��
		GridPane infoP = new GridPane();
		infoL = new Label(" ");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		regPane.getChildren().add(mainPane);

		// �ܳɿؼ�
		Scene scene = new Scene(regPane);
		regStage.setScene(scene);
		regStage.setResizable(false);
		regStage.setTitle("����ͼ��ݡ�ע��");
		regStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		regStage.show();
	}

	// ���ذ�ť�¼���Ӧ
	public class backHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			login obj = new login();
			obj.start(null);
			regStage.close();
		}
	}

	// ע�ᰴť�¼���Ӧ
	public class okHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			boolean valid = nameIn.getText() == null || nameIn.getText().equals("") || pwAIn.getText() == null
					|| pwBIn.getText().equals("") || pwBIn.getText() == null || pwBIn.getText().equals("")
					|| phoneIn.getText() == null || phoneIn.getText().equals("");
			if (valid) {
				infoL.setText("������������Ϣ");
			} else {
				String nameS = nameIn.getText();
				String pwAS = pwAIn.getText();
				String pwBS = pwBIn.getText();
				String phoneS = phoneIn.getText();
				if (info.dataBase.name.contains(nameS) || info.dataBase.adminName.equals(nameS)) {
					infoL.setText("��ѧ����ע��");
					nameIn.setText("");
					pwAIn.setText("");
					pwBIn.setText("");
					phoneIn.setText("");
				} else if (!pwAS.equals(pwBS)) {
					infoL.setText("�����������벻��");
					pwAIn.setText("");
					pwBIn.setText("");
				} else if (pwAS.length() < 6) {
					infoL.setText("���볤�Ȳ��ɶ�����λ");
					pwAIn.setText("");
					pwBIn.setText("");
				} else if (phoneS.length() != 11) {
					infoL.setText("�ֻ��ŷǷ�");
				} else {
					new checkNUPT(nameS, pwAS, phoneS).start(null);
					regStage.close();
				}
			}

		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
