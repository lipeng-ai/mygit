package com.bawei.util;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 创建uuid字符串
	 * @return
	 */
	public static String getUUIDString() {
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		//c8bf9936-0556-49da-bd42-e8b83c75494d
		String uuid=UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);

	}

}
