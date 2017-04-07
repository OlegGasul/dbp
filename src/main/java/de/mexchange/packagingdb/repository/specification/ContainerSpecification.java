package de.mexchange.packagingdb.repository.specification;

import javax.persistence.criteria.*;

import de.mexchange.packagingdb.common.search.FieldInfo;
import de.mexchange.packagingdb.common.search.FieldType;
import de.mexchange.packagingdb.common.search.ValueOption;
import de.mexchange.packagingdb.domain.BusinessUnit;
import de.mexchange.packagingdb.entity.BusinessUnitEntity;
import de.mexchange.packagingdb.entity.CoatingEntity;
import de.mexchange.packagingdb.entity.ContainerEntity;
import de.mexchange.packagingdb.service.util.FieldsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.config.http.MatcherType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Artur on 7/10/2016.
 */
public class ContainerSpecification {

    public static Specification<ContainerEntity> filterField(
            final FieldInfo fieldInfo, final ValueOption valueOption, final String fieldValue) {
        return new Specification<ContainerEntity>() {
            @Override
            public Predicate toPredicate(Root<ContainerEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                int fieldType = fieldInfo.getType().getValue();
                String fieldUrl = fieldInfo.getUrl();
                String fieldName = fieldInfo.getName();
                int optionValue = valueOption.getValue();

                Expression ep = getExpression(root, fieldInfo.getName());

                CriteriaBuilder.In in = null;
                if (optionValue == ValueOption.LESS.getValue()) {

                    if(fieldType == FieldType.DOUBLE.getValue()){
                        return criteriaBuilder.lt((Expression<Double>)ep, Double.parseDouble(fieldValue));
                    } else if(fieldType == FieldType.STRING.getValue()){
                        return criteriaBuilder.lessThan((Expression<String>)ep,fieldValue);
                    } else if(fieldType == FieldType.DATE.getValue()){
                        try {
                            DateFormat format = new SimpleDateFormat("MM/dd/yyyy" );
                            Date date = format.parse(fieldValue);
                            return criteriaBuilder.lessThan((Expression<Date>)ep, date);
                        } catch (ParseException e) {
                            return null;
                        }
                    } else {
                        return criteriaBuilder.lessThan(ep, fieldValue);
                    }

                } else if (optionValue == ValueOption.GREATER.getValue()) {

                    if(fieldType == FieldType.DOUBLE.getValue()){
                        return criteriaBuilder.gt((Expression<Double>)ep, Double.parseDouble(fieldValue));
                    } else if(fieldType == FieldType.STRING.getValue()){
                        return criteriaBuilder.greaterThan((Expression<String>)ep,fieldValue);
                    } else if(fieldType == FieldType.DATE.getValue()){
                        try {
                            DateFormat format = new SimpleDateFormat("MM/dd/yyyy" );
                            Date date = format.parse(fieldValue);
                            return criteriaBuilder.greaterThan((Expression<Date>)ep,date);
                        } catch (ParseException e) {
                            return criteriaBuilder.greaterThan(ep, fieldValue);
                        }

                    } else {
                        return criteriaBuilder.greaterThan(ep, fieldValue);
                    }
                } else if (optionValue == ValueOption.CONTAINS.getValue()) {
                    return criteriaBuilder.like(ep, "%" + fieldValue + "%");
                } else if (optionValue == ValueOption.STARTS_WITH.getValue()) {
                    return criteriaBuilder.like(ep, fieldValue + "%");
                } else if (optionValue == ValueOption.ENDS_WITH.getValue()) {
                    return criteriaBuilder.like(ep, "%" + fieldValue);
                } else if (optionValue == ValueOption.EQUAL.getValue()) {
                    if (fieldType == FieldType.OBJECT.getValue()) {
                        if (fieldUrl.equals(FieldsHelper.businessUnit)) {
                            BusinessUnitEntity entity = new BusinessUnitEntity();
                            entity.setId(Long.parseLong(fieldValue));
                            return criteriaBuilder.equal(ep, entity);
                        } else if (fieldUrl.equals(FieldsHelper.coating)) {
                            CoatingEntity entity = new CoatingEntity();
                            entity.setId(Long.parseLong(fieldValue));
                            return criteriaBuilder.equal(ep, entity);
                        }
                    } else if(fieldType == FieldType.DATE.getValue()){
                        try {
                            DateFormat format = new SimpleDateFormat("MM/dd/yyyy" );
                            Date date = format.parse(fieldValue);
                            return criteriaBuilder.and(
                                    criteriaBuilder.lessThanOrEqualTo((Expression<Date>)ep, new Date(date.getTime() + (long)1000*60*60*24 )),
                                    criteriaBuilder.greaterThanOrEqualTo((Expression<Date>)ep,date)
                            );
                        } catch (ParseException e) {
                            return criteriaBuilder.equal(ep, fieldValue);
                        }

                    }
                }

                return in;
            }
        };
    }

    //todo impl for project structure

    /**
     * http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-four-jpa-criteria-queries/
     * https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
     * https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
     * https://github.com/ggluck/umkbaselib/blob/master/src/main/java/com/umk/base/repository/AbstractCustomRepository.java
     */
   /* private Predicate addFilter(Expression ep, Object value, MatcherType matchType, QueryFilter.PropertyType propertyType, CriteriaBuilder cb){
        switch (matchType){
            case EQ:
                return  cb.equal(ep,value);
            case NE:
                return cb.notEqual(ep,value);
            case LIKE:
                return cb.like(ep,"%"+value+"%");
            case LT:
                switch (propertyType){
                    case I:
                        return cb.lt((Expression<Integer>)ep,(Integer)value);
                    case L:
                        return cb.lt((Expression<Long>)ep,(Long)value);
                    case N:
                        return cb.lt((Expression<Double>)ep,(Double)value);
                    case D:
                        return cb.lessThan((Expression<Date>)ep,(Date)value);
                    case B:
                        return cb.lessThan((Expression<Boolean>)ep,(Boolean)value);
                    case S:
                        return cb.lessThan((Expression<String>)ep,(String)value);
                }
            case GT:
                switch (propertyType){
                    case I:
                        return cb.gt((Expression<Integer>)ep,(Integer)value);
                    case L:
                        return cb.gt((Expression<Long>)ep,(Long)value);
                    case N:
                        return cb.gt((Expression<Double>)ep,(Double)value);
                    case D:
                        return cb.greaterThan((Expression<Date>)ep,(Date)value);
                    case B:
                        return cb.greaterThan((Expression<Boolean>)ep,(Boolean)value);
                    case S:
                        return cb.greaterThan((Expression<String>)ep,(String)value);
                }
            case LE:
                switch (propertyType){
                    case I:
                        return cb.le((Expression<Integer>)ep,(Integer)value);
                    case L:
                        return cb.le((Expression<Long>)ep,(Long)value);
                    case N:
                        return cb.le((Expression<Double>)ep,(Double)value);
                    case D:
                        return cb.lessThanOrEqualTo((Expression<Date>)ep,(Date)value);
                    case B:
                        return cb.lessThanOrEqualTo((Expression<Boolean>)ep,(Boolean)value);
                    case S:
                        return cb.lessThanOrEqualTo((Expression<String>)ep,(String)value);
                }
            case GE:
                switch (propertyType){
                    case I:
                        return cb.ge((Expression<Integer>)ep,(Integer)value);
                    case L:
                        return cb.ge((Expression<Long>)ep,(Long)value);
                    case N:
                        return cb.ge((Expression<Double>)ep,(Double)value);
                    case D:
                        return cb.greaterThanOrEqualTo((Expression<Date>)ep,(Date)value);
                    case B:
                        return cb.greaterThanOrEqualTo((Expression<Boolean>)ep,(Boolean)value);
                    case S:

                        return cb.greaterThanOrEqualTo((Expression<String>)ep,(String)value);
                }
            case  IN:
                return ep.in((Collection)value);
            case NOTIN:
                return cb.not(ep.in((Collection)value));
        }
        return  cb.equal(ep,value);
    }*/
    private static Expression getExpression(Root<?> root, String property) {
        if (property.indexOf(".") == -1) {
            return root.get(property);
        } else {
            Path a = root;
            String[] ps = property.split("\\.");
            for (String p : ps) {
                a = a.get(p);
            }
            return a;
        }
    }

   /* public static Specification<UserEntity> hasRoles(final String[] roles) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                CriteriaBuilder.In in = criteriaBuilder.in(root.get("role"));
                Authority authority;
                for (String r : roles) {
                    authority = new Authority(UserRole.valueOf(Integer.parseInt(r)));
                    in.value(authority);
                }
                return in;
            }
        };
    }

    public static Specification<UserEntity> searchBy(final String search) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                String containsLikePattern = getContainsLikePattern(search);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("name")), containsLikePattern),
                        criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("surname")), containsLikePattern),
                        criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("email")), containsLikePattern)
                );
            }
        };
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }*/
}
