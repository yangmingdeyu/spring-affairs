# spring-affairs
Spring的基于xml的事务配置

搜先整合Spring和Mybatis

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd 
       http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
~~~

然后创建AccountMapper , AccountMapper.xml文件, 定义一个更新方法, 先来实现转账案例

1,配置事务管理器

~~~xml
    <!-- 事务核心管理器,封装了所有事务操作. 依赖于连接池 -->
    <bean name="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="datasource"></property>
    </bean>
~~~

2,配置事务通知

~~~xml
<!-- 配置事务通知 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!-- 企业中配置CRUD方法一般使用方法名+通配符*的形式配置通知，此时类中的方法名要和配置的方法名一致 -->
            <!-- 以方法为单位,指定方法应用什么事务属性
                    isolation：用于指定事务的隔离级别。默认值是DEFAULT，表示使用数据库的默认隔离级别。
                    propagation：用于指定事务的传播行为。默认值是REQUIRED，表示一定会有事务，增删改的选择。查询方法可以选择SUPPORTS。
                    read-only：用于指定事务是否只读。只有查询方法才能设置为true。默认值是false，表示读写。
                    timeout：用于指定事务的超时时间，默认值是-1，表示永不超时。如果指定了数值，以秒为单位。
                    rollback-for：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时，事务不回滚。没有默认值。表示任何异常都回滚。
                    no-rollback-for：用于指定一个异常，当产生该异常时，事务不回滚，产生其他异常时事务回滚。没有默认值。表示任何异常都回滚。
                 -->
            <tx:method name="save*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="add*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="update*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="modify*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="delete*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="remove*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
            <tx:method name="get*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="true" />
            <tx:method name="find*" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="true" />
            
            <tx:method name="transfer" isolation="REPEATABLE_READ"
                       propagation="REQUIRED" read-only="false" />
        </tx:attributes>
    </tx:advice>
~~~

3,配置织入

~~~xml
<!-- 配置织入 -->
    <aop:config>
        <!-- 配置切点表达式 -->
        <aop:pointcut expression="execution(* com.qf.service.impl.*ServiceImpl.*(..))" id="txPc" />
        <!-- 配置切面 : 通知+切点 advice-ref:通知的名称 pointcut-ref:切点的名称 -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPc" />
    </aop:config>
~~~

### 注解开发
