package login;

import java.util.ArrayList;
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

/**
 * examCheck:通过测验验证注册人具有资格申请
 * 
 * @author 蜂鸟Swift
 *
 */
public class examCheck extends Application {
	private int nowDoing = 0;
	static String passScore;
	static ArrayList<String> question = new ArrayList<String>();
	static ArrayList<String> choiceA = new ArrayList<String>();
	static ArrayList<String> choiceB = new ArrayList<String>();
	static ArrayList<String> choiceC = new ArrayList<String>();
	static ArrayList<String> choiceD = new ArrayList<String>();
	static ArrayList<String> key = new ArrayList<String>();
	private String[] userKey;
	private static Stage examStage;
	private Label infoL;

	public void start(Stage primaryStage) {
		examStage = new Stage();
		userKey = new String[question.size()];

		// 总成界面设定
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		examStage.setX(primaryScreenBounds.getMinX());
		examStage.setY(primaryScreenBounds.getMinY());
		examStage.setWidth(primaryScreenBounds.getWidth());
		examStage.setHeight(primaryScreenBounds.getHeight());

		// 后置堆叠层面板
		BorderPane userPane = new BorderPane();
		userPane.setStyle("-fx-background-image:url(" + info.impPath.desk_backGround + ");-fx-background-size:cover;");

		BorderPane toolBarP = new BorderPane();
		toolBarP.setPadding(new Insets(5, 5, 5, 5));
		toolBarP.setPrefWidth(primaryScreenBounds.getWidth());
		toolBarP.setPrefHeight(15);
		toolBarP.setStyle("-fx-background-color: #E0FFFF;-fx-opacity: 0.5;");
		userPane.setTop(toolBarP);

		// 定制化工具栏
		Button closeToolBt = new Button("X");
		closeToolBt.setTextFill(Color.WHITE);
		closeToolBt.setStyle("-fx-background-color: #C71585;-fx-opacity: 1;");
		// 工具栏动画效果
		DropShadow shadowCloseBt = new DropShadow();
		closeToolBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			closeToolBt.setEffect(shadowCloseBt);
		});
		closeToolBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			closeToolBt.setEffect(null);
		});
		toolBarP.setRight(closeToolBt);

		Label stageTitleL = new Label("蜂鸟图书馆·资格测评");
		toolBarP.setCenter(stageTitleL);

		HBox mainPane = new HBox();
		mainPane.setPadding(new Insets(30, 30, 30, 30));
		userPane.setCenter(mainPane);

		// 左侧一级导航栏
		VBox naviPane = new VBox(5);
		Button homeBt = new Button("资格测评");
		naviPane.getChildren().addAll(homeBt);

		DropShadow shadowL = new DropShadow();
		shadowL.setColor(Color.AQUA);
		homeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.5;");
		homeBt.setTextFill(Color.WHITE);
		homeBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		homeBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);

		// 一级控件
		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(20, 20, 20, 20));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setStyle("-fx-background-color: WHITE;-fx-opacity: 0.5;");
		contentPane.setPrefWidth(primaryScreenBounds.getWidth() / 1.2);

		// 二级内容控件
		VBox detailPane = new VBox(10);
		detailPane.setPadding(new Insets(20, 20, 20, 20));
		detailPane.setMinWidth(primaryScreenBounds.getWidth() / 1.5);

		HBox titlePane = new HBox(10);
		Label titleL = new Label("资格测试");
		titleL.setStyle("-fx-font-family:KaiTi;-fx-font-size: 30px;");
		titlePane.getChildren().addAll(titleL);

		VBox quizTitlePane = new VBox(10);
		quizTitlePane.setPadding(new Insets(20, 20, 20, 20));
		Label quizTitle = new Label();
		quizTitle.setText("第 " + (nowDoing + 1) + " 题");
		if (question.isEmpty()) {
			quizTitle.setText("第 0 题");
		}
		quizTitle.setStyle("-fx-font-size: 20px;");

		Label questionL = new Label();
		questionL.setStyle("-fx-font-size: 20px;");
		questionL.setText("\t题库为空");

		quizTitlePane.getChildren().addAll(quizTitle, questionL);

		GridPane choicePane = new GridPane();
		choicePane.setPadding(new Insets(20, 20, 20, 20));
		choicePane.setMinHeight(180);
		choicePane.setVgap(10);
		choicePane.setHgap(10);

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

		// 选项选择事件响应
		choiceARs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				userKey[nowDoing] = "A";
			}
		});

		choiceBRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				userKey[nowDoing] = "B";
			}
		});

		choiceCRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				userKey[nowDoing] = "C";
			}
		});

		choiceDRs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				userKey[nowDoing] = "D";
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

		Label choiceAL = new Label("题库为空");
		Label choiceBL = new Label("题库为空");
		Label choiceCL = new Label("题库为空");
		Label choiceDL = new Label("题库为空");

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

		// 三级操作控件
		GridPane opPane = new GridPane();
		opPane.setHgap(10);
		Button beforeBt = new Button("上一题");
		beforeBt.setDisable(true);
		beforeBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		beforeBt.setTextFill(Color.WHITE);
		Button nextBt = new Button("下一题");
		nextBt.setStyle("-fx-background-color: #00BFFF;-fx-opacity: 0.8;");
		nextBt.setTextFill(Color.WHITE);
		Button finishBt = new Button("交卷");
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
		finishBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			finishBt.setEffect(shadowOp);
		});
		finishBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			finishBt.setEffect(null);
		});

		// 内容初始化
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

			choiceAs.setTextFill(Color.BLACK);
			choiceBs.setTextFill(Color.BLACK);
			choiceCs.setTextFill(Color.BLACK);
			choiceDs.setTextFill(Color.BLACK);
		}

		opPane.add(beforeBt, 1, 0);
		opPane.add(nextBt, 2, 0);
		opPane.add(new Label(), 3, 0);
		opPane.add(finishBt, 4, 0);

		// 三级信息控件
		Label infoLL = new Label("测试通过分：" + passScore + "\t总题数：" + userKey.length);
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

		// 关闭时间相应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				examStage.close();
				new login().start(null);
			}
		});

		// 上一个事件响应
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

				if (userKey[nowDoing] != null) {
					switch (userKey[nowDoing]) {
					case "A":
						choiceARs.setSelected(true);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "B":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(true);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "C":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(true);
						choiceDRs.setSelected(false);
						break;
					case "D":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(true);
					}
				} else {
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
				}

				if (nowDoing == 0) {
					beforeBt.setDisable(true);
				}
				nextBt.setDisable(false);
			}
		});

		// 下一个事件响应
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

				if (userKey[nowDoing] != null) {
					switch (userKey[nowDoing]) {
					case "A":
						choiceARs.setSelected(true);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "B":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(true);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(false);
						break;
					case "C":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(true);
						choiceDRs.setSelected(false);
						break;
					case "D":
						choiceARs.setSelected(false);
						choiceBRs.setSelected(false);
						choiceCRs.setSelected(false);
						choiceDRs.setSelected(true);
					}
				} else {
					choiceARs.setSelected(false);
					choiceBRs.setSelected(false);
					choiceCRs.setSelected(false);
					choiceDRs.setSelected(false);
				}

				if (nowDoing == question.size() - 1) {
					nextBt.setDisable(true);
				}
				beforeBt.setDisable(false);
			}
		});

		// 交卷事件响应
		finishBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int rightCount = 0;
				for (int i = 0; i < userKey.length; i++) {
					if (userKey[i] != null) {
						if (userKey[i].equals(key.get(i))) {
							rightCount++;
						}
					}
				}
				int finalScore = (int) ((double) rightCount / (double) userKey.length * 100);
				if (finalScore < Integer.parseInt(passScore)) {
					new examInfo(false, finalScore, examStage).start(null);
				} else {
					new examInfo(true, finalScore, examStage).start(null);
				}
			}
		});

		// 界面总成
		Scene scene = new Scene(userPane);
		examStage.setScene(scene);
		examStage.setResizable(false);
		examStage.setTitle("蜂鸟图书馆·资格测评");
		examStage.initStyle(StageStyle.TRANSPARENT);
		examStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		examStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
