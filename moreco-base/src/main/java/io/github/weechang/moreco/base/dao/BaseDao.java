package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Dao类基本方法
 *
 * @author zhangwei
 * date 2018/10/26
 * time 16:21
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseDomain> extends PagingAndSortingRepository<T, Serializable> {

    @Transactional
    default void logicDelete(Serializable id){
        T entity = findOne(id);
        logicDelete(entity);
    }

    @Transactional
    default void logicDelete(T entity) {
        entity.setYn(YnEnums.N.getKey());
        save(entity);
    }

    @Transactional
    default void logicDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> logicDelete(entity));
    }

//    @Transactional
//    void logicDeleteAll();

}
