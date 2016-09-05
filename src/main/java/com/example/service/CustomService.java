package com.example.service;

import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author afillatre@ippon.fr
 * @since 04/09/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface CustomService {
}
