package com.example.codingchallenge7principles.address;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AddressCriteriaRepo {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AddressCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Address> findAllWithFilter(String filter) {
        CriteriaQuery<Address> criteriaQuery = this.criteriaBuilder.createQuery(Address.class);
        Root<Address> root = criteriaQuery.from(Address.class);
        Predicate predicate = this.getFilterFirstNameOrLastNamePredicate(filter, root);
        criteriaQuery.where(predicate);

        TypedQuery<Address> typedQuery = this.entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    private Predicate getFilterFirstNameOrLastNamePredicate(String filter, Root<Address> addressRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(filter)) {
            predicates.add(
                    this.criteriaBuilder.like(
                            this.criteriaBuilder.upper(addressRoot.get(Address_.firstName)),
                            "%" + filter.toUpperCase() + "%"
                    )
            );
            predicates.add(
                    this.criteriaBuilder.like(
                            this.criteriaBuilder.upper(addressRoot.get(Address_.lastName)),
                            "%" + filter.toUpperCase() + "%"
                    )
            );
        }
        return this.criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
