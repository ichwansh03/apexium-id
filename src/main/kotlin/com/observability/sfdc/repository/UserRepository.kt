package com.observability.sfdc.repository

import com.observability.sfdc.domain.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByOrgIdAndSfdcId(orgId: String, sfdcId: String): Optional<User>
    fun findByOrgIdAndNameContainingIgnoreCase(orgId: String, name: String, pageable: Pageable): List<User>
    fun findAllByOrgId(orgId: String, pageable: Pageable): List<User>
    fun countByOrgId(orgId: String): Long
}
