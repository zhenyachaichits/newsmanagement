package com.epam.news.client.util.compiler;


import com.epam.news.client.exception.BeanCompilerException;

import javax.servlet.http.HttpServletRequest;

public interface BeanCompiler<T> {

    T constructBean(HttpServletRequest request) throws BeanCompilerException;
}
