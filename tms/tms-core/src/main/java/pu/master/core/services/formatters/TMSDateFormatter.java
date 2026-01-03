package pu.master.core.services.formatters;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Utility class for formatting {@link LocalDate} instances using the {@code dd.MM.yyyy} date pattern.
 */
public final class TMSDateFormatter
{

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    private TMSDateFormatter()
    {
        // Do not instantiate
    }


    /**
     * Formats the given {@link LocalDate} using the {@code dd.MM.yyyy} date pattern.
     *
     * @param date the date to format
     * @return a formatted date string or an empty string if the date is {@code null}
     */
    public static String format(final LocalDate date)
    {
        return date != null
               ? date.format(FORMATTER)
               : "";
    }


}