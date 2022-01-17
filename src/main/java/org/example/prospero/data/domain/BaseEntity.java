package org.example.prospero.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", length = 36)
	@Type(type = "uuid-char")
	protected UUID id;

	@Column(name = "created_on", nullable = false, updatable = false)
	protected LocalDateTime createdOn;

	@Column(name = "updated_on", nullable = false)
	protected LocalDateTime updatedOn;

	@PrePersist
	public void prePersist() {
		createdOn = LocalDateTime.now(Clock.systemUTC());
		updatedOn = createdOn;
	}

	@PreUpdate
	public void preUpdate() {
		updatedOn = LocalDateTime.now(Clock.systemUTC());
	}
}
