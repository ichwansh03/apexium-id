package com.observability.sfdc.repository

import com.observability.sfdc.domain.ApexClass
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApexClassRepository : JpaRepository<ApexClass, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<ApexClass>
    fun findByOrgIdAndNameContainingIgnoreCase(orgId: String, name: String, pageable: Pageable): List<ApexClass>
    fun findAllByOrgId(orgId: String, pageable: Pageable): List<ApexClass>
    fun countByOrgId(orgId: String): Long
}
