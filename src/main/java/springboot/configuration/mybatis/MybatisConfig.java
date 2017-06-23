package springboot.configuration.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("springboot.hello.persistence")
public class MybatisConfig {
	
	@Autowired
	private ApplicationContext applicationContext; 

	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//sqlSessionFactoryBean.setConfigurationProperties(this.mybatisProperties());
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/configuration.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:persistence/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("springboot.hello.model");
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource); 
		return transactionManager;
	}
	
	@Bean
	public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sessionTemplate;
	}
	
	/*
	 * this is not working
	 * use @MapperScan instead of this.
	 */
	//@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("springboot.hello.persistence");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sessionFactory");
		return mapperScannerConfigurer;
	}
	
	private Properties mybatisProperties() {
		Properties properties = new Properties();
		return properties;
	}
	
}
