package com.sundirect.crm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sundirect.crm.bean.APIKey;
import com.sundirect.crm.bean.OrderCreationAPI;
import com.sundirect.crm.service.APIServiceImpl;

public class SHACheckSum {
	
	

	private static final Logger log = LoggerFactory.getLogger(APIServiceImpl.class);

	/*
	 * @Value("${API.api.sk}") private String csk;
	 */

	/*
	 * public static void main(String[] args) { OrderCreationAPI model = new
	 * OrderCreationAPI(); model.setAction("create");
	 * model.setMobileNo("7448492072"); model.setSmc("70409808014");
	 * model.setPackageIds(1101); generateCheckSum(model); }
	 */

	public Map<String, String> generateCheckSum(OrderCreationAPI model,String servicekey) {
		try {
			long t = getCurrentTimestamp();
			String o = String.valueOf(t);
			String timestamp = o.substring(0, 10);
			log.info("timestamp: {}", timestamp);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(model);
			log.info("Converting model to Json String: {}", json);
			JSONObject jsonObject = new JSONObject(json);
			Map<String, Object> map = new LinkedHashMap<>();
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = jsonObject.get(key);
				map.put(key, value);
			}
			Map<String, Object> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
							LinkedHashMap::new));
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(sortedMap);
			log.info("Sorted Json String {}", jsonString);
			String finalData = timestamp + "|" + jsonString;
			log.info("Timestamp and json string {}", finalData);
			String secretKey = servicekey;
			String hashValue = generateHMAC(finalData, secretKey);
			log.info("Generated key: {}", hashValue);
			Map<String, String> retMap = new HashMap<String, String>();
			retMap.put(timestamp, hashValue);
			return retMap;
		} catch (Exception e) {
			return null;
		}
	}

	public long getCurrentTimestamp() {
		Date d = new Date();
		return d.getTime();
	}

	public static String generateHMAC(String data, String secretKey) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(secretKey.getBytes("UTF-8"));

			SecretKeySpec secretKeySpec = new SecretKeySpec(hash, "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKeySpec);

			byte[] bytes = mac.doFinal(data.getBytes("UTF-8"));

			// Convert byte array to a hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : bytes) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public APIKey fetchAPIKeys(String apiPath,OrderCreationAPI model) {
		HttpURLConnection connection = null;
		APIKey apiKey=new APIKey();
		String resp;
		String result;
		String path=apiPath;
		log.info("Order creation Url Path: {}",path);
		try {
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			//connection.setRequestProperty("X-myplex-signature",value );
			connection.setRequestProperty("X-myplex-partnerid", "SUNDIRECT");			
			//connection.setRequestProperty("X-myplex-timestamp", key);
			//connection.setRequestProperty("X-myplex-platform", "WEB_CLIENT");
			//connection.setRequestProperty("X-forwarded-for", ipAddress);
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
				//result = jsonobj.getString("message");
				
				apiKey.setSignature(jsonobj.getString("signature"));
				apiKey.setTimeStamp(jsonobj.getString("timestamp"));
				log.info("apiKey-signature::: {}",apiKey.getSignature());
				log.info("apiKey-signature::: {}",apiKey.getTimeStamp());
				return apiKey;
			}
			log.info("RESPONSE MESSAGE " + connection.getResponseMessage());
			resp = connection.getResponseMessage();
			return apiKey;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}		
		return apiKey;
		
	}

}
