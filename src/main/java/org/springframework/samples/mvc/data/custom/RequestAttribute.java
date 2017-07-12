package org.springframework.samples.mvc.data.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
Use of below Annotation explained in the link.
@Retention
@Inherited
@Documented

http://www.developer.com/java/other/article.php/10936_3556176_3/An-Introduction-to-Java-Annotations.htm
*/	
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequestAttribute {
	String value();
}