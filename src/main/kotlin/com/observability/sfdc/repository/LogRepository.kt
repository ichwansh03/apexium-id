package com.observability.sfdc.repository

import com.observability.sfdc.domain.Log
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

@Repository
interface LogRepository : JpaRepository<Log, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<Log>
    fun deleteByOrgIdAndSfdcId(orgId: String, sfdcId: String)
    fun findAllByOrgIdOrderByRequestTimeDesc(orgId: String, pageable: Pageable): List<Log>
    fun findByOrgIdAndApexClassNameContainingIgnoreCase(orgId: String, apexClassName: String, pageable: Pageable): List<Log>
    fun findByOrgIdAndAuthorNameContainingIgnoreCase(orgId: String, authorName: String, pageable: Pageable): List<Log>
    fun findByOrgIdAndApexClassNameContainingIgnoreCaseAndAuthorNameContainingIgnoreCase(orgId: String, apexClassName: String, authorName: String, pageable: Pageable): List<Log>
    fun deleteByOrgIdAndRequestTimeBefore(orgId: String, cutoff: Instant): Int
    fun existsByOrgIdAndSfdcId(orgId: String, sfdcId: String): Boolean
}
