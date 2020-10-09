package cpdaily;

import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author kit chen
 * @description �ڲ���ʵ�ַ���
 */
public class Cpdaily {
	/**
	 * �����������Ԥ��������
	 */
	public static String prepForm;
	/**
	 * ����ˢ�޵��������
	 */
	public static String stuSignWid;
	/**
	 * ������ȡ��ϸ�������а���������
	 */
	public static String signIds;

	/**
	 * ����Ԥ�������ݣ��԰��һ�ĸо����ܶ���?
	 * 
	 * @return
	 */
	public static void prepData() {
		System.out.println("��ʼԤ��������..");
		prepForm = fillForm();
		System.out.println("���ݼ������");
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public static String getAttend() {
		JSONObject jsonObject = JSONObject.fromObject(HttpUtil.sendPost(Data.queryAttend, "{}", Data.getHeaders()))
				.getJSONObject("datas").getJSONArray("unSignedTasks").getJSONObject(0);

		// ��һ������stuSignWid��Ϊ���²�ˢ����
		stuSignWid = jsonObject.getString("stuSignWid");
		signIds="{\"signWid\":" + jsonObject.getInt("signWid") + ",\"signInstanceWid\":"
				+ jsonObject.getInt("signInstanceWid") + "}";
		return signIds;
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @return
	 */
	public static String getAttendDetail() {
		return JSONObject.fromObject(HttpUtil.sendPost(Data.detailAttend, getAttend(), Data.getHeaders()))
				.getJSONObject("datas").toString();
	}

	/**
	 * ���������Ƭ
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
	 * ���
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
	 * �ύ����
	 * 
	 * @return
	 */
	public static String submitAttend() {
		String result = JSONObject.fromObject(HttpUtil.sendPost(Data.submitAttend, prepForm, Data.getSubHeaders()))
				.get("message").toString();
		if ("SUCCESS".equals(result)) {
			return "success";
		}else if("ǩ��ʧ��".equals(result)) {
			return "submited";
		} else {
			return "�Զ��ύ����ʧ�ܣ�ԭ��" + result;
		}
	}

	/**
	 * �������а�����ݡ��жϽ��Ϊsuccess��ʱ�򣬲Ż���Ч
	 * 
	 * @return
	 */
	public static String getRank() {
		String rankJson = HttpUtil.sendPost(Data.rankUrl, Cpdaily.signIds, Data.getHeaders());
		JSONArray jsonArray = JSONObject.fromObject(rankJson).getJSONObject("datas").getJSONArray("signs");

		// �����洢���а�����
		StringBuilder rankList = new StringBuilder();
		jsonArray.forEach((name) -> {
			JSONObject object = JSONObject.fromObject(name);
			rankList.append(
					object.get("studentId") + "->" + object.get("nickName") + "->" + object.get("likedNum") + "��\n");
		});

		return rankList.toString();
	}

	/**
	 * ˢ��
	 * 
	 * @return
	 */
	public static void goGoGo() {
		int i = Data.likeNum;
		String param = "{\"stuSignWid\":\"" + stuSignWid + "\",\"operType\":0}";
		System.out.println("��ʼˢ��...");
		while (i > 0) {
			i--;
			HttpUtil.sendPost(Data.giveLike, param, Data.getHeaders());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ˢ�޽���");
	}
	

}
