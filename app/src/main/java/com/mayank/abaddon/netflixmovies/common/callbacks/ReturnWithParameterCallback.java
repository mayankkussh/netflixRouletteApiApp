package com.mayank.abaddon.netflixmovies.common.callbacks;

/**
 * Created by Mayank.
 */
public interface ReturnWithParameterCallback<T, V> {
  T onResponse(V v);
}
