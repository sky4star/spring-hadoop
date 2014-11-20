/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.yarn.batch.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tests for {@link SimpleYarnBatchConfiguration}.
 *
 * @author Janne Valkealahti
 *
 */
public class SimpleYarnBatchConfigurationTests {

	@Test
	public void testConfig() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		context.stop();
	}

	@Configuration
	@EnableYarnBatchProcessing
	static class Config {

		@Bean
		public DataSource dataSource() {
			// dummy, just to let autowiring in
			// SimpleYarnBatchConfiguration to work
			return new DataSource() {

				@Override
				public <T> T unwrap(Class<T> iface) throws SQLException {
					return null;
				}

				@Override
				public boolean isWrapperFor(Class<?> iface) throws SQLException {
					return false;
				}

				@Override
				public void setLoginTimeout(int seconds) throws SQLException {
				}

				@Override
				public void setLogWriter(PrintWriter out) throws SQLException {
				}

				@Override
				public Logger getParentLogger() throws SQLFeatureNotSupportedException {
					return null;
				}

				@Override
				public int getLoginTimeout() throws SQLException {
					return 0;
				}

				@Override
				public PrintWriter getLogWriter() throws SQLException {
					return null;
				}

				@Override
				public Connection getConnection(String username, String password) throws SQLException {
					return null;
				}

				@Override
				public Connection getConnection() throws SQLException {
					return null;
				}
			};
		}
	}

}