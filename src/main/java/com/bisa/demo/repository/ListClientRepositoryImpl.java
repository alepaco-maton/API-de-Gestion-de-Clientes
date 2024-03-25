/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.repository;

import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Person;
import com.bisa.demo.entity.Reference;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2 
@Component
public class ListClientRepositoryImpl implements IListClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ListClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    private List<Client> findAllByAccessibilityGoodParte1(Integer numberReferences) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        ParameterExpression<Integer> numberReferencesParam = cb.parameter(Integer.class);

        Expression<Long> numberReferencesExp = numberReferencesParam.as(Long.class);

        // Subquery t1
        CriteriaQuery<Client> subquery1 = cb.createQuery(Client.class);
        Root<Reference> subRoot1 = subquery1.from(Reference.class);
        subquery1.select(subRoot1.get("clientId"))
                .where(
                        cb.isNull(subRoot1.get("reasonForElimination"))
                )
                .groupBy(subRoot1.get("clientId"))
                .having(cb.greaterThanOrEqualTo(cb.count(subRoot1.get("clientId")), numberReferencesExp));

        Query typedQuery = entityManager.createQuery(subquery1);
        typedQuery.setParameter(numberReferencesParam, numberReferences);
        List<Client> list = typedQuery.getResultList();

        return list;
    }

    private List<Client> findAllByAccessibilityGoodParte2(Integer numberReferencesTypeClient) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);

        ParameterExpression<Integer> numberReferencesTypeClientParam = cb.parameter(Integer.class);

        Expression<Long> numberReferencesTypeClientExp = numberReferencesTypeClientParam.as(Long.class);

        Root<Reference> referenceRoot = query.from(Reference.class);
        Join<Reference, Person> personJoin = referenceRoot.join("personId");

        query.select(referenceRoot.get("clientId"))
                .where(
                        cb.isNull(referenceRoot.get("reasonForElimination")),
                        cb.isTrue(personJoin.get("isClient"))
                )
                .groupBy(referenceRoot.get("clientId"))
                .having(cb.greaterThanOrEqualTo(cb.count(referenceRoot.get("clientId")), numberReferencesTypeClientExp));

        Query typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(numberReferencesTypeClientParam, numberReferencesTypeClient);

        List<Client> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public List<Client> findAllByAccessibilityGood(Integer numberReferences, Integer numberReferencesTypeClient) {
        Set<Client> intersection = new HashSet<>(findAllByAccessibilityGoodParte1(numberReferences));
        intersection.retainAll(findAllByAccessibilityGoodParte2(numberReferencesTypeClient));

        return intersection.stream().collect(Collectors.toList());
    }

    @Override
    public List<Client> findAllByAccessibilityRegularOption1(Integer numberReferences) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Integer> numberReferencesParam = criteriaBuilder.parameter(Integer.class);

        Expression<Long> numberReferencesExp = numberReferencesParam.as(Long.class);

        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Reference> referenceRoot = criteriaQuery.from(Reference.class);
        Join<Reference, Person> personJoin = referenceRoot.join("personId");
        Join<Reference, Client> clientJoin = referenceRoot.join("clientId");
        criteriaQuery.select(clientJoin);

        Subquery<Client> subquery = criteriaQuery.subquery(Client.class);
        Root<Reference> subqueryRoot = subquery.from(Reference.class);
        Join<Reference, Person> subqueryPersonJoin = subqueryRoot.join("personId");
        subquery.select(subqueryRoot.get("clientId"));
        Predicate subqueryPredicate = criteriaBuilder.and(
                criteriaBuilder.isNull(subqueryRoot.get("reasonForElimination")),
                subqueryPersonJoin.get("isClient")
        );
        subquery.where(subqueryPredicate);

        Predicate mainPredicate = criteriaBuilder.and(
                criteriaBuilder.isNull(referenceRoot.get("reasonForElimination")),
                criteriaBuilder.equal(personJoin.get("isClient"), false),
                criteriaBuilder.not(clientJoin.in(subquery))
        );
        criteriaQuery.where(mainPredicate);
        criteriaQuery.groupBy(clientJoin);
        criteriaQuery.having(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.count(clientJoin), numberReferencesExp));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter(numberReferencesParam, numberReferences);
        List<Client> resultList = typedQuery.getResultList();

        return resultList;
    }

    private List<Client> findAllByAccessibilityRegularOption2Parte1(Integer numberReferences) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        ParameterExpression<Integer> numberReferencesParam = cb.parameter(Integer.class);

        Expression<Long> numberReferencesExp = numberReferencesParam.as(Long.class);

        // Subquery t1
        CriteriaQuery<Client> subquery1 = cb.createQuery(Client.class);
        Root<Reference> subRoot1 = subquery1.from(Reference.class);
        subquery1.select(subRoot1.get("clientId"))
                .where(
                        cb.isNull(subRoot1.get("reasonForElimination"))
                )
                .groupBy(subRoot1.get("clientId"))
                .having(cb.equal(cb.count(subRoot1.get("clientId")), numberReferencesExp));

        TypedQuery<Client> typedQuery = entityManager.createQuery(subquery1);
        typedQuery.setParameter(numberReferencesParam, numberReferences);
        List<Client> resultList = typedQuery.getResultList();

        return resultList;
    }

    private List<Client> findAllByAccessibilityRegularOption2Parte2(Integer numberReferencesTypeClient) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);

        ParameterExpression<Integer> numberReferencesTypeClientParam = cb.parameter(Integer.class);

        Expression<Long> numberReferencesTypeClientExp = numberReferencesTypeClientParam.as(Long.class);

        Root<Reference> referenceRoot = query.from(Reference.class);
        Join<Reference, Person> personJoin = referenceRoot.join("personId");

        query.select(referenceRoot.get("clientId"))
                .where(
                        cb.isNull(referenceRoot.get("reasonForElimination")),
                        cb.isTrue(personJoin.get("isClient"))
                )
                .groupBy(referenceRoot.get("clientId"))
                .having(cb.equal(cb.count(referenceRoot.get("clientId")), numberReferencesTypeClientExp));

        Query typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(numberReferencesTypeClientParam, numberReferencesTypeClient);

        List<Client> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public List<Client> findAllByAccessibilityRegularOption2(Integer numberReferences, Integer numberReferencesTypeClient) {
        Set<Client> intersection = new HashSet<>(findAllByAccessibilityRegularOption2Parte1(numberReferences));
        intersection.retainAll(findAllByAccessibilityRegularOption2Parte2(numberReferencesTypeClient));

        return intersection.stream().collect(Collectors.toList());
    }

    @Override
    public List<Client> findAllByAccessibilityRegularBad(Integer numberReferences) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Integer> numberReferencesParam = criteriaBuilder.parameter(Integer.class);

        Expression<Long> numberReferencesExp = numberReferencesParam.as(Long.class);

        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Reference> referenceRoot = criteriaQuery.from(Reference.class);
        Join<Reference, Person> personJoin = referenceRoot.join("personId");
        criteriaQuery.select(referenceRoot.get("clientId"));

        Subquery<Client> subquery = criteriaQuery.subquery(Client.class);
        Root<Reference> subqueryRoot = subquery.from(Reference.class);
        Join<Reference, Person> subqueryPersonJoin = subqueryRoot.join("personId");
        subquery.select(subqueryRoot.get("clientId"));
        Predicate subqueryPredicate = criteriaBuilder.and(
                criteriaBuilder.isNull(subqueryRoot.get("reasonForElimination")),
                subqueryPersonJoin.get("isClient")
        );
        subquery.where(subqueryPredicate);

        Predicate mainPredicate = criteriaBuilder.and(
                criteriaBuilder.isNull(referenceRoot.get("reasonForElimination")),
                criteriaBuilder.equal(personJoin.get("isClient"), false),
                criteriaBuilder.not(referenceRoot.get("clientId").in(subquery))
        );
        criteriaQuery.where(mainPredicate);
        criteriaQuery.groupBy(referenceRoot.get("clientId"));
        criteriaQuery.having(criteriaBuilder.equal(criteriaBuilder.count(referenceRoot.get("clientId")), numberReferencesExp));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter(numberReferencesParam, numberReferences);
        List<Client> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<Client> findAllByAccessibilityRegularNull() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);

        Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
        Root<Reference> subqueryRoot = subquery.from(Reference.class);
        subquery.select(subqueryRoot.get("clientId").get("id"));
        Predicate subqueryPredicate = criteriaBuilder.isNull(subqueryRoot.get("reasonForElimination"));
        subquery.where(subqueryPredicate);
        subquery.groupBy(subqueryRoot.get("clientId").get("id"));

        Predicate mainPredicate = criteriaBuilder.not(clientRoot.get("id").in(subquery));
        criteriaQuery.select(clientRoot).where(mainPredicate);

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Client> resultList = typedQuery.getResultList();

        return resultList;
    }

}
