package com.sundirect.crm.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sundirect.crm.bean.Asset;
import com.sundirect.crm.bean.OrderCreation;
import com.sundirect.crm.bean.OrderCreationAPI;
import com.sundirect.crm.controller.APIController;
import com.sundirect.crm.utils.ApiReturn;
import com.sundirect.crm.utils.SHACheckSum;

@Component
public class APIServiceImpl implements APIService{
	private static final Logger log = LoggerFactory.getLogger(APIServiceImpl.class);
	@Value("${API.sms.url}")
	private String smsPath;
	
	@Value("${API.sms.allPlan}")
	private String allPlan;
	
	@Value("${API.sms.tenant}")
	private String tenant;

	@Value("${API.api.sk}") 
	private String csk;
	
	@Value("${API.api.order}")
	private String apiPath;
	
	@Value("${API.sms.order}")
	private String orderCreation;
	
	@Value("${API.api.server}")
	private String ipAddress;
	
	@Value("${API.sms.allSubscription}")
	private String allSubscription;
	
	@Value("${API.info.plan}")
	private String sdPlan;

	@Value("${API.info.uri}")
	private String apiUrl;
	
	@Value("${API.info.user}")
	private String apiEndPoint;
	
	@Value("${API.info.key}")
	private String key;
	
	@Value("${API.sms.allAssetDetails}")
	private String allAssetDetails;
	
	@Value("${API.sms.allAssetByType}")
	private String allAssetByType;
	
	@Override
	public String getAllPlanAPI(String status) {
		
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String path=smsPath+"/"+allPlan+"?status="+status;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				//log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}

	@Override
	public String orderCreation(OrderCreation model) {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String path=smsPath+"/"+orderCreation;
			log.info("Order creation Url Path: {}",path);
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(model);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(json);
			writer.flush();
			writer.close();
			os.close();
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			// log.info("request body " + createPayLoadSoap(rp));
			log.info("content-type " + connection.getContentType());
			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				resp = sb.toString();
				log.info("response" + resp);
				JSONObject jsonobj = new JSONObject(resp);
				result = jsonobj.getString("message");
				log.info("message:::" + result);
				return resp;
			}
			log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
			resp = connection.getResponseMessage();
			return resp;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}		
		return null;
	}
	
	
	@Override
	public String orderCreationAPI(OrderCreationAPI model) {
		HttpURLConnection connection = null;
		String resp;
		String result;
		SHACheckSum checkSum = new SHACheckSum();
		try {
			String path=apiPath;
			log.info("Order creation Url Path: {}",path);
			Map<String, String> map=new HashMap<String, String>();
			map=checkSum.generateCheckSum(model,csk);
			String key="";
			String value="";
			for(String s:map.keySet()) {
				key=s;
				value=map.get(key);
			}
			log.info("timestamp: {} hash: {}",key,value);
			log.info("smc: {} mobile: {} planId: {} action: {}",model.getSmc(),model.getMobileNo(),model.getPackageIds(),model.getAction());
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-myplex-signature",value );
			connection.setRequestProperty("X-myplex-partnerid", "SUNDIRECT");			
			connection.setRequestProperty("X-myplex-timestamp", key);
			connection.setRequestProperty("X-myplex-platform", "WEB_CLIENT");
			connection.setRequestProperty("X-forwarded-for", ipAddress);
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(model);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(json);
			writer.flush();
			writer.close();
			os.close();
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			// log.info("request body " + createPayLoadSoap(rp));
			log.info("content-type " + connection.getContentType());
			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				resp = sb.toString();
				log.info("response" + resp);
				JSONObject jsonobj = new JSONObject(resp);
				result = jsonobj.getString("message");
				log.info("message:::" + result);
				return resp;
			}
			log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
			resp = connection.getResponseMessage();
			return resp;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}		
		return null;
	}

	@Override
	public String getAllSubscription(Integer userId, String country) {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String path=smsPath+"/"+allSubscription+userId+"/"+country;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			///connection.setDoOutput(true);
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	public String getUserInfo(String info,Integer keyVal,String param) {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String path=apiUrl+apiEndPoint+"?"+key+"="+keyVal+"&"+param+"="+info;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	public String getAllSDPlan() {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String path=apiUrl+sdPlan;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				//log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	
	@Override
	public String getOtherInfo(String info,Integer keyVal,String param,String startDate, String endDate) {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			//String path=apiUrl+apiEndPoint+"?"+key+"="+keyVal+"&"+param+"="+info+"&"+
			//					"start_date"+"="+startDate+"&"+"end_date"+"="+endDate;
			String path=apiUrl+apiEndPoint+"?"+"start_date"+"="+startDate+"&"+"end_date"+"="+endDate+"&"+param+"="+info+"&"+key+"="+keyVal;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				//log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	@Override
	public String getAllAssetDetails(List<String> assets) {
		
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
			String paramString = assets.stream().collect(Collectors.joining(","));
			log.info("param value: {}",paramString);
			
			String path=smsPath+allAssetDetails+"?assetIds="+paramString;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				//log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}

	@Override
	public String getAllLiveAsset() {
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
					
			String path=smsPath+allAssetByType+"?type="+"live";
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			///connection.setDoOutput(true);
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				//log.info("response" + sb.toString());
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}
	
	
	@Override
	public String getAllDetailBasedAsset(String contentId) {
		// TODO Auto-generated method stub
		HttpURLConnection connection = null;
		String resp;
		String result;
		try {
					
			String path=apiUrl+apiEndPoint+"?"+key+"="+"1"+"&"+"content_id"+"="+contentId;
			log.info("All plan Url Path: {}",path);
			URL url= new URL(path);			
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			//connection.setRequestProperty("X-MYPLEX-TENANT-ID",tenant );
			///connection.setDoOutput(true);
			connection.connect();
			StringBuilder sb = new StringBuilder();
			int responseCode = connection.getResponseCode();
			log.info("responsecode " + responseCode);
			log.info("content-type " + connection.getContentType());

			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("inside httok");
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line.trim());
				}
				br.close();
				resp = sb.toString();
				return resp;
			} else {
				log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
				resp = connection.getResponseMessage();
				return resp;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			log.error("exception in notification with message {} for ", e.getMessage());
			return null;
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}
	

}
