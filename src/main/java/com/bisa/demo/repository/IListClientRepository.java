/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bisa.demo.repository;

import com.bisa.demo.entity.Client;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author alepaco.com
 */
@Component
public interface IListClientRepository {

// QUERYS PARA MYSQL    
//    @Query(nativeQuery = true, value = "select u.* "
//            + "from client u "
//            + "where u.id in (SELECT * "
//            + "FROM (SELECT r.client_id as 'id' "
//            + "FROM reference r "
//            + "WHERE r.reason_for_elimination is null "
//            + "group by r.client_id "
//            + "having count(r.client_id)>=?) AS t1 "
//            + "INTERSECT "
//            + "SELECT * "
//            + "FROM (SELECT r.client_id as 'id' "
//            + "FROM reference r "
//            + "INNER JOIN person h ON r.reason_for_elimination is null AND h.id = r.person_id "
//            + "where h.is_client  "
//            + "group by r.client_id "
//            + "having count(r.client_id)>=?) AS t2) ")
    List<Client> findAllByAccessibilityGood(Integer numberReferences, Integer numberReferencesTypeClient);

    // QUERYS PARA MYSQL   
//    @Query(nativeQuery = true, value = "select c.* "
//            + "from client c "
//            + "where c.id in (SELECT r.client_id "
//            + "FROM reference r  "
//            + "inner join person p on r.reason_for_elimination is null and "
//            + "p.id = r.person_id and p.is_client = false "
//            + "where r.client_id not in (SELECT y.client_id  "
//            + "FROM reference y "
//            + "INNER JOIN person h ON r.reason_for_elimination is null AND h.id = y.person_id "
//            + "where h.is_client) "
//            + "group by r.client_id "
//            + "having count(r.client_id)>=?)")
    List<Client> findAllByAccessibilityRegularOption1(Integer numberReferences);

    // QUERYS PARA MYSQL   
//    @Query(nativeQuery = true, value = "select u.* "
//            + "from client u "
//            + "where u.id in (SELECT * "
//            + "FROM (SELECT r.client_id as 'id' "
//            + "FROM reference r "
//            + "WHERE r.reason_for_elimination is null "
//            + "group by r.client_id "
//            + "having count(r.client_id)=?) AS t1 "
//            + "INTERSECT "
//            + "SELECT * "
//            + "FROM (SELECT r.client_id as 'id' "
//            + "FROM reference r "
//            + "INNER JOIN person h ON r.reason_for_elimination is null AND h.id = r.person_id "
//            + "where h.is_client  "
//            + "group by r.client_id "
//            + "having count(r.client_id)=?) AS t2) ")
    List<Client> findAllByAccessibilityRegularOption2(Integer numberReferences, Integer numberReferencesTypeClient);

    // QUERYS PARA MYSQL   
//    @Query(nativeQuery = true, value = "select c.* "
//            + "from client c "
//            + "where c.id in (SELECT r.client_id "
//            + "FROM reference r  "
//            + "inner join person p on r.reason_for_elimination is null AND p.id = r.person_id and p.is_client = false "
//            + "where r.client_id not in (SELECT y.client_id  "
//            + "FROM reference y "
//            + "INNER JOIN person h ON r.reason_for_elimination is null AND h.id = y.person_id "
//            + "where h.is_client) "
//            + "group by r.client_id "
//            + "having count(r.client_id)=?)")
    List<Client> findAllByAccessibilityRegularBad(Integer numberReferences);

    // QUERYS PARA MYSQL   
//    @Query(nativeQuery = true, value = "select * "
//            + "from client c "
//            + "where c.id not in (SELECT r.client_id "
//            + "FROM reference r "
//            + "WHERE r.reason_for_elimination is null "
//            + "group by r.client_id) ")
    List<Client> findAllByAccessibilityRegularNull();

}
