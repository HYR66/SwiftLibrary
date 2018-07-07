package manage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
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
 * testManage:试题管理器
 * 
 * @author 蜂鸟Swift
 *
 */

public class testManage extends Application {
	private int nowDoing = 0;
	private String passScore;
	private ArrayList<String> question = new ArrayList<String>();
	private ArrayList<String> choiceA = new ArrayList<String>();
	private ArrayList<String> choiceB = new ArrayList<String>();
	private ArrayList<String> choiceC = new ArrayList<String>();
	private ArrayList<String> choiceD = new ArrayList<String>();
	private ArrayList<String> key = new ArrayList<String>();
	Stage examStage;
	Label infoL;

	public void start(Stage primaryStage) {
		examStage = new Stage();
		fileReader();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		examStage.setX(primaryScreenBounds.getMinX());
		examStage.setY(primaryScreenBounds.getMinY());
		examStage.setWidth(primaryScreenBounds.getWidth());
		examStage.setHeight(primaryScreenBounds.getHeight());

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
		testBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		testBt.setTextFill(Color.WHITE);
		testBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		testBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);

		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(20, 20, 20, 20));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setStyle("-fx-background-color: WHITE;-fx-opacity: 0.5;");
		contentPane.setPrefWidth(primaryScreenBounds.getWidth() / 1.2);

		VBox detailPane = new VBox(10);
		detailPane.setPadding(new Insets(20, 20, 20, 20));
		detailPane.setMinWidth(primaryScreenBounds.getWidth() / 1.5);

		HBox titlePane = new HBox(10);
		Label titleL = new Label("试卷查看器");
		titleL.setStyle("-fx-font-family:KaiTi;-fx-font-size: 30px;");
		titlePane.getChildren().addAll(titleL);

		VBox quizTitlePane = new VBox(10);
		quizTitlePane.setPadding(new Insets(20, 20, 20, 20));
		Label quizTitle = new Label();
		quizTitle.setText("第 " + (nowDoing + 1) + " 题");
		if (question.isEmpty()) {
			quizTitle.setText("题库为空");
		}
		quizTitle.setStyle("-fx-font-size: 20px;");

		Label questionL = new Label();
		questionL.setWrapText(true);
		questionL.setStyle("-fx-font-size: 20px;");
		questionL.setText("\t题库为空");

		quizTitlePane.getChildren().addAll(quizTitle, questionL);

		GridPane choicePane = new GridPane();
		choicePane.setPadding(new Insets(20, 20, 20, 20));
		choicePane.setMinHeight(180);
		choicePane.setVgap(5);
		choicePane.setHgap(5);

		Label choiceAs = new Label("A. ");
		choiceAs.setStyle("-fx-font-size: 20px;");
		Label choiceBs = new Label("B. ");
		choiceBs.setStyle("-fx-font-size: 20px;");
		Label choiceCs = new Label("C. ");
		choiceCs.setStyle("-fx-font-size: 20px;");
		Label choiceDs = new Label("D. ");
		choiceDs.setStyle("-fx-font-size: 20px;");

		Label choiceAL = new Label("题库为空");
		Label choiceBL = new Label("题库为空");
		Label choiceCL = new Label("题库为空");
		Label choiceDL = new Label("题库为空");
		choiceAL.setWrapText(true);
		choiceBL.setWrapText(true);
		choiceCL.setWrapText(true);
		choiceDL.setWrapText(true);

		choiceAL.setStyle("-fx-font-size: 20px;");
		choiceBL.setStyle("-fx-font-size: 20px;");
		choiceCL.setStyle("-fx-font-size: 20px;");
		choiceDL.setStyle("-fx-font-size: 20px;");
		if (question.size() != 0) {
			questionL.setText(question.get(nowDoing));
			choiceAL.setText(choiceA.get(nowDoing));
			choiceBL.setText(choiceB.get(nowDoing));
			choiceCL.setText(choiceC.get(nowDoing));
			choiceDL.setText(choiceD.get(nowDoing));
		}

		choicePane.add(choiceAs, 0, 0);
		choicePane.add(choiceBs, 0, 1);
		choicePane.add(choiceCs, 0, 2);
		choicePane.add(choiceDs, 0, 3);
		choicePane.add(choiceAL, 1, 0);
		choicePane.add(choiceBL, 1, 1);
		choicePane.add(choiceCL, 1, 2);
		choicePane.add(choiceDL, 1, 3);

		GridPane opPane = new GridPane();
		opPane.setHgap(10);
		Button beforeBt = new Button("上一题");
		beforeBt.setDisable(true);
		beforeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		beforeBt.setTextFill(Color.WHITE);
		Button nextBt = new Button("下一题");
		nextBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		nextBt.setTextFill(Color.WHITE);
		Button editExamBt = new Button("编辑试题");
		editExamBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		editExamBt.setTextFill(Color.WHITE);
		DropShadow shadowOp = new DropShadow();
		beforeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			beforeBt.setEffect(shadowOp);
		});
		beforeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			beforeBt.setEffect(null);
		});
		nextBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			nextBt.setEffect(shadowOp);
		});
		nextBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			nextBt.setEffect(null);
		});
		editExamBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			editExamBt.setEffect(shadowOp);
		});
		editExamBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			editExamBt.setEffect(null);
		});
		if (question.size() == 0) {
			beforeBt.setDisable(true);
			nextBt.setDisable(true);
		} else if (nowDoing == question.size() - 1) {
			nextBt.setDisable(true);
		}
		if (nowDoing == 0) {
			beforeBt.setDisable(true);
		}

		if (question.size() != 0) {
			questionL.setText("\t" + question.get(nowDoing));
			choiceAL.setText(choiceA.get(nowDoing));
			choiceBL.setText(choiceB.get(nowDoing));
			choiceCL.setText(choiceC.get(nowDoing));
			choiceDL.setText(choiceD.get(nowDoing));
			questionL.setText("\t" + question.get(nowDoing));
			choiceAL.setText(choiceA.get(nowDoing));
			choiceBL.setText(choiceB.get(nowDoing));
			choiceCL.setText(choiceC.get(nowDoing));
			choiceDL.setText(choiceD.get(nowDoing));

			choiceAL.setTextFill(Color.BLACK);
			choiceBL.setTextFill(Color.BLACK);
			choiceCL.setTextFill(Color.BLACK);
			choiceDL.setTextFill(Color.BLACK);
			choiceAs.setTextFill(Color.BLACK);
			choiceBs.setTextFill(Color.BLACK);
			choiceCs.setTextFill(Color.BLACK);
			choiceDs.setTextFill(Color.BLACK);
			switch (key.get(nowDoing)) {
			case "A":
				choiceAL.setTextFill(Color.BLUEVIOLET);
				choiceAs.setTextFill(Color.BLUEVIOLET);
				break;
			case "B":
				choiceBL.setTextFill(Color.BLUEVIOLET);
				choiceBs.setTextFill(Color.BLUEVIOLET);
				break;
			case "C":
				choiceCL.setTextFill(Color.BLUEVIOLET);
				choiceCs.setTextFill(Color.BLUEVIOLET);
				break;
			case "D":
				choiceDL.setTextFill(Color.BLUEVIOLET);
				choiceDs.setTextFill(Color.BLUEVIOLET);
			}
		}

		opPane.add(beforeBt, 1, 0);
		opPane.add(nextBt, 2, 0);
		opPane.add(new Label(), 3, 0);
		opPane.add(editExamBt, 4, 0);

		GridPane passPane = new GridPane();
		passPane.setVgap(10);
		passPane.setHgap(20);
		Label titlePP = new Label("通过分:");
		TextField gradesTF = new TextField(passScore);
		gradesTF.setEditable(false);
		Button editGradeBt = new Button("编辑");
		editGradeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		editGradeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			editGradeBt.setEffect(shadowOp);
		});
		editGradeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			editGradeBt.setEffect(null);
		});
		editGradeBt.setTextFill(Color.WHITE);
		Button saveGradeBt = new Button("保存");
		saveGradeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		saveGradeBt.setTextFill(Color.WHITE);
		saveGradeBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			saveGradeBt.setEffect(shadowOp);
		});
		saveGradeBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			saveGradeBt.setEffect(null);
		});
		saveGradeBt.setDisable(true);
		passPane.add(titlePP, 0, 0);
		passPane.add(gradesTF, 1, 0);
		passPane.add(editGradeBt, 2, 0);
		passPane.add(saveGradeBt, 3, 0);

		editGradeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gradesTF.setEditable(true);
				editGradeBt.setDisable(true);
				saveGradeBt.setDisable(false);
			}
		});

		saveGradeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				editGradeBt.setDisable(false);
				saveGradeBt.setDisable(true);
				String newGrades = gradesTF.getText();
				Boolean newGradesValid = true;
				for (int i = 0; i < newGrades.length(); i++) {
					if (!Character.isDigit(newGrades.charAt(i))) {
						newGradesValid = false;
					}
				}
				if (!newGradesValid) {
					infoL.setText("非法输入！");
					saveGradeBt.setDisable(true);
					editGradeBt.setDisable(false);
					gradesTF.setText(passScore);
					gradesTF.setEditable(false);
				} else {
					saveGradeBt.setDisable(true);
					editGradeBt.setDisable(false);
					passScore = newGrades;
					gradesTF.setText(passScore);
					gradesTF.setEditable(false);
					String path = info.impPath.exam_json;
					try {
						PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
						write.println(utils.AESCode.getAESWord(passScore));
						for (int i = 0; i < question.size(); i++) {
							// 生成JSON数据
							Map<String, String> params = new HashMap<>();
							params.put("question", question.get(i));
							params.put("choiceA", choiceA.get(i));
							params.put("choiceB", choiceB.get(i));
							params.put("choiceC", choiceC.get(i));
							params.put("choiceD", choiceD.get(i));
							params.put("key", key.get(i));
							JSONObject jsonObject = JSONObject.fromObject(params);
							String jsonStr = jsonObject.toString();
							write.println(utils.AESCode.getAESWord(jsonStr));
						}
						write.close();
					} catch (Exception error) {
						new utils.systemError("PMCTMSFW001").start(null);
						;
						examStage.close();
					}
				}
			}
		});

		detailPane.getChildren().addAll(titlePane, new Separator(), quizTitlePane, choicePane, opPane, new Separator(),
				passPane);

		contentPane.add(detailPane, 0, 0);

		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		editExamBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new testEdit().start(null);
			}
		});

		beforeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				nowDoing--;
				quizTitle.setText("第 " + (nowDoing + 1) + " 题");
				questionL.setText("\t" + question.get(nowDoing));
				choiceAL.setText(choiceA.get(nowDoing));
				choiceBL.setText(choiceB.get(nowDoing));
				choiceCL.setText(choiceC.get(nowDoing));
				choiceDL.setText(choiceD.get(nowDoing));

				choiceAL.setTextFill(Color.BLACK);
				choiceBL.setTextFill(Color.BLACK);
				choiceCL.setTextFill(Color.BLACK);
				choiceDL.setTextFill(Color.BLACK);
				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);
				switch (key.get(nowDoing)) {
				case "A":
					choiceAL.setTextFill(Color.BLUEVIOLET);
					choiceAs.setTextFill(Color.BLUEVIOLET);
					break;
				case "B":
					choiceBL.setTextFill(Color.BLUEVIOLET);
					choiceBs.setTextFill(Color.BLUEVIOLET);
					break;
				case "C":
					choiceCL.setTextFill(Color.BLUEVIOLET);
					choiceCs.setTextFill(Color.BLUEVIOLET);
					break;
				case "D":
					choiceDL.setTextFill(Color.BLUEVIOLET);
					choiceDs.setTextFill(Color.BLUEVIOLET);
				}

				if (nowDoing == 0) {
					beforeBt.setDisable(true);
				}
				nextBt.setDisable(false);
			}
		});

		nextBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				nowDoing++;
				quizTitle.setText("第 " + (nowDoing + 1) + " 题");
				questionL.setText("\t" + question.get(nowDoing));
				choiceAL.setText(choiceA.get(nowDoing));
				choiceBL.setText(choiceB.get(nowDoing));
				choiceCL.setText(choiceC.get(nowDoing));
				choiceDL.setText(choiceD.get(nowDoing));

				choiceAL.setTextFill(Color.BLACK);
				choiceBL.setTextFill(Color.BLACK);
				choiceCL.setTextFill(Color.BLACK);
				choiceDL.setTextFill(Color.BLACK);
				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);
				switch (key.get(nowDoing)) {
				case "A":
					choiceAL.setTextFill(Color.BLUEVIOLET);
					choiceAs.setTextFill(Color.BLUEVIOLET);
					break;
				case "B":
					choiceBL.setTextFill(Color.BLUEVIOLET);
					choiceBs.setTextFill(Color.BLUEVIOLET);
					break;
				case "C":
					choiceCL.setTextFill(Color.BLUEVIOLET);
					choiceCs.setTextFill(Color.BLUEVIOLET);
					break;
				case "D":
					choiceDL.setTextFill(Color.BLUEVIOLET);
					choiceDs.setTextFill(Color.BLUEVIOLET);
				}

				if (nowDoing == question.size() - 1) {
					nextBt.setDisable(true);
				}
				beforeBt.setDisable(false);
			}
		});

		// 菜单栏按钮事件相应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(examStage);
				obj.start(null);
			}
		});

		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new adminMain().start(null);
			}
		});

		bookMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new bookManage().start(null);
			}
		});

		bookApplyBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new applyManage().start(null);
			}
		});

		userMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new userManage().start(null);
			}
		});

		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new editInfo().start(null);
			}
		});

		superBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new superOp().start(null);
			}
		});
		// 结束菜单栏事件响应

		Scene scene = new Scene(userPane);
		examStage.setScene(scene);
		examStage.setResizable(false);
		examStage.setTitle("蜂鸟图书馆管理平台");
		examStage.initStyle(StageStyle.TRANSPARENT);
		examStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		examStage.show();

		toolBarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					new common.aboutMe().start(null);
				}
			}
		});

	}

	private void fileReader() {
		java.io.File examF = new java.io.File(info.impPath.exam_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(examF));
			Scanner input = new Scanner(inputReader);
			passScore = utils.AESCode.getOrgWord(input.nextLine());
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				question.add(map.get("question"));
				choiceA.add(map.get("choiceA"));
				choiceB.add(map.get("choiceB"));
				choiceC.add(map.get("choiceC"));
				choiceD.add(map.get("choiceD"));
				key.add(map.get("key"));
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PLCLMFFR006").start(null);
			examStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
