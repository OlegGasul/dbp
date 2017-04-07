package de.mexchange.packagingdb.repository;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.InvalidParameterException;
import de.mexchange.packagingdb.common.util.ContainerHelper;
import de.mexchange.packagingdb.common.util.ContainerUtil;
import de.mexchange.packagingdb.common.util.QueryUtil;
import de.mexchange.packagingdb.entity.ContainerEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Arthur on 7/13/2016.
 */
@Repository
public class ContainerRepositoryImpl implements ContainerCustomRepository<ContainerEntity, Long> {

    private static Logger logger = Logger.getLogger(ContainerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ContainerEntity> inheritedSearch(String operator, Map queryMap) throws InternalErrorException {
        try {
            String queryString = QueryUtil.buildQuery(queryMap, operator);
            Query query = em.createNativeQuery(queryString);
            List<Object> objects = query.getResultList();
            return ContainerUtil.convert(objects);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Long inheritedCountSearch(String operator, Map queryMap) throws InternalErrorException {
        try {
            String queryString = QueryUtil.buildCountQuery(queryMap, operator);
            Query query = em.createNativeQuery(queryString);
            BigInteger count =  (BigInteger) query.getSingleResult();
            return count == null ? 0 : count.longValue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalErrorException(e);
        }
    }


}
