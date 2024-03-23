/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bisa.demo.repository;

import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Reference;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alepaco.com
 */
@Repository
public interface IReferenceRepository extends JpaRepository<Reference, Integer> {

    List<Reference> findAllByClientIdIdAndPersonIdIdAndReasonForEliminationIsNull(Integer clientId, Integer personId);

    List<Reference> findAllByClientIdIdAndReasonForEliminationIsNull(Integer clientId);

}
