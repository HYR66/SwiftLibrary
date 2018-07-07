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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
 * testEdit:试题编辑题
 * 
 * @author 蜂鸟Swift
 *
 */
public class testEdit extends Application {
	private int nowDoing = 0;
	private String newKey = "";
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

		HBox mainPane = new HBox();
		mainPane.setPadding(new Insets(30, 30, 30, 30));
		userPane.setCenter(mainPane);

		VBox naviPane = new VBox(5);
		Button homeBt = new Button("试题编辑");
		naviPane.getChildren().addAll(homeBt);

		DropShadow shadowL = new DropShadow();
		shadowL.setColor(Color.AQUA);
		homeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.5;");
		homeBt.setTextFill(Color.WHITE);
		homeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		homeBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);

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
		Label titleL = new Label("试题编辑");
		titleL.setStyle("-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 0,0,0,1);-fx-font-size: 35px;");
		titlePane.getChildren().addAll(titleL);

		VBox quizTitlePane = new VBox(10);
		quizTitlePane.setPadding(new Insets(20, 20, 20, 20));
		Label quizTitle = new Label();
		quizTitle.setText("第 " + (nowDoing + 1) + " 题");
		if (question.isEmpty()) {
			quizTitle.setText("暂时题库为空");
		}
		quizTitle.setStyle("-fx-font-size: 20px;");

		TextField questionL = new TextField();
		questionL.setStyle("-fx-font-size: 20px;");
		questionL.setText("\t暂时题库为空");

		quizTitlePane.getChildren().addAll(quizTitle, questionL);

		GridPane choicePane = new GridPane();
		choicePane.setPadding(new Insets(20, 20, 20, 20));
		choicePane.setMinHeight(180);
		choicePane.setVgap(5);
		choicePane.setHgap(5);

		ToggleGroup group = new ToggleGroup();
		RadioButton choiceARs = new RadioButton();
		choiceARs.setStyle("-fx-font-size: 20px;");
		RadioButton choiceBRs = new RadioButton();
		choiceBRs.setStyle("-fx-font-size: 20px;");
		RadioButton choiceCRs = new RadioButton();
		choiceCRs.setStyle("-fx-font-size: 20px;");
		RadioButton choiceDRs = new RadioButton();
		choiceDRs.setStyle("-fx-font-size: 20px;");
		group.getToggles().addAll(choiceARs, choiceBRs, choiceCRs, choiceDRs);

		choiceARs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				newKey = "A";
			}
		});

		choiceBRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				newKey = "B";
			}
		});

		choiceCRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				newKey = "C";
			}
		});

		choiceDRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				newKey = "D";
			}
		});

		Label choiceAs = new Label("A. ");
		choiceAs.setStyle("-fx-font-size: 20px;");
		Label choiceBs = new Label("B. ");
		choiceBs.setStyle("-fx-font-size: 20px;");
		Label choiceCs = new Label("C. ");
		choiceCs.setStyle("-fx-font-size: 20px;");
		Label choiceDs = new Label("D. ");
		choiceDs.setStyle("-fx-font-size: 20px;");

		TextField choiceAL = new TextField("题库为空");
		TextField choiceBL = new TextField("题库为空");
		TextField choiceCL = new TextField("题库为空");
		TextField choiceDL = new TextField("题库为空");

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

		choicePane.add(choiceARs, 0, 0);
		choicePane.add(choiceBRs, 0, 1);
		choicePane.add(choiceCRs, 0, 2);
		choicePane.add(choiceDRs, 0, 3);
		choicePane.add(choiceAs, 1, 0);
		choicePane.add(choiceBs, 1, 1);
		choicePane.add(choiceCs, 1, 2);
		choicePane.add(choiceDs, 1, 3);
		choicePane.add(choiceAL, 2, 0);
		choicePane.add(choiceBL, 2, 1);
		choicePane.add(choiceCL, 2, 2);
		choicePane.add(choiceDL, 2, 3);

		GridPane opPane = new GridPane();
		opPane.setHgap(10);
		Button beforeBt = new Button("上一题");
		beforeBt.setDisable(true);
		beforeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		beforeBt.setTextFill(Color.WHITE);
		Button nextBt = new Button("下一题");
		nextBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		nextBt.setTextFill(Color.WHITE);
		Button saveExamBt = new Button("保存本题");
		saveExamBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		saveExamBt.setTextFill(Color.WHITE);
		Button newExamBt = new Button("新建题目");
		newExamBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		newExamBt.setTextFill(Color.WHITE);
		Button delExamBt = new Button("删除本题");
		delExamBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		delExamBt.setTextFill(Color.WHITE);
		newExamBt.setTextFill(Color.WHITE);
		Button finishBt = new Button("提交整卷");
		finishBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		finishBt.setTextFill(Color.WHITE);
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
		saveExamBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			saveExamBt.setEffect(shadowOp);
		});
		saveExamBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			saveExamBt.setEffect(null);
		});
		newExamBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			newExamBt.setEffect(shadowOp);
		});
		newExamBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			newExamBt.setEffect(null);
		});
		delExamBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delExamBt.setEffect(shadowOp);
		});
		delExamBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delExamBt.setEffect(null);
		});
		finishBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			finishBt.setEffect(shadowOp);
		});
		finishBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			finishBt.setEffect(null);
		});

		if (question.size() == 0) {
			beforeBt.setDisable(true);
			nextBt.setDisable(true);
			delExamBt.setDisable(true);
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

			choiceAs.setTextFill(Color.BLACK);
			choiceBs.setTextFill(Color.BLACK);
			choiceCs.setTextFill(Color.BLACK);
			choiceDs.setTextFill(Color.BLACK);

			newKey = key.get(nowDoing);
			switch (key.get(nowDoing)) {
			case "A":
				choiceAs.setTextFill(Color.BLUEVIOLET);
				choiceARs.setSelected(true);
				choiceBRs.setSelected(false);
				choiceCRs.setSelected(false);
				choiceDRs.setSelected(false);
				break;
			case "B":
				choiceBs.setTextFill(Color.BLUEVIOLET);
				choiceARs.setSelected(false);
				choiceBRs.setSelected(true);
				choiceCRs.setSelected(false);
				choiceDRs.setSelected(false);
				break;
			case "C":
				choiceCs.setTextFill(Color.BLUEVIOLET);
				choiceARs.setSelected(false);
				choiceBRs.setSelected(false);
				choiceCRs.setSelected(true);
				choiceDRs.setSelected(false);
				break;
			case "D":
				choiceDs.setTextFill(Color.BLUEVIOLET);
				choiceARs.setSelected(false);
				choiceBRs.setSelected(false);
				choiceCRs.setSelected(false);
				choiceDRs.setSelected(true);
			}
		}

		opPane.add(beforeBt, 1, 0);
		opPane.add(nextBt, 2, 0);
		opPane.add(new Label(), 3, 0);
		opPane.add(saveExamBt, 4, 0);
		opPane.add(newExamBt, 5, 0);
		opPane.add(delExamBt, 6, 0);
		opPane.add(new Label(), 7, 0);
		opPane.add(finishBt, 8, 0);

		Label infoLL = new Label("通过分:" + passScore);
		infoLL.setStyle("-fx-font-size:15px");
		detailPane.getChildren().addAll(titlePane, new Separator(), quizTitlePane, choicePane, opPane, new Separator(),
				infoLL);

		contentPane.add(detailPane, 0, 0);

		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		beforeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				nowDoing--;
				quizTitle.setText("第 " + (nowDoing + 1) + " 题");
				questionL.setText("\t" + question.get(nowDoing));
				choiceAL.setText(choiceA.get(nowDoing));
				choiceBL.setText(choiceB.get(nowDoing));
				choiceCL.setText(choiceC.get(nowDoing));
				choiceDL.setText(choiceD.get(nowDoing));

				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);

				newKey = key.get(nowDoing);
				switch (key.get(nowDoing)) {
				case "A":
					choiceAs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(true);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "B":
					choiceBs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(true);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "C":
					choiceCs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(true);
					choiceDRs.setSelected(false);
					break;
				case "D":
					choiceDs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(true);
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

				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);

				newKey = key.get(nowDoing);
				switch (key.get(nowDoing)) {
				case "A":
					choiceAs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(true);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "B":
					choiceBs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(true);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "C":
					choiceCs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(true);
					choiceDRs.setSelected(false);
					break;
				case "D":
					choiceDs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(true);
				}
				if (nowDoing == question.size() - 1) {
					nextBt.setDisable(true);
				}
				beforeBt.setDisable(false);
			}
		});

		saveExamBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (question.size() == 0) {
					question.add(questionL.getText());
					choiceA.add(choiceAL.getText());
					choiceB.add(choiceBL.getText());
					choiceC.add(choiceCL.getText());
					choiceD.add(choiceDL.getText());
					key.add(newKey);
				}
				question.set(nowDoing, questionL.getText());
				choiceA.set(nowDoing, choiceAL.getText());
				choiceB.set(nowDoing, choiceBL.getText());
				choiceC.set(nowDoing, choiceCL.getText());
				choiceD.set(nowDoing, choiceDL.getText());
				key.set(nowDoing, newKey);
				newKey = key.get(nowDoing);
				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);
				switch (key.get(nowDoing)) {
				case "A":
					choiceAs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(true);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "B":
					choiceBs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(true);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
					break;
				case "C":
					choiceCs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(true);
					choiceDRs.setSelected(false);
					break;
				case "D":
					choiceDs.setTextFill(Color.BLUEVIOLET);
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(true);
				}
				infoL.setText("保存成功");
			}
		});

		newExamBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				nowDoing = key.size();
				newKey = "A";
				choiceARs.setSelected(true);
				choiceBRs.setSelected(false);
				choiceCRs.setSelected(false);
				choiceDRs.setSelected(false);
				questionL.setText("请输入问题");
				choiceAL.setText("请输入选项");
				choiceBL.setText("请输入选项");
				choiceCL.setText("请输入选项");
				choiceDL.setText("请输入选项");
				question.add("请输入问题");
				choiceA.add("请输入选项");
				choiceB.add("请输入选项");
				choiceC.add("请输入选项");
				choiceD.add("请输入选项");
				key.add("A");
				choiceARs.setSelected(true);
				choiceAs.setTextFill(Color.BLACK);
				choiceBs.setTextFill(Color.BLACK);
				choiceCs.setTextFill(Color.BLACK);
				choiceDs.setTextFill(Color.BLACK);
				nextBt.setDisable(true);
				beforeBt.setDisable(false);
				delExamBt.setDisable(false);
			}
		});

		delExamBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (question.size() == 1) {
					infoL.setText("仅余下一题，无法删除");
				} else {
					question.remove(nowDoing);
					choiceA.remove(nowDoing);
					choiceB.remove(nowDoing);
					choiceC.remove(nowDoing);
					choiceD.remove(nowDoing);
					if (nowDoing == question.size()) {
						nowDoing--;
					}

					quizTitle.setText("第 " + (nowDoing + 1) + " 题");
					questionL.setText("\t" + question.get(nowDoing));
					choiceAL.setText(choiceA.get(nowDoing));
					choiceBL.setText(choiceB.get(nowDoing));
					choiceCL.setText(choiceC.get(nowDoing));
					choiceDL.setText(choiceD.get(nowDoing));

					choiceAs.setTextFill(Color.BLACK);
					choiceBs.setTextFill(Color.BLACK);
					choiceCs.setTextFill(Color.BLACK);
					choiceDs.setTextFill(Color.BLACK);

					newKey = key.get(nowDoing);
					switch (key.get(nowDoing)) {
					case "A":
						choiceAs.setTextFill(Color.BLUEVIOLET);
						choiceARs.setSelected(true);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "B":
						choiceBs.setTextFill(Color.BLUEVIOLET);
						choiceARs.setSelected(false);
						choiceBRs.setSelected(true);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "C":
						choiceCs.setTextFill(Color.BLUEVIOLET);
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(true);
						choiceDRs.setSelected(false);
						break;
					case "D":
						choiceDs.setTextFill(Color.BLUEVIOLET);
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(true);
					}

					if (nowDoing == 0) {
						beforeBt.setDisable(true);
					}
					if (nowDoing == question.size() - 1) {
						nextBt.setDisable(true);
					}
				}
			}
		});

		finishBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
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
					new utils.systemError("PMCTMSFR003").start(null);
					;
					examStage.close();
				}
				examStage.close();
				new testManage().start(null);
			}
		});

		Scene scene = new Scene(userPane);
		examStage.setScene(scene);
		examStage.setResizable(false);
		examStage.setTitle("LibraryX Administrator");
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
			new utils.systemError("PMCTMFFR004").start(null);
			examStage.close();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
