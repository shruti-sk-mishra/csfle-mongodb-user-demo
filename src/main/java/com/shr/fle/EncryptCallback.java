package com.shr.fle;

import com.mongodb.client.vault.ClientEncryption;
import com.shr.fle.encryptions.EncryptionOptions;
import com.shr.fle.keymanagement.KMSHandler;
import org.bson.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;

/**
 * FieldCallback to encrypt the field (s) having
 * @Encrypted annotation
 *
 * @author shruti.mishra
 */
public class EncryptCallback implements FieldCallback {

    private KMSHandler kmsHandler;

    private final Object source;
    private final Document document;

    public EncryptCallback(final Object source, final Document document,
                           KMSHandler kmsHandler) {
        this.source = source;
        this.document = document;
        this.kmsHandler = kmsHandler;
    }

    /**
     * FieldCallback to encrypt the field (s) having
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
        ClientEncryption clientEncryption = kmsHandler.getClientEncryption();
        BsonBinary encryptedValue = clientEncryption.encrypt(fieldValue,
                EncryptionOptions.get(EncryptionOptions.DETERMINISTIC_ENCRYPTION_TYPE,
                        kmsHandler.getEncryptionKeyUUID()));
        // update the value in document before it is saved to mongodb
        document.put(field.getName(), encryptedValue);
    }


}
