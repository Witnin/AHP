package com.wsy.wsy_library.restful.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @Headers({"connection:keep-alive","auth-token:token"})
 *fun test(@Filed("province") int provinceId)
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(RetentionPolicy.RUNTIME)
annotation class WSY_Headers(vararg val value: String)