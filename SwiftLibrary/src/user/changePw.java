package user;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javafx.application.Application;
import javafx.application.Platform;
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
 * changePw:ͨ������������ϵͳͨ�Ž����޸�����
 * 
 * @author ����Swift
 *
 */
public class changePw extends Application {
	private static Stage checkStage;
	private Label infoL;
	private Button regBt;
	private Button backBt;
	private PasswordField pwIn;
	private PasswordField newPwIn;
	// �����ֻ������±�
	private int indexDot = 0;
	private int userIndex = 0;
	private String getTrueName = "";

	public changePw() {

	}

	public changePw(int userIndex) {
		this.userIndex = userIndex;
	}

	public void start(Stage primaryStage) {
		// �ܳɽ����趨
		checkStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		checkStage.setX(primaryScreenBounds.getMinX());
		checkStage.setY(primaryScreenBounds.getMinY());
		checkStage.setWidth(primaryScreenBounds.getWidth());
		checkStage.setHeight(primaryScreenBounds.getHeight());

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
		Label RtitleL = new Label("�����޸�");
		RtitleL.setStyle("-fx-font-size:30;");
		RtitleP.getChildren().addAll(RtitleL);

		Separator line = new Separator();

		GridPane inputP = new GridPane();
		inputP.setVgap(20);
		inputP.setHgap(20);
		inputP.setAlignment(Pos.CENTER);
		Label idL = new Label("����ѧ��\t:");
		idL.setStyle("-fx-font-size:15;");
		Label pwL = new Label("��������\t:");
		pwL.setStyle("-fx-font-size:15;");
		Label idIn = new Label(info.dataBase.name.get(userIndex));
		idIn.setStyle("-fx-font-size:15;");
		pwIn = new PasswordField();
		Label newPwL = new Label("��������\t:");
		newPwIn = new PasswordField();
		inputP.add(idL, 0, 0);
		inputP.add(idIn, 1, 0);
		inputP.add(pwL, 0, 1);
		inputP.add(pwIn, 1, 1);
		inputP.add(newPwL, 0, 2);
		inputP.add(newPwIn, 1, 2);

		VBox buttonP = new VBox(10);
		buttonP.setAlignment(Pos.CENTER);
		regBt = new Button("�����޸�");
		regBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		regBt.setStyle("-fx-background-color:#E0FFFF;");
		backBt = new Button("ȡ���޸�");
		backBt.setPrefWidth(primaryScreenBounds.getWidth() / 4.7);
		backBt.setStyle("-fx-background-color: #E0FFFF;");

		// ��ť������Ч
		DropShadow shadowR = new DropShadow();
		shadowR.setColor(Color.AQUA);
		regBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			regBt.setEffect(shadowR);
		});
		regBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			regBt.setEffect(null);
		});
		regBt.setOnAction(new regHandler());
		backBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			backBt.setEffect(shadowR);
		});
		backBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			backBt.setEffect(null);
		});
		backBt.setOnAction(new backHandler());
		buttonP.getChildren().addAll(regBt, backBt);

		// ��������򰴼���Ӧ
		newPwIn.setOnKeyPressed(e -> {
			boolean valid = e.getCode() == KeyCode.ENTER && !((pwIn.getText() == null) && (pwIn.getText()).equals(""))
					&& !(newPwIn.getText() == null) && !(newPwIn.getText().equals(""))
					&& !((idIn.getText() == null) && !((idIn.getText()).equals("")));
			if (valid) {
				regHandler enter = new regHandler();
				enter.handle(null);
			}
		});

		// ��Ϣ��ʾ��
		GridPane infoP = new GridPane();
		infoL = new Label("�����ŵ���ᴢ������������Ϣ");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.add(infoL, 0, 0);

		// �ܳɿؼ�
		rightPane.getChildren().addAll(RtitleP, line, inputP, buttonP, infoP);
		mainPane.getChildren().addAll(leftPane, rightPane);
		loginPane.getChildren().add(mainPane);

		Scene scene = new Scene(loginPane);
		checkStage.setScene(scene);
		checkStage.setResizable(false);
		checkStage.setTitle("����ͼ��ݡ������޸�");
		checkStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		checkStage.show();
	}

	// ���ذ�ť�����¼���Ӧ
	public class backHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			new userMain(userIndex).start(null);
			checkStage.close();
		}
	}

	// ע�ᰴť�����¼���Ӧ
	public class regHandler implements EventHandler<ActionEvent> {
		@SuppressWarnings("deprecation")
		public void handle(ActionEvent e) {
			String userPw = pwIn.getText();
			String newPw = newPwIn.getText();
			if (userPw == null || userPw.equals("") || newPw == null || newPw.equals("")) {
				infoL.setText("����д����");
				return;
			}

			if (newPw.length() < 6) {
				infoL.setText("���볤�ȹ���");
				return;
			}

			infoL.setText("���Ժ�����ȷ���� ");
			pwIn.setDisable(true);
			newPwIn.setDisable(true);
			regBt.setDisable(true);
			backBt.setDisable(true);

			// ���ֱ䶯����
			Thread thread = new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(300);
						indexDot++;
						if (indexDot == 4) {
							indexDot = 0;
						}

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								if (indexDot == 0) {
									infoL.setText("���Ժ�����ȷ���� ");
								} else if (indexDot == 1) {
									infoL.setText("���Ժ�����ȷ���� >");
								} else if (indexDot == 2) {
									infoL.setText("���Ժ�����ȷ���� >>");
								} else {
									infoL.setText("���Ժ�����ȷ���� >>>");
								}
							}
						});
					} catch (InterruptedException e1) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								new utils.systemError("PLCCMBFC005").start(null);
								checkStage.close();
							}
						});
					}
				}
			});
			thread.start();

			// ����״̬��֤
			new Thread(() -> {
				boolean okHTTP = true;
				Callable<String> callable = new Callable<String>() {
					@Override
					public String call() throws Exception {
						URL url = new URL("http://jwxt.njupt.edu.cn");
						InputStream in = url.openStream();
						in.close();
						return "0";
					}
				};
				ExecutorService es = Executors.newFixedThreadPool(1);
				Future<String> future = es.submit(callable);
				try {
					future.get(2000, TimeUnit.MILLISECONDS).toString();
				} catch (ExecutionException ex) {
					okHTTP = false;
				} catch (InterruptedException ex) {
					okHTTP = false;
				} catch (TimeoutException ex) {
					okHTTP = false;
				}

				if (!okHTTP) {
					thread.stop();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							infoL.setText("�����쳣�����ٴγ���");
							pwIn.setDisable(false);
							newPwIn.setDisable(false);
							regBt.setDisable(false);
							backBt.setDisable(false);
						}
					});
				} else {
					// ���ù��߰���ȡ״̬
					try {
						getTrueName = utils.connectNUPT.getName(info.dataBase.name.get(userIndex), userPw);
					} catch (Exception ex) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								new utils.systemError("PLCCMBFC006").start(null);
								checkStage.close();
							}
						});
					}

					thread.stop();

					// ��ý��
					if (getTrueName != null && !getTrueName.equals(info.dataBase.trueName.get(userIndex))) {
						// ��֤ͨ��
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								infoL.setText("��֤ͨ����д������Ϣ��");
								info.dataBase.pw.set(userIndex, newPw);
								writeInfo();
								new login.login().start(null);
								checkStage.close();
							}
						});
					} else {
						// ��֤ʧ��
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								infoL.setText("��֤ʧ��");
								pwIn.setDisable(false);
								newPwIn.setDisable(false);
								regBt.setDisable(false);
								backBt.setDisable(false);
							}
						});
					}
				}
			}).start();
		}

		// д��ע����Ϣ
		protected void writeInfo() {
			// ����JSON����
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
				new utils.systemError("PMCUMSFR001").start(null);
				checkStage.close();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
