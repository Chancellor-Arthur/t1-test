package ru.svitkin.t1test.task;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Работа со строками")
public class TaskController {
	private final TaskService taskService;

	@PostMapping
	@Operation(summary = "Подсчёт символов")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskOutputDto.class)) })
	public Map<Character, Integer> calculate(@Valid @RequestBody TaskInputDto body) {
		return taskService.calculate(body.getInput());
	}
}
