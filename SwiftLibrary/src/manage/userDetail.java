package manage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.json.JSONObject;

/**
 * userDetail:�û�����ҳ
 * 
 * @author ����Swift
 *
 */
public class userDetail extends Application {
	int nowDoing;
	int index;
	Stage detailStage = new Stage();

	userDetail() {

	}

	userDetail(int nowDoing, int index) {
		this.nowDoing = nowDoing;
		this.index = index;
	}

	public void start(Stage primaryStage) {
		Stage detailStage = new Stage();
		detailStage.initStyle(StageStyle.UNDECORATED);
		detailStage.initStyle(StageStyle.TRANSPARENT);
		detailStage.initModality(Modality.APPLICATION_MODAL);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		detailStage.setWidth(primaryScreenBounds.getWidth() / 3);
		detailStage.setHeight(primaryScreenBounds.getHeight() / 2);
		VBox vbox = new VBox();
		vbox.setStyle("-fx-background-radius:10px 10px 10px 10px;-fx-background-color: #FAEBD7;");
		vbox.setPadding(new Insets(20, 20, 20, 20));
		vbox.setSpacing(10);

		Label infoL = new Label("����");
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

		Label nameTL = new Label("ѧ��\t");
		Label nameTrueL = new Label("����\t");
		Label phoneTL = new Label("�ֻ���\t");
		Label creditTL = new Label("����\t");
		Label validTL = new Label("״̬\t");

		Label nameTF = new Label();
		nameTF.setText(info.dataBase.name.get(nowDoing));
		Label trueTF = new Label();
		trueTF.setText(info.dataBase.trueName.get(nowDoing));
		Label phoneTF = new Label();
		phoneTF.setText(info.dataBase.phone.get(nowDoing));
		Label creditTF = new Label();
		creditTF.setText(info.dataBase.credit.get(nowDoing) + "");
		Label validTF = new Label();
		if (info.dataBase.valid.get(nowDoing)) {
			validTF.setText("�˻����");
		} else {
			validTF.setText("�˻�������");
		}

		nameTL.setTextFill(Color.BLACK);
		nameTrueL.setTextFill(Color.BLACK);
		phoneTL.setTextFill(Color.BLACK);
		creditTL.setTextFill(Color.BLACK);
		validTL.setTextFill(Color.BLACK);
		nameTF.setTextFill(Color.BLACK);
		trueTF.setTextFill(Color.BLACK);
		phoneTF.setTextFill(Color.BLACK);
		creditTF.setTextFill(Color.BLACK);
		validTF.setTextFill(Color.BLACK);

		mainPane.add(nameTL, 0, 1);
		mainPane.add(nameTrueL, 0, 2);
		mainPane.add(phoneTL, 0, 3);
		mainPane.add(creditTL, 0, 4);
		mainPane.add(validTL, 0, 5);

		mainPane.add(nameTF, 1, 1);
		mainPane.add(trueTF, 1, 2);
		mainPane.add(phoneTF, 1, 3);
		mainPane.add(creditTF, 1, 4);
		mainPane.add(validTF, 1, 5);

		Separator lineA = new Separator();
		lineA.setMinWidth(primaryScreenBounds.getWidth() / 3);

		String borrowList = "";
		java.io.File bookBorrow = new java.io.File(
				System.getProperty("user.dir") + "\\database\\cloud\\user\\" + info.dataBase.name.get(nowDoing));
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(bookBorrow));
			Scanner input = new Scanner(inputReader);
			borrowList = borrowList + "\t";
			while (input.hasNextLine()) {
				String jsonStr = utils.AESCode.getOrgWord(input.nextLine());
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				borrowList = borrowList + "\n\t" + map.get("book") + " ( " + map.get("time") + " )";
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PMCUMSFR001");
			detailStage.close();
		}

		Label borrowT = new Label("����");
		borrowT.setStyle("-fx-font-size:20;");
		borrowT.setTextFill(Color.BROWN);
		Label detailBorrow = new Label();
		if (borrowList.equals("")) {
			detailBorrow.setText("\t���޽��ļ�¼");
		} else {
			detailBorrow.setText(borrowList);
		}
		detailBorrow.setTextFill(Color.BLACK);

		Label errorL = new Label();
		errorL.setTextFill(Color.RED);
		rootPane.getChildren().addAll(mainPane, lineA, borrowT, detailBorrow);
		scPane.setContent(rootPane);

		HBox opPane = new HBox(5);
		Button cancelBt = new Button("����");
		Button activeBt = new Button("������");
		Button unactiveBt = new Button("����");
		if (info.dataBase.valid.get(nowDoing)) {
			activeBt.setDisable(true);
		} else {
			unactiveBt.setDisable(true);
		}
		opPane.getChildren().addAll(cancelBt, activeBt, unactiveBt);

		vbox.getChildren().addAll(infoL, scPane, opPane, errorL);

		// ******************** �¼���Ӧ ********************
		activeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				info.dataBase.valid.set(nowDoing, true);
				fileWork();
				unactiveBt.setDisable(false);
				activeBt.setDisable(true);
				userManage.data.get(index).setUserValid(true + "");
				validTF.setText("�˻��Ѽ���");
			}
		});

		unactiveBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				info.dataBase.valid.set(nowDoing, false);
				fileWork();
				userManage.data.get(index).setUserValid(false + "");
				unactiveBt.setDisable(true);
				activeBt.setDisable(false);
				validTF.setText("�˻��Ѷ���");
			}
		});

		cancelBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				detailStage.close();
			}
		});

		// ******************** ��ť�¼���Ӧ���� ********************

		Scene scene = new Scene(vbox);
		scene.setFill(null);
		detailStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		detailStage.setScene(scene);
		detailStage.setTitle("����");
		detailStage.show();
	}

	protected void fileWork() {
		try {
			PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info.impPath.user_json)));
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
			new utils.systemError("PLCCMBFC007").start(null);
			detailStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
