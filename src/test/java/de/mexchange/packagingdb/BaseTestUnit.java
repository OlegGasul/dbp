package de.mexchange.packagingdb;

import org.junit.After;
import org.junit.Ignore;

/**
 * Base class for Unit
 * tests.
 *
 * Provides message constants and other validation/initialization members to be used in unit tests.
 */
@Ignore
public class BaseTestUnit extends BaseTest {

    protected static final String MESSAGE_NULL = "may not be null";
    protected static final String MESSAGE_EMPTY = "may not be empty";
    protected static final String MESSAGE_ENTITY_NOT_FOUND = "entity not found";
    protected static final String MESSAGE_SIZE_PATTERN = "size must be between %s and %s";
    protected static final String MESSAGE_VALID_ID = "must be greater than or equal to 1";

    @After
    public void tearDown() { }

    /**
     * Returns parameter jsonpath from array by name and index.
     *
     * @param paramName
     * @param index
     * @return
     */
    protected static String getPathParam(String paramName, int index) {
        return "$[" + index + "]" + paramName.substring(1);
    }
}
