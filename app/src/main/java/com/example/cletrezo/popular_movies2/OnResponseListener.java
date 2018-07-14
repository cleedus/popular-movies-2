package com.example.cletrezo.popular_movies2;

import java.util.List;

public interface OnResponseListener<T extends List> {
     void onSuccess(int tag, T object);
    void onError( Exception e);
}
