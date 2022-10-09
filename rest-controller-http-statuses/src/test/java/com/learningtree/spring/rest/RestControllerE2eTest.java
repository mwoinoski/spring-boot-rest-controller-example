package com.learningtree.spring.rest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)  // deploy application on Tomcat
@Sql({"classpath:schema.sql", "classpath:data.sql"})  // re-initialize H2 database for each test case
public class RestControllerE2eTest {

	@Autowired
	private WebTestClient webClient;  // sends HTTP requests to the application

	@Test
	public void testGetAllUsers() {
		var bert = new User("bert", "bertpw", 
							List.of(new UserRole(1, "bert", "admin_team")));
		var mary = new User("mary", "marypw", 
							List.of(new UserRole(3, "mary", "managers"), new UserRole(6, "mary", "admin_team")));
		
		webClient.get()
				 .uri("/users/all")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBodyList(User.class)
				 .hasSize(4)
				 .contains(bert)
				 .contains(mary);
	}

	@Test
	public void testGetUserById() {
		var ernie = new User("ernie", "erniepw", List.of(new UserRole(2, "ernie", "admin_team")));
		
		webClient.get()
				 .uri("/users/ernie")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBody(User.class).isEqualTo(ernie);
	}

	@Test
	public void testGetUserByName() {
		var ernie = new User("ernie", "erniepw", List.of(new UserRole(2, "ernie", "admin_team")));
		
		webClient.get()
				 .uri("/users/name/ernie")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBody(User.class).isEqualTo(ernie);
	}

	@Test
	public void testCreateUser() {
		var abe = new User("abe", "abepw", List.of(new UserRole(7, "abe", "president")));
		
		// verify that abe doesn't exist
		webClient.get()
				 .uri("/users/name/abe")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isNoContent();

		webClient.post()
				 .uri("/users")
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON)
				 .bodyValue(abe)
				 .exchange()
				 .expectStatus().isCreated();

		// verify that abe was added
		webClient.get()
				 .uri("/users/name/abe")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBody(User.class).isEqualTo(abe);
	}

	@Test
	public void testUpdateUser() {
		var bert = new User("bert", "bertpw", List.of(new UserRole(1, "bert", "admin_team")));
		
		// verify current bert's current state
		webClient.get()
				 .uri("/users/name/bert")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBody(User.class).isEqualTo(bert);

		// modify bert
		bert.setUserPass("bertsnewpw");
		bert.getUserRoles().get(0).setRoleName("managers");
		
		webClient.put()
				 .uri("/users")
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON)
				 .bodyValue(bert)
				 .exchange()
				 .expectStatus().isAccepted();

		// verify current bert's new state
		webClient.get()
				 .uri("/users/name/bert")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBody(User.class).isEqualTo(bert);
	}

	@Test
	public void testDeleteUser() {
		var bert = new User("bert", "bertpw", List.of(new UserRole(1, "bert", "admin_team")));
		
		// verify that bert is present
		webClient.get()
				 .uri("/users/all")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBodyList(User.class)
				 .hasSize(4)
				 .contains(bert);

		webClient.delete()
				 .uri("/users/bert")
				 .exchange()
				 .expectStatus().isNoContent();

		// verify that bert is not present
		webClient.get()
				 .uri("/users/all")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .expectStatus().isOk()
				 .expectBodyList(User.class)
				 .hasSize(3)
				 .doesNotContain(bert);
	}
}
