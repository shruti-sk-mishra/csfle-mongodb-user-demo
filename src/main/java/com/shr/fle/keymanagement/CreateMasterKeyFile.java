
/*
 * # Copyright 2020 Paychex, Inc.
 * # Licensed pursuant to the terms of the Apache License, Version 2.0 (the "License");
 * # your use of the Work is subject to the terms and conditions of the License.
 * # You may obtain a copy of the License at
 * #
 * # http://www.apache.org/licenses/LICENSE-2.0
 * #
 * # Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor
 * # provides the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 * # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including,
 * # without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT,
 * # MERCHANTABILITY, OR FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible
 * # for determining the appropriateness of using or redistributing the Work and assume
 * # any risks associated with your exercise of permissions under this License.
 */

package com.shr.fle.keymanagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Create a master key
 * & store a file
 *
 * @author shruti.mishra
 */
public class CreateMasterKeyFile {
	public static void main(String[] args) throws IOException {

		byte[] localMasterKey = new byte[96];
		new SecureRandom().nextBytes(localMasterKey);

		try (FileOutputStream stream = new FileOutputStream("master-key.txt")) {
			stream.write(localMasterKey);
		}
	}
}