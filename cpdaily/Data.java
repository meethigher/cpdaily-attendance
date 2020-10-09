package cpdaily;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �洢�����Լ��ӿڵ��࣬����������Լ�����޸�
 * 
 * @author kit chen
 *
 */
public class Data {
	/**
	 * �ϴ�����Ƭ����
	 * ע���ʽ,"url,url,url",Ӣ�Ķ���
	 */
	public static String photoUrls="����,����,����,�ȵ�";
	/**
	 * ����ˢ�޵�������Ĭ����520��
	 */
	public static int likeNum=520;
	/**
	 * {"appVersion":"8.1.11","systemName":"android","model":"MI
	 * 9","lon":0,"systemVersion":"10","deviceId":"�豸��","userId":ѧ��,"lat":0}�ļ���ֵ
	 */
	public static String cpdailyExtension = "ooxx";
	/**
	 * cookie
	 */
	public static String cookie = "acw_tc=ooxx; MOD_AUTH_CAS=ooxx";
	/**
	 * ���շ�����
	 */
	public static String toMail = "meethigher@qq.com";
	/**
	 * ��λ�ĵص�
	 */
	public static String poi = "���λ��";
	/**
	 * ����
	 */
	public static String log = "��ľ���";
	/**
	 * γ��
	 */
	public static String lat = "���γ��";
	
	
	/**
	 * ѧУ��host
	 */
	public static final String host = "https://ccut.campusphere.net";
	/**
	 * ���������䣬������β��ԣ���Ѷ��ҵ���������ȶ���
	 * 
	 * 2020-09-12 ������Ѷ�����ǰ���ţ��
	 */
	public static final String fromMail = "test@meethigher.top";

	/**
	 * ��Ѷ��ҵ��������
	 */
	public static final String fromMailPw = "test@meethigher.top";

	/**
	 * Cpdaily-Extension��Ҫ����ʵ�����
	 * 
	 * @return
	 */
	public static Map<String, String> getSubHeaders() {
		Map<String, String> map = getHeaders();
		map.put("CpdailyStandAlone", "0");
		map.put("extension", "1");
		map.put("Cpdaily-Extension", cpdailyExtension);
		return map;
	}

	/**
	 * Cookie��Ҫ����ʵ�����
	 * 
	 * @return
	 */
	public static Map<String, String> getHeaders() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("tenantId", "ccut");
		map.put("User-Agent",
				"Mozilla/5.0 (Linux; Android 10; MI 9 Build/QKQ1.190825.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 okhttp/3.8.1");
		map.put("Content-Type", "application/json;charset=utf-8");
		map.put("Host", "ccut.campusphere.net");
		map.put("Connection", "Keep-Alive");
		map.put("Accept-Encoding", "gzip");
		map.put("Cookie", cookie);
		return map;
	}

	// ������������ѧУ��һ���������޸�
	/**
	 * ����session
	 */
	public static final String keepingUrl = host + "/portal/index.html";

	/**
	 * ��ȡ���²�������
	 */
	public static final String queryAttend = host + "/wec-counselor-attendance-apps/student/attendance/getStuAttendacesInOneDay";

	/**
	 * ��ȡ��ϸ����
	 */
	public static final String detailAttend = host + "/wec-counselor-attendance-apps/student/attendance/detailSignInstance";

	/**
	 * �ύ����
	 */
	public static final String submitAttend = host + "/wec-counselor-attendance-apps/student/attendance/submitSign";

	/**
	 * ��ȡ���а�ӿ�
	 */
	public static final String rankUrl = host + "/wec-counselor-attendance-apps/student/attendance/getSignRankList";
	
	/**
	 * ˢ�޽ӿ�
	 */
	public static final String giveLike = host + "/wec-counselor-attendance-apps/student/attendance/giveLike";
	

}
