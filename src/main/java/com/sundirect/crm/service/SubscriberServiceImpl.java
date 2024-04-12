package com.sundirect.crm.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SubscriberServiceImpl implements SubscriberService {

	/*
	 * @Autowired SubscriberRepo subsInfo;
	 * 
	 * @Autowired DeviceRepo deviceInfo;
	 * 
	 * @Autowired SubscriptionRepo subscription;
	 * 
	 * @Autowired PlayerEventRepo playerEvent;
	 * 
	 * @Autowired AssetRepo assetRepo;
	 * 
	 * 
	 * @Override public MyplexUserUser findUserInformation(String id,String request)
	 * { try { if(request.equalsIgnoreCase("UserID")) {
	 * //System.out.print("userinfo-----------"); Optional<MyplexUserUser>
	 * userinfo=subsInfo.findById(Integer.parseInt(id));
	 * //System.out.print("userinfo-----------"+userinfo); return userinfo.get();
	 * }else if(request.equalsIgnoreCase("MobileNo")) {
	 * //System.out.print("MobileNo-----------"); Optional<MyplexUserUser>
	 * userinfo=subsInfo.findByMobileNo(Long.parseLong(id));
	 * //System.out.print("MobileNo-----------"+userinfo); return userinfo.get();
	 * }else if(request.equalsIgnoreCase("SMC")){ Optional<MyplexUserUser>
	 * userinfo=subsInfo.findBySmc(id); return userinfo.get(); } }catch (Exception
	 * e) { //System.out.print(e.toString()); } //Optional<MyplexUserUser>
	 * userinfo=subsInfo.findById(ids); return null; }
	 * 
	 * 
	 * @Override public List<MyplexUserUser> findUserList(String id,String request)
	 * { try { List<MyplexUserUser> userDetail=new ArrayList<MyplexUserUser>();
	 * if(request.equalsIgnoreCase("UserID")) {
	 * userDetail.add(subsInfo.findById(Integer.parseInt(id)).get()); return
	 * userDetail; }else if(request.equalsIgnoreCase("MobileNo")) {
	 * userDetail=subsInfo.findByMobileNo(Long.parseLong(id)).get(); return
	 * userDetail; }else if(request.equalsIgnoreCase("SMC")){
	 * userDetail=subsInfo.findBySmc(id).get(); return userDetail; } }catch
	 * (Exception e) { //System.out.print(e.toString()); }
	 * //Optional<MyplexUserUser> userinfo=subsInfo.findById(ids); return null; }
	 * 
	 * @Override public List<MyplexUserDevice> findDeviceInfoByUserId(Integer
	 * userId) {
	 * 
	 * List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
	 * 
	 * deviceList=deviceInfo.findByUserId(userId);
	 * 
	 * return deviceList; }
	 * 
	 * 
	 * @Override public List<Subscription> findSubscriptionByuserId(Integer userId)
	 * {
	 * 
	 * Date date=new Date();
	 * 
	 * List<Subscription>
	 * sub=subscription.findByUserIdAndValidTillGreaterThan(userId, date); //
	 * List<Subscription> sub=subscription.findByUserId(userId); return sub; }
	 * 
	 * @Override public List<Subscription> findExpiredSubscriptionByuserId(Integer
	 * userId) { Date date=new Date(); List<Subscription>
	 * sub=subscription.findByUserIdAndValidTillLessThan(userId, date); return sub;
	 * }
	 * 
	 * @Override public List<PlayerEventsPlayerevent> findPlayerevents(Integer
	 * userId) { Date currentDate=new Date(); Calendar cal=Calendar.getInstance();
	 * cal.setTime(currentDate); cal.add(Calendar.DAY_OF_YEAR, -7); Date
	 * sevenDayBefore=cal.getTime();
	 * 
	 * return playerEvent.findByUserIdAndCreatedAtGreaterThan(userId,
	 * sevenDayBefore); }
	 * 
	 * @Override public Asset findByAsset(Integer id) { return
	 * assetRepo.findById(id).get(); }
	 */

}
