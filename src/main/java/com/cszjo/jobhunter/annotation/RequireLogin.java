package com.cszjo.jobhunter.annotation;

import java.lang.annotation.*;

/**
 * Created by Han on 2017/3/5.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RequireLogin {
}
