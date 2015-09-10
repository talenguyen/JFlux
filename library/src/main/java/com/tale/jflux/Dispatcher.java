package com.tale.jflux;

import java.util.Hashtable;
import java.util.Map;

import static com.tale.jflux.Invariant.invariant;

/**
 * Author tale. Created on 7/31/15.
 */

/**
 * Dispatcher is used to broadcast payloads to registered callbacks. This is
 * different from generic pub-sub systems in two ways:
 *
 * 1) Callbacks are not subscribed to particular events. Every payload is
 * dispatched to every registered callback.
 * 2) Callbacks can be deferred in whole or part until other callbacks have
 * been executed.
 *
 * For example, consider this hypothetical flight destination form, which
 * selects a default city when a country is selected:
 *
 * var flightDispatcher = new Dispatcher();
 *
 * // Keeps track of which country is selected
 * var CountryStore = {country: null};
 *
 * // Keeps track of which city is selected
 * var CityStore = {city: null};
 *
 * // Keeps track of the base flight price of the selected city
 * var FlightPriceStore = {price: null}
 *
 * When a user changes the selected city, we dispatch the payload:
 *
 * flightDispatcher.dispatch({
 * actionType: 'city-update',
 * selectedCity: 'paris'
 * });
 *
 * This payload is digested by `CityStore`:
 *
 * flightDispatcher.register(function(payload) {
 * if (payload.actionType === 'city-update') {
 * CityStore.city = payload.selectedCity;
 * }
 * });
 *
 * When the user selects a country, we dispatch the payload:
 *
 * flightDispatcher.dispatch({
 * actionType: 'country-update',
 * selectedCountry: 'australia'
 * });
 *
 * This payload is digested by both stores:
 *
 * CountryStore.dispatchToken = flightDispatcher.register(function(payload) {
 * if (payload.actionType === 'country-update') {
 * CountryStore.country = payload.selectedCountry;
 * }
 * });
 *
 * When the callback to update `CountryStore` is registered, we save a reference
 * to the returned token. Using this token with `waitFor()`, we can guarantee
 * that `CountryStore` is updated before the callback that updates `CityStore`
 * needs to query its data.
 *
 * CityStore.dispatchToken = flightDispatcher.register(function(payload) {
 * if (payload.actionType === 'country-update') {
 * // `CountryStore.country` may not be updated.
 * flightDispatcher.waitFor([CountryStore.dispatchToken]);
 * // `CountryStore.country` is now guaranteed to be updated.
 *
 * // Select the default city for the new country
 * CityStore.city = getDefaultCityForCountry(CountryStore.country);
 * }
 * });
 *
 * The usage of `waitFor()` can be chained, for example:
 *
 * FlightPriceStore.dispatchToken =
 * flightDispatcher.register(function(payload) {
 * switch (payload.actionType) {
 * case 'country-update':
 * case 'city-update':
 * flightDispatcher.waitFor([CityStore.dispatchToken]);
 * FlightPriceStore.price =
 * getFlightPriceStore(CountryStore.country, CityStore.city);
 * break;
 * }
 * });
 *
 * The `country-update` payload will be guaranteed to invoke the stores'
 * registered callbacks in order: `CountryStore`, `CityStore`, then
 * `FlightPriceStore`.
 */
public class Dispatcher {

  public static final String PREFIX = "ID_";
  private Map<String, Callback> callbacks;
  private boolean isDispatching;
  private Map<String, Boolean> isHandled;
  private Map<String, Boolean> isPending;
  private int lastId;
  private Action pendingPayload;

  public Dispatcher() {
    callbacks = new Hashtable<>();
    isDispatching = false;
    isHandled = new Hashtable<>();
    isPending = new Hashtable<>();
    lastId = 1;
  }

  /**
   * Registers a callback to be invoked with every dispatched payload. Returns
   * a token that can be used with `waitFor()`.
   */
  public String register(Callback callback) {
    final String id = PREFIX + lastId++;
    callbacks.put(id, callback);
    return id;
  }

  /**
   * Removes a callback based on its token.
   */
  public void unregister(String token) {
    invariant(callbacks.get(token) != null,
        "Dispatcher.unregister(...): `%s` does not map to a registered callback.", token);
    callbacks.remove(token);
  }

  /**
   * Waits for the callbacks specified to be invoked before continuing execution
   * of the current callback. This method should only be used by a callback in
   * response to a dispatched payload.
   */
  public void waitFor(String... ids) {
    invariant(ids != null && ids.length > 0, "ids: Must not be null or empty");
    invariant(isDispatching, "Dispatcher.waitFor(...): Must be invoked while dispatching.");
    for (int i = 0; i < ids.length; i++) {
      final String id = ids[i];
      if (isPending.get(id)) {
        invariant(isHandled.get(id),
            "Dispatcher.waitFor(...): Circular dependency detected while waiting for `%s`.", id);
        continue;
      }
      invariant(callbacks.get(id) != null,
          "Dispatcher.waitFor(...): `%s` does not map to a registered callback.", id);
      invokeCallback(id);
    }
  }

  /**
   * Dispatches a payload to all registered callbacks.
   */
  public void dispatch(Action payload) {
    invariant(!this.isDispatching,
        "Dispatch.dispatch(...): Cannot dispatch in the middle of a dispatch.");
    this.startDispatching(payload);
    try {
      for (String id : callbacks.keySet()) {
        if (isPending.get(id)) {
          continue;
        }
        invokeCallback(id);
      }
    } finally {
      stopDispatching();
    }
  }

  /**
   * Is this Dispatcher currently dispatching.
   */
  public boolean isDispatching() {
    return isDispatching;
  }

  /**
   * Call the callback stored with the given id. Also do some internal
   * bookkeeping.
   */
  private void invokeCallback(String token) {
    isPending.put(token, true);
    callbacks.get(token).call(pendingPayload);
    isHandled.put(token, true);
  }

  /**
   * Set up bookkeeping needed when dispatching.
   */
  private void startDispatching(Action payload) {
    for (String id : callbacks.keySet()) {
      isPending.put(id, false);
      isHandled.put(id, false);
    }
    this.pendingPayload = payload;
    this.isDispatching = true;
  }

  /**
   * Clear bookkeeping used for dispatching.
   */
  private void stopDispatching() {
    this.pendingPayload = null;
    this.isDispatching = false;
  }

}
