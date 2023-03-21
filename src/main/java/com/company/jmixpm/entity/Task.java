package com.company.jmixpm.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JmixEntity
@Table(name = "TASK_", indexes = {
    @Index(name = "IDX_TASK__ASSIGNEE", columnList = "ASSIGNEE_ID"),
    @Index(name = "IDX_TASK__PROJECT", columnList = "PROJECT_ID")
})
@Entity(name = "Task_")
public class Task {

  @JmixGeneratedValue
  @Column(name = "ID", nullable = false)
  @Id
  private UUID id;

  @InstanceName
  @Column(name = "NAME", nullable = false)
  @NotNull
  private String name;

  @JoinColumn(name = "ASSIGNEE_ID", nullable = false)
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private User assignee;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "ESTIMATED_EFFORTS")
  private Integer estimatedEfforts;

  @OnDeleteInverse(DeletePolicy.CASCADE)
  @JoinColumn(name = "PROJECT_ID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Project project;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Task)) {
      return false;
    }
    Task task = (Task) o;
    return id.equals(task.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}