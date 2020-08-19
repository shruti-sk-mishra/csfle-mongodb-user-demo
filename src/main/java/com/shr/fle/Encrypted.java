package com.shr.fle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field
 * to be encrypted/ decrypted while
 * storing/ retrieving to/ from collection
 *
 * @author shruti.mishra
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Encrypted {
}
