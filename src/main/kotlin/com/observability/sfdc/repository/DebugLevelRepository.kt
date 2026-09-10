package com.observability.sfdc.repository

import com.observability.sfdc.domain.DebugLevel
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DebugLevelRepository : JpaRepository<DebugLevel, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<DebugLevel>

    @Query("SELECT d FROM DebugLevel d WHERE d.orgId = :orgId AND (LOWER(d.developerName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(d.masterLabel) LIKE LOWER(CONCAT('%', :name, '%')))")
    fun findByOrgIdAndDeveloperNameContainingIgnoreCaseOrMasterLabelContainingIgnoreCase(
        @Param("orgId") orgId: String,
        @Param("name") name: String,
        pageable: Pageable
    ): List<DebugLevel>

    fun findAllByOrgId(orgId: String, pageable: Pageable): List<DebugLevel>
    fun countByOrgId(orgId: String): Long
}
