package com.wsy.wsy_library.restful.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @POST("/cities/{province}")
 *fun test(@Path("province") int provinceId)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class WSY_PUT(val value: String, val formPost: Boolean = true)