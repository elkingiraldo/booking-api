package co.com.elkin.apps.bookingapi.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class DatesUtils {

	public static OffsetDateTime getOffsetDateTime(final Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).plusDays(1).truncatedTo(ChronoUnit.DAYS)
				.toOffsetDateTime();
	}

	public static OffsetDateTime getCurrentOffsetDateTime() {
		return Instant.now().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toOffsetDateTime();
	}

	public static long getDifferenceDays(final OffsetDateTime startDate, final OffsetDateTime endDate) {
		return startDate.until(endDate, ChronoUnit.DAYS);
	}

	public static List<LocalDate> dateListBetweenTwoDates(final LocalDate startLocalDate,
			final LocalDate endLocalDate) {
		return startLocalDate.datesUntil(endLocalDate.plusDays(1)).collect(Collectors.toList());
	}

	public static LocalDate dateToLocalDate(final Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date localDateToDate(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
