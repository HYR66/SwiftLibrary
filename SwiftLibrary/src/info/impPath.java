package info;

/**
 * impPath:���ഢ���ṩ���бر���Դ�ļ�·��
 * 
 * @author ����Swift
 */
public class impPath {
	/*
	 * ����Ϊȫ����Ҫ���Բ���
	 */
	// ȫ��AES���ܽ��ܿ���
	public static String AES_key = "201806";

	/*
	 * ����Ϊ�ر�ͼƬ��Դ�ļ�·��
	 */

	// ȫ������������ͼ��
	public static String icon_IMG = "resource/icon.jpg";
	// ȫ�����汳��
	public static String desk_backGround = "resource/backGround.jpg";
	// ����ͼ��
	public static String err_icon_IMG = "resource/errClose.png";
	// ͨ������ͼ��
	public static String pass_icon_IMG = "resource/checkPass.png";
	// ��ӭ���붯��ͼƬ
	public static String splash_GIF = "resource/login/splash.gif";
	// �˳�ϵͳͼƬ
	public static String exit_IMG = "resource/common/shutdown.jpg";
	// ע���˻�ͼƬ
	public static String login_out_IMG = "resource/common/exit.jpg";
	// ������һ��ͼƬ
	public static String back_IMG = "resource/common/home.jpg";
	// �������Ǳ���ͼƬ
	public static String about_IMG = "resource/common/about.jpg";

	/*
	 * ����Ϊ������֤���ȡĿ��·��
	 */
	// ���ʽ�����ҳ
	public static final String INDEX_URL = "http://jwxt.njupt.edu.cn";
	// ���ʽ����¼Ч��������
	public static final String SECRETCODE_URL = "http://jwxt.njupt.edu.cn/CheckCode.aspx";
	// ���ʽ����¼����
	public static final String LOGIN_URL = "http://jwxt.njupt.edu.cn/default2.aspx";
	// ���ʽ����½���������
	public static final String MAIN_URL = "http://jwxt.njupt.edu.cn/xs_main.aspx?xh=";

	/*
	 * ����Ϊƽ̨�����ļ�ȫ·��
	 */
	// �������и�Ŀ¼
	private static String env_root = System.getProperty("user.dir");
	// ƽ̨���ݸ�Ŀ¼
	public static String data_root = env_root + "\\database\\cloud";
	// �û���Դ�ļ���Ŀ¼
	public static String user_root = data_root + "\\user";
	// ����Ա�������Ϣ�ļ�
	public static String master_json = data_root + "\\loginmaster";
	// �û��������Ϣ�ļ�
	public static String user_json = data_root + "\\loginInfo";
	// ͼ����Դ�ļ���Ŀ¼
	public static String book_root = data_root + "\\book";
	// ͼ��������Ϣ�ļ�
	public static String book_json = book_root + "\\bookInfo";
	// ��������Ϣ�ļ�
	public static String book_class_json = book_root + "\\classList";
	// ������Ŀ¼
	public static String manage_root = data_root + "\\manage";
	// �ʽ�����ļ�
	public static String money_json = manage_root + "\\money";
	// �����֪�ļ�
	public static String announce_txt = manage_root + "\\announcement";
	// ������Ŀ�ļ�
	public static String applybook_json = manage_root + "\\applybook";
	// ע����������ļ�
	public static String exam_json = manage_root + "\\exam";
	// ������֤��ʶ��ѵ����ģ
	public static String code_train = env_root + "\\train";
	// ������֤���ȡ��ʱ�����
	public static String code_temp = data_root;

}
