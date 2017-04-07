package de.mexchange.packagingdb.repository.specification;

import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.entity.Authority;
import de.mexchange.packagingdb.entity.UserEntity;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Artur on 6/21/2016.
 */
public class UserSpecification {

    public static Specification<UserEntity> hasStatuses(final String[] statuses) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                CriteriaBuilder.In in = criteriaBuilder.in(root.get("status"));
                for (String s : statuses) {
                    in.value(UserStatus.valueOf(Integer.parseInt(s)));
                }
                return in;
            }
        };
    }

    public static Specification<UserEntity> hasRoles(final String[] roles) {
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
    }

}