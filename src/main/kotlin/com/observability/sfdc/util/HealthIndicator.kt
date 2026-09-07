package com.observability.sfdc.util

import com.observability.sfdc.service.AuthService
import org.springframework.boot.health.contributor.Health
import org.springframework.boot.health.contributor.HealthIndicator
import org.springframework.stereotype.Component

@Component
class HealthIndicator(private val authService: AuthService) : HealthIndicator {

    override fun health(): Health? {
        val accessToken = authService.getAccessToken()
        return if (accessToken != null) Health.up().build() else Health.down().withDetail("reason","Salesforce authentication failed!").build()
    }
}