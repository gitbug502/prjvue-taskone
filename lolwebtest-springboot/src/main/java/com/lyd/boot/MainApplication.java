package com.lyd.boot;

import com.lyd.boot.bean.Pet;
import com.lyd.boot.bean.User;
import com.lyd.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主程序类
 *
 * @SpringBootApplication: 这是一个spring boot应用
 * 这是一个合成注解，下面三个注解等同于@SpringBootApplication,需要指定包扫面路径时需要分开写注解或者写成
 * @SpringBootApplication(scanBasePackages="com.atguigu")
 * @SpringBootConfiguration: 开启spring配置 @SpringBootApplication=@SpringBootConfiguration
 * @EnableAutoConfiguration：
 * @ComponentScan: 指定包扫描路径
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.lyd.boot")
public class MainApplication {
    public static void main(String[] args) {
        //1、返回我们IOC的容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        //3、从容器中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件：" + (tom01 == tom02));//true
        //4、com.lyd.boot.config.MyConfig$$EnhancerBySpringCGLIB$$2aeb13e6@4f449e8f
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法，springboot总会检查这个组件是否在容器中有
        //保持组件单实例
        User user1 = bean.user01();
        User user2 = bean.user01();
        System.out.println(user1 == user2);

        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("用户的宠物：" + (user01.getPet() == tom));

    }
}
