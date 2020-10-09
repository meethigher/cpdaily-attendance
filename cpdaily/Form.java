package cpdaily;

/**
 * 
 * @author kit chen
 * @description 用来生成提交请求体的bean类
 */
public class Form {
	private String signInstanceWid;
	private String longitude;
	private String latitude;
	private String isMalposition;
	private String abnormalReason;
	private String signPhotoUrl;
	private String position;
	private String qrUuid;

	public String getSignInstanceWid() {
		return signInstanceWid;
	}

	public void setSignInstanceWid(String signInstanceWid) {
		this.signInstanceWid = signInstanceWid;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIsMalposition() {
		return isMalposition;
	}

	public void setIsMalposition(String isMalposition) {
		this.isMalposition = isMalposition;
	}

	public String getAbnormalReason() {
		return abnormalReason;
	}

	public void setAbnormalReason(String abnormalReason) {
		this.abnormalReason = abnormalReason;
	}

	public String getSignPhotoUrl() {
		return signPhotoUrl;
	}

	public void setSignPhotoUrl(String signPhotoUrl) {
		this.signPhotoUrl = signPhotoUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getQrUuid() {
		return qrUuid;
	}

	public void setQrUuid(String qrUuid) {
		this.qrUuid = qrUuid;
	}

	@Override
	public String toString() {
		return "{\"signInstanceWid\":\"" + signInstanceWid + "\", \"longitude\":\"" + longitude + "\", \"latitude\":\"" + latitude
				+ "\", \"isMalposition\":\"" + isMalposition + "\", \"abnormalReason\":\"" + abnormalReason
				+ "\", \"signPhotoUrl\":\"" + signPhotoUrl + "\", \"position\":\"" + position + "\", \"qrUuid\":\"" + qrUuid + "\"}";
	}
	public static void main(String[] args) {
		System.out.println(new Form().toString());
	}

}
