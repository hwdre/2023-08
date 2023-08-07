package com.Luke.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.RequestContextFilter;

//Controller Service Repository Component = 객체

@Component
public class Util {

	// 문자열이 들어오면 숫자로 변경하기
	public int sti(String str) {

		int result = 0;

		try {

			result = Integer.parseInt(str);

		} catch (Exception e) {
			// String re=""; //숫자를 모을 문자열
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {

				if (Character.isDigit(str.charAt(i))) { // 숫자인지 물어봄

					// re += str.charAt(i); //숫자형 문자열만 모음
					sb.append(str.charAt(i));

				}

			}

			result = Integer.parseInt(sb.toString()); // 모은 숫자를 숫자로 변환해서 result에 넣음

		}

		return result;

	}

	public String exchange(String str) {

		str = str.replaceAll("<", "&lt");
		str = str.replaceAll(">", "&gt");

		return str;

	}

	public String getIp() {

		String ip = null;
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
	            .getRequest();

	    // Check for IPv4 addresses first
	    String[] headersToCheck = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
	            "HTTP_X_FORWARDED_FOR", "X-Real-IP", "X-RealIP", "REMOTE_ADDR" };

	    for (String header : headersToCheck) {
	        ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            // Found a valid IPv4 address
	            break;
	        }
	    }

	    // If no IPv4 address found, fall back to the default method
	    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }

	    return ip;
	}

	
	//숫자인지 확인하는 메소드입니다.
	public boolean isNum(Object obj) {
		try {
			Integer.parseInt(String.valueOf(obj));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
