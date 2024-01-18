package com.kuma.owspacekt.util.tool

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

class RxBus private constructor() {
    private val bus: Subject<Any, Any>

    init {
        bus = SerializedSubject(PublishSubject.create())
    }

    fun postEvent(event: Any) {
        bus.onNext(event)
    }

    fun <T> toObservable(eventType: Class<T>?): Observable<T> {
        return bus.ofType(eventType)
    }

    companion object {
        @Volatile
        var rxBus: RxBus? = null

        fun getInstance(): RxBus {
            if (rxBus == null) {
                synchronized(RxBus::class.java) {
                    if (rxBus == null) {
                        rxBus = RxBus()
                    }
                }
            }
            return rxBus!!
        }
    }


}