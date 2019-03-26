package com.zgxc.library.annotation;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface RecyclerViewItemId {
    int value();
}
