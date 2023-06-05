package com.wsy.wsy_library.restful.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @GET("/cities/{province}")
 *fun test(@Path("province") int provinceId)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class WSY_Path(val value: String)