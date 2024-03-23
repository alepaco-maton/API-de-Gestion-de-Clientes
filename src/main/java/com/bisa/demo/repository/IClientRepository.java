/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bisa.demo.repository;

import com.bisa.demo.entity.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alepaco.com
 */
@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAllByPersonId(Integer personId);

    @Query(nativeQuery = true, value = "SELECT c.* "
            + "FROM client c "
            + "INNER JOIN reference r ON c.id = r.client_id "
            + "GROUP BY c.id "
            + "HAVING COUNT(r) >= ? ")
    List<Client> findAllByAccessibilityGood(Integer numberReferences, Integer numberReferencesTypeClient);

    @Query(nativeQuery = true, value = "SELECT c.* "
            + "FROM clients c "
            + "INNER JOIN references r ON c.id = r.client_id "
            + "GROUP BY c.id "
            + "HAVING COUNT(r) >= ? AND COUNT(DISTINCT CASE WHEN r.person_id IS NULL THEN r.id END) = ?")
    List<Client> findAllByAccessibilityRegularOption1(Integer numberReferences, Integer numberReferencesTypeClient);

    @Query(nativeQuery = true, value = "SELECT c.* "
            + "FROM clients c "
            + "INNER JOIN references r ON c.id = r.client_id "
            + "GROUP BY c.id "
            + "HAVING COUNT(r) = ? AND COUNT(DISTINCT CASE WHEN r.person_id IS NULL THEN r.id END) = ?")
    List<Client> findAllByAccessibilityRegularOption2(Integer numberReferences, Integer numberReferencesTypeClient);

    @Query(nativeQuery = true, value = "SELECT c.* "
            + "FROM clients c "
            + "INNER JOIN references r ON c.id = r.client_id "
            + "GROUP BY c.id "
            + "HAVING COUNT(r) = ? AND COUNT(DISTINCT CASE WHEN r.person_id IS NULL THEN r.id END) = ?")
    List<Client> findAllByAccessibilityRegularBad(Integer numberReferences, Integer numberReferencesTypeClient);

    @Query(nativeQuery = true, value = "SELECT c.* "
            + "FROM clients c "
            + "INNER JOIN references r ON c.id = r.client_id "
            + "GROUP BY c.id "
            + "HAVING COUNT(r) = ? AND COUNT(DISTINCT CASE WHEN r.person_id IS NULL THEN r.id END) = ?")
    List<Client> findAllByAccessibilityRegularNull(Integer numberReferences, Integer numberReferencesTypeClient);

}
