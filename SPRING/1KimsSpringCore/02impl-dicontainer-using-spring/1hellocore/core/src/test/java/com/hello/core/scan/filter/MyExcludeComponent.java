package com.hello.core.scan.filter;
import java.lang.annotation.*;

@Target(ElementType.TYPE) // 클래스 레벨에 붙을거라는 의미
@Retention(RetentionPolicy.RUNTIME) //
@Documented
public @interface MyExcludeComponent {

}
