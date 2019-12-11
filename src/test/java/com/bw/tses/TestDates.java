package com.bw.tses;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bawei.utils.DateUtils;

public class TestDates {

	@Test
	public void test() {
		long age = DateUtils.getAge("1990-12-10");
		System.out.println("年齡是:"+age);
	}
	@Test
	public void test1() {
		long age = DateUtils.getDaysByDeparted("1990-12-10");
		System.out.println("过去距今的日期是:"+age);
	}
	@Test
	public void test2() {
		long age = DateUtils.getDaysByFuture("2020-12-10");
		System.out.println("未来距今的日期是:"+age);
	}
	@Test
	public void test3() {
		String dateByMonthInit = DateUtils.getDateByMonthInit("1990-12-10");
		System.out.println("月初是:"+dateByMonthInit);
	}
	@Test
	public void test4() {
		String dateByMonthInit = DateUtils.getDateByMonthLast("1990-12-10");
		System.out.println("月末是:"+dateByMonthInit);
	}
}
