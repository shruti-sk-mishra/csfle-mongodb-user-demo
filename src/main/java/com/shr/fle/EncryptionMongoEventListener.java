package com.shr.fle;

import com.shr.fle.keymanagement.KMSHandler;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Customizes the 'onBeforeSave' (called right
 * before a document is saved in mongodb)
 * and 'onAfterConvert' (called right after a
 * document is retrieved) behavior
 *
 * @author shruti.mishra
 */
public class EncryptionMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    protected KMSHandler kmsHandler;


    /**
     * Gets called right
     * before a document is saved in mongodb
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        Object source = event.getSource();
        Document document = event.getDocument();
        ReflectionUtils.doWithFields(source.getClass(),
                new EncryptCallback(source, document, kmsHandler), // Custom FieldCallback to identify
                ReflectionUtils.COPYABLE_FIELDS);                  // & encrypt the @Encrypted annotated fields
    }

    /**
     * Gets called right after a
     * document is retrieved from collection
     * @param event
     */
    @Override
    public void onAfterConvert(AfterConvertEvent<Object> event) {
        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(),
                new DecryptCallback(source, kmsHandler), // Custom FieldCallback to identify
                ReflectionUtils.COPYABLE_FIELDS);       // & decrypt the @Encrypted annotated fields
    }
}
