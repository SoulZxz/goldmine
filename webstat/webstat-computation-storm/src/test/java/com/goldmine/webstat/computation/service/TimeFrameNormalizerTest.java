package com.goldmine.webstat.computation.service;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.utils.TimeFrameNormalizer;

public class TimeFrameNormalizerTest {

	private Date date;

	@BeforeClass
	public void init() {
		// 2016.02.16 10:03:41
		date = new Date(1455588221788L);
	}

	@Test
	public void testCalculateMinute() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.MINUTE;

		// expect 声明预期结果
		// 10:03:00
		Date expected = new Date(1455588180000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateHour() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.HOUR;

		// expect 声明预期结果
		// 10:00:00
		Date expected = new Date(1455588000000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateDay() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.DAY;

		// expect 声明预期结果
		// 2016.02.16 00:00:00
		Date expected = new Date(1455552000000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateWeek() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.WEEK;

		// expect 声明预期结果
		// 2016.02.14 00:00:00
		Date expected = new Date(1455379200000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateMonth() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.MONTH;

		// expect 声明预期结果
		// 2016.02.01 00:00:00
		Date expected = new Date(1454256000000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateTillNow() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.TILL_NOW;

		// expect 声明预期结果
		Date expected = null;

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameStart(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEndMinute() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.MINUTE;
		Date start = new Date(1455588180000L);

		// expect 声明预期结果
		// 10:04:00
		Date expected = new Date(1455588240000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEndHour() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.HOUR;
		// 10:00:00
		Date start = new Date(1455588000000L);

		// expect 声明预期结果
		// 11:00:00
		Date expected = new Date(1455591600000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEndDay() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.DAY;
		// 2016.02.16 00:00:00
		Date start = new Date(1455552000000L);

		// expect 声明预期结果
		// 2016.02.17 00:00:00
		Date expected = new Date(1455638400000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEndWeek() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.WEEK;
		// 2016.02.14 00:00:00
		Date start = new Date(1455379200000L);

		// expect 声明预期结果
		// 2016.02.21 00:00:00
		Date expected = new Date(1455984000000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateMonthEnd() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.MONTH;
		// 2016.02.01 00:00:00
		Date start = new Date(1454256000000L);

		// expect 声明预期结果
		// 2016.03.01 00:00:00
		Date expected = new Date(1456761600000L);

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateTillNowEnd() {
		// setup 设置所需参数
		TimeFrame timeFrame = TimeFrame.TILL_NOW;

		// expect 声明预期结果
		Date expected = null;

		// execute 执行运算
		Date actual = TimeFrameNormalizer.calculateTimeFrameEnd(date, timeFrame);

		// assert 断言期望结果与实际结果相符
		assertEquals(expected, actual);
	}
}
