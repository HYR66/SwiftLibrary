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
 * applyManage:图书荐购处理
 * 
 * @author 蜂鸟Aspire
 *
 */
public class applyManage extends Application {
	Stage applyStage;
	private int index = 0;
	private ArrayList<String> applyName = new ArrayList<String>();
	private ArrayList<String> applyAuthor = new ArrayList<String>();
	private ArrayList<String> applyPress = new ArrayList<String>();
	private ArrayList<String> applyISBN = new ArrayList<String>();
	private ArrayList<String> applyER = new ArrayList<String>();

	public void start(Stage primaryStage) {
		fileReader();
		applyStage = new Stage();

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		applyStage.setX(primaryScreenBounds.getMinX());
		applyStage.setY(primaryScreenBounds.getMinY());
		applyStage.setWidth(primaryScreenBounds.getWidth());
		applyStage.setHeight(primaryScreenBounds.getHeight());

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
		bookApplyBt.setStyle("-fx-background-color: #00CED1;-fx-opacity: 0.5;");
		bookApplyBt.setTextFill(Color.WHITE);
		bookApplyBt.setPrefWidth(primaryScreenBounds.getWidth() / 7);
		bookApplyBt.setPrefHeight(primaryScreenBounds.getHeight() / 10);
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

		GridPane corePane = new GridPane();
		corePane.setHgap(20);
		corePane.setVgap(20);
		corePane.setPadding(new Insets(40, 40, 40, 40));
		corePane.setAlignment(Pos.CENTER_LEFT);
		GridPane headLPane = new GridPane();
		Label titleLL = new Label("荐购管理");
		titleLL.setStyle("-fx-font-size: 30px;-fx-font-family:KaiTi;");
		headLPane.add(titleLL, 1, 0);

		Label infoLI = new Label(" ");
		infoLI.setTextFill(Color.RED);
		infoLI.setStyle("-fx-font-size:15;");

		GridPane detailPane = new GridPane();
		detailPane.setHgap(50);
		GridPane detailPaneA = new GridPane();
		detailPaneA.setVgap(10);
		GridPane detailPaneB = new GridPane();
		detailPaneB.setVgap(10);
		detailPaneB.setMinWidth(primaryScreenBounds.getWidth() / 5.3);
		detailPane.setAlignment(Pos.CENTER);
		detailPane.setHgap(15);
		detailPane.setVgap(15);
		Label nameL = new Label("书名：");
		nameL.setStyle("-fx-font-size: 20px;");
		detailPaneA.add(nameL, 0, 1);
		Label nameDL = new Label("\t暂无荐购记录");
		nameDL.setStyle("-fx-font-size: 20px;");
		detailPaneB.add(nameDL, 2, 1);
		Label authorL = new Label("作者：");
		authorL.setStyle("-fx-font-size: 20px;");
		detailPaneA.add(authorL, 0, 2);
		Label authorDL = new Label("\t暂无荐购记录");
		authorDL.setStyle("-fx-font-size: 20px;");
		detailPaneB.add(authorDL, 2, 2);
		Label pressL = new Label("出版社：");
		pressL.setStyle("-fx-font-size: 20px;");
		detailPaneA.add(pressL, 0, 3);
		Label pressDL = new Label("\t暂无荐购记录");
		pressDL.setStyle("-fx-font-size: 20px;");
		detailPaneB.add(pressDL, 2, 3);
		Label isbnL = new Label("ISBN:");
		isbnL.setStyle("-fx-font-size: 20px;");
		detailPaneA.add(isbnL, 0, 4);
		Label isbnDL = new Label("\t暂无荐购记录");
		isbnDL.setStyle("-fx-font-size: 20px;");
		detailPaneB.add(isbnDL, 2, 4);

		detailPane.add(detailPaneA, 0, 0);
		detailPane.add(detailPaneB, 1, 0);

		GridPane buttPane = new GridPane();
		buttPane.setAlignment(Pos.CENTER);
		buttPane.setHgap(10);
		buttPane.setVgap(20);

		DropShadow shadowBt = new DropShadow();
		Button prevBt = new Button("上一条");
		prevBt.setMinWidth(180);
		prevBt.setStyle("-fx-background-color:#00FFFF;-fx-opacity: 0.8;");
		prevBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			prevBt.setEffect(shadowBt);
		});
		prevBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			prevBt.setEffect(null);
		});
		buttPane.add(prevBt, 0, 0);
		Button nextBt = new Button("下一条");
		nextBt.setMinWidth(180);
		nextBt.setStyle("-fx-background-color:#00FFFF;-fx-opacity: 0.8;");
		nextBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			nextBt.setEffect(shadowBt);
		});
		nextBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			nextBt.setEffect(null);
		});
		buttPane.add(nextBt, 1, 0);
		Button acceptBt = new Button("同意");
		acceptBt.setMinWidth(180);
		acceptBt.setStyle("-fx-background-color:#7FFFD4;-fx-opacity: 0.8;");
		acceptBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			acceptBt.setEffect(shadowBt);
		});
		acceptBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			acceptBt.setEffect(null);
		});
		buttPane.add(acceptBt, 0, 1);
		Button noBt = new Button("忽略");
		noBt.setMinWidth(180);
		noBt.setStyle("-fx-background-color:#EE82EE;-fx-opacity: 0.8;");
		noBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			noBt.setEffect(shadowBt);
		});
		noBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			noBt.setEffect(null);
		});
		buttPane.add(noBt, 1, 1);

		if (applyName.size() == 0) {
			prevBt.setDisable(true);
			nextBt.setDisable(true);
			acceptBt.setDisable(true);
			noBt.setDisable(true);
		} else {
			nameDL.setText(applyName.get(0));
			authorDL.setText(applyAuthor.get(0));
			pressDL.setText(applyPress.get(0));
			isbnDL.setText(applyISBN.get(0));
			prevBt.setDisable(true);
			if (applyName.size() == 1) {
				nextBt.setDisable(true);
			}
		}

		prevBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				index--;
				if (index == 0) {
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				} else {
					nameDL.setText(applyName.get(index));
					authorDL.setText(applyAuthor.get(index));
					pressDL.setText(applyPress.get(index));
					isbnDL.setText(applyISBN.get(index));
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				}
			}
		});

		nextBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				index++;
				if (index == 0) {
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				} else {
					nameDL.setText(applyName.get(index));
					authorDL.setText(applyAuthor.get(index));
					pressDL.setText(applyPress.get(index));
					isbnDL.setText(applyISBN.get(index));
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				}
			}
		});

		acceptBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new addAfterApply(applyName.get(index), applyAuthor.get(index), applyPress.get(index),
						applyISBN.get(index)).start(null);
				;
				applyName.remove(index);
				applyAuthor.remove(index);
				applyPress.remove(index);
				applyISBN.remove(index);
				int userIndex = info.dataBase.name.indexOf(applyER.get(index));
				applyER.remove(index);
				info.dataBase.credit.set(userIndex, info.dataBase.credit.get(userIndex) + 2);
				fileWriter(true);
				infoLI.setText("创建成功");
				if (index == applyName.size()) {
					index--;
				}
				if (index == 0) {
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				} else if (applyName.size() == 0) {
					prevBt.setDisable(true);
					nextBt.setDisable(true);
					acceptBt.setDisable(true);
					noBt.setDisable(true);
					nameDL.setText("\t暂无荐购记录");
					authorDL.setText("\t暂无荐购记录");
					pressDL.setText("\t暂无荐购记录");
					isbnDL.setText("\t暂无荐购记录");
					prevBt.setDisable(true);
				} else {
					nameDL.setText(applyName.get(index));
					authorDL.setText(applyAuthor.get(index));
					pressDL.setText(applyPress.get(index));
					isbnDL.setText(applyISBN.get(index));
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				}
			}
		});

		noBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				applyName.remove(index);
				applyAuthor.remove(index);
				applyPress.remove(index);
				applyISBN.remove(index);
				applyER.remove(index);
				fileWriter(false);
				infoLI.setText("成功忽略该条");
				if (index == applyName.size()) {
					index--;
				}
				if (applyName.size() == 0) {
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				} else if (applyName.size() == 0) {
					prevBt.setDisable(true);
					nextBt.setDisable(true);
					acceptBt.setDisable(true);
					noBt.setDisable(true);
					nameDL.setText("\t暂无荐购记录");
					authorDL.setText("\t暂无荐购记录");
					pressDL.setText("\t暂无荐购记录");
					isbnDL.setText("\t暂无荐购记录");
					prevBt.setDisable(true);
				} else {
					nameDL.setText(applyName.get(index));
					authorDL.setText(applyAuthor.get(index));
					pressDL.setText(applyPress.get(index));
					isbnDL.setText(applyISBN.get(index));
					prevBt.setDisable(true);
					if (applyName.size() == 1) {
						nextBt.setDisable(true);
					}
				}
			}
		});

		corePane.add(headLPane, 0, 0);
		Separator lineL = new Separator();
		lineL.setMinWidth(primaryScreenBounds.getWidth() * 0.5);
		corePane.add(lineL, 0, 1);
		corePane.add(detailPane, 0, 2);
		corePane.add(new Label(), 0, 3);
		corePane.add(buttPane, 0, 4);
		corePane.add(infoLI, 0, 5);

		contentPane.add(corePane, 1, 2);

		mainPane.getChildren().addAll(naviPane, contentPane);

		HBox infoP = new HBox();
		Label infoL = new Label("平台状态：正常");
		infoL.setTextFill(Color.RED);
		infoL.setStyle("-fx-font-size:15;");
		infoP.getChildren().add(infoL);
		userPane.setBottom(infoL);

		// 菜单栏按钮事件响应
		closeToolBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				common.mainClose obj = new common.mainClose(applyStage);
				obj.start(null);
			}
		});

		homeBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new adminMain().start(null);
			}
		});

		bookMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new bookManage().start(null);
			}
		});

		userMBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new userManage().start(null);
			}
		});

		editBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new editInfo().start(null);
			}
		});

		testBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new testManage().start(null);
			}
		});

		superBt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				applyStage.close();
				new superOp().start(null);
			}
		});
		// 菜单栏按钮事件响应

		Scene scene = new Scene(userPane);
		applyStage.setScene(scene);
		applyStage.setResizable(false);
		applyStage.setTitle("蜂鸟图书馆管理平台");
		applyStage.initStyle(StageStyle.TRANSPARENT);
		applyStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(info.impPath.icon_IMG)));
		applyStage.show();

		toolBarP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					new common.aboutMe().start(null);
				}
			}
		});
	}

	private void fileReader() {
		java.io.File apply = new java.io.File(info.impPath.applybook_json);
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(apply));
			Scanner input = new Scanner(inputReader);
			while (input.hasNextLine()) {
				String jsonAES = input.nextLine();
				String jsonStr = utils.AESCode.getOrgWord(jsonAES);
				@SuppressWarnings("unchecked")
				Map<String, String> map = JSONObject.fromObject(jsonStr);
				applyName.add(map.get("name"));
				applyAuthor.add(map.get("author"));
				applyPress.add(map.get("press"));
				applyISBN.add(map.get("ISBN"));
				applyER.add(map.get("user"));
			}
			input.close();
		} catch (Exception ex) {
			new utils.systemError("PMCAMFFR001").start(null);
			;
			applyStage.close();
		}
	}

	private void fileWriter(Boolean writeU) {
		String pathA = info.impPath.applybook_json;
		try {
			PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathA)));
			for (int i = 0; i < applyName.size(); i++) {
				Map<String, String> params = new HashMap<>();
				params.put("name", applyName.get(i));
				params.put("author", applyAuthor.get(i));
				params.put("press", applyPress.get(i));
				params.put("ISBN", applyISBN.get(i));
				params.put("user", applyER.get(i));
				JSONObject jsonObject = JSONObject.fromObject(params);
				String jsonStr = jsonObject.toString();
				write.println(utils.AESCode.getAESWord(jsonStr));
			}
			write.close();
		} catch (Exception error) {
			new utils.systemError("PMCAMFFW002").start(null);
			applyStage.close();
		}

		if (writeU) {
			String pathB = info.impPath.user_json;
			try {
				PrintWriter write = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathB)));
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
				new utils.systemError("PMCAMFFW003").start(null);
				applyStage.close();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
