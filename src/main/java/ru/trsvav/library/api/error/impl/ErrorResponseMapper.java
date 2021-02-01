package ru.trsvav.library.api.error.impl;

import org.mapstruct.Mapper;
import ru.trsvav.library.api.error.response.ErrorResponse;
import ru.trsvav.library.entity.Error;

@Mapper(componentModel = "spring")
public interface ErrorResponseMapper {

    ErrorResponse ErrorToErrorResponse(Error error);
}
