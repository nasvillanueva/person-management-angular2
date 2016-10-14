package io.github.gediineko.repo.base;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by ggolong on 10/4/16.
 */
@NoRepositoryBean
public interface BaseJpaRepo<T extends Persistable, ID extends Serializable>
        extends JpaRepository<T, ID>, PagingAndSortingRepository<T, ID>,
        QueryDslPredicateExecutor<T> {
}
