package com.flotting.common.exception;

import com.flotting.api.util.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = new ErrorResponse(error.value(), error.name());
        return new ResponseEntity<>(response , error);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleNullException(NullPointerException e) {
        log.error(e.getMessage(), e);
        HttpStatus error = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(error.value(), e.getMessage());
        return new ResponseEntity<>(response, error);
    }

    @ExceptionHandler({ IllegalArgumentException.class, ClassCastException.class })
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException e) {
        log.error(e.getMessage(), e);
        HttpStatus error = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(error.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
    Subclasses Of RuntimeException

    * AnnotationTypeMismatchException,
    * ArithmeticException,
    * ArrayStoreException,
    * BufferOverflowException,
    * BufferUnderflowException,
    * CannotRedoException,
    * CannotUndoException,
    * ClassCastException,
    * CMMException,
    * ConcurrentModificationException,
    * DataBindingException,
    * DOMException,
    * EmptyStackException,
    * EnumConstantNotPresentException,
    * EventException,
    * FileSystemAlreadyExistsException,
    * FileSystemNotFoundException,
    * IllegalArgumentException,
    * IllegalMonitorStateException,
    * IllegalPathStateException,
    * IllegalStateException,
    * IllformedLocaleException,
    * ImagingOpException,
    * IncompleteAnnotationException,
    * IndexOutOfBoundsException,
    * JMRuntimeException,
    * LSException,
    * MalformedParameterizedTypeException,
    * MirroredTypesException,
    * MissingResourceException,
    * NegativeArraySizeException,
    * NoSuchElementException,
    * NoSuchMechanismException,
    * NullPointerException,
    * ProfileDataException,
    * ProviderException,
    * ProviderNotFoundException,
    * RasterFormatException,
    * RejectedExecutionException,
    * SecurityException,
    * SystemException,
    * TypeConstraintException,
    * TypeNotPresentException,
    * UndeclaredThrowableException,
    * UnknownEntityException,
    * UnmodifiableSetException,
    * UnsupportedOperationException,
    * WebServiceException,
    * WrongMethodTypeException
    * */
}
