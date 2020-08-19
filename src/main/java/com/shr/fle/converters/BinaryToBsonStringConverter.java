package com.shr.fle.converters;

import org.bson.BsonString;
import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts 'types.Binary' to
 * BsonString
 *
 * @author shruti.mishra
 */
public class BinaryToBsonStringConverter implements Converter<Binary, BsonString> {

    @Override
    public BsonString convert(Binary source) {

        String str = new String(source.getData());
        return new BsonString(str);
    }
}