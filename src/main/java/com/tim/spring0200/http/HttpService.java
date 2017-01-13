package com.tim.spring0200.http;
import java.util.HashMap;
import java.util.Map;
public class HttpService {

	public static Map<String, String> post2(String reqData, String reqUrl, String encoding) {
		Map<String, String> rspData = new HashMap<String, String>();
		// 发送后台请求数据
		HttpClient hc = new HttpClient(reqUrl, 30000, 30000);
		try {
			int status = hc.send2(reqData, encoding);
			if (200 == status) {
				String resultString = hc.getResult();
				if (null != resultString && !"".equals(resultString)) {
					System.out.println("resultString:" + resultString);
				} else {
					System.out.println("=============error==============");
				}
			} else {
			}
		} catch (Exception e) {
		}
		return rspData;
	}
	public static String get2(String reqUrl, String encoding) {
		// 发送后台请求数据
		HttpClient hc = new HttpClient(reqUrl, 30000, 30000);
		try {
			int status = hc.sendGet(encoding);
			if (200 == status) {
				String resultString = hc.getResult();
				if (null != resultString && !"".equals(resultString)) {
					return resultString;
				}
			} else {
			}
		} catch (Exception e) {
		}
		return null;
	}
	public static void main(String[] args) {
		String queryParams = "order_no=00050001&mer_id=310310189120393&start_date=20161222&end_date=20161223&dsorder_no=&&settle_flag=&settle_date=&mer_name=&mac=51E977B8587E69FB04EFDD13B8571650";
		String url3 = "http://210.22.91.77:24111/queryService/billqueryServlet";
		post2(queryParams, url3, "utf-8");

	}
}
