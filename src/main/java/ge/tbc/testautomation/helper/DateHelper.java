package ge.tbc.testautomation.helper;

import java.util.HashMap;
import java.util.Map;

public class DateHelper {
    private static final Map<String, Integer> GEORGIAN_MONTHS = initializeGeorgianMonths();

    private static Map<String, Integer> initializeGeorgianMonths() {
        Map<String, Integer> months = new HashMap<>();
        months.put("იანვარი", 1);
        months.put("თებერვალი", 2);
        months.put("მარტი", 3);
        months.put("აპრილი", 4);
        months.put("მაისი", 5);
        months.put("ივნისი", 6);
        months.put("ივლისი", 7);
        months.put("აგვისტო", 8);
        months.put("სექტემბერი", 9);
        months.put("ოქტომბერი", 10);
        months.put("ნოემბერი", 11);
        months.put("დეკემბერი", 12);
        return months;
    }

    public static Integer getGeorgianMonth(String monthName) {
        return GEORGIAN_MONTHS.get(monthName);
    }

    public static boolean isValidGeorgianMonth(String monthName) {
        return GEORGIAN_MONTHS.containsKey(monthName);
    }

    private DateHelper() {
        throw new UnsupportedOperationException("Utility class");
    }
}
