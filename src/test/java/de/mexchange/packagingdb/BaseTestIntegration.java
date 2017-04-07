package de.mexchange.packagingdb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.service.UserService;

/**
 * Base class for Integration
 * tests.
 *
 * Loads the DB configuration for tests and validates connection on each Test Case execution.
 */
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Ignore
public class BaseTestIntegration extends BaseTest {

    protected static String MESSAGE_NULL = "may not be null";
    protected static String MESSAGE_ENTITY_NOT_FOUND = "entity not found";
    protected static String MESSAGE_SIZE_PATTERN = "size must be between %s and %s";

    protected static Validator validator;

    @Autowired
    protected DataSource dataSource;
    protected Connection con;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Authentication authentication;

    private User DEFAUL_ADMIN;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUp() {
        try {
            con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT 1 FROM DUAL");

            Integer result = null;
            while (rs.next()) {
                result = rs.getInt(1);
            }
            org.junit.Assert.assertNotNull(result);
            org.junit.Assert.assertEquals(result.intValue(), 1);

            rs.close();
            statement.close();

            DEFAUL_ADMIN = userService.add(createTestAdminUser());
            assertNotNull(DEFAUL_ADMIN);
            assertNotNull(DEFAUL_ADMIN.getId());

            AuthenticationManager authenticationManager = context
                    .getBean(AuthenticationManager.class);
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(DEFAUL_ADMIN.getEmail(), "admin"));

        } catch (SQLException ex) {
            fail(ex.getMessage());
        } catch (InternalErrorException e) {
            fail(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                fail(ex.getMessage());
            }
        }
    }

    @After
    public void tearDown() { }

    @Value("${local.server.port}")
    protected int port;

    /**
     * Returns the base url for your rest interface
     *
     * @return
     */
    protected String getBaseUrl() {
        return "http://localhost:" + port;
    }

    /**
     * @param requestMappingUrl
     *            should be exactly the same as defined in RequestMapping
     *            value attribute (including the parameters in {})
     *            RequestMapping(value = yourRestUrl)
     * @param serviceReturnTypeClass
     *            should be the the return type of the service
     * @param parametersInOrderOfAppearance
     *            should be the parameters of the requestMappingUrl ({}) in
     *            order of appearance
     * @return the result of the service, or null on error
     */
    protected <T> T getEntity(String requestMappingUrl, Class<T> serviceReturnTypeClass, Object... parametersInOrderOfAppearance) {
        // Make a rest template do do the service call
        TestRestTemplate restTemplate = new TestRestTemplate();
        // Add correct headers, none for this example
        HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        try {
            // Do a call the the url
            ResponseEntity<T> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.GET, requestEntity, serviceReturnTypeClass,
                    parametersInOrderOfAppearance);
            // Return result
            return entity.getBody();
        } catch (Exception ex) {
            // Handle exceptions
            fail(ex.getMessage());
        }
        return null;
    }

    /**
     * @param requestMappingUrl
     *            should be exactly the same as defined in RequestMapping
     *            value attribute (including the parameters in {})
     *            RequestMapping(value = yourRestUrl)
     * @param serviceListReturnTypeClass
     *            should be the the generic type of the list the service
     *            returns, eg: List<serviceListReturnTypeClass>
     * @param parametersInOrderOfAppearance
     *            should be the parameters of the requestMappingUrl ({}) in
     *            order of appearance
     * @return the result of the service, or null on error
     */
    protected <T> List<T> getList(String requestMappingUrl, Class<T> serviceListReturnTypeClass, Object... parametersInOrderOfAppearance) {
        ObjectMapper mapper = new ObjectMapper();
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        try {
            // Retrieve list
            ResponseEntity<List> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.GET, requestEntity, List.class, parametersInOrderOfAppearance);
            List<Map<String, String>> entries = entity.getBody();
            List<T> returnList = new ArrayList<T>();
            for (Map<String, String> entry : entries) {
                // Fill return list with converted objects
                returnList.add(mapper.convertValue(entry, serviceListReturnTypeClass));
            }
            return returnList;
        } catch (Exception ex) {
            // Handle exceptions
            fail(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param requestMappingUrl
     *            should be exactly the same as defined in RequestMapping
     *            value attribute (including the parameters in {})
     *            RequestMapping(value = yourRestUrl)
     * @param serviceReturnTypeClass
     *            should be the the return type of the service
     * @param objectToPost
     *            Object that will be posted to the url
     * @return
     */
    protected <T> T postEntity(String requestMappingUrl, Class<T> serviceReturnTypeClass, Object objectToPost) {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(objectToPost));
            ResponseEntity<T> entity = restTemplate.postForEntity(getBaseUrl() + requestMappingUrl, requestEntity, serviceReturnTypeClass);
            return entity.getBody();
        } catch (Exception ex) {
            // Handle exceptions
            fail(ex.getMessage());
        }
        return null;
    }


    /**
     * Helper method for hamcrest. This method is useful for long comparing with int
     * @param value
     */
    protected static Matcher<Integer> is(Long value) {
        return org.hamcrest.core.Is.is(value.intValue());
    }

    /**
     * Get json String of DTO objects
     * @param dto - DTO objects
     * @return
     */
    protected static String toJson(Object dto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dto);
        } catch (IOException e) {
            e.printStackTrace();
            return dto.toString();
        }
    }

    public static Object unwrapProxy(Object bean) throws Exception {
        /*
         * If the given object is a proxy, set the return value as the object
         * being proxied, otherwise return the given object.
         */
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {

            Advised advised = (Advised) bean;

            bean = advised.getTargetSource().getTarget();
        }

        return bean;
    }

    protected User createTestAdminUser(){
        User user = new User();
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("testadmin@mail.com");
        user.setRole(UserRole.ROLE_ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        return user;
    }
}
