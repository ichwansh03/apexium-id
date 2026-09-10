package com.observability.sfdc.repository

import com.observability.sfdc.domain.ApexTrigger
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApexTriggerRepository : JpaRepository<ApexTrigger, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<ApexTrigger>

    @Query("SELECT t FROM ApexTrigger t WHERE t.orgId = :orgId AND (LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(t.sobject) LIKE LOWER(CONCAT('%', :name, '%')))")
    fun findByOrgIdAndNameContainingIgnoreCaseOrSobjectContainingIgnoreCase(
        @Param("orgId") orgId: String,
        @Param("name") name: String,
        pageable: Pageable
    ): List<ApexTrigger>

    fun findAllByOrgId(orgId: String, pageable: Pageable): List<ApexTrigger>
    fun countByOrgId(orgId: String): Long
}
