spring:
  datasource:
    url: jdbc:mysql://localhost:3306/?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTime=Asia/shanghai&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password:
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    filters: stat,wall,slf4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500

mybatis:
  type-aliases-package: xyz.soulspace.cinder.pojo
  mapper-locations: classpath:mybatis/mapper/*.xml
