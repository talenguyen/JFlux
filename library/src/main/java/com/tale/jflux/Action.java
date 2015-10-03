package com.tale.jflux;

/**
 * Author tale. Created on 7/31/15.
 */
public class Action {

  private final int id;
  private final Object payload;

  public Action(int id, Object payload) {
    this.id = id;
    this.payload = payload;
  }

  /**
   * Return the unique id of event which will be used to determine the
   * callback correspond to event.
   *
   * @return The unique id of event in the system.
   */
  public int getId() {
    return id;
  }

  public <T> T getPayload() {
    return (T) payload;
  }
}
