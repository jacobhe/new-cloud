//package com.cloud.user.web.util;
//
//import com.cloud.user.constant.SercurityConstants;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.util.ObjectUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Array;
//import java.net.URLEncoder;
//import java.util.*;
//
///**
// * @author summer
// */
//public class WebUtils extends BaseWebUtils {
//
//    public static String removeFourChar(String content) {
//        if (StringUtils.isBlank(content)) {
//            return "";
//        }
//        byte[] conbyte = content.getBytes();
//        for (int i = 0; i < conbyte.length; i++) {
//            if ((conbyte[i] & 0xF8) == 0xF0) {
//                for (int j = 0; j < 4; j++) {
//                    conbyte[i + j] = 0x30;
//                }
//                i += 3;
//            }
//        }
//        content = new String(conbyte);
//        return content.replaceAll("0000", "");
//    }
//
//    /**
//     * 登录用户的session情报取得
//     * @param request
//     * @param response
//     * @return
//     */
//    public static void setLoginMemberKey(HttpServletRequest request, HttpServletResponse response) {
//        Cookie cookie = getCookie(request, SercurityConstants.SESSION_KEY);
//        String memberKey = null;
//        if (cookie == null) {
//        		addCookie(response, SercurityConstants.SESSION_KEY, memberKey, "/", 60 * 60 * 24);
//        }
//    }
//
//    /**
//     * 登录用户的session情报取得
//     * @param request
//     * @param response
//     * @return
//     */
//    public static void  removeLoginMemberKey(HttpServletRequest request, HttpServletResponse response) {
//        Cookie cookie = getCookie(request, SercurityConstants.SESSION_KEY);
//        if (cookie != null) {
//        	 	addCookie(response, SercurityConstants.SESSION_KEY, "", "/", 60 * 60 * 24);
//        }
//    }
//
//    public static void appendQueryProperties(StringBuilder targetUrl, LinkedHashMap<String, Object> model, String encoding) {
//        boolean first = (targetUrl.indexOf("?") < 0);
//        for (Map.Entry<String, Object> entry : queryProperties(model).entrySet()) {
//            Object rawValue = entry.getValue();
//            Iterator valueIter = null;
//            if (rawValue != null && rawValue.getClass().isArray()) {
//                valueIter = Arrays.asList(ObjectUtils.toObjectArray(rawValue)).iterator();
//            } else if (rawValue instanceof Collection) {
//                valueIter = ((Collection) rawValue).iterator();
//            } else {
//                valueIter = Collections.singleton(rawValue).iterator();
//            }
//            while (valueIter.hasNext()) {
//                Object value = valueIter.next();
//                if (first) {
//                    targetUrl.append('?');
//                    first = false;
//                } else {
//                    targetUrl.append('&');
//                }
//                String encodedKey = urlEncode(entry.getKey(), encoding);
//                String encodedValue = (value != null ? urlEncode(value.toString(), encoding) : "");
//                targetUrl.append(encodedKey).append('=').append(encodedValue);
//            }
//        }
//    }
//
//    private static Map<String, Object> queryProperties(Map<String, Object> model) {
//        Map<String, Object> result = new LinkedHashMap<>();
//        for (Map.Entry<String, Object> entry : model.entrySet()) {
//            if (isEligibleProperty(entry.getValue())) {
//                result.put(entry.getKey(), entry.getValue());
//            }
//        }
//        return result;
//    }
//
//
//    private static boolean isEligibleProperty(Object value) {
//        if (value == null) {
//            return false;
//        }
//        if (isEligibleValue(value)) {
//            return true;
//        }
//
//        if (value.getClass().isArray()) {
//            int length = Array.getLength(value);
//            if (length == 0) {
//                return false;
//            }
//            for (int i = 0; i < length; i++) {
//                Object element = Array.get(value, i);
//                if (!isEligibleValue(element)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//
//        if (value instanceof Collection) {
//            Collection coll = (Collection) value;
//            if (coll.isEmpty()) {
//                return false;
//            }
//            for (Object element : coll) {
//                if (!isEligibleValue(element)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//
//        return false;
//    }
//
//    private static String urlEncode(String input, String charsetName) {
//        try {
//            return (input != null ? URLEncoder.encode(input, charsetName) : null);
//        } catch (UnsupportedEncodingException e) {
//            return null;
//        }
//    }
//
//    private static boolean isEligibleValue(Object value) {
//        return (value != null && BeanUtils.isSimpleValueType(value.getClass()));
//    }
//}