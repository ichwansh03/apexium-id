package com.observability.sfdc.repository

import com.observability.sfdc.domain.Report
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReportRepository : JpaRepository<Report, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<Report>

    @Query("SELECT r FROM Report r WHERE r.orgId = :orgId AND (LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(r.developerName) LIKE LOWER(CONCAT('%', :name, '%')))")
    fun findByOrgIdAndNameContainingIgnoreCaseOrDeveloperNameContainingIgnoreCase(
        @Param("orgId") orgId: String,
        @Param("name") name: String,
        pageable: Pageable
    ): List<Report>

    fun findAllByOrgId(orgId: String, pageable: Pageable): List<Report>
    fun countByOrgId(orgId: String): Long
}
