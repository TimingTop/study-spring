package com.timing.study.shardingsphere;

import com.timing.study.shardingsphere.entity.Order;
import com.timing.study.shardingsphere.repository.OrderRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class TestDatasource {

    public static void main(String[] args) throws SQLException {
        TestDatasource testDatasource = new TestDatasource();

        DataSource dataSource = testDatasource.createA();

        OrderRepository orderRepository = new OrderRepository(dataSource);

        orderRepository.createTableIfNotExists();

        Random random = new Random();
        Order order;
        for (int i = 0; i <= 50; i++) {
            order = new Order();
            order.setUserId(random.nextInt(50));
            order.setAddressId(random.nextLong());
            order.setStatus("hehe" + i);

            orderRepository.insert(order);
        }

        System.out.println("insert success.");
        for (int i = 1; i <= 5; i++) {

        }

    }

    // user_id 取模分库， order_id 取模分表， 2 db * 2 table
    public DataSource createA() throws SQLException {
        // 创建 数据源， 2 台 db
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        HikariDataSource dataSource_0 = new HikariDataSource();
        dataSource_0.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSource_0.setJdbcUrl("jdbc:mysql://192.168.56.101:3306/ds0?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource_0.setUsername("timing");
        dataSource_0.setPassword("timing");
        dataSourceMap.put("ds0", dataSource_0);

        HikariDataSource dataSource_1 = new HikariDataSource();
        dataSource_1.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSource_1.setJdbcUrl("jdbc:mysql://192.168.56.101:3306/ds1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource_1.setUsername("timing");
        dataSource_1.setPassword("timing");
        dataSourceMap.put("ds1", dataSource_1);


        // 配置 order 表规则， 分表规则， 就是 虚拟表 跟 实际表 之间的关系
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");



        // 配置分库 + 分表策略
        // 这个是 分库，按照 user_id % 2
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}")
        );
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}")
        );

        // 分片规则
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(orderTableRuleConfig);

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
                shardingRuleConfiguration,
                new Properties());

        return dataSource;

    }

}
