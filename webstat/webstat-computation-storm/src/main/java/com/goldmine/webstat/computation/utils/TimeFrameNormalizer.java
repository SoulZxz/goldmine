package com.goldmine.webstat.computation.utils;

import java.util.Calendar;
import java.util.Date;

import com.goldmine.webstat.computation.bean.TimeFrame;

public class TimeFrameNormalizer {

	private static final long SEC = 1000L;

	private static final long MINUTE = SEC * 60;

	private static final long HOUR = MINUTE * 60;

	private static final long DAY = HOUR * 24;

	private static final long WEEK = DAY * 7;

	public static Date calculateTimeFrameStart(Date date, TimeFrame timeFrame) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		switch (timeFrame) {
		case MINUTE:
			clearSec(calendar);
			return calendar.getTime();
		case HOUR:
			clearMinute(calendar);
			return calendar.getTime();
		case DAY:
			clearHour(calendar);
			return calendar.getTime();
		case WEEK:
			clearDayOfWeek(calendar);
			return calendar.getTime();
		case MONTH:
			clearDayOfMonth(calendar);
			return calendar.getTime();
		case TILL_NOW:
			return null;
		default:
			throw new IllegalArgumentException("unsupported TimeFrame : " + timeFrame);
		}
	}

	public static Date calculateTimeFrameEnd(Date start, TimeFrame timeFrame) {
		switch (timeFrame) {
		case MINUTE:
			return new Date(start.getTime() + MINUTE);
		case HOUR:
			return new Date(start.getTime() + HOUR);
		case DAY:
			return new Date(start.getTime() + DAY);
		case WEEK:
			return new Date(start.getTime() + WEEK);
		case MONTH:
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			addMonth(calendar);
			return calendar.getTime();
		case TILL_NOW:
			return null;
		default:
			throw new IllegalArgumentException("unsupported TimeFrame : " + timeFrame);
		}
	}

	private static void clearMilliSec(Calendar calendar) {
		calendar.clear(Calendar.MILLISECOND);
	}

	private static void clearSec(Calendar calendar) {
		calendar.clear(Calendar.SECOND);
		clearMilliSec(calendar);
	}

	private static void clearMinute(Calendar calendar) {
		calendar.clear(Calendar.MINUTE);
		clearSec(calendar);
	}

	private static void clearHour(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		clearMinute(calendar);
	}

	private static void clearDayOfWeek(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		clearHour(calendar);
	}

	private static void clearDayOfMonth(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		clearHour(calendar);
	}

	private static void addMonth(Calendar calendar) {
		calendar.add(Calendar.MONTH, 1);
	}
}
