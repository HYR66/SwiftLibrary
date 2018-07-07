package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

/**
 * connectNUPT:正方实名认证模组
 * 
 * @author 蜂鸟Swift
 *
 */
public class connectNUPT {
	public static String getName(String stuID, String userPw) throws Exception {
		// 获取效验码
		HttpGet secretCodeGet = new HttpGet(info.impPath.SECRETCODE_URL);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse responseSecret = client.execute(secretCodeGet);

		// 写入效验码图片
		FileOutputStream fileOutputStream = new FileOutputStream(new File(info.impPath.code_temp + "\\codeNUPT.gif"));
		responseSecret.getEntity().writeTo(fileOutputStream);
		fileOutputStream.close();

		// 验证码图片转换PNG
		OutputStream out = new FileOutputStream(info.impPath.code_temp + "\\codeNUPT.png");
		ImageIO.write(ImageIO.read(new File(info.impPath.code_temp + "\\codeNUPT.gif")), "png", out);
		out.close();

		// 验证码识别
		String secretCode = utils.ImagePreProcess.startOCR();
		File fileCode = new File(info.impPath.code_temp + "\\codeNUPT.png");
		if (fileCode.exists()) {
			fileCode.delete();
		}

		// 获取VIEWSTATE校验数据
		HttpGet getViewState = new HttpGet(info.impPath.INDEX_URL);
		InputStream viewStaIS = client.execute(getViewState).getEntity().getContent();
		String viewStaresult = new BufferedReader(new InputStreamReader(viewStaIS, "GB2312")).lines().parallel()
				.collect(Collectors.joining(System.lineSeparator()));
		String viewstate = Jsoup.parse(viewStaresult).select("input[name=__VIEWSTATE]").val();

		// 提交POST登录表单
		HttpPost loginPost = new HttpPost(info.impPath.LOGIN_URL);

		ArrayList<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("__VIEWSTATE", viewstate));
		postData.add(new BasicNameValuePair("txtUserName", stuID));
		postData.add(new BasicNameValuePair("Textbox1", ""));
		postData.add(new BasicNameValuePair("TextBox2", userPw));
		postData.add(new BasicNameValuePair("txtSecretCode", secretCode));
		postData.add(new BasicNameValuePair("RadioButtonList1", "%D1%A7%C9%FA"));
		postData.add(new BasicNameValuePair("Button1", ""));
		postData.add(new BasicNameValuePair("lbLanguage", ""));
		postData.add(new BasicNameValuePair("hidPdrs", ""));
		postData.add(new BasicNameValuePair("hidsc", ""));

		loginPost.setEntity(new UrlEncodedFormEntity(postData, "GB2312"));
		HttpResponse responseLogin = client.execute(loginPost);

		if (responseLogin.getStatusLine().getStatusCode() == 302) {
			// 302重定向响应表示登录成功
			HttpGet mainGet = new HttpGet(info.impPath.MAIN_URL + stuID);
			mainGet.setHeader("Referer", info.impPath.LOGIN_URL);
			InputStream loginIS = client.execute(mainGet).getEntity().getContent();
			String htmlResult = new BufferedReader(new InputStreamReader(loginIS, "GB2312")).lines().parallel()
					.collect(Collectors.joining(System.lineSeparator()));
			String stuFullName = Jsoup.parse(htmlResult).getElementById("xhxm").text();

			// 字符串处理姓名 并转为含分字的GBK编码
			String stuName = stuFullName.substring(0, stuFullName.length() - 2);
			client.close();
			return stuName;
		} else {
			client.close();
			return "####";
		}
	}
}
