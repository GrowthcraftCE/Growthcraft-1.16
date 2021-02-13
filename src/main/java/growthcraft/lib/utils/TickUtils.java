package growthcraft.lib.utils;

public class TickUtils {

    /**
     * Convert an amount of time to ticks.
     *
     * @param duration Length of time.
     * @param metric   Type of time [ seconds, minutes, hours ].
     * @return Number of ticks from the amount of time and metric.
     */
    public static int toTicks(int duration, String metric) {
        switch (metric) {
            case "minutes":
                return fromMinutes(duration);
            case "hours":
                return fromHours(duration);
            case "seconds":
            default:
                return fromSeconds(duration);
        }
    }

    /**
     * Convert seconds into ticks. There are 20 ticks per second.
     *
     * @param seconds Number of seconds.
     * @return Number of ticks per number of given seconds.
     */
    public static int fromSeconds(int seconds) {
        return seconds * 20;
    }

    /**
     * Convert minute into ticks. There are 20 ticks per seconds and 60 seconds per minute.
     *
     * @param minutes Number of minutes.
     * @return Number of ticks per number of given minutes.
     */
    public static int fromMinutes(int minutes) {
        return minutes * 20 * 60;
    }

    /**
     * Convert hours into ticks. There are 20 ticks per second, 60 seconds per minute, and 60 minutes per hour.
     *
     * @param hours Number of hours.
     * @return Number of ticks per number of given hours.
     */
    public static int fromHours(int hours) {
        return hours * 20 * 60 * 60;
    }

}
