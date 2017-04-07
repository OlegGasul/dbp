package de.mexchange.packagingdb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for both Unit/Integration
 * tests.
 *
 * Provides utility methods for gathering randomized data for business/domain objects initialization.
 */
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {

    protected static String chars = "abcdefghijklmnopqrstuvwxyz";
    protected static String charsCapital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static String charsNum = "0123456789";
    protected static String charsSpecial = "_~!@#$%^&*()";

    /** Default formating pattern for Date fields of DTO ojects */
    protected static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss Z";

    /** Default Time Zone for Date fields of DTO objects */
    protected static String TIME_ZONE = "CET";

    protected static Random random = new Random();
    protected static int counter = 0;
    protected static Long MAX_VAL = Long.MAX_VALUE;

    @AfterClass
    public static void endTests() {
    }

    ///////////////////////////////////////////////
    public static int getRandomNumber() {
        return getRandomNumber(100);
    }

    public static int getRandomNumber(int limit) {
        return 1 + random.nextInt(limit);
    }

    public static boolean getRandomBoolean() {
        return random.nextInt(2) == 0;
    }

    public static Date getRandomDate() {
        int year = 2010 + random.nextInt(6);
        int month = random.nextInt(12);
        int day = random.nextInt(30);
        int hours = 12 + random.nextInt(12);
        int minutes = random.nextInt(60);
        return new GregorianCalendar(year, month, day, hours, minutes).getTime();
    }

    public static UUID getRandomUUID() {
        return UUID.randomUUID();
    }

    public static String getRandomUUIDAsString() {
        return UUID.randomUUID().toString();
    }

    public static String getRandomPassword() {
        AtomicReference<StringBuilder> sb = new AtomicReference<>(new StringBuilder());
        sb.get().append(getRandomString(3 + random.nextInt(3)));
        sb.get().append(charsSpecial.charAt(random.nextInt(charsSpecial.length())));
        sb.get().append(charsNum.charAt(random.nextInt(charsNum.length())));
        sb.get().append(getRandomString(3 + random.nextInt(3)));
        sb.get().append(charsNum.charAt(random.nextInt(charsNum.length())));
        sb.get().append(charsSpecial.charAt(random.nextInt(charsSpecial.length())));
        return sb.get().toString();
    }

    public static String getRandomFilePath() {
        return getRandomFilePath(20 + random.nextInt(150));
    }

    public static String getRandomFilePath(int aproximateLength) {
        if (aproximateLength < 20 || aproximateLength > 255) {
            throw new RuntimeException("Length must be between 20 and 255");
        }

        boolean limitReached = false;
        String path = "";
        while (!limitReached) {
            String subPath = getRandomString(5 + random.nextInt(5));
            path += "/";

            if (path.length() + subPath.length() > aproximateLength) {
                subPath = subPath.substring(0, aproximateLength - path.length());
            }

            path = path.concat(subPath);

            if (path.length() >= aproximateLength) {
                limitReached = true;
            }
        }
        return path;
    }

    public static String getRandomString(int charsCount, boolean nullable) {
        boolean generate = false;
        if (nullable) {
            generate = getRandomBoolean();
        }
        if (generate) {
            return getRandomString(charsCount, false, false);
        }
        return null;
    }

    public static String getRandomString(int charsCount) {
        return getRandomString(charsCount, false, false);
    }

    public static String getRandomString(int charsCount, boolean includeNumberChars, boolean includeSpecialChars) {
        StringBuilder sb = new StringBuilder();

        if (!includeNumberChars && !includeSpecialChars) {
            sb.append(String.valueOf(charsCapital.charAt(random.nextInt(charsCapital.length()))));

            for (int i = 1; i < charsCount; i++) {
                sb.append(String.valueOf(chars.charAt(random.nextInt(chars.length()))));
            }
        } else {
            for (int i = 0; i < charsCount; i++) {
                int arrayIndex = random.nextInt(3);

                if (arrayIndex == 0) {
                    sb.append(String.valueOf(chars.charAt(random.nextInt(chars.length()))));
                } else if (arrayIndex == 1) {
                    if (includeNumberChars) {
                        sb.append(String.valueOf(chars.charAt(random.nextInt(chars.length()))));
                    } else {
                        sb.append(String.valueOf(charsNum.charAt(random.nextInt(charsNum.length()))));
                    }
                } else {
                    if (includeSpecialChars) {
                        sb.append(String.valueOf(chars.charAt(random.nextInt(chars.length()))));
                    } else {
                        sb.append(String.valueOf(charsSpecial.charAt(random.nextInt(charsSpecial.length()))));
                    }
                }
            }
        }

        return sb.toString();
    }

    public static byte[] getRandomByteArray(int size) {
        byte[] result = new byte[size];
        random.nextBytes(result);
        return result;
    }

    public static String getRandomMatchCode() {
        int randomIndex = random.nextInt(match_code.length);
        return match_code[randomIndex];
    }

    protected static String[] match_code = new String[]{
            "Flexibl", "Gravure", "INTERNAL", "Labels", "LFP", "P&B", "Printmedia", "Sheetf", "Tobacco", "UV", "WebOffset"
    };

    ///////////////////////////////////////////////

    protected static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("CET"));
        return df.format(date);
    }

    public static String getRandomBUName() {
        int randomIndex = random.nextInt(bu_name.length);
        return bu_name[randomIndex];
    }

    protected static String[] bu_name = new String[]{
            "Flexible Packaging",
            "PPL BU Tobacco",
            "PPL BU Labels",
            "PPL BU Paper and Board",
            "PPL BU Sheetfed",
            "PPL BU UV",
            "Publication Gravure",
            "Publication Offset",
            "Intern",
            "PPL BU Liquid Food Packaging",
            "Printmedia"
    };

}
