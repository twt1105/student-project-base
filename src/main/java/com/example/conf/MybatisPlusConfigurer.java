package com.example.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp统一映射配置
 * @author tangdongqing
 * @date 2019-12-23 14:21:25
 */
@Configuration
public class MybatisPlusConfigurer {
	/**
	 * 分页插件（旧版）
	 *
	 * @return PaginationInterceptor
	 */
//	@Bean
//	public PaginationInterceptor paginationInterceptor() {
//		return new PaginationInterceptor();
//	}

	/**
	 * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
		return interceptor;
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return configuration -> configuration.setUseDeprecatedExecutor(false);
	}
}
