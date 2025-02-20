/*-
 * #%L
 * HAPI FHIR JPA Server Test Utilities
 * %%
 * Copyright (C) 2014 - 2023 Smile CDR, Inc.
 * %%
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
 * #L%
 */
package ca.uhn.fhir.jpa.embedded;


import ca.uhn.fhir.jpa.migrate.DriverTypeEnum;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * For testing purposes.
 * <br/><br/>
 * Provides embedded database functionality. Inheritors of this class will have access to a datasource and JDBC Template for executing queries.
 * Inheritors must make a call to {@link JpaEmbeddedDatabase#initialize(DriverTypeEnum, String, String, String)}
 * in their constructor and override abstract methods.
 */
public abstract class JpaEmbeddedDatabase {

	private DriverTypeEnum myDriverType;
	private String myUsername;
	private String myPassword;
	private String myUrl;
	private DriverTypeEnum.ConnectionProperties myConnectionProperties;
	private JdbcTemplate myJdbcTemplate;

	public abstract void stop();
	public abstract void clearDatabase();

	public void initialize(DriverTypeEnum theDriverType, String theUrl, String theUsername, String thePassword){
		myDriverType = theDriverType;
		myUsername = theUsername;
		myPassword = thePassword;
		myUrl = theUrl;
		myConnectionProperties = theDriverType.newConnectionProperties(theUrl, theUsername, thePassword);
		myJdbcTemplate = myConnectionProperties.newJdbcTemplate();
	}

	public String getUsername() {
		return myUsername;
	}

	public String getPassword() {
		return myPassword;
	}

	public String getUrl() {
		return myUrl;
	}

	public JdbcTemplate getJdbcTemplate() {
		return myJdbcTemplate;
	}

	public DataSource getDataSource(){
		return myConnectionProperties.getDataSource();
	}

}
