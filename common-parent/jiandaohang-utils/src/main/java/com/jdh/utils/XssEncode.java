package com.jdh.utils;

public class XssEncode {

	public static String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			case '(':
				sb.append('（');//
				break;
			case ')':
				sb.append('）');//
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

    //输入的内容中请不要使用+,@,&,#,%,",=,\,<,>,(,)等特殊字符！
	public static boolean isIllegalString(String s){
        if (s == null || s.isEmpty()) {
            return false;
        }
       if (s.contains(">")||s.contains("<")||s.contains("\'")||s.contains("\"")||s.contains("&")||s.contains("\\")||s.contains("#")||s.contains("(")||s.contains(")")||s.contains("@")||s.contains("+")||s.contains("=")){
           return true;
       }

       return false;
    }

}