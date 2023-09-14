package ru.svitkin.t1test.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInputDto {
	@NotBlank(message = "Входная строка не может быть пустой")
	private String input;
}
