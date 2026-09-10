package com.observability.sfdc.repository

import com.observability.sfdc.domain.TraceJob
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TraceJobRepository : JpaRepository<TraceJob, Long> {
    fun findByOrgIdAndStatus(orgId: String, status: String): List<TraceJob>
    fun findByOrgIdAndTracedEntityNameContainingIgnoreCase(orgId: String, name: String): List<TraceJob>
}
