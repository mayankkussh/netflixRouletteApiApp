package com.mayank.abaddon.netflixmovies.common.callbacks;

/**
 * Created by Mayank
 */

public interface FoldCallback<T, U> {
  T onResponse(T result, U nextInput);
}