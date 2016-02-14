package com.example.zane.bookmanager.inject.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Zane on 16/2/14.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityLife {
}
