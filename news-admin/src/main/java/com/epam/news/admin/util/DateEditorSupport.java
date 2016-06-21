package com.epam.news.admin.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditorSupport extends PropertyEditorSupport {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final Logger LOG = LogManager.getLogger(DateEditorSupport.class);

    @Override
    public void setAsText(String value) {
        try {
            Date parsedDate = new SimpleDateFormat(DATE_FORMAT).parse(value);
            setValue(new Timestamp(parsedDate.getTime()));
        } catch (ParseException e) {
            LOG.error("Error while parsing date", e);
            setValue(null);
        }
    }
}
