package info;

/**
 * impPath:该类储存提供所有必备资源文件路径
 * 
 * @author 蜂鸟Swift
 */
public class impPath {
	/*
	 * 以下为全局重要属性参数
	 */
	// 全局AES加密解密口令
	public static String AES_key = "201806";

	/*
	 * 以下为必备图片资源文件路径
	 */

	// 全局任务与桌面图标
	public static String icon_IMG = "resource/icon.jpg";
	// 全局桌面背景
	public static String desk_backGround = "resource/backGround.jpg";
	// 错误图标
	public static String err_icon_IMG = "resource/errClose.png";
	// 通过测试图标
	public static String pass_icon_IMG = "resource/checkPass.png";
	// 欢迎载入动画图片
	public static String splash_GIF = "resource/login/splash.gif";
	// 退出系统图片
	public static String exit_IMG = "resource/common/shutdown.jpg";
	// 注销账户图片
	public static String login_out_IMG = "resource/common/exit.jpg";
	// 返回上一级图片
	public static String back_IMG = "resource/common/home.jpg";
	// 关于我们背景图片
	public static String about_IMG = "resource/common/about.jpg";

	/*
	 * 以下为南邮验证码获取目标路径
	 */
	// 南邮教务主页
	public static final String INDEX_URL = "http://jwxt.njupt.edu.cn";
	// 南邮教务登录效验码链接
	public static final String SECRETCODE_URL = "http://jwxt.njupt.edu.cn/CheckCode.aspx";
	// 南邮教务登录链接
	public static final String LOGIN_URL = "http://jwxt.njupt.edu.cn/default2.aspx";
	// 南邮教务登陆后关联主链
	public static final String MAIN_URL = "http://jwxt.njupt.edu.cn/xs_main.aspx?xh=";

	/*
	 * 以下为平台数据文件全路径
	 */
	// 环境运行根目录
	private static String env_root = System.getProperty("user.dir");
	// 平台数据根目录
	public static String data_root = env_root + "\\database\\cloud";
	// 用户资源文件主目录
	public static String user_root = data_root + "\\user";
	// 管理员密码等信息文件
	public static String master_json = data_root + "\\loginmaster";
	// 用户密码等信息文件
	public static String user_json = data_root + "\\loginInfo";
	// 图书资源文件主目录
	public static String book_root = data_root + "\\book";
	// 图书数据信息文件
	public static String book_json = book_root + "\\bookInfo";
	// 书库分类信息文件
	public static String book_class_json = book_root + "\\classList";
	// 管理主目录
	public static String manage_root = data_root + "\\manage";
	// 资金管理文件
	public static String money_json = manage_root + "\\money";
	// 公告告知文件
	public static String announce_txt = manage_root + "\\announcement";
	// 建购书目文件
	public static String applybook_json = manage_root + "\\applybook";
	// 注册测试试题文件
	public static String exam_json = manage_root + "\\exam";
	// 正方验证码识别训练字模
	public static String code_train = env_root + "\\train";
	// 正方验证码读取临时储存点
	public static String code_temp = data_root;

}
