package com.cocoders.todo

import com.cocoders.todo.domain.TodoListItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.sql.DataSource

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoApplicationTests @Autowired constructor(val restTemplate: TestRestTemplate, val dataSource: DataSource) {
	@LocalServerPort
	val port: Int = 0

	@BeforeAll()
	fun setUp() {
		val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
		jdbcTemplate.execute("DELETE FROM todolist")
	}

	@Test
	fun `saves item on the list`()  {
		val item1 = TodoListItem(UUID.randomUUID(),"test");
		val item2 = TodoListItem(UUID.randomUUID(),"test2");

		val request = HttpEntity<TodoListItem>(item1);
		restTemplate.postForObject(
				"http://localhost:" + port + "/todo-list-item",
				request,
				TodoListItem::class.java
		);

		val request2 = HttpEntity<TodoListItem>(item2);
		restTemplate.postForObject(
				"http://localhost:" + port + "/todo-list-item",
				request2,
				TodoListItem::class.java
		);


		val response = restTemplate.getForEntity(
				"http://localhost:" + port + "/todo-list-item",
				Array<TodoListItem>::class.java
		);

		assertThat(response).isNotNull();
		assertThat(response.statusCode).isEqualTo(HttpStatus.OK);
		assertThat(response.body?.size).isEqualTo(2);
		assertThat(response.body?.get(0)).isEqualTo(item1);
		assertThat(response.body?.get(1)).isEqualTo(item2);
	}
}
