package com.task.management.Response;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.boot.model.source.internal.hbm.XmlElementMetadata;

import com.task.management.entity.Task;
import com.task.management.entity.User;
import com.task.management.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

	private Long task_id;
	
	private String title;
	
	private String description;
	
	private Status status;
	
	private Long user;

	public static List<TaskResponse> init(List<Task> tasks) {
		return tasks.stream()
				.map(x->TaskResponse.builder()
						.task_id(x.getTask_id())
						.description(x.getDescription())
						.status(x.getStatus())
						.user(x.getUser().getId())
						.title(x.getTitle())
						 .build())
				.toList();
	}

	public static TaskResponse init(Task tasks) {
		return TaskResponse.builder()
				.task_id(tasks.getTask_id())
				.title(tasks.getTitle())
				.description(tasks.getDescription())
				.status(tasks.getStatus())
				.user(tasks.getUser().getId())
				.build();

	}
	
	
}
