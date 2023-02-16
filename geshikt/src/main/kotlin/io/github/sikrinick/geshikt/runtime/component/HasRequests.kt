package io.github.sikrinick.geshikt.runtime.component

import com.google.api.services.sheets.v4.model.Request

interface HasRequests {
    val requests: List<Request>
}
