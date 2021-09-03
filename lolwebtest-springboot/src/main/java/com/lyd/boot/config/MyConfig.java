package com.lyd.boot.config;


import ch.qos.logback.core.db.DBHelper;
import com.lyd.boot.bean.Pet;
import com.lyd.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的。
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods: 代理bean的方法
 * Full(proxyBeanMethods = true):在容器总注册的方法，在外部随便调用，都会到容器中寻找
 * Lite(proxyBeanMethods = false)：不会保存代理对象，无限次调用这些方法，每一次都会产生新的对象
 * 组件依赖场景：容器中组件存在依赖关系的时候需要启动这个组件代理方法，若不存在组件依赖关系则关闭，节省内存
 * 4、@Import({User.class, DBHelper.class})给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置资源让它进行生效
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)//告诉spring boot这是一个配置类==配置文件
@ImportResource("classpath:beans.xml")//导入资源注解
public class MyConfig {
    /**
     * proxyBeanMethods=true的情况下外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册中的单实例对象
     *
     * @return
     * @ConditionalOnBean(name = "test") 当容器中有test这个组件的时候再注册user01这个组件
     */
//    @ConditionalOnBean(name = "test")//当这个条件存在于public class MyConfig{}上，则需要满足条件后面的组件才生效
    @ConditionalOnMissingBean(name = "test")//意思相反，当不存在这个组件的时候执行后面内容
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
