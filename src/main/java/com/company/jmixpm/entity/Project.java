package com.company.jmixpm.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JmixEntity
@Table(name = "PROJECT", indexes = {
    @Index(name = "IDX_PROJECT_MANAGER", columnList = "MANAGER_ID")
})
@Entity
public class Project {

  @JmixGeneratedValue
  @Column(name = "ID", nullable = false)
  @Id
  private UUID id;

  @Composition
  @OneToMany(mappedBy = "project")
  private List<Task> tasks;

  @NotNull
  @InstanceName
  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "FINISH_DATE")
  private LocalDate finishDate;

  @JoinColumn(name = "MANAGER_ID", nullable = false)
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private User manager;

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Project)) {
      return false;
    }
    Project project = (Project) o;
    return id.equals(project.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}