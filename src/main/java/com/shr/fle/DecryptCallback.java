package com.shr.fle;

import com.shr.fle.keymanagement.KMSHandler;
import org.bson.BsonBinary;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;

/**
 * FieldCallback to decrypt the field (s) having
 * @Encrypted annotation
 *
 * @author shruti.mishra
 */
class DecryptCallback implements FieldCallback {
    private final Object source;
    private KMSHandler kmsHandler;

    public DecryptCallback(Object source, KMSHandler kmsHandler) {
        this.source = source;
        this.kmsHandler = kmsHandler;
    }

    /**
     * FieldCallback to decrypt the field (s) having
     * @Encrypted annotation
     */
    @Override
    public void doWith(Field field)
            throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAnnotationPresent(Encrypted.class)) {
            return;
        }
        ReflectionUtils.makeAccessible(field);
        BsonValue fieldValue = (BsonValue) ReflectionUtils.getField(field, source);
        BsonValue decryptedValue = kmsHandler.getClientEncryption().decrypt(fieldValue.asBinary());
        ReflectionUtils.setField(field, source, decryptedValue);
    }
}