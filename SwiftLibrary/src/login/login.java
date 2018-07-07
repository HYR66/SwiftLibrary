package login;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

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
import net.sf.json.JSONObject;

/**
 * login:��¼����
 * 
 * @author ����Swift
 *
 */
public class login extends Application {
	private static Stage loginStage;
	private Label infoL;
	private TextField idIn;
	private PasswordField pwIn;

	public void start(Stage primaryStage) {
		// �ܳɽ����趨
		loginStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		loginStage.setX(primaryScreenBounds.getMinX());
		loginStage.setY(primaryScreenBounds.getMinY());
		loginStage.setWidth(primaryScreenBounds.getWidth());
		loginStage.setHeight(primaryScreenBounds.getHeight());

		// ���öѵ������
		StackPane loginPane = new StackPane();
		loginPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		// һ�������ؼ�
		HBox mainPane = new HBox();
		mainPane.setMaxWidth(primaryScreenBounds.getWidth() / 1.6);
		mainPane.setMaxHeight(primaryScreenBounds.getHeight() * 0.6);
		mainPane.setStyle("-fx-background-radius:15px;-fx-opacity: 0.8;-fx-background-color: WHITE;");

		// ����ҳ���һ���ؼ�
		StackPane leftPane = new StackPane();
		leftPane.setPrefWidth(primaryScreenBounds.getWidth() / 3.2);
		leftPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.6);
		leftPane.setStyle("-fx-background-radius:15px 0px 0px 15px;-fx-background-color: #46A3FF;");
		DropShadow shadow = new DropShadow();
		leftPane.setEffect(shadow);

		// ����ҳ�������ؼ�
		VBox leftP = new VBox(10);
		leftP.setAlignment(Pos.CENTER);
		Label titleL = new Label();
		titleL.setText("����ͼ���");
		titleL.setTextFill(Color.WHITE);
		titleL.setStyle("-fx-font-size:35;");
		Label authorL = new Label();
		authorL.setText("���ʱ�� �� Ω��Ωһ");
		authorL.setTextFill(Color.WHITE);
		authorL.setStyle("-fx-font-size:16;");
		leftP.getChildren().addAll(titleL, authorL);
		leftPane.getChildren().addAll(leftP);

		// ����ҳ���һ���ؼ�
		VBox rightPane = new VBox(15);
		rightPane.setPadding(new Insets(40, 40, 40, 40));
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setPrefWidth(primaryScreenBounds.getWidth() / 3.2);
		rightPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.6);

		// �����������ؼ�
		HBox RtitleP = new HBox(15);
		Label RtitleL = new Label("��¼");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label idL = new Label("�˻�\t:");
		idL.setStyle("-fx-font-size:15;");
		Label pwL = new Label("����\t:");
		pwL.setStyle("-fx-font-size:15;");
		idIn = new TextField();
		pwIn = new PasswordField();
		inputP.add(idL, 0, 0);
		inputP.add(idIn, 1, 0);
		inputP.add(pwL, 0, 1);
		inputP.add(pwIn, 1, 1);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		Button okBt = new Button("��¼");
		okBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		okBt.setStyle("-fx-background-color: #E0FFFF;");
		Button regBt = new Button("ע��");
		regBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		regBt.setStyle("-fx-background-color:#E0FFFF;");

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
		regBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			regBt.setEffect(shadowR);
		});
		regBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			regBt.setEffect(null);
		});
		regBt.setOnAction(new regHandler());
		buttonP.getChildren().addAll(okBt, regBt);

		// ��������򰴼���Ӧ
		pwIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && !((pwIn.getText() == null) && (pwIn.getText()).equals(""))
					&& !((idIn.getText() == null) && !((idIn.getText()).equals("")));
			if (valid) {
				okHandler enter = new okHandler();
				enter.handle(null);
			}
		});

		// ��Ϣ��ʾ��
		GridPane infoP = new GridPane();
		infoL = new Label("");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		// �ܳɿؼ�
		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		loginPane.getChildren().add(mainPane);

		Scene scene = new Scene(loginPane);
		loginStage.setScene(scene);
		loginStage.setResizable(false);
		loginStage.setTitle("����ͼ��ݡ���¼");
		loginStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		loginStage.show();
	}

	// ע�ᰴť�����¼���Ӧ
	public class regHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			fileReader();
			if (examCheck.question.size() == 0) {
				// ���Ϊ��
				new register().start(null);
				loginStage.close();
			} else {
				// ע��ǰ�����ʸ�ⶨ
				new examCheck().start(null);
				loginStage.close();
			}
		}
	}

	// �ύ��ť�����¼���Ӧ
	public class okHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			boolean emptyIn = idIn.getText().equals("") || idIn.getText() == null || pwIn.getText().equals("")
					|| pwIn.getText() == null;
			if (emptyIn) {
				infoL.setText("����������");
			} else {
				if (idIn.getText().equals(info.dataBase.adminName) && pwIn.getText().equals(info.dataBase.adminPw)) {
					new manage.adminMain().start(null);
					loginStage.close();
				} else {
					String idS = idIn.getText().toUpperCase();
					String pwS = pwIn.getText();
					if (info.dataBase.name.contains(idS)) {
						int index = info.dataBase.name.indexOf(idS);
						if (info.dataBase.pw.get(index).equals(pwS)) {
							if (info.dataBase.valid.get(index)) {
								user.userMain stuUI = new user.userMain(index);
								stuUI.start(null);
								loginStage.close();
							} else {
								loginFail fail = new loginFail();
								fail.start(null);
								loginStage.close();
							}
						} else {
							infoL.setText("���������");
							pwIn.setText("");
						}
					} else {
						infoL.setText("�˻���δע��");
						idIn.setText("");
						pwIn.setText("");
					}
				}
			}
		}
	}

	// ��ȡ������Դ�ļ�
	private void fileReader() {
		examCheck.question.clear();
		examCheck.choiceA.clear();
		examCheck.choiceB.clear();
		examCheck.choiceC.clear();
		examCheck.choiceD.clear();
		java.io.File examF = new java.io.File(info.impPath.exam_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(examF));
			Scanner input = new Scanner(inputReader);
			examCheck.passScore = utils.AESCode.getOrgWord(input.nextLine());
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				examCheck.question.add(map.get("question"));
				examCheck.choiceA.add(map.get("choiceA"));
				examCheck.choiceB.add(map.get("choiceB"));
				examCheck.choiceC.add(map.get("choiceC"));
				examCheck.choiceD.add(map.get("choiceD"));
				examCheck.key.add(map.get("key"));
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR006").start(null);
			loginStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
