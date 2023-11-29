package com.example.nationalgeographicproject.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName; //ex: Post / Comment
    private final String name;  //ex: id/title
    private final String value;//ex: 1/JPA

    public ResourceNotFoundException(String resourceName, String name, String value) {
        super("%s not found with %s = %s".formatted(resourceName, name, value));
        this.resourceName = resourceName;
        this.name = name;
        this.value = value;
    }

    public ResourceNotFoundException(String resourceName, String name, long value) {
        super("%s not found with %s = %s".formatted(resourceName, name, value));
        this.resourceName = resourceName;
        this.name = name;
        this.value = String.valueOf(value);
    }

    public String getValue(String value) {
        return value;
    }

    public String getName(String name) {
        return name;
    }

    public String getResourceName(String resourceName) {
        return resourceName;
    }
}
