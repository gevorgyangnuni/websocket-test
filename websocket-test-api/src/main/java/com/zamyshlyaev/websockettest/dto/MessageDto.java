package com.zamyshlyaev.websockettest.dto;

/**
 * Dto pojo object for Message.
 */
public class MessageDto {

  private String value;

  public MessageDto() {
  }

  public MessageDto(String value) {
    this.value = value;
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

    MessageDto that = (MessageDto) o;

    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return "MessageDto{" +
        "value='" + value + '\'' +
        '}';
  }
}
