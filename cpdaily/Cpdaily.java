package cpdaily;

import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author kit chen
 * @description 内部的实现方法
 */
public class Cpdaily {
	/**
	 * 下面变量用来预加载数据
	 */
	public static String prepForm;
	/**
	 * 用来刷赞的请求参数
	 */
	public static String stuSignWid;
	/**
	 * 用来获取详细表单、排行榜的请求参数
	 */
	public static String signIds;

	/**
	 * 用来预加载数据，霸榜第一的感觉你能懂不?
	 * 
	 * @return
	 */
	public static void prepData() {
		System.out.println("开始预加载数据..");
		prepForm = fillForm();
		System.out.println("数据加载完毕");
	}

	/**
	 * 获取查寝
	 * 
	 * @return
	 */
	public static String getAttend() {
		JSONObject jsonObject = JSONObject.fromObject(HttpUtil.sendPost(Data.queryAttend, "{}", Data.getHeaders()))
				.getJSONObject("datas").getJSONArray("unSignedTasks").getJSONObject(0);

		// 这一步保存stuSignWid是为了下步刷赞用
		stuSignWid = jsonObject.getString("stuSignWid");
		signIds="{\"signWid\":" + jsonObject.getInt("signWid") + ",\"signInstanceWid\":"
				+ jsonObject.getInt("signInstanceWid") + "}";
		return signIds;
	}

	/**
	 * 获取查寝详细
	 * 
	 * @return
	 */
	public static String getAttendDetail() {
		return JSONObject.fromObject(HttpUtil.sendPost(Data.detailAttend, getAttend(), Data.getHeaders()))
				.getJSONObject("datas").toString();
	}

	/**
	 * 返回随机照片
	 * 
	 * @return
	 */
	public static String randomPhoto() {
		String[] photos = Data.photoUrls.split(",");
		int pieces = photos.length;
		int index = new Random().nextInt(pieces);
		return photos[index];
	}

	/**
	 * 填表
	 * 
	 * @return
	 */
	public static String fillForm() {
		JSONObject object = JSONObject.fromObject(getAttendDetail());
		Form form = new Form();
		form.setLongitude(Data.log);
		form.setLatitude(Data.lat);
		form.setPosition(Data.poi);
		form.setQrUuid("");
		form.setSignInstanceWid(object.getString("signInstanceWid"));
		form.setIsMalposition(object.getString("isMalposition"));
		form.setAbnormalReason("");
		form.setSignPhotoUrl(randomPhoto());
		return form.toString();
	}

	/**
	 * 提交查寝
	 * 
	 * @return
	 */
	public static String submitAttend() {
		String result = JSONObject.fromObject(HttpUtil.sendPost(Data.submitAttend, prepForm, Data.getSubHeaders()))
				.get("message").toString();
		if ("SUCCESS".equals(result)) {
			return "success";
		}else if("签到失败".equals(result)) {
			return "submited";
		} else {
			return "自动提交查寝失败，原因：" + result;
		}
	}

	/**
	 * 返回排行榜的内容。判断结果为success的时候，才会有效
	 * 
	 * @return
	 */
	public static String getRank() {
		String rankJson = HttpUtil.sendPost(Data.rankUrl, Cpdaily.signIds, Data.getHeaders());
		JSONArray jsonArray = JSONObject.fromObject(rankJson).getJSONObject("datas").getJSONArray("signs");

		// 用来存储排行榜数据
		StringBuilder rankList = new StringBuilder();
		jsonArray.forEach((name) -> {
			JSONObject object = JSONObject.fromObject(name);
			rankList.append(
					object.get("studentId") + "->" + object.get("nickName") + "->" + object.get("likedNum") + "赞\n");
		});

		return rankList.toString();
	}

	/**
	 * 刷赞
	 * 
	 * @return
	 */
	public static void goGoGo() {
		int i = Data.likeNum;
		String param = "{\"stuSignWid\":\"" + stuSignWid + "\",\"operType\":0}";
		System.out.println("开始刷赞...");
		while (i > 0) {
			i--;
			HttpUtil.sendPost(Data.giveLike, param, Data.getHeaders());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("刷赞结束");
	}
	

}
