package de.mexchange.packagingdb.common.util;



import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;

public class TimerUtil {

    private static Logger log = Logger.getLogger(TimerUtil.class);

    private static final Map<String, Timer> timers = new HashMap<String, Timer>();

    public static void start(String name) {
        startWithThreshold(name, 0L, TimeUnit.NANOSECONDS);
    }

    public static void startWithThreshold(String name, long threshold, TimeUnit unit) {
        Timer timer = timers.get(name);
        if (timer == null) {
            timer = new Timer();
            timers.put(name, timer);
        }

        timer.start = System.nanoTime();
        timer.invocations++;
        timer.threshold = unit.toNanos(threshold);

        if (threshold == 0L) {
            // Log the start if we have no threshold.
            log.info(name);
        }
    }

    public static void pause(String name) {
        Timer timer = timers.get(name);
        if (timer == null) return;

        timer.stop = System.nanoTime();
        timer.elapsed += (timer.stop - timer.start);
    }

    public static double stop(String name) {
        pause(name);

        Timer timer = timers.remove(name);
        if (timer == null) return 0.0;

        String invocations = timer.invocations > 1 ? "   (" + timer.invocations + " invocations)" : "";

        if (timer.elapsed > timer.threshold) {
            if (timer.threshold > 0) {
                log.warn(String.format("***** WARNING: %s took longer than expected, %.3f seconds (expected %.3f or less)  %s",
                        name, timer.elapsed / 1e9, timer.threshold / 1e9, invocations));
            } else {
                log.info(String.format("\t%s finished, %.3f seconds   %s", name, timer.elapsed / 1e9, invocations));
            }
        }

        return timer.elapsed;
    }

    private static class Timer {
        public long start;
        public long stop;
        public long elapsed;
        public int invocations;
        public long threshold;
    }
}