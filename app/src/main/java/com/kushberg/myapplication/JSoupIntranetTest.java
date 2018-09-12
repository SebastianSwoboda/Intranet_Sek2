package com.kushberg.myapplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupIntranetTest {

	public static void main(String[] args) throws IOException {
		/*
		final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n"
				+ "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
*/
		
		final String USER_AGENT = "Mozilla";
		String loginFormUrl = "https://intranet.tam.ch/";
		String loginActionUrl = "https://intranet.tam.ch/";
		String timeTableUrl = "https://intranet.tam.ch/tbz/calendar";
		String loginuser = "sebastian.swoboda@edu.tbz.ch";
		String loginpassword = "Sebolanawurscht9";
		String loginschool = "tbz";

		Map<String, String> cookies = new HashMap<>();
		Map<String, String> formData = new HashMap<>();
		Map<String, String> timeTableAccess = new HashMap<>();

		Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT)
				.execute();
		Document loginDoc = loginForm.parse();

		cookies.putAll(loginForm.cookies());

		formData.put("button", "Anmelden");
		formData.put("utf8", "e2 9c 93");
		formData.put("loginuser", loginuser);
		formData.put("loginpassword", loginpassword);
		formData.put("loginschool", loginschool);

		Connection.Response homePage = Jsoup.connect(loginActionUrl).cookies(cookies).data(formData)
				.method(Connection.Method.POST).userAgent(USER_AGENT).execute();

		// System.out.println(homePage.parse().html());

		cookies.putAll(homePage.cookies());

		timeTableAccess.put("menuitem", "Stundenplan");

		Connection.Response timetablePage = Jsoup.connect(timeTableUrl).cookies(cookies).data(timeTableAccess)
				.method(Connection.Method.GET).userAgent(USER_AGENT).execute();

//		 System.out.println(timetablePage.parse().html());
		String lol = timetablePage.url().toString();
		 System.out.println(lol);
		
		 
/*
		Document timeTableDocument = timetablePage.parse();
		Boolean lol = false;

		lol = timeTableDocument.hasClass("tta-until");
		 System.out.println(lol);
		System.out.println(timeTableDocument);
		
		*/

		/*
		for (Element div : timeTableDocument
				.select("div.in-event-header")) {
			Elements span = div.select("span.in-title");
			
			
			
			System.out.println(span.text());
				
				}*/

		}
	}

