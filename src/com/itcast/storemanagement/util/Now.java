package com.itcast.storemanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {
	public static String time(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
