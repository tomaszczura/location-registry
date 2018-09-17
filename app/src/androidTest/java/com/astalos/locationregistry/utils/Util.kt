package com.astalos.locationregistry.utils

/**
 * @author Tomasz Czura on 9/15/18.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting