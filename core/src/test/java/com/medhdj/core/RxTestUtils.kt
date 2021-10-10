package com.medhdj.core

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.ExternalResource

class RxTestUtils {
    private fun resetSchedulers() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    private fun applySchedulers() {
        // rxjava
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        // rxandroid
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    fun getTestingRule() = object : ExternalResource() {

        override fun before() {
            super.before()
            applySchedulers()
        }

        override fun after() {
            super.after()
            resetSchedulers()
        }
    }
}
