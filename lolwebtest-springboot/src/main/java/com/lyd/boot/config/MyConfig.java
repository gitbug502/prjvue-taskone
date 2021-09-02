package com.lyd.boot.config;

import com.lyd.boot.bean.Pet;
import com.lyd.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的。
 * 2、配置类本身也是组件
 * proxyBeanMethods: 代理bean的方法
 * Full(proxyBeanMethods = true):在容器总注册的方法，在外部随便调用，都会到容器中寻找
 * Lite(proxyBeanMethods = false)：不会保存代理对象，无限次调用这些方法，每一次都会产生新的对象
 * 组件依赖场景：容器中组件存在依赖关系的时候需要启动这个组件代理方法，若不存在组件依赖关系则关闭，节省内存
 */
@Configuration(proxyBeanMethods = true)//告诉spring boot这是一个配置类==配置文件
public class MyConfig {
    /**
     * proxyBeanMethods=true的情况下外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册中的单实例对象
     *
     * @return
     */
    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型，返回值就是组件在容器中的实例
    public User user01() {
       User zhangsan = new User("zhangsan", 18);
       //proxyBeanMethods = true模式下 user组件依赖pet组件 成立
       zhangsan.setPet(tomcatPat());
        return zhangsan;
    }

    @Bean("tom")//自定义组件名称
    public Pet tomcatPat() {
        return new Pet("tomcat");
    }
}
