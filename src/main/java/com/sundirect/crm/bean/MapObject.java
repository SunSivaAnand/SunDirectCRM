package com.sundirect.crm.bean;

/*import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.PlayerEventsPlayerevent;
import com.sundirect.crm.smsentity.Asset;
import com.sundirect.crm.smsentity.Subscription;
*/
public class MapObject {
	
	private Asset asset;
	//private Subscription sub;
	private DeviceInfo device;
	//private PlayerEventsPlayerevent play;
	private PlayerEvent playerEvent;
	private String message;
	private String message1;
	private Integer inc;
	public DeviceInfo getDevice() {
		return device;
	}
	public void setDevice(DeviceInfo device) {
		this.device = device;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public Integer getInc() {
		return inc;
	}
	public void setInc(Integer inc) {
		this.inc = inc;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public PlayerEvent getPlayerEvent() {
		return playerEvent;
	}
	public void setPlayerEvent(PlayerEvent playerEvent) {
		this.playerEvent = playerEvent;
	}
	
	
	 

}
