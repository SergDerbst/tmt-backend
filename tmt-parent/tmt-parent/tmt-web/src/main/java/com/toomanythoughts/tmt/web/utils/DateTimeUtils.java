package com.toomanythoughts.tmt.web.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateTimeUtils {

	public LocalDateTime toLocalDateTime(final Date date) {
		return new Timestamp(date.getTime()).toLocalDateTime();
	}
}
