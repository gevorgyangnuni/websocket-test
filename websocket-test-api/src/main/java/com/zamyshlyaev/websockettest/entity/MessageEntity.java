package com.zamyshlyaev.websockettest.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity pojo object for Message.
 */
@Entity()
@Table(name = "message")
public class MessageEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "value")
  private String value;

  public MessageEntity() {
  }

  public MessageEntity(String value) {
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MessageEntity that = (MessageEntity) o;

    if (!id.equals(that.id)) return false;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + value.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "MessageEntity{" +
        "id=" + id +
        ", value='" + value + '\'' +
        '}';
  }
}
