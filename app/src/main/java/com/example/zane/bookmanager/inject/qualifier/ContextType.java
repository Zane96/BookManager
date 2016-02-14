package com.example.zane.bookmanager.inject.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Zane on 16/2/14.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextType {
    String value()default "application";
}
