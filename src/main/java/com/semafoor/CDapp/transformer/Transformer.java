package com.semafoor.CDapp.transformer;

public interface Transformer<M, D> {

    M toModel(D dto);

    D toDto(M model);
}
