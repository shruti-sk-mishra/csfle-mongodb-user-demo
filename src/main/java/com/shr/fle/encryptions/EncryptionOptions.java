package com.shr.fle.encryptions;

import com.mongodb.client.model.vault.EncryptOptions;
import org.bson.BsonBinary;
import java.util.UUID;

/**
 * Avails the encryption options
 *
 * @author shruti.mishra
 */
public class EncryptionOptions {

    public static final String DETERMINISTIC_ENCRYPTION_TYPE = "AEAD_AES_256_CBC_HMAC_SHA_512-Deterministic";
    public static final String RANDOM_ENCRYPTION_TYPE = "AEAD_AES_256_CBC_HMAC_SHA_512-Random";

    /**
     * Builds the encryptiom options
     * basis on the algorithm
     *
     * @param algorithm
     * @param keyId
     * @return
     */
    public static EncryptOptions get(String algorithm, UUID keyId){

        EncryptOptions encryptOptions = new EncryptOptions(algorithm);
        encryptOptions.keyId(new BsonBinary(keyId));
        return encryptOptions;

    }
}
