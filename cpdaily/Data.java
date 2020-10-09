package cpdaily;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 存储数据以及接口的类，部分需根据自己情况修改
 * 
 * @author kit chen
 *
 */
public class Data {
	/**
	 * 上传的照片链接
	 * 注意格式,"url,url,url",英文逗号
	 */
	public static String photoUrls="链接,链接,链接,等等";
	/**
	 * 输入刷赞的数量，默认是520个
	 */
	public static int likeNum=520;
	/**
	 * {"appVersion":"8.1.11","systemName":"android","model":"MI
	 * 9","lon":0,"systemVersion":"10","deviceId":"设备号","userId":学号,"lat":0}的加密值
	 */
	public static String cpdailyExtension = "ooxx";
	/**
	 * cookie
	 */
	public static String cookie = "acw_tc=ooxx; MOD_AUTH_CAS=ooxx";
	/**
	 * 接收方邮箱
	 */
	public static String toMail = "meethigher@qq.com";
	/**
	 * 定位的地点
	 */
	public static String poi = "你的位置";
	/**
	 * 经度
	 */
	public static String log = "你的经度";
	/**
	 * 纬度
	 */
	public static String lat = "你的纬度";
	
	
	/**
	 * 学校的host
	 */
	public static final String host = "https://ccut.campusphere.net";
	/**
	 * 发件方邮箱，经过多次测试，腾讯企业邮箱是最稳定的
	 * 
	 * 2020-09-12 垃圾腾讯，还是阿里牛逼
	 */
	public static final String fromMail = "test@meethigher.top";

	/**
	 * 腾讯企业邮箱密码
	 */
	public static final String fromMailPw = "test@meethigher.top";

	/**
	 * Cpdaily-Extension需要根据实际情况
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
	 * Cookie需要根据实际情况
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

	// 以下内容所有学校都一样，不用修改
	/**
	 * 保持session
	 */
	public static final String keepingUrl = host + "/portal/index.html";

	/**
	 * 获取最新查寝数据
	 */
	public static final String queryAttend = host + "/wec-counselor-attendance-apps/student/attendance/getStuAttendacesInOneDay";

	/**
	 * 获取详细数据
	 */
	public static final String detailAttend = host + "/wec-counselor-attendance-apps/student/attendance/detailSignInstance";

	/**
	 * 提交查寝
	 */
	public static final String submitAttend = host + "/wec-counselor-attendance-apps/student/attendance/submitSign";

	/**
	 * 获取排行榜接口
	 */
	public static final String rankUrl = host + "/wec-counselor-attendance-apps/student/attendance/getSignRankList";
	
	/**
	 * 刷赞接口
	 */
	public static final String giveLike = host + "/wec-counselor-attendance-apps/student/attendance/giveLike";
	

}
