package lk.ijse.carRental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "lk.ijse.carRental.repo")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JPAConfig {

    @Autowired
    Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter va){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(va);
        bean.setDataSource(ds);
        bean.setPackagesToScan(env.getRequiredProperty("package.name"));
        return bean;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getRequiredProperty("my.app.url"));
        dataSource.setUsername(env.getRequiredProperty("my.app.username"));
        dataSource.setPassword(env.getRequiredProperty("my.app.password"));
        System.out.println("jpaConfigTes");
        dataSource.setDriverClassName(env.getRequiredProperty("my.app.driverclassname"));
        return dataSource;
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter vender = new HibernateJpaVendorAdapter();
        vender.setDatabasePlatform(env.getRequiredProperty("my.app.dialect"));
        vender.setDatabase(Database.MYSQL);
        vender.setShowSql(true);
        vender.setGenerateDdl(true);
        return vender;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
}
