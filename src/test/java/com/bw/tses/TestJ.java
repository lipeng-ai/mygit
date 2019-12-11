package com.bw.tses;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bawei.util.ArrayUtil;

public class TestJ {

	@Test
	public void test() {
		List<String> list=new ArrayList<String>();
		System.out.println(ArrayUtil.isEmpty(list));;
	}
	@Test
	public void test1() {
		List<String> list=new ArrayList<String>();
		list.add("123");
		System.out.println(ArrayUtil.isEmpty(list));;
	}
}
