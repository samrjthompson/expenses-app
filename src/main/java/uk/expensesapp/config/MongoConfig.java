package uk.expensesapp.config;

import com.mongodb.WriteConcern;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MongoConfig implements InitializingBean {

    private final MappingMongoConverter mappingMongoConverter;

    public MongoConfig(MappingMongoConverter mappingMongoConverter) {
        this.mappingMongoConverter = mappingMongoConverter;
    }

    // Remove _class field from data
    @Override
    public void afterPropertiesSet() {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    MongoTemplate mongoTemplate(MongoDatabaseFactory dbFactory) {
        MongoTemplate mongoTemplate = new MongoTemplate(dbFactory, mappingMongoConverter);
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongoTemplate;
    }
}
