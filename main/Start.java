package main;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import cpdaily.Cpdaily;
import cpdaily.Data;
import cpdaily.HttpUtil;
import cpdaily.SendMail;
/**
 * 
 * @author kit chen
 * @description 运行入口
 */
public class Start {
	public static int hour;
	public static int minute;
	public static int second;
	public static int delay;
	

	public static boolean flag = false;
	static {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入Cpdaily-Extension:");
		Data.cpdailyExtension = input.nextLine();
		System.out.println("请输入atw_tc:");
		String atwTc = input.nextLine();
		System.out.println("请输入MOD_AUTH_CAS:");
		String modAuthCas = input.nextLine();
		Data.cookie = "acw_tc=" + atwTc + "; MOD_AUTH_CAS=" + modAuthCas;
		System.out.println("请输入要上传的照片链接：");
		Data.photoUrls=input.nextLine();
		System.out.println("请输入接收通知的邮箱：");
		Data.toMail = input.nextLine();
		System.out.println("输入要刷赞的数量：");
		Data.likeNum=input.nextInt();
		System.out.println("请输入开始小时：");
		hour=input.nextInt();
		System.out.println("请输入开始分钟：");
		minute = input.nextInt();
		System.out.println("请输入开始秒：");
		second = input.nextInt();
		System.out.println("请输入延迟，单位毫秒：");
		delay = input.nextInt();

		input.close();
		System.out.println("正在运行...");
	}

	public static void beginSubmit() {
		if (flag) {
			if (delay > 0) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println("==============");
			System.out.println("开始签到...");
			String date = new Date().toLocaleString();
			String result = Cpdaily.submitAttend();
			if ("success".equals(result)) {
				System.out.println(date + " 签到成功");
				if (Data.likeNum>0) {
					Cpdaily.goGoGo();
				} else {
					//睡眠是为了留出时间来加载排行榜
					try {
						Thread.sleep(1000 * 30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String rankList = Cpdaily.getRank();
				String[] mails = new String[2];
				mails[0] = "查寝成功通知";
				mails[1] = "时间：" + date + "\n地点：" + Data.poi + "\n经度：" + Data.log + "\n纬度：" + Data.lat
						+ "\n排行榜：以下展示列表\n" + rankList + "\n签到Id：" + Cpdaily.signIds;
				System.out.println(SendMail.send(mails));
				flag = false;
			}else if("submited".equals(result)) {
				System.out.println(date+"已经签到过了");
				flag=false;
			} else {
				System.out.println(date + " 签到失败");
			}
		}
	}

	public static void main(String[] args) {
		// 开启保持会话线程
		new Thread(() -> {
			while (true) {
				HttpUtil.sendGet(Data.keepingUrl, Data.getHeaders());
				try {
					Thread.sleep(1000 * 60 * 10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		// 开启关键线程
		new Thread(() -> {
			Calendar c;
			while (true) {
				c = Calendar.getInstance();
				int currentHour = c.get(Calendar.HOUR_OF_DAY);
				int currentMinute = c.get(Calendar.MINUTE);
				int currentSecond = c.get(Calendar.SECOND);
				if (currentHour == (hour-1) && currentMinute == minute && currentSecond == second) {
					Cpdaily.prepData();
				}
				if (currentHour == hour && currentMinute == minute && currentSecond == second) {
					flag = true;
				}
				beginSubmit();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
